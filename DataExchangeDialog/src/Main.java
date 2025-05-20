import javax.swing.*;
import java.awt.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends JFrame{
    public Main() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1600, 1000);
        setTitle("A simple data exchange dialog box");
        setLayout(new BorderLayout());

        //Create a font for the button
        Font largeFont = new Font("Arial", Font.BOLD, 40);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(largeFont);
        //add an action listener to the about button to display the aboutDialog
        loginButton.addActionListener(_ -> {
            DataExchangeDialog dialog = new DataExchangeDialog(this);
            boolean result = dialog.showDialog();
            if(result){
                String userName = dialog.getUser();
                char[] password = dialog.getPassword();
                System.out.println("Username: " + userName);
                System.out.println("password: "+ String.valueOf(password));
            }

            else {
                System.out.println("Login canceled");
            }

        });

        add(loginButton, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}