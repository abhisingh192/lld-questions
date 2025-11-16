import entity.Match;
import entity.Player;
import entity.Team;
import repo.MatchRepository;
import service.LiveScoreService;
import service.SchedulerService;
import service.SearchService;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Create Players
        Player p1 = new Player("Rohit Sharma", "Batsman", "India");
        Player p2 = new Player("Virat Kohli", "Batsman", "India");
        Player p3 = new Player("Bumrah", "Bowler",  "India");
        Player p4 = new Player("Warner", "Batsman", "Australia");
        Player p5 = new Player("Starc", "Bowler", "Australia");
        Player p6 = new Player("Gayle", "Batman", "West Indies");

        // Create Teams
        Team india = new Team("India", Arrays.asList(p1, p2, p3));
        Team australia = new Team("Australia", Arrays.asList(p4, p5));

        // Create Matches
        Match match1 = new Match("M001", india, australia, LocalDateTime.now().plusMinutes(1));

        // Repository
        MatchRepository repo = new MatchRepository();
        repo.addMatch(match1);

        // Services
        LiveScoreService liveService = new LiveScoreService();
        SearchService searchService = new SearchService();
        SchedulerService scheduleService = new SchedulerService();

        // Display upcoming
        scheduleService.displayUpcomingMatches(repo.getAllMatches());

        // Simulate live match
        liveService.startMatch(match1);
        Thread.sleep(1000);
        liveService.updateScore(match1, india, 15, 0, 2);
        Thread.sleep(1000);
        liveService.updateScore(match1, india, 30, 1, 4);
        Thread.sleep(1000);
        liveService.endMatch(match1);

        // Search examples
        System.out.println("\n🔍 Search Results:");
        System.out.println(searchService.searchMatchesByTeam(repo.getAllMatches(), "India"));
        System.out.println(searchService.searchPlayerByName(Arrays.asList(india, australia), "Virat Kohli"));


    }
}