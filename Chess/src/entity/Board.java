package entity;

public class Board {

    private Cell[][] cells = new Cell[8][8];

    public Board() {
        initializeBoard();
    }

    private void initializeBoard() {
        // Setup empty cells
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                cells[i][j] = new Cell(i, j);

        // Setup black pieces
        cells[0][0].setPiece(new Rook(Color.BLACK));
        cells[0][1].setPiece(new Knight(Color.BLACK));
        cells[0][2].setPiece(new Bishop(Color.BLACK));
        cells[0][3].setPiece(new Queen(Color.BLACK));
        cells[0][4].setPiece(new King(Color.BLACK));
        cells[0][5].setPiece(new Bishop(Color.BLACK));
        cells[0][6].setPiece(new Knight(Color.BLACK));
        cells[0][7].setPiece(new Rook(Color.BLACK));
        for (int j = 0; j < 8; j++) cells[1][j].setPiece(new Pawn(Color.BLACK));

        // Setup white pieces
        cells[7][0].setPiece(new Rook(Color.WHITE));
        cells[7][1].setPiece(new Knight(Color.WHITE));
        cells[7][2].setPiece(new Bishop(Color.WHITE));
        cells[7][3].setPiece(new Queen(Color.WHITE));
        cells[7][4].setPiece(new King(Color.WHITE));
        cells[7][5].setPiece(new Bishop(Color.WHITE));
        cells[7][6].setPiece(new Knight(Color.WHITE));
        cells[7][7].setPiece(new Rook(Color.WHITE));
        for (int j = 0; j < 8; j++) cells[6][j].setPiece(new Pawn(Color.WHITE));
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public boolean isPathClear(Cell start, Cell end) {
        int rowDir = Integer.compare(end.row, start.row);
        int colDir = Integer.compare(end.col, start.col);
        int row = start.row + rowDir;
        int col = start.col + colDir;

        while (row != end.row || col != end.col) {
            if (!cells[row][col].isEmpty()) return false;
            row += rowDir;
            col += colDir;
        }
        return true;
    }

}
