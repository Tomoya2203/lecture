import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Edit {
    Connection conn = null;


    public Edit(Connection conn) {

        this.conn = conn;
    }

    public void updateStudent(String key_string) throws SQLException {
        String sql = "UPDATE Students SET Name = ? WHERE StudentID = ?";
        String[] key_list = key_string.split("[\\s]+");
        if (key_list.length != 2) {
            throw new IllegalArgumentException("input correctly: <StudentID> <StudentName>");
        }
        int StudentID;
        try {
            StudentID = Integer.parseInt(key_list[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("StudentID must be an integer.");
        }
        String name = key_list[1];
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, StudentID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("input correctly: <StudentID>");
            }
        }
    }

    public void updateTeacher(String key_string) throws SQLException {
        String sql = "UPDATE Teachers SET Name = ? WHERE TeacherID = ?";
        String[] key_list = key_string.split("[\\s]+");
        if (key_list.length != 2) {
            throw new IllegalArgumentException("input correctly: <TeacherID> <TeacherName>");
        }
        int TeacherID;
        try {
            TeacherID = Integer.parseInt(key_list[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("TeacherID must be an integer.");
        }
        String name = key_list[1];
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, TeacherID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("input correctly: <TeacherID>");
            }
        }
    }

    public void updateCourse(String key_string) throws SQLException {
        String sql = "UPDATE Courses SET Name = ? WHERE CourseID = ?";
        String[] key_list = key_string.split("[\\s]+");
        if (key_list.length != 2) {
            throw new IllegalArgumentException("input correctly: <CourseID> <CourseName>");
        }
        int CourseID;
        try {
            CourseID = Integer.parseInt(key_list[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("CourseID must be an integer.");
        }
        String name = key_list[1];
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, CourseID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("input correctly: <CourseID>");
            }
        }
    }

    public void updateSession(String key_string) throws SQLException {
        String checkCourseSql = "SELECT COUNT(*) FROM Courses WHERE CourseID = ?";
        String sql = "UPDATE Sessions SET CourseID = ?, Time = ?, Location = ? WHERE SessionID = ?";
        String[] key_list = key_string.split("[\\s]+");
        if (key_list.length != 4) {
            throw new IllegalArgumentException("input correctly: <SessionID> <CourseID> <Time> <Location>");
        }
        int SessionID;
        int CourseID;
        try {
            SessionID = Integer.parseInt(key_list[0]);
            CourseID = Integer.parseInt(key_list[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("SessionID and CourseID must be integers.");
        }
        String Time = key_list[2];
        String Location = key_list[3];

        try (PreparedStatement checkStmt = conn.prepareStatement(checkCourseSql)) {
            checkStmt.setInt(1, CourseID);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                throw new IllegalArgumentException("CourseID does not exist.");
            }
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, CourseID);
            pstmt.setString(2, Time);
            pstmt.setString(3, Location);
            pstmt.setInt(4, SessionID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("input correctly: <SessionID>, <CourseID>");
            }
        }
    }
}
