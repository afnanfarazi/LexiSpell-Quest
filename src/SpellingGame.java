import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SpellingGame extends JFrame {
    private WordRepository wordRepository;
    private int score;
    private boolean firstTask;
    private Word currentWord;
    private JLabel pronunciationLabel;
    private JLabel hintLabel;
    private JTextField inputField;
    private JLabel scoreLabel;
    private boolean usedHint;

    public SpellingGame() {
        wordRepository = new WordRepository();
        score = 0;
        firstTask = true;
        usedHint = false;
        setupGUI();
        nextWord();
    }

    private void setupGUI() {
        setTitle("Spelling Mastery");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(6, 1));

        pronunciationLabel = new JLabel("", SwingConstants.CENTER);
        hintLabel = new JLabel("", SwingConstants.CENTER);
        inputField = new JTextField();
        scoreLabel = new JLabel("Score: 0", SwingConstants.CENTER);
        JButton hintButton = new JButton("Get Hint");
        JButton submitButton = new JButton("Submit");

        // Set custom colors
        pronunciationLabel.setForeground(Color.BLUE);
        hintLabel.setForeground(Color.MAGENTA);
        scoreLabel.setForeground(Color.RED); // Set score label to red
        hintButton.setBackground(Color.LIGHT_GRAY);
        submitButton.setBackground(Color.LIGHT_GRAY);

        add(pronunciationLabel);
        add(hintLabel);
        add(inputField);
        add(hintButton);
        add(submitButton);
        add(scoreLabel);

        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                provideHint();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkSpelling();
            }
        });

        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    checkSpelling();
                }
            }
        });
    }

    private void nextWord() {
        currentWord = getRandomWord();
        pronunciationLabel.setText("Pronunciation: " + currentWord.getPronunciation());
        hintLabel.setText("");
        inputField.setText("");
        usedHint = false;
    }

    private Word getRandomWord() {
        Object[] wordsArray = wordRepository.getWords().values().toArray();
        return (Word) wordsArray[(int) (Math.random() * wordsArray.length)];
    }

    private void provideHint() {
        hintLabel.setText("Hint: " + currentWord.getDefinition());
        usedHint = true;
        score -= 1; // Deduct 1 point for using a hint
        scoreLabel.setText("Score: " + score);
    }

    private void checkSpelling() {
        String guess = inputField.getText().trim().toLowerCase();

        if (guess.equals(currentWord.getCorrectSpelling())) {
            JOptionPane.showMessageDialog(this, "Correct!");
            if (!usedHint) {
                score += 1; // Increase score by 1 for correct spelling without hint
            }
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect. The correct spelling is: " + currentWord.getCorrectSpelling());
            score -= 1; // Deduct 1 point for incorrect spelling
        }

        scoreLabel.setText("Score: " + score);

        int response = JOptionPane.showConfirmDialog(this, "Do you want to continue?", "Continue", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            nextWord();
        } else {
            JOptionPane.showMessageDialog(this, "Thanks for playing! Your final score: " + score);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SpellingGame().setVisible(true);
            }
        });
    }
}
