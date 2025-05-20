import javax.swing.*;
import java.awt.*;

public class ToolBar extends JFrame {
    static JPanel colorPanel = new JPanel();
    static Font largeFont = new Font("Arial", Font.PLAIN, 40);
    static Font toolTipFont = new Font("Arial", Font.PLAIN, 30);



    public ToolBar(){
        UIManager.put("ToolTip.font", toolTipFont);


        setSize(1600, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        colorPanel.setBackground(Color.gray);
        colorPanel.setLayout(new BorderLayout());

        JToolBar bar = new JToolBar(SwingConstants.HORIZONTAL);
        bar.setFloatable(false); // Prevent dragging the toolbar

        // A button to show the toolbar if it is hidden
        JButton button = new JButton("Change Color");
        button.setFont(largeFont);
        button.addActionListener(_ -> bar.setVisible(true));

        // Create buttons with different colors
        JButton blueButton = createButton(Color.BLUE, "Blue", "Change the Background Color to Blue");
        JButton yellowButton = createButton(Color.YELLOW, "Yellow", "Change the Background Color To Yellow");
        JButton redButton = createButton(Color.RED, "Red", "Change the Background Color To Red");

        // Close button to hide toolbar
        JButton closeButton = new JButton("X");
        closeButton.setForeground(Color.white);
        closeButton.setBackground(Color.GRAY);
        closeButton.setFont(largeFont);
        closeButton.setToolTipText("Close");
        closeButton.addActionListener(_ -> bar.setVisible(false));

        // Add buttons to toolbar
        bar.add(blueButton);
        bar.add(yellowButton);
        bar.add(redButton);
        bar.addSeparator();
        bar.add(closeButton);

        colorPanel.add(bar, BorderLayout.NORTH);
        colorPanel.add(button, BorderLayout.SOUTH);
        add(colorPanel);
    }

    private static JButton createButton(Color background, String colorName, String toolTipText){
        JButton button = new JButton();
        button.setText(null);
        button.setBackground(background);
        button.setText(colorName);
        button.setFont(largeFont);
        button.setToolTipText(toolTipText);
        button.setForeground(Color.BLACK);


        button.addActionListener(_ -> colorPanel.setBackground(background));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToolBar().setVisible(true));
    }
}
