import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AirplaneRacePanel extends JPanel {
    private ImageIcon airplaneIcon1;
    private ImageIcon airplaneIcon2;
    private ImageIcon airportIcon;
    private JLabel airplaneLabel1;
    private JLabel airplaneLabel2;
    private JLabel airportLabel1;
    private JLabel airportLabel2;

    public AirplaneRacePanel() {
        setLayout(null);
        setPreferredSize(new Dimension(800, 200));
        setBackground(Color.WHITE);

        airplaneIcon1 = new ImageIcon(scaleImage("C:\\Users\\danie\\IdeaProjects\\Trivial_M07\\src\\airplane1.gif", 50, 50));
        airplaneIcon2 = new ImageIcon(scaleImage("C:\\Users\\danie\\IdeaProjects\\Trivial_M07\\src\\airplane2.gif", 50, 50));
        airportIcon = new ImageIcon(scaleImage("C:\\Users\\danie\\IdeaProjects\\Trivial_M07\\src\\airport.png", 100, 100));

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

    private Image scaleImage(String filePath, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(new File(filePath));
            Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return scaledImg;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelWidth = getWidth();
        int airportX = panelWidth - airportIcon.getIconWidth() * 2;
        airportLabel1.setBounds(airportX, 50, airportIcon.getIconWidth(), airportIcon.getIconHeight());
        airportLabel2.setBounds(airportX + airportIcon.getIconWidth(), 50, airportIcon.getIconWidth(), airportIcon.getIconHeight());
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

    public void showExplosionEffects() {
        // Implement explosion effects here
        // For example, you can use a timer to display explosion images at random positions
    }
}