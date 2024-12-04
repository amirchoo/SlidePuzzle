import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;


public class Time extends JLabel implements ActionListener {
    private JButton startButton;  // We can use JButton directly

    int elapsedTime = 0;
    int minutes = 0;
    int seconds = 0;

    String minutes_string = String.format("%02d", minutes);
    String seconds_string = String.format("%02d", seconds);

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime += 1000;
            minutes = (elapsedTime / 60000) % 60;
            seconds = (elapsedTime / 1000) % 60;

            minutes_string = String.format("%02d", minutes);
            seconds_string = String.format("%02d", seconds);
            setText(minutes_string + ":" + seconds_string);
        }
    });

    // Constructor can now accept any JButton, not just StartButton
    public Time(JButton startButton) {
        setText(minutes_string + ":" + seconds_string);
        this.startButton = startButton;
        startButton.addActionListener(this);  // Listen to any button
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            start();  // Start the timer when the button is clicked
        }
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        elapsedTime = 0;
        minutes = 0;
        seconds = 0;
    }

    public String getTime() {
        return String.format("%02d:%02d", minutes, seconds);
    }

    public void setFont(Font font) {
        super.setFont(font);
    }
}
