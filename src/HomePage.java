import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomePage extends JPanel {
    public HomePage(ActionListener viewListener, ActionListener editListener, ActionListener addListener) {
        setLayout(new GridLayout(4, 1));
        add(new JLabel("Home Page"));
        JButton homeViewButton = new JButton("View Page");
        JButton homeEditButton = new JButton("Edit Page");
        JButton homeAddButton = new JButton("Add Page");

        homeViewButton.addActionListener(viewListener);
        homeEditButton.addActionListener(editListener);
        homeAddButton.addActionListener(addListener);

        add(homeViewButton);
        add(homeEditButton);
        add(homeAddButton);
    }
}
