package com.loggingframework;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileAppender extends Appender {

    public FileAppender(String appenderName, Logger logger) {
        super(appenderName, logger);
    }
    @Override
    public void append(LogMessage logMessage) {

    }
}
