import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddStudentClass {
    public static void main(String[] args) throws SQLException {
        if (args.length < 2) {
            System.out.println("Usage: java AddEnrollment <CourseID> <StudentID>");
            return;
        }

        int courseId = Integer.parseInt(args[0]);
        int studentId = Integer.parseInt(args[1]);
        String dbname = "../lib/sample.db"; // SQLite3のファイルPATH(適宜変更)
        Connection conn = null;
        PreparedStatement checkCourseStmt = null;
        PreparedStatement checkStudentStmt = null;
        PreparedStatement insertEnrollmentStmt = null;
        ResultSet rs = null;

        try {
            // SQLite JDBCドライバをロードする
            Class.forName("org.sqlite.JDBC");
            // データベースに接続する
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);

            // CourseIDの存在を確認するSQL文を定義する
            String checkCourseSql = "SELECT COUNT(*) FROM Courses WHERE CourseID = ?";
            checkCourseStmt = conn.prepareStatement(checkCourseSql);
            checkCourseStmt.setInt(1, courseId);

            // SQL文を実行して結果を取得する
            rs = checkCourseStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // CourseIDが存在する場合、StudentIDの存在を確認するSQL文を定義する
                String checkStudentSql = "SELECT COUNT(*) FROM Students WHERE StudentID = ?";
                checkStudentStmt = conn.prepareStatement(checkStudentSql);
                checkStudentStmt.setInt(1, studentId);

                // SQL文を実行して結果を取得する
                rs = checkStudentStmt.executeQuery();
                if (rs.next() && rs.getInt(1) > 0) {
                    // StudentIDが存在する場合、Enrollmentsテーブルにデータを挿入する
                    String insertEnrollmentSql = "INSERT INTO Enrollments (CourseID, StudentID) VALUES (?, ?)";
                    insertEnrollmentStmt = conn.prepareStatement(insertEnrollmentSql);
                    insertEnrollmentStmt.setInt(1, courseId);
                    insertEnrollmentStmt.setInt(2, studentId);

                    int rowsAffected = insertEnrollmentStmt.executeUpdate();
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
            // リソースを閉じる
            try {
                if (rs != null) {
                    rs.close();
                }
                if (checkCourseStmt != null) {
                    checkCourseStmt.close();
                }
                if (checkStudentStmt != null) {
                    checkStudentStmt.close();
                }
                if (insertEnrollmentStmt != null) {
                    insertEnrollmentStmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
