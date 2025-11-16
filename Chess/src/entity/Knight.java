package entity;

public class Knight extends Piece{

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, Cell start, Cell end) {

        int rowDiff = Math.abs(start.row - end.row);
        int colDiff = Math.abs(start.col - end.col);
        return rowDiff * colDiff == 2;
    }
}
