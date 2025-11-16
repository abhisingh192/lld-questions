package entity;

public class User {
    private String id;
    private String name;

    private int reputation;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.reputation = 0;
    }

    public void addReputation(int points) {
        this.reputation += points;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getReputation() {
        return reputation;
    }
}
