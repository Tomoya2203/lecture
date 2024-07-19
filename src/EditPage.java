import javax.swing.*;
import java.awt.*;

public class EditPage extends JPanel {
    public EditPage() {
        setLayout(new GridLayout(3, 2));
        add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        add(nameField);
        add(new JLabel("Description:"));
        JTextField descriptionField = new JTextField();
        add(descriptionField);
        JButton saveButton = new JButton("Save");
        add(saveButton);
    }
}
