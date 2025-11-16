package entity;

public class Board {

    private final int size = 3;

    private Symbol[][] grid;

    public Board() {
        grid = new Symbol[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = Symbol.EMPTY;
            }
        }
    }

    public Symbol getCell(int row, int col) {
        return grid[row][col];
    }

    public boolean placeSymbol(int row, int col, Symbol symbol) {

        // invalid move
        if (row < 0 || row >= size || col < 0 || col >= size || grid[row][col] != Symbol.EMPTY) {
            return false;
        }
        grid[row][col] = symbol;
        return true;

    }

    public int getSize() {
        return size;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print((grid[i][j] == Symbol.EMPTY ? "-" : grid[i][j]) + " ");
            }
            System.out.println();
        }
    }
}
