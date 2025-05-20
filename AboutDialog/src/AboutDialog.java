import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog {
    public AboutDialog(JFrame owner){
        super(owner, "About DialogTest", true);
        add(new JLabel(
                        "<html><h1><i>Core Java</i></h1><hr>By Cay Horstmann</html>"),
                BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton button = new JButton("OK");
        button.addActionListener(_ -> setVisible(false));

        //add the button to the button panel
        buttonPanel.add(button);

        //add the panel to the dialog
        add(buttonPanel, BorderLayout.SOUTH);
        setSize(250, 250);
    }
}
