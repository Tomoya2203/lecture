import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    private final String DB_NAME = "../lib/sample.db";
    private static Connection conn;
    private static Statement stmt;

    public Main() {
        try {
            Class.forName("org.sqlite.JDBC");
            this.conn = DriverManager.getConnection("jdbc:sqlite:" + this.DB_NAME);
            this.stmt = conn.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args){
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("Select an action: show, add, delete, edit, exit"); // enter case word
            String command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "show":
                    ShowDatabase db = new ShowDatabase();

                    String[] Table = {"CourseInfo","Students","Teachers","Courses","Instructors","Sessions","Enrollments"};
//                    for(int i=0;i<7;i++){
//                        System.out.println(Table[i]);
//                        db.ShowAll(Table[i]);
//                    }

                    System.out.println("Select table: CourseInfo, Students, " +
                            "Teachers, Courses,Instructor, Sessions, Enrollments ");

                    String ShowCommand = scanner.nextLine();
                    switch (ShowCommand.toLowerCase()) {
                        case "courseinfo":
                            System.out.println(Table[0]);
                            db.ShowAll(Table[0]);
                            break;
                        case "students":
                            System.out.println(Table[1]);
                            db.ShowAll(Table[1]);
                            break;
                        case "teachers":
                            System.out.println(Table[2]);
                            db.ShowAll(Table[2]);
                            break;
                        case "courses":
                            System.out.println(Table[3]);
                            db.ShowAll(Table[3]);
                            break;
                        case "instructors":
                            System.out.println(Table[4]);
                            db.ShowAll(Table[4]);
                            break;
                        case "sessions":
                            System.out.println(Table[5]);
                            db.ShowAll(Table[5]);
                            break;
                        case "enrollments":
                            System.out.println(Table[6]);
                            db.ShowAll(Table[6]);
                            break;
                        default:
                            System.out.println("Invalid table name.");
                            break;
                    }
                    
                    break;

//                case "add":
//                    System.out.println("enter student name you want to add:");
//                    String nameToAdd = scanner.nextLine();
//                    Add add = new Add(stmt);
//                    add.addStudent(nameToAdd);
//                    break;
                case "delete":
                    System.out.println("Select an action: delstudent, delteacher, " +
                            "delcourse, delsession, delinstructor, delenrollment");
                    String deleteCommand = scanner.nextLine();
                    switch (deleteCommand.toLowerCase()) {
                        case "delstudent":
                            System.out.println("Enter student you want to delete:");
                            int studentIdToDelete = Integer.parseInt(scanner.nextLine());
                            Delete delete = new Delete(stmt);
                            delete.delStudent(studentIdToDelete);
                            break;
                        case "delteacher":
                            System.out.println("Enter teacher you want to delete:");
                            int teacherIdToDelete = Integer.parseInt(scanner.nextLine());
                            Delete deleteTeacher = new Delete(stmt);
                            deleteTeacher.delTeacher(teacherIdToDelete);
                            break;
                        case "delcourse":
                            System.out.println("Enter course you want to delete:");
                            int courseIdToDelete = Integer.parseInt(scanner.nextLine());
                            Delete deleteCourse = new Delete(stmt);
                            deleteCourse.delCourse(courseIdToDelete);
                            break;
                        case "delsession":
                            System.out.println("Enter session you want to delete:");
                            int sessionIdToDelete = Integer.parseInt(scanner.nextLine());
                            Delete deleteSession = new Delete(stmt);
                            deleteSession.delSession(sessionIdToDelete);
                            break;
                        case "delinstructor":
                            System.out.println("Enter instructorID you want to delete:");
                            int instructorIdToDelete = Integer.parseInt(scanner.nextLine());
                            System.out.println("Enter teacherID you want to delete:");
                            int relatedTeacherIdToDelete = Integer.parseInt(scanner.nextLine());
                            Delete deleteInstructor = new Delete(stmt);
                            deleteInstructor.delInstructor(instructorIdToDelete, relatedTeacherIdToDelete);
                            break;
                        case "delenrollment":
                            System.out.println("Enter enrollmentID you want to delete:");
                            int enrollmentIdToDelete = Integer.parseInt(scanner.nextLine());
                            System.out.println("Enter studentID you want to delete:");
                            int relatedStudentIdToDelete = Integer.parseInt(scanner.nextLine());
                            Delete deleteEnrollment = new Delete(stmt);
                            deleteEnrollment.delEnrollment(enrollmentIdToDelete, relatedStudentIdToDelete);
                            break;
                        default:
                            System.out.println("Invalid command. Please enter again.");
                    }
                    break;
                case "edit":
                    System.out.println("Select an action: updatestudent, updateteacher, " +
                            "updatecourse, updatesession, updateinstructor, updateenrollment");
                    String UpdateCommand = scanner.nextLine();
                    switch (UpdateCommand.toLowerCase()) {
                        case "updatestudent":
                            System.out.println("Enter student you want to update:");
                            int studentIdToUpdate = Integer.parseInt(scanner.nextLine());
                            System.out.println("Enter new name:");
                            String newStudentName = scanner.nextLine();
                            Edit UpdateStudent = new Edit(stmt);
                            UpdateStudent.updateStudent(studentIdToUpdate, newStudentName);
                            break;
                        case "updateteacher":
                            System.out.println("Enter teacher you want to update:");
                            int teacherIdToUpdate = Integer.parseInt(scanner.nextLine());
                            System.out.println("Enter new name:");
                            String newTeacherName = scanner.nextLine();
                            Edit UpdateTeacher = new Edit(stmt);
                            UpdateTeacher.updateTeacher(teacherIdToUpdate, newTeacherName);
                            break;
                        case "updatecourse":
                            System.out.println("Enter course you want to update:");
                            int courseIdToUpdate = Integer.parseInt(scanner.nextLine());
                            System.out.println("Enter new course name:");
                            String newCourseName = scanner.nextLine();
                            Edit UpdateCourse = new Edit(stmt);
                            UpdateCourse.updateCourse(courseIdToUpdate, newCourseName);
                            break;
                        case "updatesession":
                            System.out.println("Enter sessionID you want to update:");
                            int sessionIdToUpdate = Integer.parseInt(scanner.nextLine());
                            System.out.println("Enter new courseID:");
                            int newCourseId = Integer.parseInt(scanner.nextLine());
                            System.out.println("Enter new time:");
                            String newTime = scanner.nextLine();
                            System.out.println("Enter new location:");
                            String newLocation = scanner.nextLine();
                            Edit UpdateSessions = new Edit(stmt);
                            UpdateSessions.updateSession(sessionIdToUpdate, newCourseId, newTime, newLocation);
                            break;
                        default:
                            System.out.println("Invalid command. Please enter again.");
                    }
                    break;
                case "exit":
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return;
                default:
                    System.out.println("Invalid command. Please enter again.");
            }}
        }
}
