package Entity;

public class Ladder extends GameEntity {
    public Ladder(int start, int end) {
        super(start, end);
        if (start >= end) throw new IllegalArgumentException("ladder top must be above bottom");

    }
}
