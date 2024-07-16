import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    private final String DB_NAME = "../lib/sample.db";
    private Connection c = null;
    private Statement stmt = null;

    // constructor
    public Delete(){
        try{
            Class.forName("org.sqlite.JDBC");
            this.c = DriverManager.getConnection("jdbc:sqlite:" + this.DB_NAME);

            this.stmt = c.createStatement();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void delStudent(int StudentID){
        try{
            int rs = stmt.executeUpdate(
                    "DELETE FROM Students WHERE StudentID = " + StudentID
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delTeacher(int TeacherID){
        try{
            int rs = stmt.executeUpdate(
                    "DELETE FROM Teachers WHERE TeacherID = " + TeacherID
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delCourse(int CourseID){
        try{
            int rs = stmt.executeUpdate(
                    "DELETE FROM Courses WHERE CourseID = " + CourseID
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delSessions(int SessionID){
        try{
            int rs = stmt.executeUpdate(
                    "DELETE FROM Sessions WHERE SessionID = " + SessionID
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delInstructor(int CourseID, int TeacherID){
        try{
            int rs = stmt.executeUpdate(
                    "DELETE FROM Instructors WHERE CourseID = " + CourseID + " AND TeacherID = " + TeacherID
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delEnrollment(int CourseID, int StudentID){
        try{
            int rs = stmt.executeUpdate(
                    "DELETE FROM Enrollments WHERE StudentID = " + StudentID + " AND CourseID = " + CourseID
            );
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            if (this.c != null) {
                this.c.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    public static void main(String[] args) throws SQLException {
//        Delete db = new Delete();
//        db.delEnrollment(1, 1);
//
//        db.closeConnection();
//    }
}