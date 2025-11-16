package service;

import entity.User;
import repo.DataStore;

public class UserService {

    private DataStore dataStore;

    public UserService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public void createUser(String userId, String name) {
        dataStore.getUsers().put(userId, new User(userId, name));
    }

    public User getUser(String userId) {
        return dataStore.getUsers().get(userId);
    }
}
