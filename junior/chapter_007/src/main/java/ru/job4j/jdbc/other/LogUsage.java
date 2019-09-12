package ru.job4j.jdbc.other;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TODO Description
 * Created by roman.pogorelov on 12.09.2019
 */
public class LogUsage {
    private static final Logger LOG = LogManager.getLogger(LogUsage.class);

    public static void main(String[] args) {
        int version = 1;
        LOG.trace("trace message {}", version);
        LOG.debug("trace message {}", version);
        LOG.info("trace message {}", version);
        LOG.warn("trace message {}", version);
        LOG.error("trace message {}", version);
    }
}
