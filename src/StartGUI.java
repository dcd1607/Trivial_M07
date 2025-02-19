import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartGUI extends JFrame {
    private JTextField player1Name;
    private JTextField player2Name;
    private JButton startButton;

    public StartGUI() {
        setTitle("Juego de Trivial");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("¡Bienvenido al Juego de Trivial!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        JLabel player1Label = new JLabel("Nombre del Jugador 1:");
        player1Label.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(player1Label, gbc);

        player1Name = new JTextField(20);
        player1Name.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(player1Name, gbc);

        JLabel player2Label = new JLabel("Nombre del Jugador 2:");
        player2Label.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(player2Label, gbc);

        player2Name = new JTextField(20);
        player2Name.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(player2Name, gbc);

        startButton = new JButton("Comenzar Juego");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(startButton, gbc);
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