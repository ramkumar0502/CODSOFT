import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApp {
    private static final int TIME_LIMIT = 15; 
    private static int currentQuestionIndex = 0;
    private static int score = 0;
    private static Timer timer;
    private static int timeRemaining;
    private static JLabel timerLabel;
    private static JLabel questionLabel;
    private static JButton[] optionButtons;

    private static Question[] questions = {
        new Question("What is the capital of France?", new String[]{"Paris", "London", "Rome", "Berlin"}, "Paris"),
        new Question("What is the largest planet in our solar system?", new String[]{"Earth", "Jupiter", "Saturn", "Mars"}, "Jupiter"),
        new Question("Who wrote 'To Kill a Mockingbird'?", new String[]{"Harper Lee", "Mark Twain", "Ernest Hemingway", "F. Scott Fitzgerald"}, "Harper Lee")
    };

    public static void main(String[] args) {
        SwingUtilities.invokeLater(QuizApp::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        frame.add(questionPanel, BorderLayout.CENTER);

        questionLabel = new JLabel("");
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        questionPanel.add(questionLabel);

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(0, 1));
        frame.add(optionsPanel, BorderLayout.EAST);

        timerLabel = new JLabel("");
        frame.add(timerLabel, BorderLayout.NORTH);

        optionButtons = new JButton[questions[0].getOptions().length];
        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].addActionListener(new OptionButtonListener());
            optionsPanel.add(optionButtons[i]);
        }

        JButton nextButton = new JButton("Next Question");
        nextButton.addActionListener(new NextButtonListener());
        frame.add(nextButton, BorderLayout.SOUTH);

        loadQuestion(currentQuestionIndex);

        frame.setSize(400, 300);
        frame.setVisible(true);
    }

    private static void loadQuestion(int index) {
        if (index >= questions.length) {
            showResults();
            return;
        }

        Question question = questions[index];
        questionLabel.setText(question.getQuestion());

        for (int i = 0; i < optionButtons.length; i++) {
            optionButtons[i].setText(question.getOptions()[i]);
        }

        startTimer();
    }

    private static void startTimer() {
        timeRemaining = TIME_LIMIT;
        updateTimerLabel();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeRemaining--;
                updateTimerLabel();
                if (timeRemaining <= 0) {
                    timer.cancel();
                    submitAnswer(null); 
                }
            }
        }, 1000, 1000);
    }

    private static void updateTimerLabel() {
        SwingUtilities.invokeLater(() -> timerLabel.setText("Time remaining: " + timeRemaining + " seconds"));
    }

    private static void submitAnswer(String selectedOption) {
        timer.cancel();
        Question question = questions[currentQuestionIndex];
        if (selectedOption != null && selectedOption.equals(question.getCorrectAnswer())) {
            score++;
        }
        currentQuestionIndex++;
        loadQuestion(currentQuestionIndex);
    }

    private static void showResults() {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(timerLabel);
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JLabel resultLabel = new JLabel("Quiz Completed. Your score: " + score + "/" + questions.length, SwingConstants.CENTER);
        frame.add(resultLabel, BorderLayout.CENTER);

        frame.setSize(400, 200);
        frame.setVisible(true);
    }

    private static class OptionButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            submitAnswer(button.getText());
        }
    }

    private static class NextButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            submitAnswer(null); 
        }
    }
}
