import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class PopupMenu extends JFrame {
    public PopupMenu(){

        Font largeFont = new Font("Arial", Font.PLAIN, 40);
        UIManager.put("MenuItem.font", largeFont); // Increase font size
        UIManager.put("Menu.font", largeFont);     // For the JMenu itself
        UIManager.put("MenuItem.background", new Color(0x2B2B2B));  // Dark gray for menu items


        // Add key binding for Ctrl + X
        // Create an action that exits the application
        Action exitAction = new AbstractAction("Exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);  // Exit the application
            }
        };
        exitAction.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_X);
        exitAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));



        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1600, 900);

        JPanel popupPanel = new JPanel();
        add(popupPanel);


        JPopupMenu popup = getJPopupMenu(exitAction);
        popupPanel.setComponentPopupMenu(popup);

        // ✅ ADDING THE KEY BINDING TO THE PANEL
        InputMap inputMap = popupPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = popupPanel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK), "exit");
        actionMap.put("exit", exitAction);


    }

    private static JPopupMenu getJPopupMenu(Action exitAction) {
        JPopupMenu popup = new JPopupMenu();
        popup.setBackground(new Color(0x2B2B2B));
        popup.setOpaque(true);

        // Add menu items
        JMenuItem menuItem1 = new JMenuItem("Option 1");
        menuItem1.setForeground(Color.WHITE);

        JMenuItem menuItem2 = new JMenuItem("Option 2");
        menuItem2.setForeground(Color.WHITE);

        JMenuItem menuItem3 = new JMenuItem(exitAction);
        menuItem3.setForeground(Color.WHITE);

        popup.add(menuItem1);
        popup.add(menuItem2);
        popup.addSeparator();  // Add a separator line
        popup.add(menuItem3);
        return popup;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PopupMenu().setVisible(true));
    }
}
