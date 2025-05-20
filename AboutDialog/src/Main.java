import javax.swing.*;
import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends JFrame{
    public Main() {
        final AboutDialog[] dialog = {null};

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1600, 1000);
        setTitle("A simple dialog box");
        setLayout(new BorderLayout());

        //Create a font for the button
        Font largeFont = new Font("Arial", Font.BOLD, 40);

        JButton aboutButton = new JButton("About");
        aboutButton.setFont(largeFont);
        //add an action listener to the about button to display the aboutDialog
        aboutButton.addActionListener(_ -> {
            if (dialog[0] == null) // first time
                dialog[0] = new AboutDialog(this);
            dialog[0].setVisible(true);
        });

        add(aboutButton, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}