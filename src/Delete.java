import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Delete {
    Connection conn = null;

    public Delete(Connection conn) {
        this.conn = conn;
    }

    public void delStudent(String key_string) throws SQLException {
        String sql = "DELETE FROM Students WHERE StudentID = ?";
        String[] key_list = key_string.split("[\\s]+");
        if (key_list.length != 1) {
            throw new IllegalArgumentException("input correctly: <StudentID>");
        }
        int StudentID;
        try {
            StudentID = Integer.parseInt(key_list[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("StudentID must be an integer.");
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, StudentID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("StudentID does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delTeacher(String key_string) throws SQLException {
        String sql = "DELETE FROM Teachers WHERE TeacherID = ?";
        String[] key_list = key_string.split("[\\s]+");
        if (key_list.length != 1) {
            throw new IllegalArgumentException("input correctly: <TeacherID>");
        }
        int TeacherID;
        try {
            TeacherID = Integer.parseInt(key_list[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("TeacherID must be an integer.");
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, TeacherID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("TeacherID does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delCourse(String key_string) throws SQLException {
        String sql = "DELETE FROM Courses WHERE CourseID = ?";
        String[] key_list = key_string.split("[\\s]+");
        if (key_list.length != 1) {
            throw new IllegalArgumentException("input correctly: <CourseID>");
        }
        int CourseID;
        try {
            CourseID = Integer.parseInt(key_list[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("CourseID must be an integer.");
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, CourseID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("CourseID does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delSession(String key_string) throws SQLException {
        String sql = "DELETE FROM Sessions WHERE SessionID = ?";
        String[] key_list = key_string.split("[\\s]+");
        if (key_list.length != 1) {
            throw new IllegalArgumentException("input correctly: <SessionID>");
        }
        int SessionID;
        try {
            SessionID = Integer.parseInt(key_list[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("SessionID must be an integer.");
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, SessionID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("SessionID does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delInstructor(String key_string) throws SQLException {
        String sql = "DELETE FROM Instructors WHERE CourseID = ? AND TeacherID = ?";
        String[] key_list = key_string.split("[\\s]+");
        if (key_list.length != 2) {
            throw new IllegalArgumentException("input correctly: <CourseID> <TeacherID>");
        }
        int CourseID;
        int TeacherID;
        try {
            CourseID = Integer.parseInt(key_list[0]);
            TeacherID = Integer.parseInt(key_list[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("CourseID and TeacherID must be integers.");
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, CourseID);
            pstmt.setInt(2, TeacherID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("The combination of TeacherID and CourseID does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delEnrollment(String key_string) throws SQLException {
        String sql = "DELETE FROM Enrollments WHERE StudentID = ? AND CourseID = ?";
        String[] key_list = key_string.split("[\\s]+");
        if (key_list.length != 2) {
            throw new IllegalArgumentException("input correctly: <StudentID> <CourseID>");
        }
        int StudentID;
        int CourseID;
        try {
            StudentID = Integer.parseInt(key_list[0]);
            CourseID = Integer.parseInt(key_list[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("input correctly: <StudentID> <CourseID>");
        }
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, StudentID);
            pstmt.setInt(2, CourseID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new IllegalArgumentException("The combination of StudentID and CourseID does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
