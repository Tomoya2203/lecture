import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    private Statement stmt = null;

    public Delete(Statement stmt){
        this.stmt = stmt;
    }

    public void delStudent(int StudentID){
        try{
            int rs = stmt.executeUpdate(
                    "DELETE FROM Students WHERE StudentID = " + StudentID
            );
            if (rs == 0){
                System.out.println("Student not found");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delTeacher(int TeacherID){
        try{
            int rs = stmt.executeUpdate(
                    "DELETE FROM Teachers WHERE TeacherID = " + TeacherID
            );
            if (rs == 0){
                System.out.println("Teacher not found");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delCourse(int CourseID){
        try{
            int rs = stmt.executeUpdate(
                    "DELETE FROM Courses WHERE CourseID = " + CourseID
            );
            if (rs == 0){
                System.out.println("Course not found");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delSession(int SessionID){
        try{
            int rs = stmt.executeUpdate(
                    "DELETE FROM Sessions WHERE SessionID = " + SessionID
            );
            if (rs == 0){
                System.out.println("Session not found");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delInstructor(int CourseID, int TeacherID){
        try{
            int rs = stmt.executeUpdate(
                    "DELETE FROM Instructors WHERE CourseID = " + CourseID + " AND TeacherID = " + TeacherID
            );
            if (rs == 0){
                System.out.println("Instructor not found");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delEnrollment(int CourseID, int StudentID){
        try{
            int rs = stmt.executeUpdate(
                    "DELETE FROM Enrollments WHERE StudentID = " + StudentID + " AND CourseID = " + CourseID
            );
            if (rs == 0){
                System.out.println("Enrollment not found");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}