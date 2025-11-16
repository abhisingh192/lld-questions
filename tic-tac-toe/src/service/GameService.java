package service;

import entity.Board;
import entity.Player;
import entity.Symbol;

public class GameService {
    private final Board board;
    private final Player player1;
    private final Player player2;

    private Player currentPlayer;

    private boolean isGameOver;


    public GameService(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new Board();
        this.isGameOver = false;
        this.currentPlayer = player1;
    }

    public void playMove(int row, int col) {
        if (isGameOver) {
            System.out.println("game already over");
            return ;

        }

        boolean isValidMove = board.placeSymbol(row, col, currentPlayer.getSymbol());

        if (!isValidMove) {
            System.out.println("invalid move, try again");
            return;
        }

        board.display();
        System.out.println();


        if (checkWinner(currentPlayer)) {
            System.out.println( currentPlayer.getName() + " won, game over");
            isGameOver = true;
        }

        if (isBoardFull()) {
            System.out.println( "game drawn");
            isGameOver = true;
        }

        switchTurn();
    }

    private void switchTurn() {
        if (currentPlayer.equals(player1))
            currentPlayer = player2;
        else currentPlayer = player1;
    }

    private boolean isBoardFull() {

        for(int i=0; i<board.getSize();i++) {
            for(int j=0; j<board.getSize(); j++) {
                if (board.getCell(i,j).equals(Symbol.EMPTY)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean checkWinner(Player currentPlayer) {
        int size = board.getSize();
        Symbol symbol = currentPlayer.getSymbol();

        // Check rows and cols
        for (int i = 0; i < size; i++) {
            if (board.getCell(i, 0) == symbol && board.getCell(i, 1) == symbol && board.getCell(i, 2) == symbol) return true;
            if (board.getCell(0, i) == symbol && board.getCell(1, i) == symbol && board.getCell(2, i) == symbol) return true;
        }

        // Check diagonals
        if (board.getCell(0, 0) == symbol && board.getCell(1, 1) == symbol && board.getCell(2, 2) == symbol) return true;
        if (board.getCell(0, 2) == symbol && board.getCell(1, 1) == symbol && board.getCell(2, 0) == symbol) return true;

        return false;
    }
}
