package org.example.common.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogger {
    private final Logger logger;

    public TestLogger(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public void info(String message) {
        logger.info(message);
    }

    public void debug(String message) {
        logger.debug(message);
    }

    public void warn(String message) {
        logger.warn(message);
    }

    public void error(String message) {
        logger.error(message);
    }
}
