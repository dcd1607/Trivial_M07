import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public TrivialController() {
        startView = new StartGUI();
        startView.addStartListener(new StartListener());
        startView.setVisible(true);
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

            mainBoardView = new MainBoardGUI(player1Name, player2Name);
            mainBoardView.addNextTurnListener(new NextTurnListener());
            mainBoardView.setVisible(true);
            startView.dispose();
            loadQuestion();
        }
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.size()) {
            Question question = questions.get(currentQuestionIndex);
            QuestionGUI questionView = new QuestionGUI(question.getQuestionText(), question.getOptions().toArray(new String[0]));
            mainBoardView.getQuestionPanel().removeAll();
            mainBoardView.getQuestionPanel().add(questionView);
            mainBoardView.getQuestionPanel().revalidate();
            mainBoardView.getQuestionPanel().repaint();
            questionView.addOptionListener(new QuestionListener(question));
        } else {
            showResult();
        }
    }

    private class QuestionListener implements ActionListener {
        private Question question;

        public QuestionListener(Question question) {
            this.question = question;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedOption = e.getActionCommand();
            if (selectedOption.equals(question.getOptions().get(question.getCorrectOption()))) {
                currentPlayer.incrementScore();
                currentPlayer.incrementPosition();
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(mainBoardView, "¡Correcto!"));
            } else {
                SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(mainBoardView, "¡Incorrecto!"));
            }
            currentQuestionIndex++;
            switchPlayer();
            mainBoardView.updatePlayer1Score(player1.getScore());
            mainBoardView.updatePlayer2Score(player2.getScore());
            mainBoardView.updateTurnLabel(currentPlayer.getName());
            mainBoardView.getAirplaneRacePanel().updatePositions(player1.getScore(), player2.getScore());
            if (player1.getScore() >= 10 || player2.getScore() >= 10) {
                showResultWithDelay();
            } else {
                loadQuestion();
            }
        }
    }

    private void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    private class NextTurnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadQuestion();
        }
    }

    private void showResultWithDelay() {
        mainBoardView.getAirplaneRacePanel().showExplosionEffects();
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showResult();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void showResult() {
        String resultMessage = "¡Juego Terminado!\n";
        if (player1.getScore() >= 10) {
            resultMessage += player1.getName() + " ¡gana!";
        } else if (player2.getScore() >= 10) {
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