import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class NextPage extends JPanel {

    public NextPage(Calculator calculator) {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("More feature", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> calculator.showPage("Basic"));

        add(label, BorderLayout.CENTER);
        add(backButton, BorderLayout.NORTH);

    }

}