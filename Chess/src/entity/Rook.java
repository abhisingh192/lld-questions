package entity;

public class Rook extends Piece{

    // hathi
    public Rook (Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, Cell start, Cell end) {
        int rowDiff = Math.abs(start.row - end.row);
        int colDiff = Math.abs(start.col - end.col);
        return (start.row == end.row || start.col == end.col)
                && board.isPathClear(start, end);
    }
}
