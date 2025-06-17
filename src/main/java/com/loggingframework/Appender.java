package com.loggingframework;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class Appender {
    private String appenderName;
    private Logger logger;

    public Appender(String appenderName, Logger logger) {
        this.appenderName = appenderName;
        this.logger = logger;
    }

    public abstract void append(LogMessage logMessage);
}
