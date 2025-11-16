package entity;

public abstract class Piece {

    protected Color color;

    public Color getColor() {
        return color;
    }

    public Piece(Color color) {
        this.color = color;
    }

    public abstract boolean isValidMove(Board board, Cell start, Cell end);

    public boolean isOpponentPiece(Cell cell) {
        return cell.getPiece() != null && this.color != cell.getPiece().color;
    }
}
