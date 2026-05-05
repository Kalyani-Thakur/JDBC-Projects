package EmployeeApp;

public class Query {
    
    static String insert = "INSERT INTO employee (name, email, salary, joining_date, position, department) VALUES (?,?,?,?,?,?)";

    static String update = "UPDATE employee SET name = ?, salary = ? WHERE id = ?";

    static String updateFull = "UPDATE employee SET name=?, email=?, salary=?, position=?, department=? WHERE id=?";
    
    static String delete = "DELETE FROM employee WHERE id = ?";
    
    static String selectById = "SELECT * FROM employee WHERE id = ?";

    static String selectAll = "SELECT id, name, email, salary, joining_date, position, department FROM employee";
    
    
}






















// static String select = "SELECT * FROM employee";