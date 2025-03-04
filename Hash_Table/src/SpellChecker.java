import java.io.*;
import java.util.*;

public class SpellChecker {
    private HashMap<String, Boolean> dictionary;

    public SpellChecker(String dictionaryPath) throws IOException {
        dictionary = new HashMap<>();
        loadDictionary(dictionaryPath);
    }

    private void loadDictionary(String path) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;

        while ((line = br.readLine()) != null) {
            dictionary.put(line.toLowerCase().trim(), true);
            System.out.println("Loaded word: " + line.toLowerCase().trim()); // Debugging statement
        }
        br.close();
        System.out.println("📖 Dictionary loaded with " + dictionary.size() + " words.");
    }

    public boolean checkWord(String word) {
        word = word.toLowerCase().trim();
        System.out.println("Checking word: " + word); // Debugging statement
        return dictionary.containsKey(word);
    }

    public String findClosestWord(String word) {
        word = word.toLowerCase().trim();
        String closestWord = null;
        int minDistance = Integer.MAX_VALUE;

        for (String dictWord : dictionary.keySet()) {
            int distance = levenshteinDistance(word, dictWord);
            if (distance < minDistance) {
                minDistance = distance;
                closestWord = dictWord;
            }
        }

        return closestWord;
    }

    private int levenshteinDistance(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i <= a.length(); i++) {
            for (int j = 0; j <= b.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + (a.charAt(i - 1) == b.charAt(j - 1) ? 0 : 1),
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }

        return dp[a.length()][b.length()];
    }
}
