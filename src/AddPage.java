import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddPage extends JPanel {
    private Add add;
    private CardLayout cardLayout;
    private JPanel inputPanel;

    public AddPage(Add add) {
        this.add = add;

        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(6, 1));
        JButton studentButton = new JButton("Add Student");
        JButton teacherButton = new JButton("Add Teacher");
        JButton courseButton = new JButton("Add Course");
        JButton sessionButton = new JButton("Add Session");
        JButton instructorButton = new JButton("Add Instructor");
        JButton enrollmentButton = new JButton("Add Enrollment");
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
    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Student Name:"));
        JTextField studentNameField = new JTextField();
        panel.add(studentNameField);
        JButton addButton = new JButton("Add");
        panel.add(new JLabel());
        panel.add(addButton);

        addButton.addActionListener(e -> {
            try {
                String name = studentNameField.getText();
                add.addStudent(name);
                studentNameField.setText("");
            } catch (SQLException | IllegalArgumentException ex) {
                showError(ex);
            }
        });

        return panel;
    }


    private JPanel createTeacherPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Teacher Name:"));
        JTextField teacherNameField = new JTextField();
        panel.add(teacherNameField);
        JButton addButton = new JButton("Add");
        panel.add(new JLabel());
        panel.add(addButton);

        addButton.addActionListener(e -> {
            try {
                String name = teacherNameField.getText();
                add.addTeacher(name);
                teacherNameField.setText("");
            } catch (SQLException | IllegalArgumentException ex) {
                showError(ex);
            }
        });

        return panel;
    }

    private JPanel createCoursePanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Course Name:"));
        JTextField courseNameField = new JTextField();
        panel.add(courseNameField);
        JButton addButton = new JButton("Add");
        panel.add(new JLabel());
        panel.add(addButton);

        addButton.addActionListener(e -> {
            try {
                String name = courseNameField.getText();
                add.addCourse(name);
                courseNameField.setText("");
            } catch (SQLException | IllegalArgumentException ex) {
                showError(ex);
            }
        });

        return panel;
    }

    private JPanel createSessionPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Course ID:"));
        JTextField courseIdField = new JTextField();
        panel.add(courseIdField);
        panel.add(new JLabel("Time:"));
        JTextField timeField = new JTextField();
        panel.add(timeField);
        panel.add(new JLabel("Location:"));
        JTextField locationField = new JTextField();
        panel.add(locationField);
        JButton addButton = new JButton("Add");
        panel.add(new JLabel());
        panel.add(addButton);

        addButton.addActionListener(e -> {
            try {
                String input = courseIdField.getText() + " " + timeField.getText() + " " + locationField.getText();
                add.addSession(input);
                courseIdField.setText("");
                timeField.setText("");
                locationField.setText("");
            } catch (SQLException | IllegalArgumentException ex) {
                showError(ex);
            }
        });

        return panel;
    }


    private JPanel createInstructorPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Course ID:"));
        JTextField courseIdField = new JTextField();
        panel.add(courseIdField);
        panel.add(new JLabel("Teacher ID:"));
        JTextField teacherIdField = new JTextField();
        panel.add(teacherIdField);
        JButton addButton = new JButton("Add");
        panel.add(new JLabel());
        panel.add(addButton);

        addButton.addActionListener(e -> {
            try {
                String input = courseIdField.getText() + " " + teacherIdField.getText();
                add.addInstructor(input);
                courseIdField.setText("");
                teacherIdField.setText("");
            } catch (SQLException | IllegalArgumentException ex) {
                showError(ex);
            }
        });

        return panel;
    }

    private JPanel createEnrollmentPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Course ID:"));
        JTextField courseIdField = new JTextField();
        panel.add(courseIdField);
        panel.add(new JLabel("Student ID:"));
        JTextField studentIdField = new JTextField();
        panel.add(studentIdField);
        JButton addButton = new JButton("Add");
        panel.add(new JLabel());
        panel.add(addButton);

        addButton.addActionListener(e -> {
            try {
                String input = courseIdField.getText() + " " + studentIdField.getText();
                add.addEnrollment(input);
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
