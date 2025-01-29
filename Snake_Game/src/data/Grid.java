package data;

import java.util.Random;

public class Grid {
    final int rows;
    final int cols;
    private final Cell[][] cells;
    private final Random random = new Random();

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        cells = new Cell[rows][cols];

        // Initialize grid with empty cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public String getCellFill(int row, int col) {
        return cells[row][col].getFill();
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
