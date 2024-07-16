import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddCourse {
    public static void main(String[] args) throws SQLException {
        if (args.length < 2) {
            System.out.println("Usage: java AddCourse <CourseName> <TeacherID>");
            return;
        }

        String courseName = args[0];
        int teacherId = Integer.parseInt(args[1]);
        String dbname = "../lib/sample.db"; // SQLite3のファイルPATH(適宜変更)
        Connection conn = null;
        PreparedStatement checkTeacherStmt = null;
        PreparedStatement insertCourseStmt = null;
        PreparedStatement insertInstructorStmt = null;
        ResultSet rs = null;

        try {
            // SQLite JDBCドライバをロードする
            Class.forName("org.sqlite.JDBC");
            // データベースに接続する
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);

            // TeacherIDの存在を確認するSQL文を定義する
            String checkTeacherSql = "SELECT COUNT(*) FROM Teachers WHERE TeacherID = ?";
            checkTeacherStmt = conn.prepareStatement(checkTeacherSql);
            checkTeacherStmt.setInt(1, teacherId);

            // SQL文を実行して結果を取得する
            rs = checkTeacherStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // TeacherIDが存在する場合、Coursesテーブルにデータを挿入する
                String insertCourseSql = "INSERT INTO Courses (Name) VALUES (?)";
                insertCourseStmt = conn.prepareStatement(insertCourseSql, PreparedStatement.RETURN_GENERATED_KEYS);
                insertCourseStmt.setString(1, courseName);

                int rowsAffected = insertCourseStmt.executeUpdate();
                System.out.println("Inserted " + rowsAffected + " row(s) into Courses table.");

                // 挿入したCourseのCourseIDを取得する
                rs = insertCourseStmt.getGeneratedKeys();
                int courseId = -1;
                if (rs.next()) {
                    courseId = rs.getInt(1);
                }

                // Instructorsテーブルにデータを挿入する
                if (courseId != -1) {
                    String insertInstructorSql = "INSERT INTO Instructors (CourseID, TeacherID) VALUES (?, ?)";
                    insertInstructorStmt = conn.prepareStatement(insertInstructorSql);
                    insertInstructorStmt.setInt(1, courseId);
                    insertInstructorStmt.setInt(2, teacherId);

                    rowsAffected = insertInstructorStmt.executeUpdate();
                    System.out.println("Inserted " + rowsAffected + " row(s) into Instructors table.");
                }

            } else {
                System.out.println("TeacherID " + teacherId + " does not exist in Teachers table.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // リソースを閉じる
            try {
                if (rs != null) {
                    rs.close();
                }
                if (checkTeacherStmt != null) {
                    checkTeacherStmt.close();
                }
                if (insertCourseStmt != null) {
                    insertCourseStmt.close();
                }
                if (insertInstructorStmt != null) {
                    insertInstructorStmt.close();
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
