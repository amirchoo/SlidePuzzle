import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PuzzleController(); // Initialize and start the controller
        });
    }
}