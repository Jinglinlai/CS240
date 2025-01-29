package data;

public class HighScore {
    private static HighScore instance = null;

    private HighScore() {
    }

    public static HighScore getInstance() {
        if (instance == null) {
            instance = new HighScore();
        }
        return instance;
    }

    public void display() {
        System.out.println("Displaying high scores...");
        // Logic to display high scores here
    }
}
