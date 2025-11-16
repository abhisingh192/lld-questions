package logger;

public class FileAppender implements LogAppender {

    @Override
    public void append(LogMessage message) {
        // Simulate file logging
        System.out.println("Logging to file: [" + message.getTimestamp() + "] " +
                message.getLevel() + ": " +
                message.getMessage());
    }
}
