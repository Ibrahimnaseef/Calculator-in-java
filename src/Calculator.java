import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {
    int bordWidth = 360;
    int bordHeight = 540;
    Color customLightGrey = new Color(212, 212, 210);
    Color customBlack = new Color(28, 28, 28);
    Color customDarkGrey = new Color(80, 80, 80);
    Color customOrange = new Color(255, 149, 0);
    String[] buttonValues = {
            "AC", "+/-", "%", "÷",
            "7", "8", "9", "×",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "√", "="
    };
    String[] rightSymbols = { "÷", "×", "-", "+", "=" };
    String[] topSymbols = { "AC", "+/-", "%" };

    JFrame frame = new JFrame("Calculator");
    // to create display label
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    Calculator() {
        // to set frame
        frame.setSize(bordWidth, bordHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        // to set display label
        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);
        // to set display panel
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);
        // to set buttons panel
        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(customBlack);
        frame.add(buttonsPanel);
        // to set buttons
        for (int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonvalue = buttonValues[i];
            button.setText(buttonvalue);
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            // to get rid of the focus border
            button.setFocusable(false);
            // set colors to buttons
            if (Arrays.asList(topSymbols).contains(buttonvalue)) {
                button.setBackground(customLightGrey);
                button.setForeground(customBlack);
            } else if (Arrays.asList(rightSymbols).contains(buttonvalue)) {
                button.setBackground(customOrange);
                button.setForeground(Color.WHITE);

            } else {
                button.setBackground(customDarkGrey);
                button.setForeground(Color.WHITE);
            }

            buttonsPanel.add(button);
        }
    }

}
