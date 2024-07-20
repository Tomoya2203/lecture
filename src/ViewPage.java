import javax.swing.*;
import java.awt.*;

public class ViewPage extends JPanel {
    public ViewPage() {
        setLayout(new BorderLayout());
        JTable table = new JTable(new Object[][]{
                {"Item 1", "Description 1"},
                {"Item 2", "Description 2"},
                {"Item 3", "Description 3"}
        }, new Object[]{"Name", "Description"});
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
