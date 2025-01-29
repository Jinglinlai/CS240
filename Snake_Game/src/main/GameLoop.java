package main;

import data.Cell;
import data.Grid;
import data.Snake;
import entities.Food;
import java.awt.Point;
import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;
import ui.GameDisplay;
import ui.Menu;
import util.TypeWriter;

public class GameLoop {
    private final Grid grid;
    private final Snake snake;
    private Food food;
    private final GameDisplay display;
    private final Scanner scanner;
    private boolean session = true;
    private int score = 0;  // Track score
    private static int highScore = 0; // Static variable for high score

    public GameLoop() {
        grid = new Grid(10, 20); // 10x20 grid
        snake = new Snake(grid.getCells()[5][5]); // Snake starts in the middle
        food = generateFood(); // Place initial food
        display = new GameDisplay();
        scanner = new Scanner(System.in);
    }

    public void start() {
        TypeWriter print = new TypeWriter();
        print.SlowType("Game starting...\n");
        Menu.setGameActive(true); // Mark game as active

        while (session) {
            clearScreen(); // Clear terminal
            display.screen(convertCellsToPoints(snake.getBody()), new Point(food.x, food.y));

            // Display the current score and high score
            System.out.println("Score: " + score);
            System.out.println("High Score: " + highScore);

            // Get user input for movement
            System.out.print("Enter move (WASD): ");
            char direction = scanner.next().toLowerCase().charAt(0);

            // Determine next cell
            Cell nextCell = getNextCell(direction);

            // Handle collisions and movement
            if (nextCell == null || nextCell.getFill().equals("snake")) {
                session = false;
                print.SlowType("Game Over! You hit the wall or yourself.\n");
                print.SlowType("Your score: " + score + "\n");
                checkHighScore();  // Check if the current score is a high score
                Menu.setGameActive(false); // Mark game as not active
            } else if (nextCell.getFill().equals("food")) {
                snake.move(nextCell);
                snake.grow();  // Grow the snake after eating food
                food = generateFood();  // Regenerate food
                score++;  // Increase score when food is eaten
            } else {
                snake.move(nextCell); // Regular move
            }

            try {
                Thread.sleep(300); // Delay for game speed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Save the high score to a file
        saveHighScore();

        // Display final score and high score before returning to the menu
        print.SlowType("Game Over! Your final score: " + score + "\n");
        print.SlowType("High Score: " + highScore + "\n");

        // Wait for user input to return to the menu
        System.out.println("Press any key to return to the main menu...");
        scanner.nextLine(); // Wait for the user to press enter or any key

        print.SlowType("Returning to main menu...\n");
        new ui.Menu(); // Return to the menu
    }

    private void checkHighScore() {
        if (score > highScore) {
            highScore = score; // Update high score if current score is higher
        }
    }

    private void saveHighScore() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("highscore.txt"))) {
            writer.write(String.valueOf(highScore));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Static methods to access high score
    public static int getHighScore() {
        return highScore;
    }

    public static void setHighScore(int highScore) {
        GameLoop.highScore = highScore;
    }

    private Cell getNextCell(char direction) {
        Cell head = snake.getHead();
        int newRow = head.getRow();
        int newCol = head.getCol();

        switch (direction) {
            case 'w': newRow--; break; // Move up
            case 's': newRow++; break; // Move down
            case 'a': newCol--; break; // Move left
            case 'd': newCol++; break; // Move right
            default: return null;
        }

        if (newRow < 0 || newRow >= grid.getRows() || newCol < 0 || newCol >= grid.getCols()) {
            return null; // Out of bounds
        }
        return grid.getCells()[newRow][newCol];
    }

    private Food generateFood() {
        // Regenerate food in a random position that is not occupied by the snake
        int attempts = 0;
        int maxAttempts = grid.getRows() * grid.getCols(); // Fail-safe

        while (attempts < maxAttempts) {
            int x = (int) (Math.random() * grid.getCols());
            int y = (int) (Math.random() * grid.getRows());

            Cell cell = grid.getCells()[y][x];

            // Ensure the food is placed in an empty cell
            if (cell.getFill().equals("empty")) {
                cell.setFill("food");
                return new Food(x, y);
            }

            attempts++;
        }

        // Fallback food position in case of failure to find empty space
        return new Food(3, 7);
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J"); // ANSI escape to clear terminal
        System.out.flush();
    }

    private LinkedList<Point> convertCellsToPoints(LinkedList<Cell> cells) {
        LinkedList<Point> points = new LinkedList<>();
        for (Cell cell : cells) {
            points.add(new Point(cell.getCol(), cell.getRow()));
        }
        return points;
    }
}
