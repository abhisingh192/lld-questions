import entity.Color;
import entity.Player;
import service.GameService;

public class Main {
    public static void main(String[] args) {


        Player white = new Player("Alice", Color.WHITE);
        Player black = new Player("Bob", Color.BLACK);

        GameService game = new GameService(white, black);

        game.displayBoard();
        game.makeMove(6, 4, 4, 4); // White pawn moves forward
        game.makeMove(1, 4, 3, 4); // Black pawn moves forward
        game.makeMove(7, 6, 5, 5); // White knight moves
        game.displayBoard();
    }
}