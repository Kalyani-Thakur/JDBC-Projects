import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateSystem {
    static Scanner sc = new Scanner(System.in);
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    public static void AddMedicines() throws SQLException {
        try (Connection conn = StoreDB.connect()) {
            System.out.println(GREEN+"\n_____Add Medicine in the Stock_____"+RESET);
            System.out.print("How many Medicine do you want to Add in Stock__: ");
            int insert = sc.nextInt();
            sc.nextLine();

            for (int i = 1; i <= insert; i++) {
                System.out.println(GREEN+"\n--- Medicine " + i + " ---"+RESET);

                System.out.print("Enter Medicine Name: ");
                String mName = sc.nextLine();

                System.out.print("Enter Medicine Price: ");
                double mPrice = sc.nextDouble();

                System.out.print("Enter Medicine Stock: ");
                int mStock = sc.nextInt();
                sc.nextLine();

                String addmed = "INSERT INTO Medicine (m_Name, m_Price, m_Stock) VALUES (?, ?, ?)";
                PreparedStatement pstm = conn.prepareStatement(addmed);

                pstm.setString(1, mName);
                pstm.setDouble(2, mPrice);
                pstm.setInt(3, mStock);

                pstm.executeUpdate();
                System.out.println(YELLOW+"[" + i + "] Medicines added successfuly!"+RESET);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void RestockMedicine() {
        System.out.println(GREEN+"\n_______Updating Medicine's_______"+RESET);

        System.out.print("Enter Medicine ID: ");
        int mid = sc.nextInt();

        try(Connection conn = StoreDB.connect()){

            System.out.print("Enter new price: ");
            double mPrice = sc.nextDouble();

            System.out.print("Enter new Stock: ");
            int mStock = sc.nextInt();

            String updateMed = "UPDATE Medicine SET m_Price=?, m_Stock=? WHERE m_id=?";
            PreparedStatement pstm = conn.prepareStatement(updateMed);
            pstm.setDouble(1, mPrice);
            pstm.setInt(2, mStock);
            pstm.setInt(3, mid);

            int rows = pstm.executeUpdate();

            if(rows> 0) {
                System.out.println(YELLOW+"Medicine ID "+mid+" updated successfully!"+RESET);
            }
            else {
                System.out.println(RED+"No medicine found with ID "+mid+RESET);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}








// System.out.println("4. Update Customer with Medicines");