package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import main.GameLoop;
import util.TypeWriter;

public class Menu {
    private GameLoop gameLoop;
    private static boolean isGameActive = false;  // Track if a game is currently active

    public Menu() {
        // Load the high score at the start
        loadHighScore();
        showMenu();
    }

    private void showMenu() {
        TypeWriter print = new TypeWriter();
        print.SlowType("\nMENU");
        print.SlowType("_____________________________\n");
        print.SlowType("1. Start new game");
        print.SlowType("2. Resume current game");
        print.SlowType("3. See high scores");
        print.SlowType("4. Quit Program");

        System.out.print("Select an option: ");
        Scanner scanner = new Scanner(System.in);
        char choice = scanner.next().charAt(0);

        switch (choice) {
            case '1':
                gameLoop = new GameLoop();
                gameLoop.start();
                break;
            case '2':
                if (isGameActive) {
                    gameLoop.start();
                } else {
                    System.out.println("No active game to resume!");
                    showMenu();
                }
                break;
            case '3':
                // Display high scores
                displayHighScores();
                break;
            case '4':
                System.out.println("Quitting the program...");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid option. Try again.");
                showMenu();
                break;
        }
    }

    private void displayHighScores() {
        System.out.println("High Score: " + GameLoop.getHighScore());
        System.out.println("Press any key to return to the menu...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Wait for user input
        showMenu(); // Go back to the main menu
    }

    private void loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader("highscore.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                GameLoop.setHighScore(Integer.parseInt(line)); // Load saved high score into the GameLoop class
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setGameActive(boolean active) {
        isGameActive = active; // Set the flag for whether a game is active
    }
}
