import javax.swing.*;

public class PuzzleController {

    private final GameMenu gameMenu;
    private final PuzzleFrame puzzleFrame;
    private final Leaderboard leaderboard;
    private final SlidePuzzleBoard slidePuzzleBoard;
    private final Time timer;

    public PuzzleController() {
        // Initialize all components
        gameMenu = new GameMenu();
        leaderboard = new Leaderboard();
        slidePuzzleBoard = new SlidePuzzleBoard();
        puzzleFrame = new PuzzleFrame(slidePuzzleBoard, leaderboard);
        timer = new Time(puzzleFrame.getStartButton());

        // Initialize the Game Menu actions
        gameMenu.getStartButton().addActionListener(e -> startGame());
        gameMenu.getLeaderboardButton().addActionListener(e -> showLeaderboard());

        // Initialize the PuzzleFrame actions
        puzzleFrame.getStartButton().addActionListener(e -> startGame());
        puzzleFrame.getReshuffleButton().addActionListener(e -> reshufflePuzzle());

        // Initialize leaderboard actions
        leaderboard.getMainMenuButton().addActionListener(e -> returnToGameMenu());

        // Show the GameMenu first
        gameMenu.setVisible(true);
        puzzleFrame.setVisible(false); // Hide the game frame initially
    }

    // Start a new game (called when Start is clicked)
    private void startGame() {
        slidePuzzleBoard.createPuzzleBoard(); // Shuffles the puzzle
        puzzleFrame.update(); // Updates the view with the new puzzle state
        puzzleFrame.setVisible(true); // Show the puzzle frame
        gameMenu.setVisible(false);// Hide the game menu
        timer.start(); // Start the timer



    }

    // Show the leaderboard (called when Leaderboard is clicked)
    private void showLeaderboard() {
        leaderboard.setVisible(true);
        gameMenu.setVisible(false); // Hide the game menu when leaderboard is shown
    }

    // Return to the main game menu from leaderboard
    private void returnToGameMenu() {
        gameMenu.setVisible(true); // Show the game menu
        leaderboard.setVisible(false); // Hide the leaderboard
    }

    // Reshuffle the puzzle when clicked
    private void reshufflePuzzle() {
        slidePuzzleBoard.createPuzzleBoard();
        puzzleFrame.update();
        puzzleFrame.getReshuffleButton().setText("Reshuffle");
    }
}
