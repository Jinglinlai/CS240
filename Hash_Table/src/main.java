import java.io.IOException;
import java.util.Scanner;

public class main {
    private static final String RESOURCE_FOLDER = "resources/";

    public static void main(String[] args) throws IOException {
        SpellChecker spellChecker = new SpellChecker(RESOURCE_FOLDER + "dictionary.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a word to check spelling (or type 'exit' to quit): ");
            String word = scanner.nextLine().toLowerCase();

            if (word.equals("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (spellChecker.checkWord(word)) {
                System.out.println("Correct");
            } else {
                System.out.println("Misspelled");
                String suggestion = spellChecker.findClosestWord(word);
                if (suggestion != null) {
                    System.out.println("Did you mean: " + suggestion + "?");
                }
            }
        }

        scanner.close();
    }
}
