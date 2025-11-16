package service;

import entity.Match;
import entity.MatchStatus;
import entity.Team;

public class LiveScoreService {

    public void startMatch(Match match) {
        match.setStatus(MatchStatus.LIVE);
        System.out.println("Match " + match.getId() + " has started.");
    }

    public synchronized void updateScore(Match match, Team battingTeam, int runs, int wickets, int overs) {
        if (battingTeam.equals(match.getTeamA())) {
            match.getScoreA().updateScore(runs, wickets, overs);
        } else {
            match.getScoreB().updateScore(runs, wickets, overs);
        }

        System.out.println("🔄 Live Update for " + battingTeam.getName() + ": " +
                (battingTeam.equals(match.getTeamA()) ? match.getScoreA() : match.getScoreB()));
    }

    public void endMatch(Match match) {
        match.setStatus(MatchStatus.COMPLETED);
        System.out.println("Match " + match.getId() + " has ended.");
    }
}
