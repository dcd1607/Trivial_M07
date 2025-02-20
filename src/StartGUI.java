import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartGUI extends JFrame {
    private JTextField player1Name;
    private JTextField player2Name;
    private JButton startButton;
    private SoundPlayer soundPlayer;

    public StartGUI(SoundPlayer soundPlayer) {
        this.soundPlayer = soundPlayer;
        setTitle("Juego de Trivial");
        setSize(1920, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        BackgroundPanel backgroundPanel = new BackgroundPanel(new ImageIcon("C:\\Users\\danie\\IdeaProjects\\Trivial_M07\\src\\Imagenes\\fondo_menu.png").getImage());
        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel titleLabel = new JLabel("¡Bienvenido al Juego de Trivial!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.WHITE); // Set the text color to white
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        backgroundPanel.add(titleLabel, gbc);

        JLabel player1Label = new JLabel("Nombre del Jugador 1:");
        player1Label.setFont(new Font("Arial", Font.BOLD, 24));
        player1Label.setForeground(Color.WHITE); // Set the text color to white
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        backgroundPanel.add(player1Label, gbc);

        player1Name = new JTextField(20);
        player1Name.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridx = 1;
        gbc.gridy = 1;
        backgroundPanel.add(player1Name, gbc);

        JLabel player2Label = new JLabel("Nombre del Jugador 2:");
        player2Label.setFont(new Font("Arial", Font.BOLD, 24));
        player2Label.setForeground(Color.WHITE); // Set the text color to white
        gbc.gridx = 0;
        gbc.gridy = 2;
        backgroundPanel.add(player2Label, gbc);

        player2Name = new JTextField(20);
        player2Name.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridx = 1;
        gbc.gridy = 2;
        backgroundPanel.add(player2Name, gbc);

        startButton = new JButton("Comenzar Juego");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        backgroundPanel.add(startButton, gbc);

        add(backgroundPanel, BorderLayout.CENTER);

        soundPlayer.play("src/musica/CancionTurca.mp3");
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

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(Image backgroundImage) {
            this.backgroundImage = backgroundImage;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}