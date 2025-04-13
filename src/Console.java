import java.sql.SQLException;
import java.util.Scanner;

public class Console {
    public boolean DisplayLoop(ParkingLot parkingLot) throws SQLException {
        System.out.println("Commands on our Parking System");
        System.out.println("1.Park a Car:");
        System.out.println("2.Leave a slot:");
        System.out.println("3.Status of Parking Lot:");
        System.out.println("4.Query of Registration Numbers by Color:");
        System.out.println("5.Query by Registration Number:");
        System.out.println("6.Query of Slots by Color:");
        System.out.println("7.Exit");
        System.out.println("Enter your choice: ");
        int choice;
        Scanner input = new Scanner(System.in);
        choice = input.nextInt();
        Execution execution = new Execution();
        switch(choice)
        {
            case 1:
                Car car= takeIncar();
                parkingLot.park(car);
                break;
            case 2:
                System.out.println("Enter the slot number: ");
                int SlotNumber = input.nextInt();
                parkingLot.leaveSlot(SlotNumber);
                break;
            case 3:
                execution.GetCarList();
                break;
            case 4:
                System.out.println("Enter the color: ");
                String Color = input.next();
                execution.getSlotdataByColor(Color);
                break;
            case 5:
                System.out.println("Enter registration number: ");
                String RegistrationNumber = input.next();
                execution.getCarInfoByRegisterNumber(RegistrationNumber);
            case 6:
                System.out.println("Enter the color: ");
                String Color2 = input.next();
                execution.getSlotListByColor(Color2);
                break;
            case 7:
                execution.tableDatadrop();
                return false;
        }
        return true;
    }
    public Car takeIncar()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the car Registration Number: ");
        String registrationNumber= input.nextLine();
        System.out.println("Enter the car color: ");
        String color= input.nextLine();
        Car car= new Car(registrationNumber,color);
        return car;
    }
}
