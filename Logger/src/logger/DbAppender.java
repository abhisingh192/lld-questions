package logger;

public class DbAppender implements LogAppender {

    @Override
    public void append(LogMessage message) {
        // Simulate database logging
        System.out.println("Logging to database: [" + message.getTimestamp() + "] " +
                message.getLevel() + ": " +
                message.getMessage());
    }
}
