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
            String userName = "root";
            String password = "a$,;24ai<PrMsQI";  

            conn  = DriverManager.getConnection(url+dbName, userName, password);
        
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}