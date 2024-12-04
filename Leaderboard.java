import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Leaderboard extends JFrame {
    private JTextArea leaderboardTextArea;
    private List<String> leaderboardEntries;
    private JButton mainMenuButton;

    public Leaderboard() {
        setTitle("Leaderboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        leaderboardTextArea = new JTextArea();
        leaderboardTextArea.setEditable(false);
        add(new JScrollPane(leaderboardTextArea), BorderLayout.CENTER);

        leaderboardEntries = new ArrayList<>();

        // Load existing leaderboard entries from file
        loadLeaderboard();

        // Display leaderboard
        displayLeaderboard();

        // Create and add "Main Menu" button
        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setFont(new Font("Serif", Font.PLAIN, 18));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(mainMenuButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Add a new score to the leaderboard
    public void addScore(String name, String time) {
        leaderboardEntries.add(name + "\t" + time);
        saveLeaderboard();
        displayLeaderboard();
    }

    // Load leaderboard from the file
    public void loadLeaderboard() {
        File file = new File("ranking.txt");
        try {
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    leaderboardEntries.add(line);
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save leaderboard to the file
    private void saveLeaderboard() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ranking.txt"));
            for (String entry : leaderboardEntries) {
                writer.write(entry);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Display the leaderboard in the text area
    public void displayLeaderboard() {
        leaderboardTextArea.setText("");
        for (String entry : leaderboardEntries) {
            leaderboardTextArea.append(entry + "\n");
        }
    }

    public JButton getMainMenuButton() {
        return mainMenuButton;
    }
}
