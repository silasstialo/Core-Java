import javax.swing.*;
import java.awt.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends JFrame {
    public Main(){
        setSize(1600, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Font largeFont = new Font("Arial", Font.PLAIN, 40);
        setLayout(new BorderLayout());

        //a button to invoke the color chooser
        JButton colorChooserButton = new JButton("Select a Color");
        colorChooserButton.setFont(largeFont);

        colorChooserButton.addActionListener(_ -> {
            //display a color chooser
            //Color chosenColor = JColorChooser.showDialog(this, "Choose a color", Color.BLACK);
            JColorChooser chooser = new JColorChooser();
            JDialog dialog = JColorChooser.createDialog(
                    this,
                    "Background color",
                    false,
                    chooser,
                    null,
                    null
            );
            chooser.getSelectionModel().addChangeListener(_ -> {
                getContentPane().setBackground(chooser.getColor());
                System.out.println(chooser.getColor());
            });
            dialog.setVisible(true);
        });

        add(colorChooserButton, BorderLayout.SOUTH);

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));

        }
}