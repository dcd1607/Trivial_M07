// QuestionGUI.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class QuestionGUI extends JPanel {
    private JLabel questionLabel;
    private JButton[] optionButtons;
    private JLabel feedbackLabel;
    private JLabel timeLabel;
    private JButton nextQuestionButton;
    private int correctOptionIndex;
    private ImageIcon backgroundImage;

    public QuestionGUI(String questionText, String[] options, int correctOptionIndex) {
        this.correctOptionIndex = correctOptionIndex;
        this.backgroundImage = new ImageIcon("src/Imagenes/fondo.png");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        timeLabel = new JLabel("30 segundos restantes");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        timeLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(timeLabel, gbc);

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new GridBagLayout());
        questionPanel.setOpaque(false);
        GridBagConstraints panelGbc = new GridBagConstraints();
        panelGbc.insets = new Insets(10, 10, 10, 10);
        panelGbc.fill = GridBagConstraints.HORIZONTAL;

        questionLabel = new JLabel("<html><div style='text-align: center; color: white;'>" + questionText + "</div></html>");
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panelGbc.gridx = 0;
        panelGbc.gridy = 1;
        panelGbc.gridwidth = 2;
        questionPanel.add(questionLabel, panelGbc);

        JPanel optionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        optionsPanel.setOpaque(false);
        optionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JButton(options[i]);
            optionButtons[i].setFont(new Font("Arial", Font.BOLD, 18));
            optionsPanel.add(optionButtons[i]);
        }
        panelGbc.gridy = 2;
        panelGbc.gridwidth = 2;
        questionPanel.add(optionsPanel, panelGbc);

        feedbackLabel = new JLabel("");
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panelGbc.gridy = 3;
        panelGbc.gridwidth = 2;
        questionPanel.add(feedbackLabel, panelGbc);

        nextQuestionButton = new JButton("Siguiente Pregunta");
        nextQuestionButton.setFont(new Font("Arial", Font.BOLD, 18));
        nextQuestionButton.setVisible(false);
        panelGbc.gridy = 4;
        panelGbc.gridwidth = 2;
        questionPanel.add(nextQuestionButton, panelGbc);

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(questionPanel, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
    }

    public void setFeedback(String feedback) {
        feedbackLabel.setText(feedback);
    }

    public void addOptionListener(ActionListener listener) {
        for (JButton button : optionButtons) {
            button.addActionListener(listener);
        }
    }

    public void addNextQuestionListener(ActionListener listener) {
        nextQuestionButton.addActionListener(listener);
    }

    public void updateTimeLabel(int secondsLeft) {
        timeLabel.setText(secondsLeft + " segundos restantes");
    }

    public void showNextQuestionButton() {
        nextQuestionButton.setVisible(true);
    }

    public void disableOptionButtons() {
        for (JButton button : optionButtons) {
            button.setEnabled(false);
        }
    }

    public void highlightCorrectAnswer(int correctOptionIndex) {
        optionButtons[correctOptionIndex].setBackground(Color.GREEN);
    }

    public void highlightSelectedAnswer(int selectedOptionIndex, boolean isCorrect) {
        if (isCorrect) {
            optionButtons[selectedOptionIndex].setBackground(Color.GREEN);
        } else {
            optionButtons[selectedOptionIndex].setBackground(Color.RED);
        }
    }

    public JButton[] getOptionButtons() {
        return optionButtons;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}