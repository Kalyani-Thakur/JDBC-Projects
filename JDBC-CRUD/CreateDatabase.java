import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateDatabase {
    public static void main(String[] args) {
    //  Connecting database  

        // conn establish
        // statement create
        // execute - query
        // conn close

        try {
            String url = "jdbc:mysql://localhost:3306/";
            String userName = "YourUsername";
            String password = "YourPassword";

            Connection conn = DriverManager.getConnection(url, userName, password); 
            Statement stm = conn.createStatement();

            String query = "Create database mydatabase";
            boolean bl = stm.execute(query);

            System.out.println("Database created successfully = " +bl);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

