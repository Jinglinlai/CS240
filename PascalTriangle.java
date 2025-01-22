public class PascalTriangle {

    // Recursive method to get value at (row, col)
    public static int calculateValue(int row, int col) {
        if (col == 0 || col == row) return 1; // Base case
        return calculateValue(row - 1, col - 1) + calculateValue(row - 1, col); // Recursive step
    }

    // Method to print Triangle
    public static void printPascalTriangle(int numRows) {
        int maxWidth = String.valueOf(calculateValue(numRows - 1, numRows / 2)).length(); // Max number width
        for (int row = 0; row < numRows; row++) {
            // Print spaces
            for (int space = 0; space < (numRows - row - 1) * maxWidth; space++) {
                System.out.print(" ");
            }
            // Print current row
            for (int col = 0; col <= row; col++) {
                System.out.printf("%" + (maxWidth * 2) + "d", calculateValue(row, col));
            }
            System.out.println(); // Print the next row
        }
    }

    public static void main(String[] args) {
        int numRows = 17; //Change this value to print Pascal's Triangle with different number of rows
        System.out.println("Pascal's Triangle with " + numRows + " rows:");
        printPascalTriangle(numRows);
    }
}
