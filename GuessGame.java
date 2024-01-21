import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GuessGame extends JFrame {

    private int secretNumber;
    private int maxAttempts = 7;
    private int attempts;
    private int totalRounds;
    private int totalScore;

    private JTextField guessTextField;
    private JTextArea feedbackTextArea;

    public GuessGame() {
        setTitle("Number Guessing Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeGame();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel instructionLabel = new JLabel("Guess the number between 1 and 100. You have " + maxAttempts + " attempts.");
        panel.add(instructionLabel);

        guessTextField = new JTextField();
        panel.add(guessTextField);

        JButton submitButton = new JButton("Submit Guess");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });
        panel.add(submitButton);

        feedbackTextArea = new JTextArea();
        feedbackTextArea.setEditable(false);
        panel.add(feedbackTextArea);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeGame() {
        Random random = new Random();
        secretNumber = random.nextInt(100) + 1;
        attempts = 0;
    }

    private void processGuess() {
        if (attempts < maxAttempts) {
            try {
                int userGuess = Integer.parseInt(guessTextField.getText());
                if (userGuess == secretNumber) {
                    feedbackTextArea.setText("Congratulations! You guessed the correct number " + secretNumber + " in " + (attempts + 1) + " attempts.");
                    totalScore++;
                    initializeGame();
                    totalRounds++;
                } else if (userGuess < secretNumber) {
                    feedbackTextArea.setText("Too low. Try again.");
                } else {
                    feedbackTextArea.setText("Too high. Try again.");
                }
                attempts++;
            } catch (NumberFormatException ex) {
                feedbackTextArea.setText("Invalid input. Please enter a valid number.");
            }
        } else {
            feedbackTextArea.setText("Sorry, you've run out of attempts. The correct number was " + secretNumber + ".");
            initializeGame();
            totalRounds++;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GuessGame();
            }
        });
    }
}
