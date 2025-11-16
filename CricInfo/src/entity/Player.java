package entity;

public class Player {
    private String name;
    private String role; // e.g., Batsman, Bowler, All-rounder, Wicketkeeper
    private String team;

    public Player(String name, String role, String team) {
        this.name = name;
        this.role = role;
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return name + " (" + role + ")";
    }
}
