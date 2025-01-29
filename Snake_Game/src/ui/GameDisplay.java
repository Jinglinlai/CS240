package ui;

import java.awt.*;
import java.util.LinkedList;

public class GameDisplay {
    private int rows = 10; // grid size (10 x 20)
    private int cols = 20;

    public GameDisplay() {
    }

    public GameDisplay(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public void screen(LinkedList<Point> snakeBody, Point foodPos) {
        char[][] grid = new char[rows][cols];

        // Initialize grid with empty cells
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = '.'; // Empty space
            }
        }

        // Place the snake on the grid
        for (Point segment : snakeBody) {
            grid[segment.y][segment.x] = 'O'; // Snake segment
        }

        // Place the food on the grid
        grid[foodPos.y][foodPos.x] = '*';

        // Print the grid to the console
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(); // Add an extra newline for better readability
    }
}
