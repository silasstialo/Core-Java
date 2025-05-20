import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends JFrame {
    public Main(){
        setSize(2000, 1200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        UIManager.put("ToolTip.font", new Font("Arial", Font.PLAIN, 30));


        //crete a file chooser object
        JFileChooser chooser = new JFileChooser();
        chooser.setPreferredSize(new Dimension(1600, 1000));
        chooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        //a button to save a file
        JButton fileSaveButton = new JButton("Save a file");
        fileSaveButton.setToolTipText("Select a folder where the file will be saved");
        Font buttonFont = new Font("Arial", Font.BOLD, 40);
        fileSaveButton.setFont(buttonFont);
        add(fileSaveButton, BorderLayout.SOUTH);
        fileSaveButton.addActionListener(_ ->{
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = chooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION){
                String selectedFolder = chooser.getSelectedFile().getPath();
                System.out.println(selectedFolder);
            }
        });


        //a button to open a file
        JButton fileOpenButton = new JButton("Open A file");
        fileOpenButton.setFont(buttonFont);
        add(fileOpenButton, BorderLayout.NORTH);
        fileOpenButton.addActionListener(_ -> {
            chooser.setFileFilter(new FileNameExtensionFilter("Image Files (*.jpg, *.jpeg, *.png, *.gif)", "jpg", "jpeg", "png", "gif"));
            int result = chooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION){
                String selectedFile = chooser.getSelectedFile().getPath();
                System.out.println(selectedFile);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}