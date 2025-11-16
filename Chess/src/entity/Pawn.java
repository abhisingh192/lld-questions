package entity;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color);
    }


    @Override
    public boolean isValidMove(Board board, Cell start, Cell end) {
        int dir = (color == Color.WHITE) ? -1 : 1;
        int rowDiff = end.row - start.row;
        int colDiff = Math.abs(start.col - end.col);

        if (colDiff == 0 && rowDiff == dir && end.isEmpty()) return true;
        if (colDiff == 1 && rowDiff == dir && isOpponentPiece(end)) return true;
        return false;
    }

}
