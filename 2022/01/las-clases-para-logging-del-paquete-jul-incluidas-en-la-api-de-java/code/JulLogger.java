package io.github.picodotdev.blogbitix.javalogging;

import java.util.logging.Level;
import java.util.logging.Handler;

public class JulLogger implements Logger {

    private static Handler handler;
    private final java.util.logging.Logger logger;

    private JulLogger(Class clazz) {
        this.logger = java.util.logging.Logger.getLogger(clazz.getName());
        if (handler != null) {
            this.logger.addHandler(handler);
        }
    }

    public static void setLoggingHandler(Handler handler) {
        JulLogger.handler = handler;
    }

    public static Logger getLogger(Class clazz) {
        return new JulLogger(clazz);
    }

    @Override
    public void trace(String message) {
        trace(message, new Object[0]);
    }

    @Override
    public void trace(String message, Object... params) {
        logger.log(Level.FINE, adapt(message), params);
    }

    ...

    private String adapt(String message) {
        String m = message;
        int i = 0;
        while (m.contains("{}")) {
            m = m.replaceFirst("\\{\\}", "{" + i + "}");
            i += 1;
        }
        return m;
    }
}
