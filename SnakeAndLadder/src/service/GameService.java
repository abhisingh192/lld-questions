package service;

import Entity.Board;
import Entity.Dice;
import Entity.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameService {

    private final Board board;
    private final Queue<Player> players;
    private final Dice dice;
    private boolean isGameOver;

    public GameService(Board board, List<Player> players, Dice dice) {
        this.board = board;
        this.players = new LinkedList<>(players);
        this.dice = dice;
        this.isGameOver = false;
    }

    public void startGame() {
        while (!isGameOver) {
            Player currentPlayer = players.poll();
            int roll = dice.roll();
            System.out.println(currentPlayer.getName() + " rolled a " + roll);
            int newPosition = currentPlayer.getPosition() + roll;

            if (newPosition > board.getSize()) {
                System.out.println(currentPlayer.getName() + " cannot move.");
            } else {
                newPosition = board.getNextPosition(newPosition);
                currentPlayer.setPosition(newPosition);
                System.out.println(currentPlayer.getName() + " moved to " + newPosition);

                if (newPosition == board.getSize()) {
                    System.out.println(currentPlayer.getName() + " wins!");
                    isGameOver = true;
                }
            }

            if (!isGameOver) {
                players.offer(currentPlayer);
            }
        }
    }
}
