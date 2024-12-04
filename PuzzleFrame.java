import javax.swing.*;
import java.awt.*;

public class PuzzleFrame extends JFrame {
    private SlidePuzzleBoard board;
    private PuzzleButton[][] button_board;
    private Time timer;
    private Leaderboard leaderboard;
    private JButton startButton;  // This can now be a regular JButton
    private JButton reshuffleButton;

    Font font = new Font("Arial", Font.BOLD, 26);
    Font f = new Font("Serif", Font.BOLD, 20);

    public PuzzleFrame(SlidePuzzleBoard b, Leaderboard leaderboard) {
        board = b;
        button_board = new PuzzleButton[4][4];
        this.leaderboard = leaderboard;

        setTitle("Puzzle Game");
        setLayout(new BorderLayout());
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create StartButton and Reshuffle button
        startButton = new JButton("Start");  // Start button
        reshuffleButton = new JButton("Reshuffle");
        reshuffleButton.setVisible(false); // Initially hidden

        // Create Time and pass StartButton to Time's constructor
        timer = new Time(startButton);  // Pass the StartButton (JButton) to Time

        // Main panel holds grid and button panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Puzzle grid setup
        JPanel gridPanel = new JPanel(new GridLayout(4, 4));
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                button_board[row][col] = new PuzzleButton(board, this, row, col, timer);
                button_board[row][col].setFocusable(false);
                button_board[row][col].setMargin(new Insets(0, 0, 0, 0));
                gridPanel.add(button_board[row][col]);
            }
        }
        mainPanel.add(gridPanel, BorderLayout.CENTER);

        // Timer setup - Ensure it appears at the top
        JPanel timerPanel = new JPanel();
        timerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        timer.setFont(font);
        timerPanel.add(timer);  // Add timer to the top

        // Button panel setup
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        startButton.setFont(f);
        reshuffleButton.setFont(f);
        buttonPanel.add(startButton);
        buttonPanel.add(reshuffleButton);

        // Bottom panel setup
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH); // Add buttons to the bottom section of bottomPanel

        // Add main panel and bottom panel to the frame
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(timerPanel, BorderLayout.NORTH);

        add(mainPanel);
        update();
        setVisible(true);
    }

    // Method to update the button grid based on the board state
    public void update() {
        PuzzlePiece[][] boardState = board.contents();

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                PuzzlePiece piece = boardState[row][col];
                if (piece == null) {
                    button_board[row][col].setText("");
                    button_board[row][col].setEnabled(false);
                } else {
                    button_board[row][col].setText(String.valueOf(piece.faceValue()));
                    button_board[row][col].setFont(font);
                    button_board[row][col].setEnabled(true);
                }
            }
        }
    }

    // Finish the game if it's over
    public void finish() {
        if (board.gameOver()) {
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    if (board.getPuzzlePiece(row, col) == null) {
                        button_board[row][col].setText("Done");
                    } else {
                        button_board[row][col].setEnabled(false);
                    }
                }
            }
            String name = JOptionPane.showInputDialog("Input your name");
            leaderboard.addScore(name, timer.getTime()); // Add score to leaderboard
            leaderboard.setVisible(true); // Show leaderboard after game finishes
        }
    }

    // Methods to get the buttons for handling in the controller
    public JButton getStartButton() {
        return startButton;
    }

    public JButton getReshuffleButton() {
        return reshuffleButton;
    }

    public PuzzleButton[][] getButtonBoard() {
        return button_board;
    }
}
