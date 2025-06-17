package com.loggingframework;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConsoleAppender extends Appender {
    @Override
    public void append(LogMessage logMessage) {

    }
}
