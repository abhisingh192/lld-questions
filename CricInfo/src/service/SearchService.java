package service;

import entity.Match;
import entity.Player;
import entity.Team;

import java.util.List;

public class SearchService {

    public List<Match> searchMatchesByTeam(List<Match> matches, String teamName) {
        // Implementation to search matches by team name
        return matches.stream()
                .filter(match -> match.getTeamA().getName().equalsIgnoreCase(teamName) ||
                                 match.getTeamB().getName().equalsIgnoreCase(teamName))
                .toList();
    }

    public List<Match> searchMatchesByStatus(List<Match> matches, String status) {
        // Implementation to search matches by status
        return matches.stream()
                .filter(match -> match.getStatus().toString().equalsIgnoreCase(status))
                .toList();
    }

    public List<Player> searchPlayerByName(List<Team> team, String playerName) {
        // Implementation to search matches by player name
        return  team.stream()
                .flatMap(t -> t.getPlayers().stream())
                .filter(p -> p.getName().equalsIgnoreCase(playerName))
                .toList();
    }
}
