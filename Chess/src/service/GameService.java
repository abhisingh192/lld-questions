package service;

import entity.Board;
import entity.Cell;
import entity.Piece;
import entity.Player;

public class GameService {

    private final Board board;

    private final Player whitePlayer;
    private final Player blackPlayer;

    private Player currentPlayer;

    public GameService(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.currentPlayer = whitePlayer; // White starts first
        this.board = new Board();
    }

     public boolean makeMove(int startRow, int startCol, int endRow, int endCol) {
         Cell start = board.getCell(startRow, startCol);
         Cell end = board.getCell(endRow, endCol);
         Piece piece = start.getPiece();

         if (piece == null || piece.getColor() != currentPlayer.getColor()) {
             System.out.println("Invalid move! Not your piece.");
             return false;
         }

         if (!piece.isValidMove(board, start, end)) {
             System.out.println("Illegal move for " + piece.getClass().getSimpleName());
             return false;
         }

         end.setPiece(piece);
         start.setPiece(null);

         // Checkmate/stalemate logic could be added here
         System.out.println(currentPlayer.getName() + " moved " + piece.getClass().getSimpleName() +
                 " from (" + startRow + "," + startCol + ") to (" + endRow + "," + endCol + ")");
         switchTurn();
         return true;
     }

    private void switchTurn() {
        currentPlayer = (currentPlayer == whitePlayer) ? blackPlayer : whitePlayer;
    }

    public void displayBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece p = board.getCell(i, j).getPiece();
                System.out.print((p == null ? "--" : p.getClass().getSimpleName().charAt(0) + "") + " ");
            }
            System.out.println();
        }
    }
}
