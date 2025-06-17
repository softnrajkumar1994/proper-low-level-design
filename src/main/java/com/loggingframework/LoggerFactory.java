package com.loggingframework;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class LoggerFactory {
    private static HashMap<String, Logger> loggers;
    private static LoggerFactory loggerFactory;
    private static Logger rootLogger;

    static {
        if (loggerFactory == null) {
            loggerFactory = new LoggerFactory();
        }

    }

    private LoggerFactory() {
        loggers = new HashMap<>();
        rootLogger = new Logger("root");
    }

    public static Logger getLogger(String loggerStr) {
        if (!loggers.containsKey(loggerStr)) {
            Logger logger = new Logger(loggerStr);
            loggers.put(loggerStr, logger);
        }
        return loggers.getOrDefault(loggerStr, rootLogger);
    }

}
