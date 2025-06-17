package com.loggingframework;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class LoggerFactory<T> {
    private HashMap<T, Logger> loggers;
    private LoggerFactory loggerFactory;
    private Logger rootLogger;

    private LoggerFactory() {
        loggers = new HashMap<>();
        rootLogger = new Logger("root");
    }

    public synchronized LoggerFactory getLoggerFactory() {
        if (loggerFactory == null) {
            loggerFactory = new LoggerFactory();
        }
        return loggerFactory;
    }

    public Logger getLogger(T loggerStr) {
        return loggers.getOrDefault(loggerStr, rootLogger);
    }

}
