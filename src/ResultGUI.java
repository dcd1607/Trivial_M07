import javax.swing.*;
import java.awt.*;

public class ResultGUI extends JFrame {
    public ResultGUI(String message) {
        setTitle("Juego de Trivial");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        ImageIcon backgroundIcon = new ImageIcon("src/Imagenes/explosion.gif");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BorderLayout());
        backgroundLabel.setBackground(Color.BLACK);
        backgroundLabel.setOpaque(true);
        setContentPane(backgroundLabel);


        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new BorderLayout());
        messagePanel.setOpaque(false);

        JLabel resultLabel = new JLabel("<html><div style='text-align: center; color: white; font-size: 36px;'>" + message + "</div></html>");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 36));
        messagePanel.add(resultLabel, BorderLayout.CENTER);

        add(messagePanel, BorderLayout.CENTER);


        setVisible(true);
    }
}