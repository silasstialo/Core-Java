import javax.swing.*;
import java.awt.*;

public class LookAndFeel extends JFrame {
    JPanel buttonsPanel;
    JPanel colorPanel;
    private static final Font buttonFont = new Font("Roboto Medium", Font.PLAIN, 50);

    public LookAndFeel(){
        setLayout(new BorderLayout());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1600, 900);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        colorPanel = new JPanel();

        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : infos) {
            createButton(info.getName(), info.getClassName());
        }

        add(colorPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

    }

    void createButton(String name, String className){
        JButton button = new JButton(name);
        button.setFont(buttonFont);
        buttonsPanel.add(button);
        button.addActionListener(_ -> {
            try {
                UIManager.setLookAndFeel(className);
                SwingUtilities.updateComponentTreeUI(this);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LookAndFeel().setVisible(true));
    }
}
