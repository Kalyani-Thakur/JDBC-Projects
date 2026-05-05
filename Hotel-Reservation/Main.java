import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, InterruptedException {
        HotelReservation hr = new HotelReservation();

        while (true) {
            System.out.println("\n...WELCOME TO HOTEL RESERVATION SYSTEM...");
            System.out.println("1. Reserve a Room");
            System.out.println("2. View Reservation");
            System.out.println("3. Get Room Number");
            System.out.println("4. Update Reservation");
            System.out.println("5. Delete Reservation");
            System.out.println("0. Exit");
            
            Scanner sc = new Scanner(System.in);
            System.out.print("\nEnter your Choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    hr.reserveRoom();
                    break;
                case 2:
                    hr.viewReservation();
                    break;
                case 3:
                    hr.getRoomNumber();
                    break;
                case 4:
                    hr.updateReservation();
                    break;
                case 5:
                    hr.deleteReservation();
                    break;
                case 0:
                    hr.Exits();
                    sc.close();
                    return;
            
                default:
                System.out.println("Invalid choice! Try again");
            }
            
        }
    }
}
