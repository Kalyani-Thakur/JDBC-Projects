import java.sql.SQLException;
import java.util.Scanner;

public class MainStore {

    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) throws SQLException, InterruptedException {

        while (true) {
            System.out.println(GREEN+"_____________________________________________________________");
            System.out.println("________________WELCOME TO THE MEDICINE STORE________________"+RESET);
            System.out.println("_____________________________________________________________");
            System.out.println("|      1. Buy Medicine      ||      2. VIEW                 |");
            System.out.println("_____________________________________________________________");
            System.out.println("|      3. UPDATE            ||      0. Exit Store           |");
            System.out.println("_____________________________________________________________");

            Scanner sc = new Scanner(System.in);
            System.out.print(GREEN+"\nEnter your choice: "+RESET);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    MedicineDao.BuyMedicines();
                    break;

                case 2:
                    MedicineDao.VIEW();
                    break;  

                case 3:
                    MedicineDao.UPDATE();
                break;
                
                case 0:
                    MedicineDao.Exit();
                    sc.close();
                    return;
            
                default:
                System.out.println(RED+"Invalid input try again!"+RESET);
                    break;
            }
        }
    }
}











// ==============+===============+==================+==========+
/*
______WELCOME TO THE MEDICINE STORE______
1. Buy Medicine
2. View [1. View Customer, 2. View Medicines, 3. View Purchase]
3. View Customer & Medicine Details
4. View All Customers with Total Price
5. Update [1. Update Customer Info, 2. Add Medicines, 3. Restock Medicines]
6. Delete Medicines
7. Payment / Clear Due Amount
8. View Low Stock Medicines
9. Search [1. Customer, 2. Medicine]
10. Generate Bill / Print Receipt
0. Exit Store


public static void updateCoustomerWithMedicines() {
        System.out.println("\nWhat do you want to Update?");
        System.out.println("1. Update Customer Name and Mobile Number.");
        System.out.println("2. Add Medicines");
        System.out.println("Enter 1 or 2 :- ");
        int option = sc.nextInt();
        sc.nextLine(); // consume newline

        try (Connection conn = StoreDB.connect()) {
            if (option == 1) {
                // --- Update Customer ---
                System.out.print("Enter customer id: ");
                int c_ID = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Customer new name: ");
                String newcName = sc.nextLine();

                System.out.print("Enter Customer new MobileNo.: ");
                String newcMobile = sc.nextLine();

                String updateQuery = "UPDATE Customer SET c_Name = ?, c_MobileNo = ? WHERE c_Id = ?";
                PreparedStatement pstm = conn.prepareStatement(updateQuery);
                pstm.setString(1, newcName);
                pstm.setString(2, newcMobile);
                pstm.setInt(3, c_ID);

                pstm.executeUpdate();
                System.out.println("Customer new information updated!");
            } else if (option == 2) {
                // --- Insert Medicines ---
                System.out.print("Enter customer id: ");
                int c_ID = sc.nextInt();
                sc.nextLine();

                System.out.print("How many medicines (5-10)? ");
                int n = sc.nextInt();
                sc.nextLine();

                String insertMedQuery = "INSERT INTO Medicine (c_Id, m_Name, p_Med) VALUES (?, ?, ?)";
                PreparedStatement newMed = conn.prepareStatement(insertMedQuery);

                for (int i = 1; i <= n; i++) {
                    System.out.print("Enter medicine " + i + " name: ");
                    String newMedName = sc.nextLine();

                    System.out.print("Enter price of " + newMedName + ": ");
                    double price = sc.nextDouble();
                    sc.nextLine();

                    newMed.setInt(1, c_ID); // customer id
                    newMed.setString(2, newMedName); // medicine name
                    newMed.setDouble(3, price); // price
                    newMed.executeUpdate();
                }
                System.out.println("Medicines inserted successfully!");
            } else {
                System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void deleteMedicine() {
        try (Connection conn = StoreDB.connect()) {
            System.out.print("Enter Customer ID: ");
            int c_Id = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Medicine ID to delete: ");
            int m_Id = sc.nextInt();
            sc.nextLine();

            // Step 1: Update customer's left_price by subtracting the medicine's price
            String updateQuery = "UPDATE Customer c " +
                    "JOIN Medicine m ON c.c_Id = m.c_Id " +
                    "SET c.left_amount = c.left_amount - m.p_Med " +
                    "WHERE m.m_Id=? AND c.c_Id=?";
            PreparedStatement ps1 = conn.prepareStatement(updateQuery);
            ps1.setInt(1, m_Id);
            ps1.setInt(2, c_Id);
            ps1.executeUpdate();

            // Step 2: Delete the medicine
            String delQuery = "DELETE FROM Medicine WHERE m_Id=? AND c_Id=?";
            PreparedStatement ps2 = conn.prepareStatement(delQuery);
            ps2.setInt(1, m_Id);
            ps2.setInt(2, c_Id);
            ps2.executeUpdate();

            System.out.println("Medicine deleted and price updated successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCustomer() {
        System.out.println("Deleting Customer Details with Medicines\n");
        System.out.print("Enter Customer ID: ");
        int c_Id = sc.nextInt();
        sc.nextLine();

        try (Connection conn = StoreDB.connect()) {

            String deleteCQuery = "DELETE c, m " +
                    "FROM Customer c " +
                    "LEFT JOIN Medicine m ON c.c_Id = m.c_Id " +
                    "WHERE c.c_Id = ?";

            PreparedStatement pstm = conn.prepareStatement(deleteCQuery);
            pstm.setInt(1, c_Id);
            int rowsDeleted = pstm.executeUpdate();

            System.out.println("Deleted " + rowsDeleted + " record(s) successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/

