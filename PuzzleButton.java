import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PuzzleButton extends JButton implements ActionListener {
    private SlidePuzzleBoard board;
    private PuzzleFrame frame;
    private int row;
    private int col;
    private Leaderboard leaderboard;
    private String name;
    private Time time;


    public PuzzleButton(SlidePuzzleBoard b, PuzzleFrame f, int row, int col, Time time) {
        board = b;
        frame = f;
        this.row = row;
        this.col = col;
        this.time = time;
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        PuzzlePiece piece1 = board.getPuzzlePiece(row, col);
        if (piece1 != null) {
            boolean moved = board.move(piece1.faceValue());
            if (moved) {
                frame.update();
            }
        }
        if (board.gameOver()) {
            frame.finish();
            name = JOptionPane.showInputDialog("Input your name");

            // Create or get the existing leaderboard instance
            leaderboard = new Leaderboard();
            leaderboard.addScore(name, time.getTime());// Add the player's score to the leaderboard
            time.stop();
            time.reset();
        }
    }
}
