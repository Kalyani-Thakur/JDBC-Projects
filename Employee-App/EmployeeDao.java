package EmployeeApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class EmployeeDao {

    static final String YELLOW = "\u001B[33m";
    static final String GREEN = "\u001B[32m";
    static final String RED = "\u001B[31m";
    static final String RESET = "\u001B[0m";

    // INSERT
    public static void createEmployee(Employee emp) throws SQLException {
        Connection conn = DB.connect();
        String query = Query.insert;

        PreparedStatement prestm = conn.prepareStatement(query);

        prestm.setString(1, emp.getName());
        prestm.setString(2, emp.getEmail());
        prestm.setString(5, emp.getPosition());
        prestm.setString(6, emp.getDepartment());
        prestm.setDate(4, emp.getJoiningDate());
        prestm.setDouble(3, emp.getSalary());

        prestm.executeUpdate();
        System.out.println(GREEN + "Employee details added successfully!" + RESET);
        prestm.close();
        conn.close();
    }

    // SELECT ALL
    public static ResultSet readAllEmployee() throws SQLException {
        Connection conn = DB.connect();
        String query = Query.selectAll;

        PreparedStatement prestm = conn.prepareStatement(query);
        ResultSet rs = prestm.executeQuery();

        System.out.print(GREEN + "\nAll Employee Details :-" + RESET);
        System.out.println("\n__________________________________________________________________________________________________________________________");
        System.out.println("|                                                        ALL EMPLOYEE DETAILS                                            |");
        System.out.println("__________________________________________________________________________________________________________________________");
        System.out.println("| ID | Name             | Email                | Position             | Department           |Joining Date | Salary      |");
        System.out.println("__________________________________________________________________________________________________________________________");
        
        while (rs.next()) {
            
            int empId = rs.getInt("id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String position = rs.getString("position");
            String department = rs.getString("department");
            Date joinDate = rs.getDate("joining_date");
            double salary = rs.getDouble("salary");
            
            System.out.printf("| %-2d | %-16s | %-20s | %-20s | %-20s | %-11s | %-11.2f |\n",
            empId, name, email, position, department, joinDate, salary);
        }
        System.out.println("__________________________________________________________________________________________________________________________");

        rs.close();
        prestm.close();
        conn.close();
                return rs;
    }

    // SELECT BY ID
    public static void readEmployeeById(int id) throws SQLException {
        Connection conn = DB.connect();
        String query = Query.selectById;

        
        PreparedStatement prestm = conn.prepareStatement(query);
        prestm.setInt(1, id);

        ResultSet rs = prestm.executeQuery();
        if (rs.next()) {

        
            System.out.println("| ID | Name             | Email                | Position             | Department           |Joining Date | Salary      |");
            System.out.println("__________________________________________________________________________________________________________________________");
        
        System.out.printf(
            "| %-2d | %-16s | %-20s | %-20s | %-20s | %-11s | %-11.2f |\n",
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("position"),
            rs.getString("department"),
            rs.getDate("joining_date").toString(), 
            rs.getDouble("salary")
        );
      
        System.out.println("__________________________________________________________________________________________________________________________");

        } 
        else {
            System.out.println(EmployeeDao.RED+"Employee not found!"+EmployeeDao.RESET);
        }

        rs.close();
        prestm.close();
        conn.close();
    }

    // UPDATE (name + salary)
    public static void updateEmployee(Employee updateEmp) throws SQLException {
        Connection conn = DB.connect();
        String query = Query.update;

        PreparedStatement prestm = conn.prepareStatement(query);

        prestm.setString(1, updateEmp.getName());
        prestm.setDouble(2, updateEmp.getSalary());
        prestm.setInt(3, updateEmp.getId());

        prestm.executeUpdate();
        prestm.close();
        conn.close();
    }

    // UPDATE FULL
    public static void updateEmployeeFull(Employee emp, int id) throws SQLException {
        Connection conn = DB.connect();
        String query = Query.updateFull;

        PreparedStatement prestm = conn.prepareStatement(query);

        prestm.setString(1, emp.getName());
        prestm.setString(2, emp.getEmail());
        prestm.setDouble(3, emp.getSalary());
        prestm.setString(4, emp.getPosition());
        prestm.setString(5, emp.getDepartment());
        prestm.setInt(6, id);
        
        prestm.executeUpdate();
        prestm.close();
        conn.close();
    }

    // DELETE
    public static void deleteEmployee(Employee delEmp) throws SQLException {
        Connection conn = DB.connect();
        String query = Query.delete;

        PreparedStatement prestm = conn.prepareStatement(query);
        prestm.setInt(1, delEmp.getId());

        prestm.executeUpdate();
        prestm.close();
        conn.close();
    }

    public static void Exit() throws InterruptedException {
        System.out.print(YELLOW+ "Exiting System" +RESET);
        int i = 5;
        while (i != 0) {
            System.out.print(".");
            Thread.sleep(1000);
            i--;
        }
        
    }
}






// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;

// public class EmployeeDao {
// public static void createEmployee(Employee emp) throws SQLException {
// Connection conn = DB.connect();
// String query = Query.insert;
// PreparedStatement prestm = conn.prepareStatement(query);

// prestm.setString(1, emp.getName());
// prestm.setString(2, emp.getEmail());
// prestm.setDouble(3, emp.getSalary());
// prestm.setDate(4, emp.getJoiningDate());
// prestm.setString(5, emp.getPosition());
// prestm.setString(6, emp.getDepartment());

// prestm.executeUpdate();
// prestm.close();
// conn.close();

// }

// public static ResultSet readAllEmployee() throws SQLException {
// Connection conn = DB.connect();
// String query = Query.selectAll;
// PreparedStatement prestm = conn.prepareStatement(query);
// ResultSet rs = prestm.executeQuery();
// return rs;
// }

// public static void updateEmployee(Employee updateEmp) throws SQLException {
// Connection conn = DB.connect();
// String query = Query.update;
// PreparedStatement prestm = conn.prepareStatement(query);

// prestm.setString(1, updateEmp.getUpdatedName());
// prestm.setDouble(2, updateEmp.getSalary());
// prestm.setInt(3, updateEmp.getIdToUpdate());

// prestm.executeUpdate();
// prestm.close();
// conn.close();
// }

// public static void deleteEmployee(Employee delEmp) throws SQLException {
// Connection con = DB.connect();
// String query = Query.delete;
// PreparedStatement prestm = con.prepareStatement(query);
// prestm.setInt(1, delEmp.getIdToDelete());

// prestm.execute();
// prestm.close();

// }

// }
