package repo;

import entity.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {

    private final Map<String, User> map = new ConcurrentHashMap<>();

    public void add(User user) {
        map.put(user.getId(), user);
    }

    public User getById(String id) {
        return map.get(id);
    }

    public User getByUsername(String username) {
        return map.values().stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

}
