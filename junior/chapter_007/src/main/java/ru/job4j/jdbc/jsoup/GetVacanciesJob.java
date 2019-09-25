package ru.job4j.jdbc.jsoup;

import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * TODO Description
 * Created by roman.pogorelov on 19.09.2019
 */
public class GetVacanciesJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        SqlRuParser parser = (SqlRuParser) jobExecutionContext.getJobDetail().getJobDataMap().get("parser");
        DBStorage db = (DBStorage) jobExecutionContext.getJobDetail().getJobDataMap().get("db");
        parser.handlePagination();
        db.saveInDB(parser.getVacancies());
    }
}