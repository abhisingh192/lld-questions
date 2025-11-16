import Entity.*;
import manager.GameManager;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        // Snakes and Ladders setup
        List<Snake> snakes = Arrays.asList(new Snake(99, 10), new Snake(95, 75), new Snake(90, 50));
        List<Ladder> ladders = Arrays.asList(new Ladder(5, 25), new Ladder(40, 89));

        Board board = new Board(100, snakes, ladders);
        Dice dice = new Dice(6);

        List<Player> players1 = Arrays.asList(new Player("p1", "Alice"), new Player("p2", "Bob"));
        List<Player> players2 = Arrays.asList(new Player("p3", "Charlie"), new Player("p4", "Daisy"));

        GameManager manager = new GameManager();

        String game1 = manager.createGame(board, players1, dice);
        String game2 = manager.createGame(board, players2, dice);

        // Run multiple sessions (in real world, we can thread these)
        manager.startGame(game1);
//        manager.startGame(game2);


    }
}