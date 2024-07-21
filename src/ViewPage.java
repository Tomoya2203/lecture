import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ViewPage extends JPanel {
    private Connection conn;
    private CardLayout cardLayout;
    private JPanel tablePanel;
    private JTextField searchField;

    public ViewPage(Connection conn) {
        this.conn = conn;
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(7, 1));
        JButton courseInfoButton = new JButton("View Course Info");
        JButton studentsButton = new JButton("View Students");
        JButton teachersButton = new JButton("View Teachers");
        JButton coursesButton = new JButton("View Courses");
        JButton sessionsButton = new JButton("View Sessions");
        JButton enrollmentsButton = new JButton("View Enrollments");
        JButton instructorsButton = new JButton("View Instructors");

        buttonPanel.add(courseInfoButton);
        buttonPanel.add(studentsButton);
        buttonPanel.add(teachersButton);
        buttonPanel.add(coursesButton);
        buttonPanel.add(sessionsButton);
        buttonPanel.add(enrollmentsButton);
        buttonPanel.add(instructorsButton);

        cardLayout = new CardLayout();
        tablePanel = new JPanel(cardLayout);

        tablePanel.add(createTablePanel("CourseInfo"), "CourseInfo");
        tablePanel.add(createTablePanel("Students"), "Students");
        tablePanel.add(createTablePanel("Teachers"), "Teachers");
        tablePanel.add(createTablePanel("Courses"), "Courses");
        tablePanel.add(createTablePanel("Sessions"), "Sessions");
        tablePanel.add(createTablePanel("Enrollments"), "Enrollments");
        tablePanel.add(createTablePanel("Instructors"), "Instructors");

        courseInfoButton.addActionListener(e -> switchTable("CourseInfo"));
        studentsButton.addActionListener(e -> switchTable("Students"));
        teachersButton.addActionListener(e -> switchTable("Teachers"));
        coursesButton.addActionListener(e -> switchTable("Courses"));
        sessionsButton.addActionListener(e -> switchTable("Sessions"));
        enrollmentsButton.addActionListener(e -> switchTable("Enrollments"));
        instructorsButton.addActionListener(e -> switchTable("Instructors"));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> performSearch());
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        add(buttonPanel, BorderLayout.WEST);
        add(searchPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
    }

    private JPanel createTablePanel(String tableName) {
        JPanel panel = new JPanel(new BorderLayout());
        JTable table = new JTable(getTableModel(tableName));
        table.setAutoCreateRowSorter(true);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private DefaultTableModel getTableModel(String tableName) {
        DefaultTableModel model = new DefaultTableModel(getColumnNames(tableName), 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[][] data = getTableData(tableName);
        for (Object[] row : data) {
            model.addRow(row);
        }

        return model;
    }

    private Object[][] getTableData(String tableName) {
        List<Object[]> data = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            String query = getQueryForTable(tableName);
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                data.add(getRowData(rs, tableName));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return data.toArray(new Object[0][]);
    }

    private String getQueryForTable(String tableName) {
        if (tableName.equals("CourseInfo")) {
            return "SELECT Courses.CourseID, Courses.Name AS CourseName, Sessions.Time, Sessions.Location, " +
                   "GROUP_CONCAT(Teachers.TeacherID) AS TeacherIDs, GROUP_CONCAT(Teachers.Name) AS TeacherNames " +
                   "FROM Courses " +
                   "JOIN Sessions ON Courses.CourseID = Sessions.CourseID " +
                   "JOIN Instructors ON Courses.CourseID = Instructors.CourseID " +
                   "JOIN Teachers ON Instructors.TeacherID = Teachers.TeacherID " +
                   "GROUP BY Courses.CourseID, Sessions.SessionID";
        } else {
            return "SELECT * FROM " + tableName;
        }
    }

    private Object[] getRowData(ResultSet rs, String tableName) throws SQLException {
        switch (tableName) {
            case "CourseInfo":
                return new Object[]{rs.getInt("CourseID"), rs.getString("CourseName"), rs.getInt("Time"),
                                    rs.getString("Location"), rs.getString("TeacherIDs"), rs.getString("TeacherNames")};
            case "Students":
                return new Object[]{rs.getInt("StudentID"), rs.getString("Name")};
            case "Teachers":
                return new Object[]{rs.getInt("TeacherID"), rs.getString("Name")};
            case "Courses":
                return new Object[]{rs.getInt("CourseID"), rs.getString("Name")};
            case "Sessions":
                return new Object[]{rs.getInt("SessionID"), rs.getString("CourseID"), rs.getInt("Time"),
                                    rs.getString("Location")};
            case "Enrollments":
                return new Object[]{rs.getString("CourseID"), rs.getString("StudentID")};
            case "Instructors":
                return new Object[]{rs.getString("CourseID"), rs.getString("TeacherID")};
            default:
                throw new IllegalArgumentException("Unknown table name: " + tableName);
        }
    }

    private String[] getColumnNames(String tableName) {
        switch (tableName) {
            case "CourseInfo":
                return new String[]{"CourseID", "CourseName", "Time", "Location", "TeacherIDs", "TeacherNames"};
            case "Students":
                return new String[]{"StudentID", "Name"};
            case "Teachers":
                return new String[]{"TeacherID", "Name"};
            case "Courses":
                return new String[]{"CourseID", "Name"};
            case "Sessions":
                return new String[]{"SessionID", "CourseID", "Time", "Location"};
            case "Enrollments":
                return new String[]{"CourseID", "StudentID"};
            case "Instructors":
                return new String[]{"CourseID", "TeacherID"};
            default:
                throw new IllegalArgumentException("Unknown table name: " + tableName);
        }
    }

    private void switchTable(String tableName) {
        cardLayout.show(tablePanel, tableName);
        searchField.setText("");
    }

    private void performSearch() {
        String searchText = searchField.getText().toLowerCase();
        Component currentTable = tablePanel.getComponent(0);

        for (Component comp : tablePanel.getComponents()) {
            if (comp.isVisible()) {
                currentTable = comp;
                break;
            }
        }

        if (currentTable instanceof JPanel) {
            JScrollPane scrollPane = (JScrollPane) ((JPanel) currentTable).getComponent(0);
            JTable table = (JTable) scrollPane.getViewport().getView();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) table.getModel());
            table.setRowSorter(sorter);

            if (searchText.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        }
    }
}
