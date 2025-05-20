import javax.swing.*;

public class MouseFrame extends JFrame {
    public MouseFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new MouseComponent());
        pack();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MouseFrame().setVisible(true));
    }

}
