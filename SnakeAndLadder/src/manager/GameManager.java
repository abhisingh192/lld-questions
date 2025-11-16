package manager;

import Entity.Board;
import Entity.Dice;
import Entity.Player;
import service.GameService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GameManager {

    private final Map<String, GameService> activeGames = new HashMap<>();

    public String createGame(Board board, List<Player> players, Dice dice) {
        String gameId = UUID.randomUUID().toString();
        GameService game = new GameService(board, players, dice);
        activeGames.put(gameId, game);
        return gameId;
    }

    public void startGame(String gameId) {
        GameService game = activeGames.get(gameId);
        if (game == null) {
            System.out.println("Game ID not found!");
            return;
        }
        System.out.println("Starting game with ID: " + gameId);
        game.startGame();
    }

}
