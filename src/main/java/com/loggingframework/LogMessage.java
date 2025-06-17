package com.loggingframework;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class LogMessage {
    private String message;
    private LogLevel logLevel;
    private Date timeStamp;

    public LogMessage(String message, LogLevel logLevel) {
        this.message = message;
        this.logLevel = logLevel;
        this.timeStamp = new Date();
    }
}
