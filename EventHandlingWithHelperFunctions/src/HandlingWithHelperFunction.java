import javax.swing.*;
import java.awt.*;

public class HandlingWithHelperFunction extends JFrame {
    JPanel buttonsPanel;
    JPanel colorPanel;
    private static final Font buttonFont = new Font("Roboto Medium", Font.PLAIN, 50);


    public HandlingWithHelperFunction(){
        setLayout(new BorderLayout());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1600, 900);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        colorPanel = new JPanel();


        createButton("Red", Color.RED);
        createButton("Green", Color.GREEN);
        createButton("Blue", Color.BLUE);

        add(colorPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

    }
    //A helper function to create buttons,and add an action listener
    void createButton(String name, Color color){
        JButton button = new JButton(name);
        button.setFont(buttonFont);
        buttonsPanel.add(button);
        button.addActionListener(_ -> colorPanel.setBackground(color));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HandlingWithHelperFunction().setVisible(true));
    }
}
