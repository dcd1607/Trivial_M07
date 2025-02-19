import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainBoardGUI extends JFrame {
    private JLabel player1Label;
    private JLabel player2Label;
    private JProgressBar player1Progress;
    private JProgressBar player2Progress;
    private JLabel turnLabel;
    private JButton nextTurnButton;
    private JButton nextQuestionButton;
    private JPanel racePanel;
    private JPanel questionPanel;
    private AirplaneRacePanel airplaneRacePanel;

    public MainBoardGUI(String player1Name, String player2Name) {
        setTitle("Juego de Trivial");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior para la carrera de aviones
        racePanel = new JPanel();
        racePanel.setLayout(new BoxLayout(racePanel, BoxLayout.Y_AXIS));
        airplaneRacePanel = new AirplaneRacePanel();
        racePanel.add(airplaneRacePanel);

        JPanel player1Panel = new JPanel(new BorderLayout());
        player1Label = new JLabel(player1Name + ": 0 puntos");
        player1Label.setFont(new Font("Arial", Font.BOLD, 24));
        player1Progress = new JProgressBar(0, 10);
        player1Progress.setValue(0);
        player1Progress.setStringPainted(true);
        player1Progress.setFont(new Font("Arial", Font.BOLD, 24));
        player1Panel.add(player1Label, BorderLayout.WEST);
        player1Panel.add(player1Progress, BorderLayout.CENTER);
        racePanel.add(player1Panel);

        JPanel player2Panel = new JPanel(new BorderLayout());
        player2Label = new JLabel(player2Name + ": 0 puntos");
        player2Label.setFont(new Font("Arial", Font.BOLD, 24));
        player2Progress = new JProgressBar(0, 10);
        player2Progress.setValue(0);
        player2Progress.setStringPainted(true);
        player2Progress.setFont(new Font("Arial", Font.BOLD, 24));
        player2Panel.add(player2Label, BorderLayout.WEST);
        player2Panel.add(player2Progress, BorderLayout.CENTER);
        racePanel.add(player2Panel);

        add(racePanel, BorderLayout.NORTH);

        // Panel inferior para las preguntas
        questionPanel = new JPanel(new BorderLayout());
        turnLabel = new JLabel("Turno: Jugador 1");
        turnLabel.setFont(new Font("Arial", Font.BOLD, 24));
        questionPanel.add(turnLabel, BorderLayout.NORTH);

        nextTurnButton = new JButton("Siguiente Turno");
        nextTurnButton.setFont(new Font("Arial", Font.BOLD, 24));
        questionPanel.add(nextTurnButton, BorderLayout.SOUTH);

        nextQuestionButton = new JButton("Siguiente Pregunta");
        nextQuestionButton.setFont(new Font("Arial", Font.BOLD, 24));
        questionPanel.add(nextQuestionButton, BorderLayout.CENTER);

        add(questionPanel, BorderLayout.CENTER);
    }

    public void updatePlayer1Score(int score) {
        player1Label.setText(player1Label.getText().split(":")[0] + ": " + score + " puntos");
        player1Progress.setValue(score);
        airplaneRacePanel.updatePositions(score, player2Progress.getValue());
    }

    public void updatePlayer2Score(int score) {
        player2Label.setText(player2Label.getText().split(":")[0] + ": " + score + " puntos");
        player2Progress.setValue(score);
        airplaneRacePanel.updatePositions(player1Progress.getValue(), score);
    }

    public AirplaneRacePanel getAirplaneRacePanel() {
        return airplaneRacePanel;
    }

    public void updateTurnLabel(String turn) {
        turnLabel.setText("Turno: " + turn);
    }

    public void addNextTurnListener(ActionListener listener) {
        nextTurnButton.addActionListener(listener);
    }

    public void addNextQuestionListener(ActionListener listener) {
        nextQuestionButton.addActionListener(listener);
    }

    public JPanel getQuestionPanel() {
        return questionPanel;
    }
}