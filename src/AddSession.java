import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddSession {
    public static void main(String[] args) throws SQLException {
        if (args.length < 3) {
            System.out.println("Usage: java AddSession <CourseID> <Time> <Location>");
            return;
        }

        String dbname = "../lib/sample.db"; // SQLite3のファイルPATH(適宜変更)
        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement insertStmt = null;
        ResultSet rs = null;

        try {
            // SQLite JDBCドライバをロードする
            Class.forName("org.sqlite.JDBC");
            // データベースに接続する
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);

            // CourseIDの存在を確認するSQL文を定義する
            String checkCourseSql = "SELECT COUNT(*) FROM Courses WHERE CourseID = ?";
            checkStmt = conn.prepareStatement(checkCourseSql);
            checkStmt.setInt(1, Integer.parseInt(args[0]));

            // SQL文を実行して結果を取得する
            rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                // CourseIDが存在する場合、Sessionsテーブルにデータを挿入する
                String insertSql = "INSERT INTO Sessions (CourseID, Time, Location) VALUES (?, ?, ?)";
                insertStmt = conn.prepareStatement(insertSql);

                insertStmt.setInt(1, Integer.parseInt(args[0]));
                insertStmt.setString(2, args[1]);
                insertStmt.setString(3, args[2]);

                int rowsAffected = insertStmt.executeUpdate();
                System.out.println("Inserted " + rowsAffected + " row(s) into Sessions table.");
            } else {
                System.out.println("CourseID " + args[0] + " does not exist in Courses table.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // リソースを閉じる
            try {
                if (rs != null) {
                    rs.close();
                }
                if (checkStmt != null) {
                    checkStmt.close();
                }
                if (insertStmt != null) {
                    insertStmt.close();
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
