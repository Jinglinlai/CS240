import java.io.*;

public class ResultExporter {
    private static final String EXPORT_FOLDER = "exports/";
    private static final String EXPORT_PATH = EXPORT_FOLDER + "exported_words.txt";

    public static void export(WordTree tree) {
        try {
            // Ensure the exports folder exists
            File folder = new File(EXPORT_FOLDER);
            if (!folder.exists()) {
                folder.mkdir(); // Create folder if it doesn’t exist
            }

            // Create or overwrite the file
            PrintWriter writer = new PrintWriter(new FileWriter(EXPORT_PATH, false));
            System.out.println("Exporting results...");

            // Export the tree to the file
            tree.exportTreeToFile(writer);

            writer.close(); // Close writer after writing
            System.out.println("Results exported successfully to " + EXPORT_PATH);
        } catch (IOException e) {
            System.out.println("Export failed: " + e.getMessage());
        }
    }
}
