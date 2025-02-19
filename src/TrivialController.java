import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class TrivialController {
    private StartGUI startView;
    private MainBoardGUI mainBoardView;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private List<Question> questions;
    private int currentQuestionIndex;
    private Timer questionTimer;
    private Timer progressBarTimer;
    private SoundPlayer soundPlayer;

    public TrivialController() {
        startView = new StartGUI();
        startView.addStartListener(new StartListener());
        startView.setVisible(true);
        soundPlayer = new SoundPlayer();
    }

    private class StartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String player1Name = startView.getPlayer1Name();
            String player2Name = startView.getPlayer2Name();
            player1 = new Player(player1Name);
            player2 = new Player(player2Name);
            currentPlayer = player1;
            currentQuestionIndex = 0;

            questions = QuestionLoader.loadQuestionsFromXML("C:\\Users\\danie\\IdeaProjects\\Trivial_M07\\src\\Preguntes2025.xml");
            Collections.shuffle(questions); // Shuffle questions

            mainBoardView = new MainBoardGUI(player1Name, player2Name);
            mainBoardView.addNextTurnListener(new NextTurnListener());
            mainBoardView.addNextQuestionListener(new NextQuestionListener());
            mainBoardView.setVisible(true);
            startView.dispose();
            updateTurnLabel();

            // Start playing music
            soundPlayer.play("C:\\Users\\danie\\IdeaProjects\\Trivial_M07\\src\\musica.mp3");
        }
    }

    private class NextQuestionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentQuestionIndex++;
            loadQuestion();
        }
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            List<String> options = question.getOptions();
            int correctOptionIndex = question.getCorrectOption();
            String correctOption = options.get(correctOptionIndex);
            Collections.shuffle(options); // Shuffle options

            // Find the new index of the correct option after shuffling
            int newCorrectOptionIndex = options.indexOf(correctOption);

            QuestionGUI questionView = new QuestionGUI(question.getQuestionText(), options.toArray(new String[0]), newCorrectOptionIndex);
            mainBoardView.getQuestionPanel().removeAll();
            mainBoardView.getQuestionPanel().add(questionView, BorderLayout.CENTER);
            mainBoardView.getQuestionPanel().revalidate();
            mainBoardView.getQuestionPanel().repaint();
            questionView.addOptionListener(new QuestionListener(question, questionView));
            questionView.addNextQuestionListener(new NextQuestionListener());

            // Start the timer for 30 seconds
            if (questionTimer != null) {
                questionTimer.stop();
            }
            questionTimer = new Timer(30000, e -> handleTimeout(questionView));
            questionTimer.setRepeats(false);
            questionTimer.start();

            // Start the progress bar timer
            if (progressBarTimer != null) {
                progressBarTimer.stop();
            }
            progressBarTimer = new Timer(1000, new ActionListener() {
                int timeLeft = 30;

                @Override
                public void actionPerformed(ActionEvent e) {
                    timeLeft--;
                    questionView.updateTimeLabel(timeLeft);
                    if (timeLeft <= 0) {
                        progressBarTimer.stop();
                    }
                }
            });
            progressBarTimer.start();
        } else {
            triggerExplosionAndShowResult();
        }
    }

    private void handleTimeout(QuestionGUI questionView) {
        questionView.setFeedback("¡Tiempo agotado!");
        questionView.showNextQuestionButton();
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(mainBoardView, "¡Tiempo agotado!"));
        switchPlayer();
        updateTurnLabel();
    }

    private class QuestionListener implements ActionListener {
        private Question question;
        private QuestionGUI questionView;

        public QuestionListener(Question question, QuestionGUI questionView) {
            this.question = question;
            this.questionView = questionView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (questionTimer != null) {
                questionTimer.stop();
            }
            if (progressBarTimer != null) {
                progressBarTimer.stop();
            }

            String selectedOption = e.getActionCommand();
            int selectedOptionIndex = -1;
            for (int i = 0; i < questionView.getOptionButtons().length; i++) {
                if (questionView.getOptionButtons()[i].getText().equals(selectedOption)) {
                    selectedOptionIndex = i;
                    break;
                }
            }

            boolean isCorrect = selectedOptionIndex == questionView.getCorrectOptionIndex();
            questionView.highlightSelectedAnswer(selectedOptionIndex, isCorrect);
            if (!isCorrect) {
                questionView.highlightCorrectAnswer(questionView.getCorrectOptionIndex());
                soundPlayer.playSoundEffect("C:\\Users\\danie\\IdeaProjects\\Trivial_M07\\src\\incorrect_sound.wav");
            } else {
                soundPlayer.playSoundEffect("C:\\Users\\danie\\IdeaProjects\\Trivial_M07\\src\\correct_sound.wav");
            }

            if (isCorrect) {
                currentPlayer.incrementScore();
                currentPlayer.incrementPosition();
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(mainBoardView, "¡Correcto!"));
                updatePlayerProgress();
                if (currentPlayer.getScore() >= 10) {
                    triggerExplosionAndShowResult();
                    return;
                }
            } else {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(mainBoardView, "¡Incorrecto!"));
            }

            questionView.showNextQuestionButton();
            questionView.disableOptionButtons(); // Disable option buttons after answering
            switchPlayer();
            updateTurnLabel();
        }
    }

    private void updatePlayerProgress() {
        if (currentPlayer == player1) {
            mainBoardView.updatePlayer1Score(player1.getScore());
        } else {
            mainBoardView.updatePlayer2Score(player2.getScore());
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    private void updateTurnLabel() {
        mainBoardView.updateTurnLabel(currentPlayer.getName());
    }

    private class NextTurnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadQuestion();
        }
    }

    private void triggerExplosionAndShowResult() {
        mainBoardView.getAirplaneRacePanel().showFullScreenExplosion();

        // Ensure the explosion window is displayed
        SwingUtilities.invokeLater(() -> {
            // Timer to wait for 1.5 seconds before showing the result
            Timer explosionTimer = new Timer(1500, e -> showResult());
            explosionTimer.setRepeats(false);
            explosionTimer.start();
        });
    }

    private void showResult() {
        // Stop playing music
        soundPlayer.stop();

        String resultMessage = "¡Juego Terminado!\n";
        if (player1.getScore() > player2.getScore()) {
            resultMessage += player1.getName() + " ¡gana!";
        } else if (player2.getScore() > player1.getScore()) {
            resultMessage += player2.getName() + " ¡gana!";
        } else {
            resultMessage += "¡Empate!";
        }
        ResultGUI resultView = new ResultGUI(resultMessage);
        resultView.setVisible(true);
        mainBoardView.dispose();
    }

    public static void main(String[] args) {
        new TrivialController();
    }
}