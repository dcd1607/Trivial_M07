import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class QuestionGUI extends JPanel {
    private JLabel questionLabel;
    private JButton[] optionButtons;
    private JLabel feedbackLabel;

    public QuestionGUI(String questionText, String[] options) {
        setLayout(new BorderLayout());

        questionLabel = new JLabel(questionText);
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setFont(new java.awt.Font("Arial", 1, 24));
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(2, 2));
        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton(options[i]);
            optionButtons[i].setFont(new java.awt.Font("Arial", 1, 18));
            optionsPanel.add(optionButtons[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        feedbackLabel = new JLabel("");
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        feedbackLabel.setFont(new java.awt.Font("Arial", 1, 18));
        add(feedbackLabel, BorderLayout.SOUTH);
    }

    public void setFeedback(String feedback) {
        feedbackLabel.setText(feedback);
    }

    public void addOptionListener(ActionListener listener) {
        for (JButton button : optionButtons) {
            button.addActionListener(listener);
        }
    }
}
