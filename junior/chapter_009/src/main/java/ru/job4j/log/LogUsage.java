package ru.job4j.log;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Experiments with logger
 *
 * Created by roman.pogorelov on 22.09.2019
 */
public class LogUsage {
//    private static final Logger LOG = LogManager.getLogger(LogUsage.class.getName());
    private static final Logger LOG = Logger.getLogger(LogUsage.class.getName());

    public static void main(String[] args) {
//        LOG.trace("trace message");
//        LOG.debug("debug message");
//        LOG.info("info message");
//        LOG.warn("warn message");
//        LOG.error("error message");
        LOG.log(Level.INFO, "info", new Exception());
        LOG.log(Level.SEVERE, "sever", new Exception());
    }
}
