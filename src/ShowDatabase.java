
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowDatabase {

    private final String DB_NAME = "../lib/sample.db";

    private Connection c = null;

    private Statement stmt = null;

    public ShowDatabase(){
        try{
            Class.forName("org.sqlite.JDBC");
            this.c = DriverManager.getConnection("jdbc:sqlite:" + this.DB_NAME);

            this.stmt = c.createStatement();
        }catch (Exception e){
            System.out.println(e);
        }
    }

//    public Database(String dbname){
//        try{
//            Class.forName("org.sqlite.JDBC");
//            this.c = DriverManager.getConnection("jdbc:sqlite:" + dbname);
//            this.stmt = c.createStatement();
//        }catch (Exception e){
//            System.out.println(e);
//        }
//    }

    public void createTable(String tablename){
        try{
            this.stmt.executeQuery(
                "CREATE TABLE " + tablename +
                "(id INTEGER,value INTEGER,description STRING)");
            this.c.commit();
        }catch (SQLException e){
           System.out.println("------------------------------------------------");
           System.out.println(tablename+ " already exists.");
           System.out.println("------------------------------------------------");
        }
    }

    public void readTable(int StudentID, String Table){
        try{
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + Table +" WHERE StudentID = " + StudentID);
            while (rs.next()) {
                String name = rs.getString("Name"); // get data of value col.
                System.out.println("id:" + StudentID + ", name:" + name); // print out the data.
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void ShowAll(String Table) {
        try {
            String query = "";
            if (Table.equals("CourseInfo")) {
                query = "SELECT Courses.CourseID, Courses.Name AS CourseName, Sessions.Time, Sessions.Location, " +
                        "GROUP_CONCAT(Teachers.TeacherID) AS TeacherIDs, GROUP_CONCAT(Teachers.Name) AS TeacherNames " +
                        "FROM Courses " +
                        "JOIN Sessions ON Courses.CourseID = Sessions.CourseID " +
                        "JOIN Instructors ON Courses.CourseID = Instructors.CourseID " +
                        "JOIN Teachers ON Instructors.TeacherID = Teachers.TeacherID " +
                        "GROUP BY Courses.CourseID, Sessions.SessionID";
            } else {
                query = "SELECT * FROM " + Table;
            }
    
            ResultSet rs = stmt.executeQuery(query);
    
            if (Table.equals("CourseInfo")) {
                while (rs.next()) {
                    int CourseID = rs.getInt("CourseID");
                    String CourseName = rs.getString("CourseName");
                    int Time = rs.getInt("Time");
                    String Location = rs.getString("Location");
                    String TeacherIDs = rs.getString("TeacherIDs");
                    String TeacherNames = rs.getString("TeacherNames");
    
                    System.out.println("CourseID: " + CourseID + ", CourseName: " + CourseName +
                            ", Time: " + Time + ", Location: " + Location +
                            ", TeacherIDs: " + TeacherIDs + ", TeacherNames: " + TeacherNames);
                }
            } else if (Table.equals("Students")) {
                while (rs.next()) {
                    int StudentID = rs.getInt("StudentID");
                    String name = rs.getString("Name");
                    System.out.println("StudentID:" + StudentID + ", name:" + name);
                }
            } else if (Table.equals("Teachers")) {
                while (rs.next()) {
                    int TeacherID = rs.getInt("TeacherID");
                    String name = rs.getString("Name");
                    System.out.println("TeacherID:" + TeacherID + ", name:" + name);
                }
            } else if (Table.equals("Courses")) {
                while (rs.next()) {
                    int CourseID = rs.getInt("CourseID");
                    String name = rs.getString("Name");
                    System.out.println("CourseID:" + CourseID + ", name:" + name);
                }
            } else if (Table.equals("Sessions")) {
                while (rs.next()) {
                    int SessionID = rs.getInt("SessionID");
                    String CourseID = rs.getString("CourseID");
                    int Time = rs.getInt("Time");
                    String Location = rs.getString("Location");
                    System.out.println("SessionID:" + SessionID + ", CourseID:" + CourseID + ", Time:" + Time + ", Location:" + Location);
                }
            } else if (Table.equals("Enrollments")) {
                while (rs.next()) {
                    String CourseID = rs.getString("CourseID");
                    String StudentID = rs.getString("StudentID");
                    System.out.println("CourseID:" + CourseID + ", StudentID:" + StudentID);
                }
            } else if (Table.equals("Instructors")) {
                while (rs.next()) {
                    String CourseID = rs.getString("CourseID");
                    String TeacherID = rs.getString("TeacherID");
                    System.out.println("CourseID:" + CourseID + ", TeacherID:" + TeacherID);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     public void searchTable(String column, String searchValue, String Table) {
        try {
            String query;
            if (Table.equals("CourseInfo")) {
                query = "SELECT Courses.CourseID, Courses.Name AS CourseName, Sessions.Time, Sessions.Location, " +
                        "GROUP_CONCAT(Teachers.TeacherID) AS TeacherIDs, GROUP_CONCAT(Teachers.Name) AS TeacherNames " +
                        "FROM Courses " +
                        "JOIN Sessions ON Courses.CourseID = Sessions.CourseID " +
                        "JOIN Instructors ON Courses.CourseID = Instructors.CourseID " +
                        "JOIN Teachers ON Instructors.TeacherID = Teachers.TeacherID " +
                        "WHERE " + column + " LIKE ? " +
                        "GROUP BY Courses.CourseID, Sessions.SessionID";
            } else {
                query = "SELECT * FROM " + Table + " WHERE " + column + " LIKE ?";
            }

            PreparedStatement pstmt = c.prepareStatement(query);
            pstmt.setString(1, "%" + searchValue + "%");

            // クエリの実行
            ResultSet rs = pstmt.executeQuery();

            // 結果セットの処理
            if (Table.equals("Courses")) {
                while (rs.next()) {
                    int CourseID = rs.getInt("CourseID");
                    String Name = rs.getString("Name");
                    System.out.println("CourseID: " + CourseID + ", Name: " + Name);
                }
            } else if (Table.equals("Sessions")) {
                while (rs.next()) {
                    int SessionID = rs.getInt("SessionID");
                    int CourseID = rs.getInt("CourseID");
                    String Time = rs.getString("Time");
                    String Location = rs.getString("Location");
                    System.out.println("SessionID: " + SessionID + ", CourseID: " + CourseID + ", Time: " + Time + ", Location: " + Location);
                }
            } else if (Table.equals("Teachers")) {
                while (rs.next()) {
                    int TeacherID = rs.getInt("TeacherID");
                    String Name = rs.getString("Name");
                    System.out.println("TeacherID: " + TeacherID + ", Name: " + Name);
                }
            } else if (Table.equals("Students")) {
                while (rs.next()) {
                    int StudentID = rs.getInt("StudentID");
                    String Name = rs.getString("Name");
                    System.out.println("StudentID: " + StudentID + ", Name: " + Name);
                }
            } else if (Table.equals("Instructors")) {
                while (rs.next()) {
                    int CourseID = rs.getInt("CourseID");
                    int TeacherID = rs.getInt("TeacherID");
                    System.out.println("CourseID: " + CourseID + ", TeacherID: " + TeacherID);
                }
            } else if (Table.equals("Enrollments")) {
                while (rs.next()) {
                    int CourseID = rs.getInt("CourseID");
                    int StudentID = rs.getInt("StudentID");
                    System.out.println("CourseID: " + CourseID + ", StudentID: " + StudentID);
                }
            } else if (Table.equals("CourseInfo")) {
                while (rs.next()) {
                    int CourseID = rs.getInt("CourseID");
                    String CourseName = rs.getString("CourseName");
                    String Time = rs.getString("Time");
                    String Location = rs.getString("Location");
                    String TeacherIDs = rs.getString("TeacherIDs");
                    String TeacherNames = rs.getString("TeacherNames");
                    System.out.println("CourseID: " + CourseID + ", CourseName: " + CourseName + ", Time: " + Time + ", Location: " + Location + ", TeacherIDs: " + TeacherIDs + ", TeacherNames: " + TeacherNames);
                }
            }

            // リソースのクローズ
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        

    public void readCoursesTable(){
        try{
            ResultSet rs = stmt.executeQuery(
                    "SELECT Course.ID, Course.Name AS CourseName, Teachers.Name AS TeachersName,   FROM Courses " +
                            "JOIN Instructors ON Courses.TeacherID = Teachers.TeacherID" +
                            "JOIN Teachers ON Courses.TeacherID = Teachers.TeacherID " +
                            "JOIN Sessions ON Courses.CourseID = Sessions.CourseID"
            );
            while (rs.next()) {
                String name = rs.getString("Name"); // get data of value col.
                System.out.println("id:" + ", name:" + name); // print out the data.
            }
            rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void insertData(String tablename,int id, int value, String description){
        try{
            this.stmt.executeUpdate(
                "INSERT INTO " + tablename +
                "(\"id\",\"value\",\"description\")"
                +
                "VALUES("
                + id + ","
                + value + ","
                + "\"" +  description + "\")"
                );

            System.out.println("id"  + "->" + id + ", value"  + "->" + value +  ", description"  + "->" + description + " for " + tablename );

        }catch (SQLException e){
          System.out.println(e);
        }
    }

    public void updateTable(String tablename,int id){
        try{
            this.stmt.executeUpdate(
                "UPDATE " + tablename + " SET " +
                "description = \" updated description.\" WHERE id = "
                +
                id
                );
        }catch (SQLException e){
          System.out.println(e);
        }
    }



    public void dropTable(String tablename){
        try{
            this.stmt.executeUpdate(
                "DROP TABLE " + tablename
                );
        }catch (SQLException e){
          System.out.println(e);
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

    public static void main(String[] args) {
        ShowDatabase db = new ShowDatabase();

        String tablename = "table1";
        //db.readTable(2,"Students");
        //db.readTable(1,"Enrollments");

        /* 
        db.ShowAll("Sessions");
        db.ShowAll("Teachers");
        db.ShowAll("Instructors");
        db.ShowAll("Courses");
        db.ShowAll("Sessions");
        db.ShowAll("Enrollments");
        */
        String[] Table = {"CourseInfo","Students","Teachers","Courses","Instructors","Sessions","Enrollments"};
        for(int i=0;i<7;i++){
            System.out.println(Table[i]);
            db.ShowAll(Table[i]);
        }
        //db.readCoursesTable();



//        db.createTable(tablename);
//        for (int i = 1; i < 11; i++) {
//            db.insertData(tablename, i,i*100 , "description " + i);
//        }
//
//        db.updateTable(tablename, 1);
//
//        db.readTable(tablename);
//
//        db.dropTable(tablename);
//
//        db.closeConnection();
    }

}
