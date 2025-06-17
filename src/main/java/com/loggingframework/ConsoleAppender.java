package com.loggingframework;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConsoleAppender extends Appender {
    public ConsoleAppender(String appenderName, Logger logger) {
        super(appenderName, logger);
    }

    @Override
    public void append(LogMessage logMessage) {
        System.out.println(String.format("[%s][%s][%s]", logMessage.getTimeStamp(), logMessage.getLogLevel(), logMessage.getMessage()));
    }
}
