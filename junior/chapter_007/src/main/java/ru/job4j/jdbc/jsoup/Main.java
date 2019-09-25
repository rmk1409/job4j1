package ru.job4j.jdbc.jsoup;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO Description
 * Created by roman.pogorelov on 25.09.2019
 */
public class Main {
    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        String properties = args[0];
        SqlRuParser parser = new SqlRuParser(properties);
        DBStorage db = new DBStorage(properties);
        new Main().runJob(parser, db);
    }

    /**
     * Get schedule from property file and run job.
     */
    public void runJob(SqlRuParser parser, DBStorage db) {
        Properties properties = new Properties();
        try {
            properties.load(
                    Objects.requireNonNull(
                            SqlRuParser.class.getClassLoader().getResourceAsStream(parser.getPropertiesFileName())
                    )
            );
        } catch (IOException e) {
            LOG.log(Level.ALL, e.getMessage(), e);
        }
        JobDataMap jobData = new JobDataMap();
        jobData.put("parser", parser);
        jobData.put("db", db);
        JobDetail jobDetail = JobBuilder.newJob(GetVacanciesJob.class)
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
            LOG.log(Level.ALL, e.getMessage(), e);
        }
    }
}
