package com.loggingframework;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Logger {
    private List<Appender> appenders;
    private String name;

    public Logger(String name) {
        this.name = name;
        this.appenders = new ArrayList<>();
        ConsoleAppender consoleAppender = new ConsoleAppender(name + "_console_appender", this);
        addAppender(consoleAppender);
    }

    public void addAppender(Appender appender) {
        if (!this.appenders.contains(appender)) {
            this.appenders.add(appender);
        }
    }


    private void log(String message, LogLevel level) {
        if (LoggingConfig.getLoggingConfig().getLogLevel().ordinal() <= level.ordinal()) {
            LogMessage logMessage = new LogMessage(message, level);
            for (Appender appender : appenders) {
                appender.append(logMessage);
            }
        }
    }

    public void info(String message) {
        log(message, LogLevel.INFO);
    }

    public void debug(String message) {
        log(message, LogLevel.DEBUG);
    }

    public void warn(String message) {
        log(message, LogLevel.WARN);
    }

    public void trace(String message) {
        log(message, LogLevel.TRACE);
    }

    public void fatal(String message) {
        log(message, LogLevel.FATAL);
    }
}
