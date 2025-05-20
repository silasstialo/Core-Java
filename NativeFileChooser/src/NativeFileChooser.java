import javax.swing.*;
import java.awt.*;

public class NativeFileChooser extends JFrame {
    private final JLabel imageLabel;

    public NativeFileChooser() {
        // Set up the frame
        setTitle("Native File Chooser");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(2500, 1500);

        // Button to open system file chooser
        JButton selectButton = new JButton("Select Image");
        selectButton.addActionListener(e -> selectImage());

        // Panel to display the image
        JPanel imagePanel = new JPanel();
        imageLabel = new JLabel();
        imagePanel.add(imageLabel);

        // Add components to frame
        add(selectButton, BorderLayout.NORTH);
        add(imagePanel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Center window
        setVisible(true);
    }

    private void selectImage() {
        FileDialog fileDialog = new FileDialog(this, "Select an Image", FileDialog.LOAD);
        fileDialog.setVisible(true); // Open native file chooser

        String selectedFile = fileDialog.getFile();
        if (selectedFile != null) {
            String imagePath = fileDialog.getDirectory() + selectedFile;
            displayImage(imagePath);
        }
    }

    private void displayImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);

        // Resize image
        Image img = icon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(img));

        revalidate(); // Refresh layout
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NativeFileChooser::new);
    }
}
