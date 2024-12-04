import javax.swing.*;
import java.awt.*;

public class StartButton extends JButton {
    private SlidePuzzleBoard board;
    private PuzzleFrame frame;
    private Time time;

    public StartButton(SlidePuzzleBoard b, PuzzleFrame f) {
        super("Start");
        board = b;
        frame = f;
        time = new Time(this);
        setFont(new Font("Arial", Font.BOLD, 25));
    }

    public Time getTime() {
        return time;
    }

    public SlidePuzzleBoard getBoard() {
        return board;
    }

    public PuzzleFrame getFrame() {
        return frame;
    }
}
