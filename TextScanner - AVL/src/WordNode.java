public class WordNode {
    String word;      // The word stored in the node
    int count;        // Frequency of the word
    int height;       // Height of the node (for AVL balancing)
    WordNode left;    // Reference to the left child
    WordNode right;   // Reference to the right child

    // Constructor initializes the node with the given word and sets height to 1
    public WordNode(String word) {
        this.word = word;
        this.count = 1;
        this.height = 1; // Height is 1 for a newly created node
    }
}
