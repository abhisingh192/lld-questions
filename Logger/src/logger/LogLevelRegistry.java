package logger;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class LogLevelRegistry {

    /*
    In this case, enabledLevels is a final variable, meaning you cannot reassign it to point to a different Set.
    However, you can still modify the contents of the Set (e.g., add or remove elements) because the Set itself is mutable.
     */
    private final Set<LogLevel> enabledLevels = ConcurrentHashMap.newKeySet();

    public void enableLevel(LogLevel level) {
        enabledLevels.add(level);
    }

    public void disableLevel(LogLevel level) {
        enabledLevels.remove(level);
    }

    public boolean isLevelEnabled(LogLevel level) {
        return enabledLevels.contains(level);
    }


}
