import java.io.*;
import java.util.*;

public class FileHandler {
    public static List<String> readFile(String filePath) throws IOException {
        List<String> words = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            words.addAll(Arrays.asList(line.toLowerCase().replaceAll("[^a-zA-Z ]", "").split("\\s+")));
        }
        reader.close();
        return words;
    }
}
