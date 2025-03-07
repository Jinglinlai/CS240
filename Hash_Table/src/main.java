import java.io.IOException;
import java.util.Scanner;

public class main {
    private static final String RESOURCE_FOLDER = "resources/";

    public static void main(String[] args) throws IOException {
        // Initialize the SpellChecker with the path to the dictionary file
        SpellChecker spellChecker = new SpellChecker(RESOURCE_FOLDER + "dictionary.txt");
        Scanner scanner = new Scanner(System.in);

        // Main loop to continuously check words until the user types 'exit'
        while (true) {
            System.out.print("Enter a word to check spelling (or type 'exit' to quit): ");
            String word = scanner.nextLine().toLowerCase();

            // Exit the loop if the user types 'exit'
            if (word.equals("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            // Check if the word is spelled correctly
            if (spellChecker.checkWord(word)) {
                System.out.println("Correct");
            } else {
                System.out.println("Misspelled");
                // Suggest the closest word if the word is misspelled
                String suggestion = spellChecker.findClosestWord(word);
                if (suggestion != null) {
                    System.out.println("Did you mean: " + suggestion + "?");
                }
            }
        }

        // Close the scanner to free up resources
        scanner.close();
    }
}
