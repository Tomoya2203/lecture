import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Add {

    Connection conn = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    PreparedStatement pstmt3 = null;
    ResultSet rs = null;

    String sql_line;

    public Add(Connection conn){
        this.conn = conn;
    }

    public void addStudent(String key_string){
        String[] key_list = key_string.split("[\s]+");
        if (key_list.length != 1) {
            System.out.println("input correctly: <StudentName>");
            return;
        }
        String studentName = key_list[0];


        try {
            String sql = "INSERT INTO Students (Name) VALUES (?)";
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, studentName);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Inserted " + rowsAffected + " row(s)");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addTeacher(String key_string){
        String[] key_list = key_string.split("[\s]+");
        if (key_list.length != 1) {
            System.out.println("input correctly: <TeacherName>");
            return;
        }
        String teacherName = key_list[0];

        try {
            String sql = "INSERT INTO Teachers (Name) VALUES (?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, teacherName);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Inserted " + rowsAffected + " row(s)");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addCourse(String key_string){
        String[] key_list = key_string.split("[\s]+");
        if (key_list.length != 1) {
            System.out.println("input correctly: <CourseName>");
            return;
        }
        String courseName = key_list[0];

        try {
            String sql = "INSERT INTO Courses (Name) VALUES (?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, courseName);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Inserted " + rowsAffected + " row(s)");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addSession(String key_string){
        String[] key_list = key_string.split("[\s]+");
        if (key_list.length != 3) {
            System.out.println("input correctly: <CourseID> <Time> <Location>");
            return;
        }

        try {
            String checkCourseSql = "SELECT COUNT(*) FROM Courses WHERE CourseID = ?";
            pstmt = conn.prepareStatement(checkCourseSql);
            pstmt.setInt(1, Integer.parseInt(key_list[0]));

            rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                String insertSql = "INSERT INTO Sessions (CourseID, Time, Location) VALUES (?, ?, ?)";
                pstmt2 = conn.prepareStatement(insertSql);

                pstmt2.setInt(1, Integer.parseInt(key_list[0]));
                pstmt2.setString(2, key_list[1]);
                pstmt2.setString(3, key_list[2]);

                int rowsAffected = pstmt2.executeUpdate();
                System.out.println("Inserted " + rowsAffected + " row(s) into Sessions table.");
            } else {
                System.out.println("CourseID " + key_list[0] + " does not exist in Courses table.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (pstmt2 != null) {
                    pstmt2.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void addInstructor(String key_string){
        String[] key_list = key_string.split("[\s]+");
        if (key_list.length != 2) {
            System.out.println("input correctly: <CourseID> <TeacherID>");
            return;
        }
        int courseId = Integer.parseInt(key_list[0]);
        int teacherId = Integer.parseInt(key_list[1]);

        try {
            String checkCourseSql = "SELECT COUNT(*) FROM Courses WHERE CourseID = ?";
            pstmt = conn.prepareStatement(checkCourseSql);
            pstmt.setInt(1, courseId);

            rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                String checkTeacherSql = "SELECT COUNT(*) FROM Teachers WHERE TeacherID = ?";
                pstmt2 = conn.prepareStatement(checkTeacherSql);
                pstmt2.setInt(1, teacherId);

                rs = pstmt2.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    String insertInstructorSql = "INSERT INTO instructors (CourseID, TeacherID) VALUES (?, ?)";
                    pstmt3 = conn.prepareStatement(insertInstructorSql);
                    pstmt3.setInt(1, courseId);
                    pstmt3.setInt(2, teacherId);

                    int rowsAffected = pstmt3.executeUpdate();
                    System.out.println("Inserted " + rowsAffected + " row(s) into Instructors table.");
                } else {
                    System.out.println("TeacherID " + teacherId + " does not exist in Teachers table.");
                }
            } else {
                System.out.println("CourseID " + courseId + " does not exist in Courses table.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (pstmt2 != null) {
                    pstmt2.close();
                }
                if (pstmt3 != null) {
                    pstmt3.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public void addEnrollment(String key_string){
        String[] key_list = key_string.split("[\s]+");
        if (key_list.length != 2) {
            System.out.println("input correctly: <CourseID> <StudentID>");
            return;
        }
        int courseId = Integer.parseInt(key_list[0]);
        int studentId = Integer.parseInt(key_list[1]);

        try {
            String checkCourseSql = "SELECT COUNT(*) FROM Courses WHERE CourseID = ?";
            pstmt = conn.prepareStatement(checkCourseSql);
            pstmt.setInt(1, courseId);

            rs = pstmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                String checkStudentSql = "SELECT COUNT(*) FROM Students WHERE StudentID = ?";
                pstmt2 = conn.prepareStatement(checkStudentSql);
                pstmt2.setInt(1, studentId);

                rs = pstmt2.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    String insertEnrollmentSql = "INSERT INTO Enrollments (CourseID, StudentID) VALUES (?, ?)";
                    pstmt3 = conn.prepareStatement(insertEnrollmentSql);
                    pstmt3.setInt(1, courseId);
                    pstmt3.setInt(2, studentId);

                    int rowsAffected = pstmt3.executeUpdate();
                    System.out.println("Inserted " + rowsAffected + " row(s) into Enrollments table.");
                } else {
                    System.out.println("StudentID " + studentId + " does not exist in Students table.");
                }
            } else {
                System.out.println("CourseID " + courseId + " does not exist in Courses table.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (pstmt2 != null) {
                    pstmt2.close();
                }
                if (pstmt3 != null) {
                    pstmt3.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
