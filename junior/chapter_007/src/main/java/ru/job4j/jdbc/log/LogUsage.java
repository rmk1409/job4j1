package ru.job4j.jdbc.log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TODO Description
 * Created by roman.pogorelov on 12.09.2019
 */
public class LogUsage {
    private static final Logger LOG = LogManager.getLogger(LogUsage.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");
    }
}
