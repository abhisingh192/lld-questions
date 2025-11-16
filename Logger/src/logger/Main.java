package logger;

public class Main {
    public static void main(String[] args) {
        // Define log levels
        LogLevel INFO  = LogLevel.INFO;
        LogLevel DEBUG = LogLevel.DEBUG;
        LogLevel ERROR = LogLevel.ERROR;

        LogLevelRegistry registry = new LogLevelRegistry();
        registry.enableLevel(INFO);
        registry.enableLevel(ERROR); // DEBUG not enabled

        Logger logger = new Logger(registry);
        logger.addAppender(new ConsoleAppender());


        logger.log(INFO, "Application started.");    // ✅ printed
        logger.log(DEBUG, "Debugging in progress."); // ❌ skipped
        logger.log(ERROR, "Something went wrong!");  // ✅ printed
    }
}
