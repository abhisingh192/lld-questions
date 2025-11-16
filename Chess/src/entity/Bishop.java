package entity;

public class Bishop extends Piece{

    // oont
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, Cell start, Cell end) {
        return Math.abs(start.row - end.row) == Math.abs(start.col - end.col)
                && board.isPathClear(start, end);
    }
}
