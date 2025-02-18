import java.io.*;
import java.util.*;

public class FileHandler {
    // Reads words from a given file and returns a list of words
    public static List<String> readFile(String filePath) throws IOException {
        List<String> words = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            // Convert to lowercase, remove punctuation, and split by spaces
            words.addAll(Arrays.asList(line.toLowerCase().replaceAll("[^a-zA-Z ]", "").split("\\s+")));
        }
        reader.close();
        return words;
    }
}
