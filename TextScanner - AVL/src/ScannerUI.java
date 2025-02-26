import java.io.*;
import java.util.*;

public class ScannerUI {
    private Scanner scanner = new Scanner(System.in);
    private WordTree wordTree = new WordTree();
    private Dictionary dictionary;
    private static final String RESOURCE_FOLDER = "resources/";

    public void start() {
        try {
            dictionary = new Dictionary(RESOURCE_FOLDER + "dictionary.txt");
            System.out.println("Loaded dictionary.");

            List<String> availableFiles = getAvailableTextFiles();
            if (availableFiles.isEmpty()) {
                System.out.println("No text files found in the resources folder.");
                return;
            }

            System.out.println("Available text files:");
            for (int i = 0; i < availableFiles.size(); i++) {
                System.out.println((i + 1) + ". " + availableFiles.get(i));
            }
            System.out.print("Choose a file to scan (1-" + availableFiles.size() + "): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 1 || choice > availableFiles.size()) {
                System.out.println("Invalid choice.");
                return;
            }

            String selectedFile = RESOURCE_FOLDER + availableFiles.get(choice - 1);
            List<String> words = FileHandler.readFile(selectedFile);
            for (String word : words) {
                wordTree.insert(word);
            }

            System.out.println("Scanned file: " + selectedFile);
            displayOptions();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private List<String> getAvailableTextFiles() {
        List<String> textFiles = new ArrayList<>();
        File folder = new File(RESOURCE_FOLDER);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
        if (files != null) {
            for (File file : files) {
                textFiles.add(file.getName());
            }
        }
        return textFiles;
    }

    private void displayOptions() {
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Search for a word");
            System.out.println("2. Print word tree");
            System.out.println("3. Export results");
            System.out.println("4. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter word to search: ");
                    String word = scanner.nextLine();
                    System.out.println("Frequency: " + wordTree.search(word));
                    break;
                case 2:
                    wordTree.printTree();
                    break;
                case 3:
                    ResultExporter.export(wordTree);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}
