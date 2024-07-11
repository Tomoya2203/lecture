package last;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SelectSample {

    public static void main(String[] args) throws SQLException {
        String dbname = "sample.db"; // SQLite3のファイルPATH(適宜変更)
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);

            stmt = conn.createStatement();

            // Studentsテーブルからデータを取得(適宜変更)
            ResultSet rs = stmt.executeQuery("SELECT * FROM Students");
            while (rs.next()) {
                int id = rs.getInt("StudentID"); // id列のデータを取得(適宜変更)
                String name = rs.getString("Name"); // name列のデータを取得(適宜変更)
                System.out.println(id + "," + name); // 取得したデータを表示(適宜変更)
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
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
