import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Edit {
//    private final String DB_NAME = "../lib/sample.db";
//    private Connection c = null;
    private Statement stmt = null;

//    // constructor
//    public Edit(){
//        try{
//            Class.forName("org.sqlite.JDBC");
//            this.c = DriverManager.getConnection("jdbc:sqlite:" + this.DB_NAME);
//            this.stmt = c.createStatement();
//        }catch (Exception e){
//            System.out.println(e);
//        }
//    }

    public Edit(Statement stmt){
        this.stmt = stmt;
    }

    public void updateStudent(int StudentID, String name){
        try{
            int rs = stmt.executeUpdate(
                    "UPDATE Students SET Name = '" + name + "' WHERE StudentID = " + StudentID
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateTeacher(int TeacherID, String Name){
        try{
            int rs = stmt.executeUpdate(
                    "UPDATE Teachers SET Name = '" + Name + "' WHERE TeacherID = " + TeacherID
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateCourse(int CourseID, String Name){
        try{
            int rs = stmt.executeUpdate(
                "UPDATE Courses SET Name = '" + Name + "' WHERE CourseID = " + CourseID
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateSession(int SessionID, int CourseID, String Time, String Location){
        try{
            int rs = stmt.executeUpdate(
                    "UPDATE Sessions SET Location = '" + Location + "', Time = '" + Time
                            + "', CourseID = " + CourseID + " WHERE SessionID = " + SessionID
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) throws SQLException {
//        Edit db = new Edit();
//        db.updateStudent(1, "生徒A");
//        db.updateTeacher(1, "先生A");
//        db.updateCourse(1, "科目A");
//        db.updateSession(1, 1, "日曜4限", "Room 101");
//    }
}
