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

    JFrame frame = new JFrame("Calculator");
    JLabel displayLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    Calculator() {

        frame.setSize(bordWidth, bordHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }

}
