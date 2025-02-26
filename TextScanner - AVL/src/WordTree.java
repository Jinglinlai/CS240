import java.io.PrintWriter;

public class WordTree {
    private WordNode root; // Root node of the AVL tree

    // Returns the height of the node, or 0 if null
    private int getHeight(WordNode node) {
        return node == null ? 0 : node.height;
    }

    // Calculates the balance factor of the node (left height - right height)
    private int getBalance(WordNode node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    // Updates the height of the node based on the height of its children
    private void updateHeight(WordNode node) {
        if (node != null) {
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        }
    }

    // Performs a right rotation to balance the tree
    private WordNode rotateRight(WordNode y) {
        WordNode x = y.left; // New root
        WordNode T2 = x.right; // Subtree to move

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights of affected nodes
        updateHeight(y);
        updateHeight(x);

        return x; // New root after rotation
    }

    // Performs a left rotation to balance the tree
    private WordNode rotateLeft(WordNode x) {
        WordNode y = x.right; // New root
        WordNode T2 = y.left; // Subtree to move

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights of affected nodes
        updateHeight(x);
        updateHeight(y);

        return y; // New root after rotation
    }

    // Balances the node after insertion by applying rotations if necessary
    private WordNode balanceTree(WordNode node, String word) {
        int balance = getBalance(node);

        // Left Left Case: Right rotate
        if (balance > 1 && word.compareTo(node.left.word) < 0) {
            return rotateRight(node);
        }

        // Right Right Case: Left rotate
        if (balance < -1 && word.compareTo(node.right.word) > 0) {
            return rotateLeft(node);
        }

        // Left Right Case: Left rotate + Right rotate
        if (balance > 1 && word.compareTo(node.left.word) > 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Right Left Case: Right rotate + Left rotate
        if (balance < -1 && word.compareTo(node.right.word) < 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node; // Node is balanced, no rotations needed
    }

    // Inserts a word into the AVL tree (recursive)
    public void insert(String word) {
        root = insertRec(root, word);
    }

    private WordNode insertRec(WordNode node, String word) {
        // Base case: Create a new node if empty
        if (node == null) return new WordNode(word);

        // Insert word into the left or right subtree
        if (word.equals(node.word)) node.count++; // Increase count if word already exists
        else if (word.compareTo(node.word) < 0) node.left = insertRec(node.left, word);
        else node.right = insertRec(node.right, word);

        // Update height after insertion
        updateHeight(node);

        // Balance the node if needed
        return balanceTree(node, word);
    }

    // Searches for a word and returns its count, or 0 if not found
    public int search(String word) {
        return searchRec(root, word);
    }

    private int searchRec(WordNode node, String word) {
        if (node == null) return 0; // Word not found
        if (word.equals(node.word)) return node.count; // Word found, return count
        return word.compareTo(node.word) < 0 ? searchRec(node.left, word) : searchRec(node.right, word);
    }

    // Prints all words in alphabetical order with their counts
    public void printTree() {
        printInOrder(root);
    }

    private void printInOrder(WordNode node) {
        if (node != null) {
            printInOrder(node.left); // Print left subtree
            System.out.println(node.word + " - " + node.count); // Print current node
            printInOrder(node.right); // Print right subtree
        }
    }

    // Exports the tree to a file in alphabetical order
    public void exportTreeToFile(PrintWriter writer) {
        if (root == null) {
            writer.println("The word tree is empty."); // No words to export
        } else {
            exportInOrder(root, writer);
        }
    }

    private void exportInOrder(WordNode node, PrintWriter writer) {
        if (node != null) {
            exportInOrder(node.left, writer); // Write left subtree
            writer.println(node.word + " - " + node.count); // Write current node to file
            exportInOrder(node.right, writer); // Write right subtree
        }
    }
}
