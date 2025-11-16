package entity;

import java.time.LocalDateTime;

public class Match {
    private String id;
    private Team teamA;
    private Team teamB;
    private MatchStatus status;
    private Score scoreA;
    private Score scoreB;
    private LocalDateTime startTime;

    public Match(String id, Team teamA, Team teamB, LocalDateTime startTime) {
        this.id = id;
        this.teamA = teamA;
        this.teamB = teamB;
        this.status = MatchStatus.SCHEDULED;
        this.scoreA = new Score();
        this.scoreB = new Score();
        this.startTime = startTime;
    }

    public String getId() {
        return id;
    }

    public Team getTeamA() {
        return teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public Score getScoreA() {
        return scoreA;
    }

    public Score getScoreB() {
        return scoreB;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", teamA=" + teamA +
                ", teamB=" + teamB +
                '}';
    }
}
