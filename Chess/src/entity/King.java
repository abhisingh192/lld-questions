package entity;

public class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, Cell start, Cell end) {
        int rowDiff = Math.abs(start.row - end.row);
        int colDiff = Math.abs(start.col - end.col);

        // King can move one square in any direction
        return (rowDiff <= 1 && colDiff <= 1);
    }

    @Override
    public String toString() {
        return color == Color.WHITE ? "♔" : "♚";
    }
}
