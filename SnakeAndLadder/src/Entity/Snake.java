package Entity;

public class Snake extends GameEntity {
    public Snake(int start, int end) {
        super(start, end);
        if (end >= start) throw new IllegalArgumentException("Snake tail must be below head");

    }
}
