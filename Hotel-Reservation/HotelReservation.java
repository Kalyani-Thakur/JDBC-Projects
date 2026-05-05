import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class HotelReservation {

    static Scanner sc = new Scanner(System.in);
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    // RESERVATION
    public static void reserveRoom() throws SQLException {
        try {
            System.out.print("\nEnter guest name: ");
            String guestName = sc.nextLine();

            System.out.print("Enter room number: ");
            int roomNumber = sc.nextInt();
            sc.nextLine(); // clear buffer

            System.out.print("Enter contact number: ");
            String contactNumber = sc.nextLine(); // keep as String

            Connection conn = DB.connect();

            String insertQuery = "INSERT INTO reservation (guest_name, room_number, contact_number) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1, guestName);
            pstmt.setInt(2, roomNumber);
            pstmt.setString(3, contactNumber);

            pstmt.executeUpdate();
            System.out.println(GREEN+ "Reservation successfully added!" +RESET);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CHECKING RESERVATION
    public void viewReservation() {
        try {
            Connection conn = DB.connect();

            String readQuery = "SELECT * FROM reservation";

            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(readQuery);

            System.out.println("\nCurrent Reservations:");
            System.out.println(
                    "+----------------+-------------------+---------------+-------------------+--------------------------+");
            System.out.println(
                    "| Reservation ID | Guest Name        | Room Number   | Contact Number    | Reservation Date         |");
            System.out.println(
                    "+----------------+-------------------+---------------+-------------------+--------------------------+");

            while (rs.next()) {
                int reservationId = rs.getInt("reservation_id");
                String guestName = rs.getString("guest_name");
                int roomNumber = rs.getInt("room_number");
                String contactNumber = rs.getString("contact_number");
                String reservationDate = rs.getTimestamp("reservation_date").toString();

                // Format and display the reservation data in a table-like format
                System.out.printf("| %-14d | %-16s  | %-12d  | %-17s | %-19s    |\n",
                        reservationId, guestName, roomNumber, contactNumber, reservationDate);
            }

            System.out.println(
                    "+----------------+-------------------+---------------+-------------------+--------------------------+");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // GET RESERVATION ROOM NUMBER
    public void getRoomNumber() {
        System.out.print("Enter Reservation ID: ");
        int reservation_Id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter guest name: ");
        String guestName = sc.nextLine();

        try {
            Connection conn = DB.connect();

            String getQuery = "SELECT room_number FROM reservation " +
                    "WHERE reservation_id = ? AND guest_name = ?";

            PreparedStatement pstm = conn.prepareStatement(getQuery);
            pstm.setInt(1, reservation_Id);
            pstm.setString(2, guestName);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                int roomNumber = rs.getInt("room_number");
                System.out.println(GREEN+ "Room number for Reservation ID " + reservation_Id +
                        " and Guest " + guestName + " is: " + roomNumber +RESET);
            } else {
                System.out.println(RED+ "Reservation not found for the given ID and guest name." +RESET);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE RESERVATION
    public static void updateReservation() {
        System.out.print("Enter reservation ID: ");
        int reservation_Id = sc.nextInt();
        sc.nextLine();

        try {
            Connection conn = DB.connect();

            if (!reservationExits(reservation_Id)) {
                System.out.println(RED+ "Reservation not found for the given ID" +RESET);
                return;
            }

            System.out.print("Enter new guest name: ");
            String newGuestName = sc.nextLine();

            System.out.print("Enter new room number: ");
            int newRoomNumber = sc.nextInt();

            System.out.print("Enter new contact number: ");
            String newContactNumber = sc.next();

            String updateQuery = "UPDATE reservation SET guest_name = '" + newGuestName + "', " +
                    "room_number = " + newRoomNumber + ", " +
                    "contact_number = '" + newContactNumber + "' " +
                    "WHERE reservation_id = " + reservation_Id;

            Statement stm = conn.createStatement();
            int affectedRows = stm.executeUpdate(updateQuery);

            if (affectedRows > 0) {
                System.out.println(GREEN+ "Reservation update successfully!" +RESET);
            } else {
                System.out.println(RED+ "Reservation failed!" +RESET);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE RESERVATION
    public void deleteReservation() {
        System.out.print("Enter reservation ID: ");
        int reservation_id = sc.nextInt();

        try {
            if (!reservationExits(reservation_id)) {
                System.out.println(RED+ "Reservation not found for the given ID" +RESET);
                return;
            }

            Connection conn = DB.connect();

            String deleteQuery = "DELETE FROM reservation WHERE reservation_id = ?";

            PreparedStatement pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setInt(1, reservation_id);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println(GREEN+ "Reservtion deleted successfully!" +RESET);
            } else {
                System.out.println(RED+ "Reservtion deletion failed!" +RESET);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // RESERVATION EXIT
    static boolean reservationExits(int reservation_id) {
        String re = "SELECT reservation_id FROM reservation WHERE reservation_id = " + reservation_id;

        try {
            Connection conn = DB.connect();
            Statement stm = conn.createStatement();

            ResultSet rs = stm.executeQuery(re);
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // EXITING
    public void Exits() throws InterruptedException {
        System.out.print(YELLOW+ "Exiting System");
        int i = 5;
        while (i != 0) {
            System.out.print(".");
            Thread.sleep(1000);
            i--;
        }
        System.out.println("\nThankYou For Using Hotel Reservation System!!!"+ RESET);
    }
}
