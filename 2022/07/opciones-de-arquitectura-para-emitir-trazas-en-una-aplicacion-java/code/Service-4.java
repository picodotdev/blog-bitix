import io.github.picodotdev.blogbitix.platform.logging.Logger;

public Service {

    private Logger logger;

    public Service(Logger logger) {
        this.logger = logger;
    }

    public void method() {
        logger.info("Message");
    }
}