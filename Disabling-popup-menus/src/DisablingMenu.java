import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class DisablingMenu extends JFrame {
    private static JMenuItem saveItem;
    private static JMenuItem saveAsItem;
    static boolean isReadOnly = false;
    static Font largeFont = new Font("Arial", Font.PLAIN, 40);


    public DisablingMenu(){

        //UIManager.put("MenuItem.font", largeFont); // Increase font size
        //UIManager.put("Menu.font", largeFont);     // For the JMenu itself
        //UIManager.put("MenuItem.background", new Color(0x2B2B2B));  // Dark gray for menu items

        // Set the background color for all PopupMenu instances in the application
        //UIManager.put("PopupMenu.background", new Color(0x2B2B2B)); // Dark gray for the popup menu

        UIManager.put("PopupMenuUI", "javax.swing.plaf.basic.BasicPopupMenuUI");


        try {
            // Set the Look and Feel to the system default
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());        } catch (UnsupportedLookAndFeelException | ClassNotFoundException |
                                                                                                 InstantiationException | IllegalAccessException e) {
            System.out.println("Unable to set look and feel");
        }


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
        //Font buttonFont = new Font("Serif", Font.BOLD, 40); // Font name, style, size

        add(popupPanel);


        JPopupMenu popup = getJPopupMenu(exitAction);
        popup.setBackground(new Color(0x2B2B2B)); // Set background color for popup menu
        popup.setOpaque(true); // Make sure it's opaque

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
        saveItem = new JMenuItem("Save");
        saveItem.setForeground(Color.WHITE);
        saveItem.setEnabled(!isReadOnly);
        saveItem.setFont(largeFont);

        saveAsItem = new JMenuItem("Save as");
        saveAsItem.setForeground(Color.WHITE);
        saveAsItem.setEnabled(!isReadOnly);
        saveAsItem.setFont(largeFont);

        JMenuItem menuItem4 = new JMenuItem("Cut");
        menuItem4.setForeground(Color.WHITE);
        menuItem4.setFont(largeFont);

        JMenuItem readOnlyItem = new JMenuItem("Toggle Read Only");
        readOnlyItem.setForeground(Color.WHITE);
        readOnlyItem.setFont(largeFont);

        //add action listener to the read only menu item
        readOnlyItem.addActionListener(_ -> {
            isReadOnly = !isReadOnly; // Toggle the read-only flag
            updateMenuItems();
        });

        JMenuItem menuItem3 = new JMenuItem(exitAction);
        menuItem3.setForeground(Color.WHITE);
        menuItem3.setFont(largeFont);

        popup.add(saveItem);
        popup.add(saveAsItem);
        popup.add(menuItem4);
        popup.add(readOnlyItem);
        popup.addSeparator();  // Add a separator line
        popup.add(menuItem3);


        return popup;
    }


    // Helper method to update the enabled state of menu items based on the read-only flag
    private static void updateMenuItems() {
        // Now this method can access the instance variables
        saveItem.setEnabled(!isReadOnly); // Disable or enable "Save"
        saveAsItem.setEnabled(!isReadOnly); // Disable or enable "Save as"
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DisablingMenu().setVisible(true));
    }
}
