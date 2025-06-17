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
    }

    public void addAppender(Appender appender) {
        if (!this.appenders.contains(appender)) {
            this.appenders.add(appender);
        }
    }
}
