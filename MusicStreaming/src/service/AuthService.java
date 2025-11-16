package service;

import entity.User;
import repo.UserRepo;

import java.util.UUID;

public class AuthService {

    private final UserRepo userRepo;

    public AuthService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User register(String username, String password) {
        User existing = userRepo.getUserByUsername(username);
        if(existing != null) throw new RuntimeException("User already exists!");
        User user = new User(UUID.randomUUID().toString(), username, password);
        userRepo.addUser(user);
        System.out.println("✅ Registered user: " + username);
        return user;
    }

    public User login(String username, String password) {
        User user = userRepo.getUserByUsername(username);
        if(user == null || !user.checkPassword(password)) throw new RuntimeException("Invalid credentials");
        System.out.println("✅ Logged in: " + username);
        return user;
    }
}
