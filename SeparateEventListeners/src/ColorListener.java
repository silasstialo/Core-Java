import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorListener implements ActionListener {
    private final Color backGroundColor;
    private final Component targetComponent;

    /*Instead of coupling ColorListener to a specific panel (JPanel),
     you can pass the target component as a more generic Component (or even Container) type.
      This allows you to reuse the listener with any component, not just a JPanel.*/
    public ColorListener(Color color, Component component){
        this.backGroundColor = color;
        this.targetComponent = component;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        targetComponent.setBackground(backGroundColor);
    }
}
