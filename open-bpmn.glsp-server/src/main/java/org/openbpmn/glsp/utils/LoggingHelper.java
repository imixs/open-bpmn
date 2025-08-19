package org.openbpmn.glsp.utils;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * The helper class provides a method to optimize the logging format.
 */
public class LoggingHelper {

    public static void setupLogging() {
        // loading Logging configuration
        try {
            LogManager.getLogManager().readConfiguration(
                    LoggingHelper.class.getResourceAsStream("/logging.properties"));

            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();

            for (Handler handler : handlers) {
                if (handler instanceof ConsoleHandler) {
                    handler.setFormatter(new java.util.logging.Formatter() {
                        @Override
                        public String format(LogRecord record) {
                            // shorten logger name to class name
                            String loggerName = record.getLoggerName();
                            String shortName = loggerName.substring(loggerName.lastIndexOf('.') + 1);
                            // compute thread-Name
                            String threadName = Thread.currentThread().getName();
                            return String.format(
                                    "%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS,%1$tL000000 [%2$s] %3$s  %4$s - %5$s%n",
                                    new java.util.Date(record.getMillis()),
                                    threadName,
                                    record.getLevel(),
                                    shortName,
                                    record.getMessage());
                        }
                    });
                }
            }
            Logger.getLogger(LoggingHelper.class.getName())
                    .info("Open-BPMN: setup custom logging - [/logging.properties]");
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
    }
}
