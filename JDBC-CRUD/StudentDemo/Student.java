package StudentDemo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Student {
    public void createDatabase() {

        try {
             String url = "jdbc:mysql://localhost:3306/";
            String userName = "YourUsername";
            String password = "YourPassword";

            Connection conn = DriverManager.getConnection(url, userName, password); 
            Statement stm = conn.createStatement();

            String query = "Create database codingwallah";
            stm.execute(query);
            System.out.println("Database created successfully");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try {
            
            String url = "jdbc:mysql://localhost:3306/CodingWallah";
            String userName = "YourUsername";
            String password = "YourPassword";

            Connection conn = DriverManager.getConnection(url, userName, password);
            Statement stm = conn.createStatement();

            String query = "CREATE table student (sid int(3), sname varchar(200), semail varchar(200)) ";

            stm.execute(query);
            System.out.println("Table created successfully");

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createData() {
        try {
            
            String url = "jdbc:mysql://localhost:3306/";
            String db = "codingwallah";
            String userName = "YourUsername";
            String password = "YourPassword";

            Connection conn = DriverManager.getConnection(url + db, userName, password);

            String query = "INSERT into student (sid, sname, semail) VALUES (?, ?, ?)";

            PreparedStatement pstm = conn.prepareStatement(query); 
            pstm.setInt(1, 13); 
            pstm.setString(2, "Dim"); 
            pstm.setString(3, "dim@gmail.com"); 

            // pstm.executeUpdate(); 
            pstm.execute(); 
            // pstm.executeQuery();

            System.out.println("Table created successfully");

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readData() {
        try {
            
            String url = "jdbc:mysql://localhost:3306/";
            String db = "codingwallah";
            String userName = "YourUsername";
            String password = "YourPassword";

            Connection conn = DriverManager.getConnection(url + db, userName, password);

            String query = "Select * from student";

            Statement stm = conn. createStatement(); 

            ResultSet rs = stm.executeQuery(query); 
            while (rs.next()) {
                System.out.println("id = "+rs.getInt(1));
                System.out.println("name = "+rs.getString(2));
                System.out.println("email = "+rs.getString(3));
            }

            System.out.println("Read successfully ");
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateData() {
        try {
            
            String url = "jdbc:mysql://localhost:3306/";
            String db = "codingwallah";
            String userName = "YourUsername";
            String password = "YourPassword";

            Connection conn = DriverManager.getConnection(url + db, userName, password);

            String query = "UPDATE student set sid = ? where sname = ?";

            PreparedStatement pstm = conn.prepareStatement(query); 
            pstm.setInt(1, 11); 
            pstm.setString(2, "shivam"); 
 
            pstm.execute(); 

            System.out.println("data updated successfully");

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteData() {
        try {
            
            String url = "jdbc:mysql://localhost:3306/CodingWallah";
            String userName = "YourUsername";
            String password = "YourPassword";

            Connection conn = DriverManager.getConnection(url, userName, password);
            Statement stm = conn.createStatement();

            String query = "DELETE from student where sid = 1";
            String query2 = "DELETE from student where sid = 11";

            stm.execute(query);
            stm.execute(query2);
            System.out.println("data deleted successfully");

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

