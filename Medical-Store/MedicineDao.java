import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MedicineDao {
    static Scanner sc = new Scanner(System.in);
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    public static void BuyMedicines() throws SQLException {

        // Step 1: Insert into Customer table
        System.out.print("Enter Customer Name: ");
        String cName = sc.nextLine();

        System.out.print("Enter Customer Mobile No.: ");
        String cMobile = sc.nextLine();

        try (Connection conn = StoreDB.connect()) {

            String custQuery = "INSERT INTO Customer (c_Name, c_MobileNo) VALUES (?, ?)";
            PreparedStatement custStmt = conn.prepareStatement(custQuery, Statement.RETURN_GENERATED_KEYS);
            custStmt.setString(1, cName);
            custStmt.setString(2, cMobile);
            custStmt.executeUpdate();

            ResultSet rs = custStmt.getGeneratedKeys();
            rs.next();
            int customerId = rs.getInt(1);

            // Step 2: Show available medicines
            String showQuery = "SELECT m_Id, m_Name, m_Price, m_Stock FROM Medicine";
            Statement showStmt = conn.createStatement();
            ResultSet medList = showStmt.executeQuery(showQuery);

            System.out.println(GREEN + "\nAvailable Medicines:" + RESET);
            System.out.println("+------+--------------------------------+----------+----------+");
            System.out.println("| M_Id |          Medicine_Name         | Price    | Stock    |");
            System.out.println("+------+--------------------------------+---------------------+");

            while (medList.next()) {
                System.out.printf("| %-4d | %-30s | %-8.2f | %-8d |\n",
                        medList.getInt("m_Id"),
                        medList.getString("m_Name"),
                        medList.getDouble("m_Price"),
                        medList.getInt("m_Stock"));
            }
            System.out.println("+------+--------------------------------+---------------------+");

            // Step 3: Let the customer choose medicines and quantities
            System.out.print(GREEN+"How many different medicines to buy? "+RESET);
            // System.out.print(RESET);
            int count = sc.nextInt();

            String purchaseQuery = "INSERT INTO Purchase (c_Id, m_Id, quantity, total_price) VALUES (?, ?, ?, ?)";
            PreparedStatement purchaseStmt = conn.prepareStatement(purchaseQuery);

            for (int i = 0; i < count; i++) {
                System.out.print("Enter Medicine ID: ");
                int mid = sc.nextInt();
                System.out.print("Enter Quantity: ");
                int qty = sc.nextInt();

                // Get medicine price
                String priceQuery = "SELECT m_Price, m_Stock FROM Medicine WHERE m_Id = ?";
                PreparedStatement priceStmt = conn.prepareStatement(priceQuery);
                priceStmt.setInt(1, mid);
                ResultSet priceRs = priceStmt.executeQuery();

                if (priceRs.next()) {
                    double price = priceRs.getDouble("m_Price");
                    int stock = priceRs.getInt("m_Stock");

                    if (qty > stock) {
                        System.out.println(RED + "Not enough stock available!" + RESET);
                        continue;
                    }

                    double total = qty * price;

                    // Insert into purchase table
                    purchaseStmt.setInt(1, customerId);
                    purchaseStmt.setInt(2, mid);
                    purchaseStmt.setInt(3, qty);
                    purchaseStmt.setDouble(4, total);
                    purchaseStmt.executeUpdate();

                    // Update stock
                    String updateStock = "UPDATE Medicine SET m_Stock = m_Stock - ? WHERE m_Id = ?";
                    PreparedStatement updateStmt = conn.prepareStatement(updateStock);
                    updateStmt.setInt(1, qty);
                    updateStmt.setInt(2, mid);
                    updateStmt.executeUpdate();

                    // System.out.println(YELLOW+"Added " + qty + " of medicine ID " + mid + " to purchase."+RESET);
                    System.out.println(YELLOW+qty+ " Medicine with ID " + mid +" have been added to the cart."+RESET);
                    
                } else {
                    System.out.println(RED + "Invalid medicine ID!" + RESET);
                }
            }

            // Step 4: Confirm total bill
            String totalQuery = "SELECT SUM(total_price) AS TotalBill FROM Purchase WHERE c_Id = ?";
            PreparedStatement totalStmt = conn.prepareStatement(totalQuery);
            totalStmt.setInt(1, customerId);
            ResultSet totalRs = totalStmt.executeQuery();
            if (totalRs.next()) {
                System.out
                        .println(YELLOW +"\nTotal Bill for Customer ID " + customerId + ": " + totalRs.getDouble("TotalBill")+RESET);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // View Customer with Medicine Names
    public static void VIEW() throws SQLException {
        int option1;
        do {
            System.out.println(GREEN + "\n_____________Viewing System_____________" + RESET);
            System.out.println("1. View Customer Full Detail");
            System.out.println("2. ALL Customer with Purchases Detail");
            System.out.println("3. View Medicine's");
            System.out.println("0. Exit View System");

            System.out.print(GREEN+ "\nChoose option: " +RESET);
            option1 = sc.nextInt();

            switch (option1) {
                case 1:
                    ViewSystem.viewCustomerFullDetail();
                    break;
                case 2:
                    ViewSystem.viewAllCustomerWithPurchase();
                    break;
                case 3:
                    ViewSystem.ViewMedicines();
                    break;
                case 0:
                    System.out.println(YELLOW+"Exiting View System..."+RESET);
                    break;
                default:
                    System.out.println(RED + "Invalid input, try again!" + RESET);
                    break;
            }
        } while (option1 != 0);
    }

    public static void UPDATE() throws SQLException {
        int option2;

        do {
            System.out.println(GREEN+"\n_____UPDATE SYSTEM_____"+RESET);
            System.out.println("1. Add Medicines");
            System.out.println("2. Restock Medicine");
            System.out.println("0. Exit");

            System.out.print(GREEN+"Enter your choice: "+RESET);
            option2 = sc.nextInt();

            switch (option2) {
                case 1:
                    UpdateSystem.AddMedicines();
                    break;

                case 2:
                    UpdateSystem.RestockMedicine();
                    break;

                case 0:
                    System.out.println(YELLOW+"Exiting UPDATE System..."+RESET);
                    break;

                default:
                    System.out.println(RED+"Invalid input, try again!"+RESET);
                    break;
            }
        } while (option2 != 0);

    }

    public static void Exit() throws InterruptedException {
        System.out.print(YELLOW+"Exiting Store");
        int i = 5;
        while (i != 0) {
            System.out.print(".");
            Thread.sleep(1000);
            i--;
        }
        System.out.println("\nThank you for choosing us.\nWe appreciate your trust."+RESET);

    }

}

// public static void payingBill(int customerId, double total) throws
// SQLException {
// String confirm;
// System.out.print("\nDo you want to pay ? ");
// confirm = sc.nextLine();
// if (confirm.equalsIgnoreCase("yes")) {
// System.out.print("\nPay Medicine Bill: ");
// double pay = sc.nextDouble();

// if (pay > total) {
// System.out.println("You are paying more! Kindly pay the correct amount.");
// return;
// }

// total = total - pay;

// Connection conn = StoreDB.connect();
// String query = "UPDATE Customer SET left_amount=? WHERE c_Id=?";
// PreparedStatement pstm = conn.prepareStatement(query);
// pstm.setDouble(1, total);
// pstm.setInt(2, customerId);
// pstm.executeUpdate();

// if (total == 0) {
// System.out.println("Thank you! Your bill is fully paid.");
// } else {
// System.out.println("You paid " + pay + ". Remaining balance: " + total);
// }

// } else {
// System.out.println("Pay next time!");
// }
// }


// Deleting system
    // public static void DELETE() {
        
    //      _____DELETE SYSTEM_____
         
    //      1. Delete Customer
    //      2. Delete Medicine
    //      3. Delete Purchase
    //      4. Delete All Data
    //      0. Exit
         

    // }