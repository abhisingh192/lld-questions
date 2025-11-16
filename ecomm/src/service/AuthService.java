package service;

import entity.User;
import repo.UserRepository;

import java.util.UUID;

public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String username, String password, String email) {
        if (userRepository.getByUsername(username) != null) {
            throw new RuntimeException("user exists");
        }
        User u = new User(UUID.randomUUID().toString(), username, password, email);
        userRepository.add(u);
        return u;
    }

    public User login(String username, String password) {
        User u = userRepository.getByUsername(username);
        if (u == null || !u.checkPassword(password)) {
            throw new RuntimeException("invalid credentials");
        }
        return u;
    }
}
