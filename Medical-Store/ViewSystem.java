import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ViewSystem {
        static Scanner sc = new Scanner(System.in);
        private static final String GREEN = "\u001B[32m";
        private static final String RED = "\u001B[31m";
        private static final String YELLOW = "\u001B[33m";
        private static final String RESET = "\u001B[0m";

        public static void viewCustomerFullDetail() throws SQLException {
                // Scanner sc = new Scanner(System.in);
                System.out.print("Enter Customer ID to View Details: ");
                int cId = sc.nextInt();

                try (Connection conn = StoreDB.connect()) {

                        // CUSTOMER DETAILS
                        String custQuery = "SELECT c_Id, c_Name, c_MobileNo, buy_Date FROM Customer WHERE c_Id = ?";
                        PreparedStatement custStmt = conn.prepareStatement(custQuery);
                        custStmt.setInt(1, cId);
                        ResultSet custRs = custStmt.executeQuery();

                        if (custRs.next()) {
                                System.out.println(GREEN + "\n________________________ CUSTOMER " + cId
                                                + " DETAIL _______________________" + RESET);
                                System.out.println(
                                                "+------+-------------------+-------------------+-------------------------+");
                                System.out.println(
                                                "| C_Id |   Customer_Name   | Customer_MobileNo |           Buy_Date      |");
                                System.out.println(
                                                "+------+-------------------+-------------------+-------------------------+");
                                System.out.printf("| %-4d | %-17s | %-17s | %-23s |\n", //  %-11.2f
                                                custRs.getInt("c_Id"),
                                                custRs.getString("c_Name"),
                                                custRs.getString("c_MobileNo"),
                                                custRs.getString("buy_Date"));
                                                // custRs.getDouble("left_Amount"));
                                System.out.println(
                                                "+------+-------------------+-------------------+-------------------------+");
                        } else {
                                System.out.println(RED + " No customer found with ID: " + cId + RESET);
                                return;
                        }

                        // MEDICINE DETAILS WITH PURCHASE
                        String medQuery = "SELECT m.m_Id, m.m_Name, m.m_Price, " +
                                        "p.quantity, p.total_price, p.purchase_Date " +
                                        "FROM Medicine m " +
                                        "JOIN Purchase p ON m.m_Id = p.m_Id " +
                                        "WHERE p.c_Id = ?";
                        PreparedStatement medStmt = conn.prepareStatement(medQuery);
                        medStmt.setInt(1, cId);
                        ResultSet medRs = medStmt.executeQuery();

                        System.out.println(GREEN + "\n_________ MEDICINES BOUGHT by Customer ID: " + cId + " _________"
                                        + RESET);
                        System.out.println(
                                        "+------+------+---------------------------+------------+----------+-------------+-------------------------+");
                        System.out.println(
                                        "| C_Id | M_Id |     Medicine_Name         | Price_Med  | Quantity | Total_Price |      Purchase_Date      |");
                        System.out.println(
                                        "+------+------+---------------------------+------------+----------+-------------+-------------------------+");
                        double totalPurchaseAmount = 0;

                        boolean hasMed = false;
                        while (medRs.next()) {
                                hasMed = true;
                                int mid = medRs.getInt("m_Id");
                                String mname = medRs.getString("m_Name");
                                double price = medRs.getDouble("m_Price");
                                int quantity = medRs.getInt("quantity");
                                double total = medRs.getDouble("total_price");
                                String pdate = medRs.getString("purchase_Date");

                                System.out.printf("| %-4d | %-4d | %-25s | %-10.2f | %-8d | %-11.2f | %-23s |\n",
                                                cId, mid, mname, price, quantity, total, pdate);

                                totalPurchaseAmount += total;
                        }
                        if (!hasMed)
                                System.out.println(RED + "| No medicines found for this customer.|" + RESET);
                        System.out.println(
                                        "+------+------+---------------------------+------------+----------+-------------+-------------------------+");

                        // --- STEP 4: SHOW TOTAL PURCHASE AMOUNT ---
                        System.out.println(
                                        YELLOW+"\nTotal Purchase Amount for Customer ID " + cId + " = " + totalPurchaseAmount +RESET);
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public static void viewAllCustomerWithPurchase() throws SQLException {
                try (Connection conn = StoreDB.connect()) {
                        String viewQuery = "SELECT c.c_Id, c.c_Name, c.c_MobileNo, " +
                                        "MAX(p.purchase_Date) AS purchase_Date, " +
                                        "SUM(p.total_price) AS total_price " +
                                        "FROM Customer c " +
                                        "JOIN Purchase p ON c.c_Id = p.c_Id " +
                                        "GROUP BY c.c_Id, c.c_Name, c.c_MobileNo";       //, c.left_Amount , c.left_Amount

                        PreparedStatement stmt = conn.prepareStatement(viewQuery);
                        ResultSet rs = stmt.executeQuery();

                        System.out.println(GREEN + "\n_________ALL Customer with Purchases________\n" + RESET);
                        System.out.println(
                                        "+------+-------------------+-------------------+-----------------------+-------------+");
                        System.out.println(
                                        "| C_Id |   Customer_Name   | Customer_MobileNo |     Purchase_Date     | Total_Price |");
                        System.out.println(
                                        "+------+-------------------+-------------------+-----------------------+-------------+");

                        while (rs.next()) {
                                System.out.printf("| %-4d | %-17s | %-17s | %-21s | %-11.2f |\n",
                                                rs.getInt("c_Id"),
                                                rs.getString("c_Name"),
                                                rs.getString("c_MobileNo"),
                                                rs.getString("purchase_Date"),
                                                rs.getDouble("total_price"));
                                                // rs.getDouble("left_Amount"));
                        }

                        System.out.println(
                                        "+------+-------------------+-------------------+-----------------------+-------------+");
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

        public static void ViewMedicines() {
                try (Connection conn = StoreDB.connect()) {

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
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }

}

// // PURCHASE DETAILS
// String purchaseQuery = "SELECT purchase_Date, total_price FROM Purchase WHERE
// c_Id = ?";
// PreparedStatement purchaseStmt = conn.prepareStatement(purchaseQuery);
// purchaseStmt.setInt(1, cId);
// ResultSet purRs = purchaseStmt.executeQuery();

// System.out.println("\n___Purchase Details___");
// System.out.println("+-------------+-----------------------+-------------+");
// System.out.println("| Purchase_Id | Purchase_Date | Total_Price |");
// System.out.println("+-------------+-----------------------+-------------+");

// boolean hasPurchase = false;
// while (purRs.next()) {
// hasPurchase = true;
// System.out.printf("| %-11d | %-21s | %-11.2f |\n",
// purRs.getString("purchase_Date"),
// purRs.getDouble("total_price"));
// }
// if (!hasPurchase)
// System.out.println("| No purchase records found. | |");
// System.out.println("+-------------+-----------------------+-------------+");