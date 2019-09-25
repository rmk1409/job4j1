package ru.job4j.jdbc.jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import ru.job4j.jdbc.tracker.ConnectionFactory;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Общее описание.
 * Приложение парсер должно заходить на сайт sql.ru в раздел работа и собирать Java вакансии.
 * <p>
 * Ваша задача
 * 1. Реализовать модуль сборки анализа данных с sql.ru.
 * 2. Система должна использовать Jsoup для парсинга страниц.
 * 3. Система должна запускаться раз в день.
 * Для этого нужно использовать библиотеку quartz.
 * <dependency>
 * <groupId>org.quartz-scheduler</groupId>
 * <artifactId>quartz</artifactId>
 * <version>2.3.0</version>
 * </dependency>
 * Пример использования - http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/tutorial-lesson-06.html
 * Для запуска раз в день нужно использовать cron exression - 0 0 12 * * ?
 * http://www.quartz-scheduler.org/documentation/quartz-2.x/tutorials/crontrigger.html
 * <p>
 * 4. Система должна собирать данные только про вакансии java. учесть что JavaScript не подходит. как и Java Script.
 * 5. Данные должны храниться в базе данных.
 * В базе должна быть таблица vacancy (id, name, text, link)
 * id - первичный ключ
 * name - имя вакансии
 * text - текст вакансии
 * link - текст, ссылка на вакансию
 * <p>
 * 6. Учесть дубликаты. Вакансии с одинаковым именем считаются дубликатами.
 * 7. Учитывать время последнего запуска. если это первый запуск. то нужно собрать все объявления с начала года.
 * 8. В системе не должно быть вывода, либо ввода информации. все настройки должны быть в файле. app.properties.
 * https://docs.oracle.com/javase/tutorial/essential/environment/properties.html
 * В файл app.properties указываем. настройки к базе данных и периодичность запуска приложения.
 * jdbc.driver=..
 * jdbc.url=...
 * jdbc.username=...
 * jdbc.password=...
 * cron.time=...
 * <p>
 * 9. для вывода нужной информации использовать логгер log4j. Описание здесь 4.1. Log4j 2. Логирование системы.
 * <p>
 * 10. Пример запуска приложения.
 * java -jar SqlRuParser app.properties
 * <p>
 * Created by roman.pogorelov on 19.09.2019
 */
public class SqlRuParser {
    private static final Logger LOGGER = Logger.getLogger(SqlRuParser.class.getName());
    private static final String URL_PATTERN = "https://www.sql.ru/forum/job-offers/%d";
    private static final String JOB = ".*[Jj]ava.*";
    private static final String JS = ".*[Ss]cript.*";
    private static final String SHORT_DATE_FORMAT = "HH:mm";
    private static final String DATE_FORMAT = "d MMM y, HH:mm";
    private static final DateFormatSymbols SYMBOLS = new DateFormatSymbols() {
        @Override
        public String[] getMonths() {
            return new String[]{"янв", "фев", "мар", "апр", "май", "июн",
                    "июл", "авг", "сен", "окт", "ноя", "дек"};
        }

    };
    private String propertiesFileName = "app.properties";
    private java.sql.Connection connection = ConnectionFactory.getConnection(this.propertiesFileName);
    private Map<String, Vacancy> vacancies = new HashMap<>();

    public static void main(String[] args) {
        SqlRuParser parser = new SqlRuParser(args[0]);
        parser.runJob(parser);
    }

    public SqlRuParser() {
    }

    public SqlRuParser(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
    }

    /**
     * Parses string, check today/yesterday and return date.
     *
     * @param stringDate string date object
     * @return date object
     */
    public Date parseDate(String stringDate) {
        boolean today = stringDate.contains("сегодня");
        boolean yesterday = stringDate.contains("вчера");
        Date result = new Date();
        try {
            if (today || yesterday) {
                String day = today ? "сегодня, " : "вчера, ";
                Date time = new SimpleDateFormat(SHORT_DATE_FORMAT, SYMBOLS).parse(stringDate.replace(day, ""));
                if (yesterday) {
                    result.setDate(result.getDate() - 1);
                }
                result.setHours(time.getHours());
                result.setMinutes(time.getMinutes());
                result.setSeconds(0);
            } else {
                result = new SimpleDateFormat(DATE_FORMAT, SYMBOLS).parse(stringDate);
            }
        } catch (ParseException e) {
            LOGGER.log(Level.ALL, e.getMessage(), e);
        }
        return result;
    }

