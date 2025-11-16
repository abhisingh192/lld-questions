package entity;

public class Queen extends Piece{

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean isValidMove(Board board, Cell start, Cell end) {
        int rowDiff = Math.abs(start.row - end.row);
        int colDiff = Math.abs(start.col - end.col);
        return (rowDiff == colDiff || start.row == end.row || start.col == end.col)
                && board.isPathClear(start, end);
    }

}
