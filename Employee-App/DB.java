package EmployeeApp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    static Connection conn = null;
    public static Connection connect()
    {
        try {
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "EmployeeApp"; 
            String userName = "username";
            String password = "password";  

            conn  = DriverManager.getConnection(url+dbName, userName, password);
        
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
