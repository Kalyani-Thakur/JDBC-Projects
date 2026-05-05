package EmployeeApp;

import java.sql.Date;

public class Employee {

    private int id;
    private String name;
    private String email;
    private String position;
    private String department;
    private Date joiningDate;
    private double salary;

    // Constructor for INSERT
    public Employee(String name, String email, String position, String department,Date joiningDate, double salary) {
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.joiningDate = joiningDate;
        this.position = position;
        this.department = department;
    }

    // Constructor for UPDATE (example: update name)
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Constructor for DELETE Employee
    public Employee(int id){
        this.id = id;
    }
    
    // Getters
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }
    
    public Date getJoiningDate() {
        return joiningDate;
    }

    public double getSalary() {
        return salary;
    }
    
}


