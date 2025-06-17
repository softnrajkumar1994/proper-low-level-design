package com.loggingframework;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LogMessage {
    private String message;
    private LogLevel logLevel;
    private Date timeStamp;
}
