import java.io.*;
import java.util.*;

public class Dictionary {
    private Set<String> words = new HashSet<>();

    public Dictionary(String dictionaryFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(dictionaryFile));
        String word;
        while ((word = reader.readLine()) != null) {
            words.add(word.trim().toLowerCase());
        }
        reader.close();
    }

    public boolean isWordInDictionary(String word) {
        return words.contains(word);
    }
}
