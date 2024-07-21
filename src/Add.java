import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Add {

    Connection conn = null;
    PreparedStatement pstmt2 = null;
    PreparedStatement pstmt3 = null;
    ResultSet rs = null;

    String sql_line;

    public Add(Connection conn){
        this.conn = conn;
    }

    public void addStudent(String key_string) throws SQLException{
        String sql = "INSERT INTO students (name) VALUES (?)";

        String[] key_list = key_string.split("[\\s]+");
        if (key_list.length != 1) {
            throw new IllegalArgumentException("input correctly: <StudentName>");
        }
        String studentName = key_list[0];
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, studentName);
            pstmt.executeUpdate();
        }
    }

    public void addTeacher(String key_string) throws SQLException{
        String sql = "INSERT INTO Teachers (Name) VALUES (?)";

        String[] key_list = key_string.split("[\\s]+");
        if (key_list.length != 1) {
            throw new IllegalArgumentException("input correctly: <TeacherName>");
        }
        String teacherName = key_list[0];

        try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, teacherName);
            pstmt.executeUpdate();

        }
    }

    public void addCourse(String key_string) throws SQLException {
        String sql = "INSERT INTO Courses (Name) VALUES (?)";

        String[] key_list = key_string.split("[\\s]+");
        if (key_list.length != 1) {
            throw new IllegalArgumentException("input correctly: <CourseName>");
        }
        String courseName = key_list[0];
        try(PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, courseName);
            pstmt.executeUpdate();
        }
    }

    public void addSession(String key_string) throws SQLException {
        String checkCourseSql = "SELECT COUNT(*) FROM Courses WHERE CourseID = ?";
        String insertSql = "INSERT INTO Sessions (CourseID, Time, Location) VALUES (?, ?, ?)";

        String[] key_list = key_string.split("[\\s]+");
        if (key_list.length != 3) {
            throw new IllegalArgumentException("input correctly: <CourseID> <Time> <Location>");
        }

        try(PreparedStatement pstmt = conn.prepareStatement(checkCourseSql)) {
            pstmt.setInt(1, Integer.parseInt(key_list[0]));
            rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                try(PreparedStatement pstmt2 = conn.prepareStatement(insertSql)){
                    pstmt2.setInt(1, Integer.parseInt(key_list[0]));
                    pstmt2.setString(2, key_list[1]);
                    pstmt2.setString(3, key_list[2]);
                    pstmt2.executeUpdate();
                }
            } else {
                throw new IllegalArgumentException("CourseID " + key_list[0] + " does not exist in Courses table.");
            }
        }
    }

    public void addInstructor(String key_string) throws SQLException{
        int courseId;
        int teacherId;
        String checkCourseSql = "SELECT COUNT(*) FROM Courses WHERE CourseID = ?";
        String checkTeacherSql = "SELECT COUNT(*) FROM Teachers WHERE TeacherID = ?";
        String insertInstructorSql = "INSERT INTO instructors (CourseID, TeacherID) VALUES (?, ?)";

        String[] key_list = key_string.split("[\\s]+");
        if (key_list.length != 2) {
            throw new IllegalArgumentException("input correctly: <CourseID> <TeacherID>");
        }
        try{
            courseId = Integer.parseInt(key_list[0]);
            teacherId = Integer.parseInt(key_list[1]);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("input correctly: <CourseID> <TeacherID>");
        }

        try(PreparedStatement pstmt = conn.prepareStatement(checkCourseSql)) {
            pstmt.setInt(1, courseId);
            rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                try(PreparedStatement pstmt2 = conn.prepareStatement(checkTeacherSql)){
                    pstmt2.setInt(1, teacherId);
                    rs = pstmt2.executeQuery();
                    if (rs.next() && rs.getInt(1) > 0) {
                        try(PreparedStatement pstmt3 = conn.prepareStatement(insertInstructorSql)){
                            pstmt3.setInt(1, courseId);
                            pstmt3.setInt(2, teacherId);
                            pstmt3.executeUpdate();
                        }
                    } else {
                        throw new IllegalArgumentException("TeacherID " + teacherId + " does not exist in Teachers table.");
                    }
                }
            } else {
                throw new IllegalArgumentException("CourseID " + courseId + " does not exist in Courses table.");
            }
        }
    }



    public void addEnrollment(String key_string) throws SQLException {
        int courseId;
        int studentId;
        String checkCourseSql = "SELECT COUNT(*) FROM Courses WHERE CourseID = ?";
        String checkStudentSql = "SELECT COUNT(*) FROM Students WHERE StudentID = ?";
        String insertEnrollmentSql = "INSERT INTO Enrollments (CourseID, StudentID) VALUES (?, ?)";


        String[] key_list = key_string.split("[\\s]+");
        if (key_list.length != 2) {
            throw new IllegalArgumentException("input correctly: <CourseID> <StudentID>");
        }
        try{
            courseId = Integer.parseInt(key_list[0]);
            studentId = Integer.parseInt(key_list[1]);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("input correctly: <CourseID> <TeacherID>");
        }

        try(PreparedStatement pstmt = conn.prepareStatement(checkCourseSql)) {
            pstmt.setInt(1, courseId);
            rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                try(PreparedStatement pstmt2 = conn.prepareStatement(checkStudentSql)){
                    pstmt2.setInt(1, studentId);
                    rs = pstmt2.executeQuery();
                    if (rs.next() && rs.getInt(1) > 0) {
                        try(PreparedStatement pstmt3 = conn.prepareStatement(insertEnrollmentSql)){
                            pstmt3.setInt(1, courseId);
                            pstmt3.setInt(2, studentId);
                            pstmt3.executeUpdate();
                        }
                    } else {
                        throw new IllegalArgumentException("TeacherID " + studentId + " does not exist in Teachers table.");
                    }
                }
            } else {
                throw new IllegalArgumentException("CourseID " + courseId + " does not exist in Courses table.");
            }
        }
    }
}
