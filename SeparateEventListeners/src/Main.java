import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends JFrame {
    private static final Font buttonFont = new Font("Roboto Medium", Font.PLAIN, 50);

    private static final int DEFAULT_WIDTH = 1600;
    private static final int DEFAULT_HEIGHT = 900;
    private static final JPanel colorPanel = new JPanel();

    public Main(){
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a custom JLabel for the message with the desired font
        JLabel messageLabel = new JLabel("Are you sure you want to exit?");
        messageLabel.setFont(buttonFont);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        UIManager.put("OptionPane.buttonFont", buttonFont);


        //add a listener to listen to the close event and act accordingly
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(Main.this,
                        messageLabel, //message displayed in the options pane
                        "Confirm exit", //options pane title
                        JOptionPane.YES_NO_OPTION, //option type doesn't matter here
                        JOptionPane.QUESTION_MESSAGE, //message type is a question
                        null, //no icon
                        new String[]{"Exit", "Cancel"}, //custom button labels
                        "Cancel"); //default option

                //handle the user's response
                if(confirm == 0){
                    System.exit(0);
                }


            }


        });

        colorPanel.setBackground(Color.WHITE);

        //create the buttons
        JButton redButton = new JButton("Red");
        redButton.setFont(buttonFont);
        redButton.addActionListener(new ColorListener(Color.RED, colorPanel));

        JButton greenButton = new JButton("Green");
        greenButton.setFont(buttonFont);
        greenButton.addActionListener(new ColorListener(Color.GREEN, colorPanel));

        JButton blueButton = new JButton("Blue");
        blueButton.setFont(buttonFont);
        blueButton.addActionListener(new ColorListener(Color.BLUE, colorPanel));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.add(redButton);
        buttonsPanel.add(greenButton);
        buttonsPanel.add(blueButton);

        add(colorPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);


    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}