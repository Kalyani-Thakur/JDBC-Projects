package EmployeeApp;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws SQLException, InterruptedException {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println(EmployeeDao.YELLOW + "\n________________________________________");
            System.out.println("|         Employee Management          |");
            System.out.println("----------------------------------------");

            System.out.println("1. Add Employee                        |");
            System.out.println("2. View All Employees                  |");
            System.out.println("3. View Employee by ID                 |");
            System.out.println("4. Update Employee (Name + Salary)     |");
            System.out.println("5. Update Full Employee Details        |");
            System.out.println("6. Delete Employee                     |");
            System.out.println("7. Exit                                |");
            System.out.println("________________________________________" + EmployeeDao.RESET);

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.println(EmployeeDao.GREEN + "\nEnter Employee Details :-" + EmployeeDao.RESET);
                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    System.out.print("Position: ");
                    String position = sc.nextLine();

                    System.out.print("Department: ");
                    String department = sc.nextLine();

                    System.out.print("Enter Joining Date (yyyy-MMM-dd): ");
                    String input = sc.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
                    LocalDate localDate = LocalDate.parse(input, formatter);
                    Date joiningDate = Date.valueOf(localDate);

                    System.out.print("Salary: ");
                    double salary = sc.nextDouble();
                    sc.nextLine();

                    Employee emp = new Employee(name, email, position, department, joiningDate, salary);

                    EmployeeDao.createEmployee(emp);
                    break;

                case 2:
                    EmployeeDao.readAllEmployee();
                    break;

                case 3:
                    
        System.out.println(EmployeeDao.GREEN+"\nView respective employee detail:-"+EmployeeDao.RESET);
                    System.out.print("Enter Emplyee ID : ");
                    int empId = sc.nextInt();

                    EmployeeDao.readEmployeeById(empId);
                    break;

                case 4:
                    System.out.println(EmployeeDao.GREEN + "Updating Employee Name and Salary :-" + EmployeeDao.RESET);
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String name1 = sc.nextLine();

                    System.out.print("Enter New Salary: ");
                    double salary1 = sc.nextDouble();

                    Employee emp1 = new Employee(id, name1, salary1);

                    EmployeeDao.updateEmployee(emp1);
                    System.out.println(EmployeeDao.GREEN + "Employee name & salary updated successfully!" + EmployeeDao.RESET);
                    break;

                case 5:
                    System.out.println(EmployeeDao.GREEN + "Updating Employee All Details :-" + EmployeeDao.RESET);
                    System.out.print("Enter ID: ");
                    int id1 = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String NewName = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String NewEmail = sc.nextLine();

                    System.out.print("Enter Salary: ");
                    double NewSalary = sc.nextDouble();
                    sc.nextLine();

                    System.out.print("Enter Position: ");
                    String NewPosition = sc.nextLine();

                    System.out.print("Enter Department: ");
                    String NewDepartment = sc.nextLine();

                    Employee emp2 = new Employee(NewName, NewEmail, NewPosition, NewDepartment, null, NewSalary);

                    EmployeeDao.updateEmployeeFull(emp2, id1);
                    System.out.println(EmployeeDao.GREEN + "Employee Details updated successfully!" + EmployeeDao.RESET);
                    break;

                case 6:
                    System.out.println(EmployeeDao.GREEN+"\nDelete respective employee data:-"+EmployeeDao.RESET);
                    System.out.print("Enter ID: ");
                    int ID = sc.nextInt();

                    Employee deletEmp = new Employee(ID);
                    EmployeeDao.deleteEmployee(deletEmp);
                    System.out.println(EmployeeDao.GREEN + "Employee data deleted successfully!" + EmployeeDao.RESET);
                    break;

                case 7:
                    EmployeeDao.Exit();
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again");
            }
        }

    }

}










 // if (choice < 1 || choice >= 8) {
            // System.out.println("Invalid number!");
            // break;
            // }