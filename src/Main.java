import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Our Parking System");
        System.out.println("Creating Parking lot with ......  slots");
        System.out.println("Enter Number of Slots: ");
        int capacity= sc.nextInt();
//        System.out.println(capacity);
        ParkingLot parkingLot= new ParkingLot(capacity);
//        System.out.println("parkign lot created!");
        Console console = new Console();
        boolean flag = true;
        for(int i=0; i<capacity; i++){
            flag = console.DisplayLoop(parkingLot);
            if(!flag){
                break;
            }
        }
    }
}
