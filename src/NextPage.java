import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class NextPage extends JPanel {
    Color customLightGrey = new Color(212, 212, 210);
    Color customBlack = new Color(28, 28, 28);
    Color customDarkGrey = new Color(80, 80, 80);
    Color customOrange = new Color(255, 149, 0);
    String[] buttonValues = {
            "AC", "+/-", "%", "|x|",
            "log", "10^{x}", "n!", "π",
            "sin", "cos", "tan", "-",
            "√", "x^2", "1/x", "+",
            "ln", ".", "BACK", "="
    };
    String[] rightSymbols = { "|x|", "π", "-", "+", "=" };
    String[] topSymbols = { "AC", "+/-", "%" };
   // JLabel displayLabel = new JLabel();
    JPanel buttonPanel = new JPanel();
    String numC;

    public NextPage(Calculator calculator) {
        setLayout(new BorderLayout());

        buttonPanel.setLayout(new GridLayout(5, 4));
        buttonPanel.setBackground(customBlack);
        

        for (int i = 0; i < buttonValues.length; i++) {

          //  String currentValue = calculator.getDisplay();
            JButton button = new JButton();
            String buttonvalue = buttonValues[i];
            button.setText(buttonvalue);
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setFocusable(false);
            button.setFocusPainted(false);
            button.setBorder(new LineBorder(customBlack));
            if(Arrays.asList(topSymbols).contains(buttonvalue)){
                button.setBackground(customLightGrey);
                button.setForeground(customBlack);
            }else if (Arrays.asList(rightSymbols).contains(buttonvalue)){
                button.setBackground(customOrange);
                button.setForeground(Color.WHITE);
            }else{
            button.setBackground(customDarkGrey);
            button.setForeground(Color.WHITE);
            
            }
            buttonPanel.add(button);
            button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JButton button=(JButton) e.getSource();
                    

                    
                    String buttonValue=button.getText();
                    if(Arrays.asList(rightSymbols).contains(buttonValue)){
                        if(buttonValue.equals("=")){
                            if(calculator.getA()!=null){
                                calculator.setB(calculator.getDisplay());
                                double numA=Double.parseDouble(calculator.getA());
                                double numB=Double.parseDouble(calculator.getB());
                                if(calculator.getOperator().equals("%")){
                                    calculator.setDisplay(calculator.removeZeroDecimal(numA % numB));
                                }
                                calculator.clearAll();

                            }
                        }

                    }else if(Arrays.asList(topSymbols).contains(buttonValue)){
                        if(buttonValue.equals("AC")){
                            calculator.clearAll();
                            calculator.setDisplay("0");
                        }else if (buttonValue.equals("+/-")){
                            double numDisplay=Double.parseDouble(calculator.getDisplay());
                            numDisplay *= -1;
                            calculator.setDisplay(calculator.removeZeroDecimal(numDisplay));

                        }else if(buttonValue.equals("%")){
                            if(calculator.getOperator()==null){
                                calculator.setA(calculator.getDisplay());
                                calculator.setDisplay("0");
                                calculator.setB("0");
                            }
                            calculator.setOperator(buttonvalue);
                        }
                    }else{
                        if (buttonValue.equals(".")){
                            if(!calculator.getDisplay().contains(".")){
                                calculator.setDisplay(calculator.getDisplay()+buttonValue);
                            }
                        }
                        else if(buttonValue.equals("BACK")){
                            calculator.showPage("Basic");
                        }

                    }

                }
            });

        }

        add(buttonPanel, BorderLayout.CENTER);

        // JLabel label = new JLabel("More feature", JLabel.CENTER);
        // label.setFont(new Font("Arial", Font.BOLD, 24));

        // JButton backButton = new JButton("Back");
        // backButton.addActionListener(e -> calculator.showPage("Basic"));

        // add(label, BorderLayout.CENTER);
        // add(backButton, BorderLayout.SOUTH);

        // setVisible(true);
      
    }

}