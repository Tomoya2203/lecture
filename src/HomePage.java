import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HomePage extends JPanel {
    public HomePage(ActionListener viewListener, ActionListener editListener, ActionListener addListener, ActionListener deleteListener) {
        setLayout(new GridLayout(5, 1));
        add(new JLabel("Home Page"));
        JButton homeViewButton = new JButton("View Page");
        JButton homeEditButton = new JButton("Edit Page");
        JButton homeAddButton = new JButton("Add Page");
        JButton homeDeleteButton = new JButton("Delete Page");

        homeViewButton.addActionListener(viewListener);
        homeEditButton.addActionListener(editListener);
        homeAddButton.addActionListener(addListener);
        homeDeleteButton.addActionListener(deleteListener);

        add(homeViewButton);
        add(homeEditButton);
        add(homeAddButton);
        add(homeDeleteButton);
    }
}
