import javax.swing.*;
import java.awt.event.ActionListener;

public class StartGUI extends JFrame {
    private JTextField player1Name;
    private JTextField player2Name;
    private JButton startButton;

    public StartGUI() {
        setTitle("Juego de Trivial");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("¡Bienvenido al Juego de Trivial!");
        titleLabel.setFont(new java.awt.Font("Arial", 1, 24));
        add(titleLabel);

        JLabel player1Label = new JLabel("Nombre del Jugador 1:");
        player1Label.setFont(new java.awt.Font("Arial", 1, 18));
        add(player1Label);
        player1Name = new JTextField(20);
        player1Name.setFont(new java.awt.Font("Arial", 1, 18));
        add(player1Name);

        JLabel player2Label = new JLabel("Nombre del Jugador 2:");
        player2Label.setFont(new java.awt.Font("Arial", 1, 18));
        add(player2Label);
        player2Name = new JTextField(20);
        player2Name.setFont(new java.awt.Font("Arial", 1, 18));
        add(player2Name);

        startButton = new JButton("Comenzar Juego");
        startButton.setFont(new java.awt.Font("Arial", 1, 18));
        add(startButton);
    }

    public void addStartListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }

    public String getPlayer1Name() {
        return player1Name.getText();
    }

    public String getPlayer2Name() {
        return player2Name.getText();
    }
}
