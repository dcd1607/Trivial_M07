import javax.swing.*;
import java.awt.*;

public class AirplaneRacePanel extends JPanel {
    private ImageIcon airplaneIcon1;
    private ImageIcon airplaneIcon2;
    private ImageIcon airportIcon;
    private ImageIcon backgroundIcon;
    private JLabel airplaneLabel1;
    private JLabel airplaneLabel2;
    private JLabel airportLabel1;
    private JLabel airportLabel2;

    public AirplaneRacePanel() {
        setLayout(null);
        setPreferredSize(new Dimension(800, 200));
        setBackground(Color.WHITE);

        airplaneIcon1 = new ImageIcon("C:\\Users\\danie\\IdeaProjects\\Trivial_M07\\src\\Imagenes\\airplane1.gif");
        airplaneIcon2 = new ImageIcon("C:\\Users\\danie\\IdeaProjects\\Trivial_M07\\src\\Imagenes\\airplane2.gif");
        airportIcon = resizeIcon(new ImageIcon("C:\\Users\\danie\\IdeaProjects\\Trivial_M07\\src\\Imagenes\\airport.png"), 150, 150);
        backgroundIcon = new ImageIcon("C:\\Users\\danie\\IdeaProjects\\Trivial_M07\\src\\Imagenes\\nuves.gif");

        airplaneLabel1 = new JLabel(airplaneIcon1);
        airplaneLabel2 = new JLabel(airplaneIcon2);
        airportLabel1 = new JLabel(airportIcon);
        airportLabel2 = new JLabel(airportIcon);

        add(airplaneLabel1);
        add(airplaneLabel2);
        add(airportLabel1);
        add(airportLabel2);

        // Initial positions
        airplaneLabel1.setBounds(0, 50, airplaneIcon1.getIconWidth(), airplaneIcon1.getIconHeight());
        airplaneLabel2.setBounds(0, 100, airplaneIcon2.getIconWidth(), airplaneIcon2.getIconHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);

        int panelWidth = getWidth();
        int airportX = panelWidth - airportIcon.getIconWidth() * 2;
        int airportY = getHeight() - airportIcon.getIconHeight();
        airportLabel1.setBounds(airportX, airportY, airportIcon.getIconWidth(), airportIcon.getIconHeight());
        airportLabel2.setBounds(airportX + airportIcon.getIconWidth(), airportY, airportIcon.getIconWidth(), airportIcon.getIconHeight());
    }

    public void updatePositions(int score1, int score2) {
        int panelWidth = getWidth();
        int maxPosition = panelWidth - airplaneIcon1.getIconWidth() - airportIcon.getIconWidth() * 2;
        int position1 = (int) ((score1 / 10.0) * maxPosition);
        int position2 = (int) ((score2 / 10.0) * maxPosition);

        airplaneLabel1.setLocation(position1, 50);
        airplaneLabel2.setLocation(position2, 100);
        repaint();
    }

    public void showFullScreenExplosion() {
        // Create a borderless window for the explosion
        JWindow explosionWindow = new JWindow();
        explosionWindow.setLayout(new BorderLayout());

        // Load the explosion image
        ImageIcon explosionIcon = new ImageIcon("C:\\Users\\danie\\IdeaProjects\\Trivial_M07\\src\\Imagenes\\explosion.gif");
        JLabel explosionLabel = new JLabel(explosionIcon);
        explosionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the image to the window
        explosionWindow.add(explosionLabel, BorderLayout.CENTER);

        // Set the window to cover the entire screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        explosionWindow.setBounds(0, 0, screenSize.width, screenSize.height);
        explosionWindow.setVisible(true);

        // Timer to close the explosion after 1.5 seconds
        Timer timer = new Timer(1500, e -> explosionWindow.dispose());
        timer.setRepeats(false);
        timer.start();
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
}