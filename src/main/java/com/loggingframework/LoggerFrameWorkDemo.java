package com.loggingframework;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoggerFrameWorkDemo {
    public static void main(String args[]) {
        Logger logger = LoggerFactory.getLogger(LoggerFrameWorkDemo.class.toString());
        logger.info("Info before level change");
        logger.debug("Debug before level change");
        logger.warn("Warn before level change");
        logger.trace("Trace before level change");

        LoggingConfig.getLoggingConfig().setLogLevel(LogLevel.TRACE);
        System.out.println(" ");

        logger.info("Info after level change");
        logger.debug("Debug after level change");
        logger.warn("Warn after level change");
        logger.trace("Trace after level change");
        logger.fatal("Fatal after level change");
    }

}
