package Entity;

import Entity.GameEntity;
import Entity.Ladder;
import Entity.Snake;

import java.util.List;

public class Board {
    private int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    public Board(int size, List<Snake> snakes, List<Ladder> ladders) {
        this.size = size;
        this.snakes = snakes;
        this.ladders = ladders;
    }

    public int getNextPosition(int position) {
        // Check snakes
        for (Snake snake : snakes) {
            if (snake.getStart() == position) return snake.getEnd();
        }
        // Check ladders
        for (Ladder ladder : ladders) {
            if (ladder.getStart() == position) return ladder.getEnd();
        }

        return position;
    }

    public int getSize() {
        return size;
    }


}
