import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridBagLayoutTest extends JFrame {
    static Font largeFont = new Font("Arial", Font.PLAIN, 40);
    static JTextArea textArea;
    static String face = "Arial";
    static int size = 40;
    static int style = Font.PLAIN;

    public GridBagLayoutTest(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1600, 1000);
        setLayout(new GridBagLayout());



        //set GridBagLayout as the layout manager
        GridBagLayout layout = new GridBagLayout();
        JPanel panel = new JPanel();
        panel.setSize(800, 1000);
        panel.setLayout(layout);
        panel.setBackground(Color.GRAY);

        //constraints for the left panel
        GridBagConstraints leftPanelConstraints = new GridBagConstraints();
        leftPanelConstraints.gridx = 0;
        leftPanelConstraints.gridy = GridBagConstraints.RELATIVE;
        leftPanelConstraints.gridwidth = 1;
        leftPanelConstraints.gridheight = GridBagConstraints.REMAINDER;
        leftPanelConstraints.fill = GridBagConstraints.BOTH;
        leftPanelConstraints.weightx = 100;
        leftPanelConstraints.weighty = 100;


        //The right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        //constraints for the right panel;
        GridBagConstraints rightPanelConstraints = new GridBagConstraints();
        rightPanelConstraints.gridx = GridBagConstraints.RELATIVE;
        rightPanelConstraints.gridy = GridBagConstraints.RELATIVE;
        rightPanelConstraints.gridwidth = GridBagConstraints.REMAINDER;
        rightPanelConstraints.gridheight = GridBagConstraints.REMAINDER;
        rightPanelConstraints.fill = GridBagConstraints.BOTH;
        rightPanelConstraints.weighty = 100;
        rightPanelConstraints.weightx = 100;

        //Create a label for the face
        JLabel faceLabel = new JLabel("Face:");
        faceLabel.setFont(largeFont);

        //constraints for the label
        GridBagConstraints faceConstraints = new GridBagConstraints();
        faceConstraints.gridx = GridBagConstraints.RELATIVE;
        faceConstraints.gridy = 0;

        //create a combo box for selecting the face
        String[] availableFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();//This will return an array of all available fonts
        JComboBox<String> faceCombo = new JComboBox<>(availableFonts);
        faceCombo.setFont(largeFont);

        faceCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                String selected = (String) comboBox.getSelectedItem();
                setFont(selected, style, size);
            }
        });

        //constraints for the face combo box
        GridBagConstraints faceComboConstraints = new GridBagConstraints();
        faceComboConstraints.gridx = GridBagConstraints.RELATIVE;
        faceComboConstraints.gridy = GridBagConstraints.RELATIVE;
        faceComboConstraints.gridwidth = GridBagConstraints.REMAINDER;
        faceComboConstraints.weighty = 100;

        //create a text area
        textArea = new JTextArea(10, 20);
        textArea.setText("The quick brown fox jumped over the lazy dog");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setFont(largeFont);


        //label for the size combo box
        JLabel sizeLabel = new JLabel("Size:");
        sizeLabel.setFont(largeFont);


        //constraints for the size label
        GridBagConstraints sizeLabelConstraints = new GridBagConstraints();
        sizeLabelConstraints.gridx = GridBagConstraints.RELATIVE;
        sizeLabelConstraints.gridy = GridBagConstraints.RELATIVE;

        //create a combo box for the size
        JComboBox<Integer> sizeComboBox = new JComboBox<>(new Integer[] { 8, 10, 12, 15, 18, 24, 36, 48 , 50, 55, 58, 60, 63});
        sizeComboBox.setFont(largeFont);
        sizeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                Integer selected = (Integer) comboBox.getSelectedItem();
                assert selected != null;
                setFont(face, style, selected);
            }
        });


        // constraints for the size combo box
        GridBagConstraints sizeComboConstraints = new GridBagConstraints();
        sizeComboConstraints.gridy = GridBagConstraints.RELATIVE;
        sizeComboConstraints.gridx = GridBagConstraints.RELATIVE;
        sizeComboConstraints.gridwidth = GridBagConstraints.REMAINDER;
        sizeComboConstraints.fill = GridBagConstraints.HORIZONTAL;
        sizeComboConstraints.weighty = 100;


        //Create the bold checkbox
        JCheckBox bold = new JCheckBox("Bold");
        bold.setFont(largeFont);

        //constraints for the bold checkbox
        GridBagConstraints boldConstraints = new GridBagConstraints();
        boldConstraints.gridy = GridBagConstraints.RELATIVE;
        boldConstraints.gridx = 1;
        boldConstraints.gridwidth = GridBagConstraints.REMAINDER;
        boldConstraints.gridheight = 3;
        boldConstraints.fill = GridBagConstraints.BOTH;
        boldConstraints.weighty = 100;
        boldConstraints.insets = new Insets(5, 5, 5, 5);


        //Create the italic checkbox
        JCheckBox italic = new JCheckBox("Italic");
        italic.setFont(largeFont);


        // Common action for both checkboxes
        ActionListener styleUpdater = e -> {
            int fontStyle = Font.PLAIN;
            if (bold.isSelected() && italic.isSelected()) {
                fontStyle = Font.BOLD | Font.ITALIC;
            } else if (bold.isSelected()) {
                fontStyle = Font.BOLD;
            } else if (italic.isSelected()) {
                fontStyle = Font.ITALIC;
            }
            style = fontStyle; // Update the global style
            setFont(face, style, size);
        };

        bold.addActionListener(styleUpdater);
        italic.addActionListener(styleUpdater);


        //constraints for the bold checkbox
        GridBagConstraints italicConstraints = new GridBagConstraints();
        italicConstraints.gridy = GridBagConstraints.RELATIVE;
        italicConstraints.gridx = 1;
        italicConstraints.gridwidth = GridBagConstraints.REMAINDER;
        italicConstraints.gridheight = 3;
        italicConstraints.fill = GridBagConstraints.BOTH;
        italicConstraints.weighty = 100;
        italicConstraints.insets = new Insets(5, 5, 5, 5);



        //add the components to the panel
        panel.add(faceLabel, faceConstraints);
        panel.add(faceCombo, faceComboConstraints);
        panel.add(sizeLabel, sizeLabelConstraints);
        panel.add(sizeComboBox, sizeComboConstraints);
        panel.add(bold, boldConstraints);
        panel.add(italic, italicConstraints);

        rightPanel.add(textArea, BorderLayout.CENTER);

        //add the panel to the frame
        add(panel, leftPanelConstraints);
        add(rightPanel, rightPanelConstraints);
    }

    void setFont(String face, int style, int size){
        textArea.setFont(new Font(face, style, size));
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new GridBagLayoutTest().setVisible(true));
    }
}
