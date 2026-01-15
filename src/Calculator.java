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
            "0", ".", "more", "="
    };
    String[] rightSymbols = { "÷", "×", "-", "+", "=" };
    String[] topSymbols = { "AC", "+/-", "%" };

    JFrame frame = new JFrame("Calculator");

    // to create display label
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();
    CardLayout cardLayout = new CardLayout();
    JPanel container = new JPanel(cardLayout);

    String A = "0";
    String operator = null;
    String B = null;
    String numC;

    public Calculator() {
        // to set frame
        frame.setSize(bordWidth, bordHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        // frame.setVisible(true);
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
        // previous one
        // frame.add(buttonsPanel);
        // card layout container

        container.add(buttonsPanel, "Basic");
        container.add(new NextPage(this), "MORE");

        frame.add(container, BorderLayout.CENTER);

        // container.add(new NextPage(this), "more");
        // to set buttons
        for (int i = 0; i < buttonValues.length; i++) {
            JButton button = new JButton();
            String buttonvalue = buttonValues[i];
            button.setText(buttonvalue);
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            // to get rid of the focus border
            button.setFocusable(false);
            button.setFocusPainted(false);
            // button.setBorderPainted(false);
            button.setBorder(new LineBorder(customBlack));
            // set colors to buttons
            if (Arrays.asList(topSymbols).contains(buttonvalue)) {
                // set background color to light grey for the button
                button.setBackground(customLightGrey);
                // set text color to black for the text
                button.setForeground(customBlack);
            } else if (Arrays.asList(rightSymbols).contains(buttonvalue)) {
                button.setBackground(customOrange);
                // set text color to white for the text
                button.setForeground(Color.WHITE);

            } else {
                button.setBackground(customDarkGrey);
                button.setForeground(Color.WHITE);
            }

            buttonsPanel.add(button);
            // add action listener to the button means make button clickable
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // button click event
                    JButton button = (JButton) e.getSource();
                    // to identify which button is clicked
                    String buttonValue = button.getText();
                    if (Arrays.asList(rightSymbols).contains(buttonValue)) {// if the clicked button is one of the right
                                                                            // symbols
                        if (buttonValue == "=") {
                            if (A != null) {
                                B = displayLabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);
                                if (operator == "+") {
                                    displayLabel.setText(removeZeroDecimal(numA + numB));
                                } else if (operator == "-") {
                                    displayLabel.setText(removeZeroDecimal(numA - numB));
                                } else if (operator == "×") {
                                    displayLabel.setText(removeZeroDecimal(numA * numB));
                                } else if (operator == "÷") {
                                    displayLabel.setText(removeZeroDecimal(numA / numB));
                                } else if (operator == "%") {
                                    displayLabel.setText(removeZeroDecimal(numA % numB));
                                }
                                clearAll();
                            }

                        } else if ("+-×÷".contains(buttonValue)) {
                            if (operator == null) {
                                A = displayLabel.getText();
                                displayLabel.setText("0");
                                B = "0";
                            }
                            operator = buttonValue;
                        }
                    } else if (Arrays.asList(topSymbols).contains(buttonValue)) {// if the clicked button is one of the
                                                                                 // top symbols
                        if (buttonValue == "AC") {
                            clearAll();
                            displayLabel.setText("0");

                        } else if (buttonValue == "+/-") {
                            // convert the display label text to double
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1;
                            displayLabel.setText(removeZeroDecimal(numDisplay));// remove .0 if the number is integer
                                                                                // using
                                                                                // removeZeroDecimal function

                        } else if (buttonValue == "%") {
                            // double numDisplay = Double.parseDouble(displayLabel.getText());
                            // numDisplay /= 100;
                            // displayLabel.setText(removeZeroDecimal(numDisplay));
                            if (operator == null) {
                                A = displayLabel.getText();
                                displayLabel.setText("0");
                                B = "0";
                            }
                            operator = buttonValue;
                        }
                        // reset everything

                    } else { // if the clicked button is one of the number buttons
                        if (buttonValue == ".") {
                            if (!displayLabel.getText().contains(".")) {
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        } else if ("1234567890".contains(buttonValue)) {
                            if (displayLabel.getText().equals("0")) {
                                // if the display label is 0 then replace it with the clicked number
                                displayLabel.setText(buttonValue);
                            } else {
                                // append the clicked number to the display label
                                displayLabel.setText(displayLabel.getText() + buttonValue);
                            }
                        } else if (buttonValue.equals("more")) {
                            showPage("MORE");

                            // Point p = frame.getLocation();
                            // new NextPage(p);
                            // frame.dispose();

                            // added the sqrt function
                            // if (displayLabel.getText() != "0") {
                            // double numDisplay = Double.parseDouble(displayLabel.getText());
                            // numDisplay = Math.sqrt(numDisplay);
                            // displayLabel.setText(removeZeroDecimal(numDisplay));
                            // }
                        }

                    }

                }
            });
            // placing the frame.setVisible(true); make the system render it properly

        }
        frame.setVisible(true);

    }

    public void showPage(String name) {
        cardLayout.show(container, name);
    }

    void clearAll() {
        A = "0";
        operator = null;
        B = null;
    }

    // to remove .0 from double if the number is integer
    String removeZeroDecimal(double num) {

        if (num % 1 == 0) {
            // convert to integer to remove the .0 value and return string
            return Integer.toString((int) num);
        }
        // if its not whole number remove resend it as string
        numC = Double.toString(num);
        return String.format("%.3f", num);

    }

}
