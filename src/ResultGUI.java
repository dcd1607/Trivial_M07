import javax.swing.*;
import java.awt.*;

public class ResultGUI extends JFrame {
    public ResultGUI(String message) {
        setTitle("Juego de Trivial");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel resultLabel = new JLabel(message);
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(resultLabel, BorderLayout.CENTER);

        // Asegúrate de que la ventana esté visible
        setVisible(true);
    }
}
