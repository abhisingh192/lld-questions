import entity.Player;
import entity.Symbol;
import service.GameService;

public class Main {
    public static void main(String[] args) {

        Player p1 = new Player("p1", "Alice", Symbol.X);
        Player p2 = new Player("p2", "Bob", Symbol.O);

        GameService game = new GameService(p1, p2);

        // Example moves
        game.playMove(0, 0); // Alice
        game.playMove(0, 1); // Bob
        game.playMove(1, 1); // Alice
        game.playMove(0, 2); // Bob
        game.playMove(2, 2); // Alice wins
    }
}