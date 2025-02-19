import javax.swing.*;
import java.awt.*;

public class ResultGUI extends JFrame {
    public ResultGUI(String message) {
        setTitle("Juego de Trivial");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Load the explosion GIF
        ImageIcon backgroundIcon = new ImageIcon("C:\\Users\\danie\\IdeaProjects\\Trivial_M07\\src\\explosion.gif");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.setBackground(Color.BLACK); // Set background color to black
        backgroundLabel.setOpaque(true); // Make the background color visible
        setContentPane(backgroundLabel);

        // Create a panel for the result message
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());
        messagePanel.setOpaque(false); // Make the panel transparent

        JLabel resultLabel = new JLabel("<html><div style='text-align: center; color: white; font-size: 36px;'>" + message + "</div></html>");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 36));
        messagePanel.add(resultLabel, BorderLayout.CENTER);

        add(messagePanel, BorderLayout.CENTER);

        // Ensure the window is visible
        setVisible(true);
    }
}