package data;

public class Cell {
    private final int row;
    private final int col;
    private String fill; // empty, snake, food

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.fill = "empty";
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }
}
