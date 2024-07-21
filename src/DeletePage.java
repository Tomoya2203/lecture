import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class DeletePage extends JPanel {
    private Delete delete;
    private CardLayout cardLayout;
    private JPanel inputPanel;

    public DeletePage(Delete delete) {
        this.delete = delete;

        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(6, 1));
        JButton studentButton = new JButton("Delete Student");
        JButton teacherButton = new JButton("Delete Teacher");
        JButton courseButton = new JButton("Delete Course");
        JButton sessionButton = new JButton("Delete Session");
        JButton instructorButton = new JButton("Delete Instructor");
        JButton enrollmentButton = new JButton("Delete Enrollment");
        buttonPanel.add(studentButton);
        buttonPanel.add(teacherButton);
        buttonPanel.add(courseButton);
        buttonPanel.add(sessionButton);
        buttonPanel.add(instructorButton);
        buttonPanel.add(enrollmentButton);

        cardLayout = new CardLayout();
        inputPanel = new JPanel(cardLayout);

        inputPanel.add(createStudentPanel(), "Student");
        inputPanel.add(createTeacherPanel(), "Teacher");
        inputPanel.add(createCoursePanel(), "Course");
        inputPanel.add(createSessionPanel(), "Session");
        inputPanel.add(createInstructorPanel(), "Instructor");
        inputPanel.add(createEnrollmentPanel(), "Enrollment");

        studentButton.addActionListener(e -> cardLayout.show(inputPanel, "Student"));
        teacherButton.addActionListener(e -> cardLayout.show(inputPanel, "Teacher"));
        courseButton.addActionListener(e -> cardLayout.show(inputPanel, "Course"));
        sessionButton.addActionListener(e -> cardLayout.show(inputPanel, "Session"));
        instructorButton.addActionListener(e -> cardLayout.show(inputPanel, "Instructor"));
        enrollmentButton.addActionListener(e -> cardLayout.show(inputPanel, "Enrollment"));

        add(buttonPanel, BorderLayout.WEST);
        add(inputPanel, BorderLayout.CENTER);
    }

    // Student Panel
    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Student ID:"));
        JTextField studentIdField = new JTextField();
        panel.add(studentIdField);
        JButton deleteButton = new JButton("Delete");
        panel.add(new JLabel());
        panel.add(deleteButton);

        deleteButton.addActionListener(e -> {
            try {
                String studentId = studentIdField.getText();
                delete.delStudent(studentId);
                studentIdField.setText("");
            } catch (SQLException | IllegalArgumentException ex) {
                showError(ex);
            }
        });

        return panel;
    }

    // Teacher Panel
    private JPanel createTeacherPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Teacher ID:"));
        JTextField teacherIdField = new JTextField();
        panel.add(teacherIdField);
        JButton deleteButton = new JButton("Delete");
        panel.add(new JLabel());
        panel.add(deleteButton);

        deleteButton.addActionListener(e -> {
            try {
                String teacherId = teacherIdField.getText();
                delete.delTeacher(teacherId);
                teacherIdField.setText("");
            } catch (SQLException | IllegalArgumentException ex) {
                showError(ex);
            }
        });

        return panel;
    }

    // Course Panel
    private JPanel createCoursePanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Course ID:"));
        JTextField courseIdField = new JTextField();
        panel.add(courseIdField);
        JButton deleteButton = new JButton("Delete");
        panel.add(new JLabel());
        panel.add(deleteButton);

        deleteButton.addActionListener(e -> {
            try {
                String courseId = courseIdField.getText();
                delete.delCourse(courseId);
                courseIdField.setText("");
            } catch (SQLException | IllegalArgumentException ex) {
                showError(ex);
            }
        });

        return panel;
    }

    // Session Panel
    private JPanel createSessionPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Session ID:"));
        JTextField sessionIdField = new JTextField();
        panel.add(sessionIdField);
        JButton deleteButton = new JButton("Delete");
        panel.add(new JLabel());
        panel.add(deleteButton);

        deleteButton.addActionListener(e -> {
            try {
                String sessionId = sessionIdField.getText();
                delete.delSession(sessionId);
                sessionIdField.setText("");
            } catch (SQLException | IllegalArgumentException ex) {
                showError(ex);
            }
        });

        return panel;
    }

    // Instructor Panel
    private JPanel createInstructorPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Course ID:"));
        JTextField courseIdField = new JTextField();
        panel.add(courseIdField);
        panel.add(new JLabel("Teacher ID:"));
        JTextField teacherIdField = new JTextField();
        panel.add(teacherIdField);
        JButton deleteButton = new JButton("Delete");
        panel.add(new JLabel());
        panel.add(deleteButton);

        deleteButton.addActionListener(e -> {
            try {
                String input = courseIdField.getText() + " " + teacherIdField.getText();
                delete.delInstructor(input);
                courseIdField.setText("");
                teacherIdField.setText("");
            } catch (SQLException | IllegalArgumentException ex) {
                showError(ex);
            }
        });

        return panel;
    }

    // Enrollment Panel
    private JPanel createEnrollmentPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Course ID:"));
        JTextField courseIdField = new JTextField();
        panel.add(courseIdField);
        panel.add(new JLabel("Student ID:"));
        JTextField studentIdField = new JTextField();
        panel.add(studentIdField);
        JButton deleteButton = new JButton("Delete");
        panel.add(new JLabel());
        panel.add(deleteButton);

        deleteButton.addActionListener(e -> {
            try {
                String input = courseIdField.getText() + " " + studentIdField.getText();
                delete.delEnrollment(input);
                courseIdField.setText("");
                studentIdField.setText("");
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
