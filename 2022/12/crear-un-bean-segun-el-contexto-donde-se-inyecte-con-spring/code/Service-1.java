package io.github.picodotdev.blogbitix.springinjectionpoint;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public Service {

    private static final Logger logger = LogManager.getLogger(Service.class);

    public Service() {
    }

    public void method() {
        logger.info("Message");
    }
}