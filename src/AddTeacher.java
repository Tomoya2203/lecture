import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddTeacher {
    public static void main(String[] args) throws SQLException {
        String dbname = "../lib/sample.db"; // SQLite3のファイルPATH(適宜変更)
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // SQLite JDBCドライバをロードする
            Class.forName("org.sqlite.JDBC");
            // データベースに接続する
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);

            // SQL文を定義する
            String sql = "INSERT INTO Teachers (Name) VALUES (?)";
            // PreparedStatementを作成する
            pstmt = conn.prepareStatement(sql);

            // パラメータを設定する
            // pstmt.setString(1, "先生1");
            pstmt.setString(1, args[0]);

            // SQL文を実行する
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Inserted " + rowsAffected + " row(s)");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // PreparedStatementとコネクションを閉じる
            try {
                if (pstmt != null) {
                    pstmt.close();
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
