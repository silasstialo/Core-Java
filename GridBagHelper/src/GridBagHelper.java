import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GridBagHelper extends JFrame {
    static Font largeFont = new Font("Arial", Font.PLAIN, 40);
    static JTextArea textArea;
    static String face = "Arial";
    static int size = 40;
    static int style = Font.PLAIN;
    static Color aristocratPeach = new Color(236, 206, 185);
    static Color grayish =new Color(207, 202, 199);

    public GridBagHelper(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1600, 1000);
        setLayout(new GridBagLayout());



        //set GridBagLayout as the layout manager
        GridBagLayout layout = new GridBagLayout();
        JPanel panel = new JPanel();
        panel.setSize(800, 1000);
        panel.setLayout(layout);
        panel.setBackground(aristocratPeach);



        //The right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        //Create a label for the face
        JLabel faceLabel = new JLabel("Face:");
        faceLabel.setFont(largeFont);


        //create a combo box for selecting the face
        JComboBox<String> faceCombo = createFontFaceComboBox();
        faceCombo.setFont(largeFont);

        faceCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox comboBox = (JComboBox) e.getSource();
                String selected = (String) comboBox.getSelectedItem();
                if (selected != null) {
                    face = selected; // <-- update global variable
                    setFont(face, style, size);
                }
            }
        });


        //create a text area
        textArea = new JTextArea(10, 20);
        textArea.setText("The quick brown fox jumped over the lazy dog");
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setFont(largeFont);
        textArea.setBackground(grayish);


        //label for the size combo box
        JLabel sizeLabel = new JLabel("Size:");
        sizeLabel.setFont(largeFont);


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



        //Create the bold checkbox
        JCheckBox bold = new JCheckBox("Bold");
        bold.setFont(largeFont);


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


        //add the components to the panel
        panel.add(faceLabel, new GBC(GridBagConstraints.RELATIVE, 0));
        panel.add(faceCombo, new GBC(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, GridBagConstraints.REMAINDER, 1).setWeight(100, 100));
        panel.add(sizeLabel, new GBC(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE));
        panel.add(sizeComboBox, new GBC(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, GridBagConstraints.REMAINDER, 1).setFill(GridBagConstraints.HORIZONTAL).setWeight(100, 100));
        panel.add(bold, new GBC(1, GridBagConstraints.RELATIVE, GridBagConstraints.REMAINDER, 3).setFill(GridBagConstraints.BOTH).setWeight(100, 100).setInsets(5));
        panel.add(italic, new GBC(1, GridBagConstraints.RELATIVE, GridBagConstraints.REMAINDER, 3).setFill(GridBagConstraints.BOTH).setWeight(100, 100).setInsets(5));

        rightPanel.add(textArea, BorderLayout.CENTER);

        //add the panel to the frame
        add(panel, new GBC(0, GridBagConstraints.RELATIVE, 1, GridBagConstraints.REMAINDER).setFill(GridBagConstraints.BOTH).setWeight(100, 100));
        add(rightPanel, new GBC(GridBagConstraints.RELATIVE, GridBagConstraints.RELATIVE, GridBagConstraints.REMAINDER, GridBagConstraints.REMAINDER).setFill(GridBagConstraints.BOTH).setWeight(100, 100));
    }

    void setFont(String face, int style, int size){
        textArea.setFont(new Font(face, style, size));
    }

    private JComboBox<String> createFontFaceComboBox() {
        String[] availableFonts = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();

        JComboBox<String> faceCombo = new JComboBox<>(availableFonts);
        faceCombo.setFont(largeFont);

        return faceCombo;
    }



    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new GridBagHelper().setVisible(true));
    }
}
