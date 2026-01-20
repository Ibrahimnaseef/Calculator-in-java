import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.lang.Math;
import java.math.BigInteger;

public class NextPage extends JPanel {
    Color customLightGrey = new Color(214, 214, 214);
    Color customBlack = new Color(28, 28, 28);
    Color customDarkGrey = new Color(80, 80, 80);
    Color customOrange = new Color(255, 149, 0);
    String[] buttonValues = {
            "AC", "+/-", "%", "|x|",
            "log", "10^x", "n!", "π",
            "sin", "cos", "tan", "-",
            "√", "x²", "1/x", "+",
            "ln", ".", "Back", "="
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
                    if(calculator.getDisplay().equals("NaN")){
                                calculator.setDisplay("0");
                    }
                    if(Arrays.asList(rightSymbols).contains(buttonValue)){
                        if(buttonValue.equals("=")){
                            if(calculator.getA()!=null){
                                calculator.setB(calculator.getDisplay());
                                double numA=Double.parseDouble(calculator.getA());
                                double numB=Double.parseDouble(calculator.getB());
                                if(calculator.getOperator().equals("%")){
                                    calculator.setDisplay(calculator.removeZeroDecimal(numA % numB));
                                }else if(calculator.getOperator().equals("+")){
                                    calculator.setDisplay(calculator.removeZeroDecimal(numA+numB));
                                }else if(calculator.getOperator().equals("-")){
                                    calculator.setDisplay(calculator.removeZeroDecimal(numA-numB));
                                }
                                calculator.clearAll();

                            }
                        }else if("-+".contains(buttonValue)){
                            if(calculator.getOperator()==null){
                                calculator.setA(calculator.getDisplay());
                                calculator.setDisplay("0");
                                calculator.setB("0");

                            }
                            calculator.setOperator(buttonvalue);
                        }else if(buttonValue.equals("|x|")){
                            double numDisplay=Double.parseDouble(calculator.getDisplay());
                            double result=Math.abs(numDisplay);
                            calculator.setDisplay(calculator.removeZeroDecimal(result));
                        }else if(buttonValue.equals("π")){
                            if(calculator.getDisplay().equals("0")){
                                calculator.setDisplay("3.141");
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
                        }else if(buttonValue.equals("log")){
                            double numDisplay=Double.parseDouble(calculator.getDisplay());
                            if(numDisplay>0){
                                double result=Math.log10(numDisplay);
                                calculator.setDisplay(calculator.removeZeroDecimal(result));
                            }else if(numDisplay<=0){
                                calculator.setDisplay("NaN");
                            }
                        }
                        else if(buttonValue.equals("ln")){
                            double numDisplay=Double.parseDouble(calculator.getDisplay());
                            if(numDisplay>0){
                                double result=Math.log(numDisplay);
                                calculator.setDisplay(calculator.removeZeroDecimal(result));
                            }else if(numDisplay<=0){
                                calculator.setDisplay("NaN");
                            }
                        }else if(buttonValue.equals("10^x")){
                            double num=Double.parseDouble(calculator.getDisplay());
                            double result=Math.pow(10, num);
                            calculator.setDisplay(calculator.removeZeroDecimal(result));
                        }else if(buttonValue.equals("x²")){
                            double num =Double.parseDouble(calculator.getDisplay());
                            double result=Math.pow(num, 2);
                            calculator.setDisplay(calculator.removeZeroDecimal(result));
                        }
                        
                        else if(buttonValue.equals("n!")){
                            double num=Double.parseDouble(calculator.getDisplay());
                            
                            String result=fact(num);
                            calculator.setDisplay(result);
                            
                        }else if(buttonValue.equals("1/x")){
                            double num=Double.parseDouble(calculator.getDisplay());
                            if(num==0){
                                calculator.setDisplay("NaN");
                            }else {
                                double result=1/num;
                                calculator.setDisplay(calculator.removeZeroDecimal(result));
                            }
                            // double result=1/num;
                            // String res=String.valueOf(result);
                            // calculator.setDisplay(res);
                        }else if(buttonValue.equals("√")){
                            double num=Double.parseDouble(calculator.getDisplay());
                            if(num<0){
                                calculator.setDisplay("NaN");
                            }else{
                                double result= Math.sqrt(num);
                                calculator.setDisplay(calculator.removeZeroDecimal(result));
                            }
                        }else if(buttonValue.equals("sin")){
                            double num=Double.parseDouble(calculator.getDisplay());
                            double result=Math.sin(Math.toRadians(num));
                            calculator.setDisplay(calculator.removeZeroDecimal(result));

                        }else if(buttonValue.equals("cos")){
                             double num=Double.parseDouble(calculator.getDisplay());
                            double result=Math.cos(Math.toRadians(num));
                            calculator.setDisplay(calculator.removeZeroDecimal(result));
                        }else if(buttonValue.equals("tan")){
                            
                             double num=Double.parseDouble(calculator.getDisplay());
                             if((num%180)==90){
                                calculator.setDisplay("NaN");
                            }else{
                            double result=Math.tan(Math.toRadians(num));
                            calculator.setDisplay(calculator.removeZeroDecimal(result));
                            }
                        }
                        else if(buttonValue.equals("Back")){
                            calculator.showPage("Basic");
                        }

                    }

                }
            });

        }

        add(buttonPanel, BorderLayout.CENTER);
      
    }
    public String fact(double num){
        int i;
        if(num<0||num%1!=0){
            return "NaN";
        }
        int n=(int)num;
        BigInteger fact=BigInteger.ONE;

        for(i=1;i<=n;i++){
            fact=fact.multiply(BigInteger.valueOf(i));
        }
        return fact.toString();
    }

}