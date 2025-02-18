// WordTree.java - Implements a Binary Search Tree (BST) for storing words
import java.io.PrintWriter;

public class WordTree {
    private WordNode root; // Root node of the BST

    // Inserts a word into the tree, or increments count if it already exists
    public void insert(String word) {
        root = insertRec(root, word);
    }

    private WordNode insertRec(WordNode node, String word) {
        if (node == null) return new WordNode(word); // Create a new node if empty
        if (word.equals(node.word)) node.count++; // Increase count if word exists
        else if (word.compareTo(node.word) < 0) node.left = insertRec(node.left, word); // Go left for smaller words
        else node.right = insertRec(node.right, word); // Go right for larger words
        return node;
    }

    // Searches for a word and returns its count, or 0 if not found
    public int search(String word) {
        return searchRec(root, word);
    }

    private int searchRec(WordNode node, String word) {
        if (node == null) return 0; // Word not found
        if (word.equals(node.word)) return node.count; // Word found, return count
        return word.compareTo(node.word) < 0 ? searchRec(node.left, word) : searchRec(node.right, word); // Recursively search left or right
    }

    // Prints all words in alphabetical order along with their counts
    public void printTree() {
        printInOrder(root);
    }

    private void printInOrder(WordNode node) {
        if (node != null) {
            printInOrder(node.left); // Visit left subtree
            System.out.println(node.word + " - " + node.count); // Print current node
            printInOrder(node.right); // Visit right subtree
        }
    }

    // Exports the tree to a file in alphabetical order
    public void exportTreeToFile(PrintWriter writer) {
        if (root == null) {
            writer.println("The word tree is empty.");
        } else {
            exportInOrder(root, writer);
        }
    }

    private void exportInOrder(WordNode node, PrintWriter writer) {
        if (node != null) {
            exportInOrder(node.left, writer); // Visit left subtree
            writer.println(node.word + " - " + node.count); // Write current node to file
            exportInOrder(node.right, writer); // Visit right subtree
        }
    }
}
