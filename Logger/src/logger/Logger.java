package logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Logger {
    private final LogLevelRegistry registry;
    private final List<LogAppender> appenders = new CopyOnWriteArrayList<>();

    public Logger(LogLevelRegistry registry) {
        this.registry = registry;
    }

    public void addAppender(LogAppender appender) {
        appenders.add(appender);
    }

    public void log(LogLevel level, String message) {
        if (registry.isLevelEnabled(level)) {
            LogMessage logMessage = new LogMessage(level, message);
            for (LogAppender appender : appenders) {
                appender.append(logMessage);
            }
        }
    }
}
