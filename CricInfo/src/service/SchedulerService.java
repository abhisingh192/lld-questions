package service;

import entity.Match;
import entity.MatchStatus;

import java.time.LocalDateTime;
import java.util.List;

public class SchedulerService {

    public void displayUpcomingMatches(List<Match> matches) {
        System.out.println("\n📅 Upcoming Matches:");
        matches.stream()
                .filter(m -> m.getStatus() == MatchStatus.SCHEDULED && m.getStartTime().isAfter(LocalDateTime.now()))
                .forEach(System.out::println);
    }
}
