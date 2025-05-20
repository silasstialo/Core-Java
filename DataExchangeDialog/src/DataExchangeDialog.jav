import javax.swing.*;
import java.awt.*;


public class DataExchangeDialog extends JDialog {
    static Font largeFont = new Font("Arial", Font.BOLD, 35);
    private boolean ok = false;
    private final JTextField usernameTextField;
    private final JTextField passwordTextField;

    public DataExchangeDialog(JFrame owner){
        super(owner, "Login", true);
        setLayout(new GridBagLayout());

        //create a panel to take user input
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());

        //create the username label
        JLabel usernameLabel = new JLabel("User Name: ");
        usernameLabel.setFont(largeFont);
        inputPanel.add(usernameLabel, new GBC(0, GridBagConstraints.RELATIVE, 4, 1).setFill(GridBagConstraints.BOTH).setWeight(100, 100));

        //create the username text field
        usernameTextField = new JTextField(20);
        usernameTextField.setFont(largeFont);
        inputPanel.add(usernameTextField, new GBC(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, GridBagConstraints.REMAINDER, 1).setFill(GridBagConstraints.BOTH).setWeight(100, 100).setInsets(15));

        //Create the password label
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(largeFont);
        inputPanel.add(passwordLabel, new GBC(0, GridBagConstraints.RELATIVE, 4, 1).setFill(GridBagConstraints.BOTH).setWeight(100, 100));

        //create the password text field
        passwordTextField = new JPasswordField(20);
        passwordTextField.setFont(largeFont);
        inputPanel.add(passwordTextField, new GBC(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, GridBagConstraints.REMAINDER, 1).setFill(GridBagConstraints.BOTH).setWeight(100, 100).setInsets(15));

        //create the ok button
        JButton okayButton = new JButton("OK");
        okayButton.setFont(largeFont);
        inputPanel.add(okayButton, new GBC(0, GridBagConstraints.RELATIVE, 3, 2).setFill(GridBagConstraints.BOTH).setWeight(100, 100));

        //add an action listener to the okay button
        okayButton.addActionListener(_ -> {
            String username = usernameTextField.getText().trim();
            String password = passwordTextField.getText().trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Both username and password must be filled!",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                ok = true;
                dispose();
            }
        });

        //create the cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(largeFont);
        inputPanel.add(cancelButton, new GBC(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, GridBagConstraints.REMAINDER, 2).setFill(GridBagConstraints.BOTH).setWeight(100, 100));

        //add an action listener to the cancel button
        cancelButton.addActionListener(_ -> dispose()
        );

        add(inputPanel, new GBC(1, 1, 1, 1).setWeight(100, 100).setFill(GridBagConstraints.BOTH));
        setSize(700, 400);


    }

    //A method to access the username
    public String getUser(){
        return usernameTextField.getText();
    }

    //a method to get the password
    public char[] getPassword(){
        return passwordTextField.getText().toCharArray();
    }


    //a method to display the dialog
    public boolean showDialog(){
        setVisible(true);
        return ok;
    }
}
