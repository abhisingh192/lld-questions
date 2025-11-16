package logger;

public class LogLevel {

    private final String message;
    private final int priority;

    public LogLevel(String message, int priority) {
        this.message = message;
        this.priority = priority;
    }

    public static final LogLevel INFO = new LogLevel("INFO", 1);
    public static final LogLevel DEBUG = new LogLevel("DEBUG", 2);
    public static final LogLevel ERROR = new LogLevel("ERROR", 3);
}
