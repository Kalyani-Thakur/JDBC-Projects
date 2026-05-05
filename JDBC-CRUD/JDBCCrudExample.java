
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class JDBCCrudExample {

    public static void main(String[] args) {

        try {
            // 1. Database Connection
            String url = "jdbc:mysql://localhost:3306/yourdatabasename";
            String username = "your_username";
            String password = "********";

            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Database Connected Successfully");

            Statement stmt = con.createStatement();

            // 2. CREATE (Insert Data)
            String insertQuery = "INSERT INTO student VALUES (1,'Riya',22)";
            int insertRows = stmt.executeUpdate(insertQuery);
            System.out.println(insertRows + " row inserted");

            // 3. READ (Fetch Data)
            String selectQuery = "SELECT * FROM student";
            ResultSet rs = stmt.executeQuery(selectQuery);

            System.out.println("\nStudent Table Data:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");

                System.out.println(id + " " + name + " " + age);
            }

            // 4. UPDATE Data
            String updateQuery = "UPDATE student SET age=23 WHERE id=1";
            int updateRows = stmt.executeUpdate(updateQuery);
            System.out.println(updateRows + " row updated");

            // 5. DELETE Data
            String deleteQuery = "DELETE FROM student WHERE id=1";
            int deleteRows = stmt.executeUpdate(deleteQuery);
            System.out.println(deleteRows + " row deleted");

            // Close connection
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}




