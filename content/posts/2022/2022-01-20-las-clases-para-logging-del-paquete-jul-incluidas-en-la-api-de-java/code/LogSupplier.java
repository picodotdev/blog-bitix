import io.github.picodotdev.blogbitix.javalogging.Log4j2Logger;
import io.github.picodotdev.blogbitix.javalogging.LoggerSupplier;
import io.github.picodotdev.blogbitix.javalogging.LogManager;
import io.github.picodotdev.blogbitix.javalogging.JulLogger;

...

LoggerSupplier supplier = null;
if (log.equals("log4j")) {
    supplier = (clazz) -> Log4j2Logger.getLogger(clazz);
} else if (log.equals("jul-gcp")) {
    java.util.logging.LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("/jul-gcp.properties"));
    JulLogger.setLoggingHandler(new LoggingHandler());
    supplier = (clazz) -> JulLogger.getLogger(clazz);
} else if (log.equals("jul")) {
    java.util.logging.LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("/jul.properties"));
    supplier = (clazz) -> JulLogger.getLogger(clazz);
} else {
    java.util.logging.LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("/jul.properties"));
    supplier = (clazz) -> JulLogger.getLogger(clazz);
}
LogManager.configure(supplier);