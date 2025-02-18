import java.io.*;
import java.util.*;

public class Dictionary {
    private Set<String> words = new HashSet<>();

    // Load words from a dictionary file
    public Dictionary(String dictionaryFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(dictionaryFile));
        String word;
        while ((word = reader.readLine()) != null) {
            words.add(word.trim().toLowerCase());
        }
        reader.close();
    }

    // Check if a word is in the dictionary
    public boolean isWordInDictionary(String word) {
        return words.contains(word);
    }
}
