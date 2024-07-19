import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Edit {
    private Statement stmt = null;

    public Edit(Statement stmt){
        this.stmt = stmt;
    }

    public void updateStudent(int StudentID, String name){
        try{
            int rs = stmt.executeUpdate(
                    "UPDATE Students SET Name = '" + name + "' WHERE StudentID = " + StudentID
            );
            if (rs == 0){
                System.out.println("Student not found");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateTeacher(int TeacherID, String Name){
        try{
            int rs = stmt.executeUpdate(
                    "UPDATE Teachers SET Name = '" + Name + "' WHERE TeacherID = " + TeacherID
            );
            if (rs == 0){
                System.out.println("Teacher not found");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateCourse(int CourseID, String Name){
        try{
            int rs = stmt.executeUpdate(
                "UPDATE Courses SET Name = '" + Name + "' WHERE CourseID = " + CourseID
            );
            if (rs == 0){
                System.out.println("Course not found");
            }
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
            if (rs == 0){
                System.out.println("Session not found");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
