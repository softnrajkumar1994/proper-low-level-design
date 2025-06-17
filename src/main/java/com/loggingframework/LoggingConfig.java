package com.loggingframework;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoggingConfig {
    private static LoggingConfig loggingConfig;
    private LogLevel logLevel;

    private LoggingConfig() {
        this.logLevel = LogLevel.INFO;
    }

    public synchronized static LoggingConfig getLoggingConfig() {
        if (loggingConfig == null) {
            loggingConfig = new LoggingConfig();
        }
        return loggingConfig;
    }

    public void setLogLevel(LogLevel logLevel) {
        this.logLevel = logLevel;
    }
}