    /**
     * Saving all vacancies in DB.
     */
    public void saveInDB() {
        try (PreparedStatement st = connection.prepareStatement("insert into vacancy(name, description, link) values(?,?,?)")
        ) {
            connection.setAutoCommit(false);
            for (Map.Entry<String, Vacancy> entry : vacancies.entrySet()) {
                st.setString(1, entry.getValue().getName());
                st.setString(2, entry.getValue().getDescription());
                st.setString(3, entry.getValue().getLink());
                st.addBatch();
                st.clearParameters();
            }
            st.executeBatch();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOGGER.log(Level.ALL, e.getMessage(), e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.log(Level.ALL, e.getMessage(), e);
            }
        }
    }

    /**
     * Check vacancy table in DB, whether it's empty or not.
     *
     * @return whether vacancy table is empty or not
     */
    public boolean checkIsFirstRun() {
        boolean result = false;
        ResultSet resultSet;
        try (java.sql.Connection connection = ConnectionFactory.getConnection(propertiesFileName)) {
            Statement st = connection.createStatement();
            resultSet = st.executeQuery("select id from vacancy limit 1;");
            result = resultSet != null && !resultSet.next();
        } catch (SQLException e) {
            LOGGER.log(Level.ALL, e.getMessage(), e);
        }
        return result;
    }

    /**
     * Parse all the sites in the pagination.
     */
    public void handlePagination() {
        Connection connect = Jsoup.connect("https://www.sql.ru/forum/job-offers");
        Document doc;
        try {
            doc = connect.get();
            Date until = new Date();
            if (this.checkIsFirstRun()) {
                until.setMonth(0);
                until.setDate(1);
            } else {
                until.setDate(until.getDate() - 1);
            }
            int last = Integer.parseInt(doc.select("table.sort_options a:last-child").text());
            for (int i = 1; i < last; i++) {
                if (!this.parseSite(String.format(URL_PATTERN, i), until)) {
                    break;
                }
            }
            System.out.println(vacancies.size());
        } catch (IOException e) {
            LOGGER.log(Level.ALL, e.getMessage(), e);
        }
    }

    /**
     * Get job name, date, description, link from the site until the input date argument.
     * Put the results in the vacancies field.
     * Return false if reaches the earliest date to parse.
     *
     * @param url   is used to parse
     * @param until the earliest date to parse
     * @return true - everything in site are parsed, false - reached the earliest date to parse
     */
    public boolean parseSite(String url, Date until) {
        boolean result = false;
        Connection connect = Jsoup.connect(url);
        try {
            Elements rows = connect.get().select("table.forumTable tr");
            for (int i = 4; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements linkElement = row.select("td:nth-child(2) > a:first-child");
                Elements dateElement = row.select("td:last-child");
                String jobName = linkElement.text();
                if (jobName.matches(JOB) && !jobName.matches(JS)) {
                    Date date = this.parseDate(dateElement.text());
                    if (until.before(date)) {
                        String href = linkElement.attr("href");
                        String description = Jsoup.connect(href)
                                .get()
                                .select("table.msgTable")
                                .first()
                                .select("td:nth-child(2)")
                                .text();
                        this.vacancies.put(jobName, new Vacancy(jobName, description, href));
                    } else {
                        break;
                    }
                }
                result = true;
            }
        } catch (IOException e) {
            LOGGER.log(Level.ALL, e.getMessage(), e);
        }
        return result;
    }

    /**
     * Get schedule from property file and run job.
     */
    public void runJob(SqlRuParser parser) {
        Properties properties = new Properties();
        try {
            properties.load(
                    Objects.requireNonNull(
                            SqlRuParser.class.getClassLoader().getResourceAsStream(this.propertiesFileName)
                    )
            );
        } catch (IOException e) {
            LOGGER.log(Level.ALL, e.getMessage(), e);
        }
        JobDataMap jobData = new JobDataMap();
        jobData.put("parser", parser);
        JobDetail jobDetail = JobBuilder.newJob(ParserJob.class)
                .usingJobData(jobData).build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("ChronoTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(properties.getProperty("cron.time")))
                .build();
        Scheduler scheduler;
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            LOGGER.log(Level.ALL, e.getMessage(), e);
        }
    }
}