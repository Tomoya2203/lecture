import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class EditPage extends JPanel {
    private Edit edit;
    private CardLayout cardLayout;
    private JPanel inputPanel;

    public EditPage(Edit edit) {
        this.edit = edit;

        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(6, 1));
        JButton studentButton = new JButton("Edit Student");
        JButton teacherButton = new JButton("Edit Teacher");
        JButton courseButton = new JButton("Edit Course");
        JButton sessionButton = new JButton("Edit Session");
        buttonPanel.add(studentButton);
        buttonPanel.add(teacherButton);
        buttonPanel.add(courseButton);
        buttonPanel.add(sessionButton);

        cardLayout = new CardLayout();
        inputPanel = new JPanel(cardLayout);

        inputPanel.add(createStudentPanel(), "Student");
        inputPanel.add(createTeacherPanel(), "Teacher");
        inputPanel.add(createCoursePanel(), "Course");
        inputPanel.add(createSessionPanel(), "Session");

        studentButton.addActionListener(e -> cardLayout.show(inputPanel, "Student"));
        teacherButton.addActionListener(e -> cardLayout.show(inputPanel, "Teacher"));
        courseButton.addActionListener(e -> cardLayout.show(inputPanel, "Course"));
        sessionButton.addActionListener(e -> cardLayout.show(inputPanel, "Session"));

        add(buttonPanel, BorderLayout.WEST);
        add(inputPanel, BorderLayout.CENTER);
    }

    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Student ID:"));
        JTextField studentIdField = new JTextField();
        panel.add(studentIdField);
        panel.add(new JLabel("New Student Name:"));
        JTextField studentNameField = new JTextField();
        panel.add(studentNameField);
        JButton editButton = new JButton("Edit");
        panel.add(new JLabel());
        panel.add(editButton);

        editButton.addActionListener(e -> {
            try {
                String input = studentIdField.getText() + " " + studentNameField.getText();
                edit.updateStudent(input);
                studentIdField.setText("");
                studentNameField.setText("");
            } catch (SQLException | IllegalArgumentException ex) {
                showError(ex);
            }
        });

        return panel;
    }

    private JPanel createTeacherPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Teacher ID:"));
        JTextField teacherIdField = new JTextField();
        panel.add(teacherIdField);
        panel.add(new JLabel("New Teacher Name:"));
        JTextField teacherNameField = new JTextField();
        panel.add(teacherNameField);
        JButton editButton = new JButton("Edit");
        panel.add(new JLabel());
        panel.add(editButton);

        editButton.addActionListener(e -> {
            try {
                String input = teacherIdField.getText() + " " + teacherNameField.getText();
                edit.updateTeacher(input);
                teacherIdField.setText("");
                teacherNameField.setText("");
            } catch (SQLException | IllegalArgumentException ex) {
                showError(ex);
            }
        });

        return panel;
    }

    private JPanel createCoursePanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Course ID:"));
        JTextField courseIdField = new JTextField();
        panel.add(courseIdField);
        panel.add(new JLabel("New Course Name:"));
        JTextField courseNameField = new JTextField();
        panel.add(courseNameField);
        JButton editButton = new JButton("Edit");
        panel.add(new JLabel());
        panel.add(editButton);

        editButton.addActionListener(e -> {
            try {
                String input = courseIdField.getText() + " " + courseNameField.getText();
                edit.updateCourse(input);
                courseIdField.setText("");
                courseNameField.setText("");
            } catch (SQLException | IllegalArgumentException ex) {
                showError(ex);
            }
        });

        return panel;
    }

    private JPanel createSessionPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Session ID:"));
        JTextField sessionIdField = new JTextField();
        panel.add(sessionIdField);
        panel.add(new JLabel("New Course ID:"));
        JTextField courseIdField = new JTextField();
        panel.add(courseIdField);
        panel.add(new JLabel("New Time:"));
        JTextField timeField = new JTextField();
        panel.add(timeField);
        panel.add(new JLabel("New Location:"));
        JTextField locationField = new JTextField();
        panel.add(locationField);
        JButton editButton = new JButton("Edit");
        panel.add(new JLabel());
        panel.add(editButton);

        editButton.addActionListener(e -> {
            try {
                String input = sessionIdField.getText() + " " + courseIdField.getText() + " " +
                        timeField.getText() + " " + locationField.getText();
                edit.updateSession(input);
                sessionIdField.setText("");
                courseIdField.setText("");
                timeField.setText("");
                locationField.setText("");
            } catch (SQLException | IllegalArgumentException ex) {
                showError(ex);
            }
        });

        return panel;
    }

    private void showError(Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
