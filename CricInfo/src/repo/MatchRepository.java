package repo;

import entity.Match;

import java.util.ArrayList;
import java.util.List;

public class MatchRepository {

    private List<Match> matches = new ArrayList<>();

    public void addMatch(Match match) {
        matches.add(match);
    }

    public List<Match> getAllMatches() {
        return matches;
    }

    public Match getMatchById(String id) {
        return matches.stream()
                .filter(match -> match.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

}
