package repo;

import entity.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepo {

    private final Map<String, User> userStorage = new ConcurrentHashMap<>();

    public void addUser(User user) {
        userStorage.put(user.getId(), user);
    }

    public User getUserByUsername(String username) {
        return userStorage.values().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
