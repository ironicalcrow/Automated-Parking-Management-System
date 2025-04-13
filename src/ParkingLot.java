import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingSlot> Slots;
    Execution execution;
    private int capacity;
    ParkingLot(int capacity) throws SQLException {
        this.execution= new Execution();
        this.capacity = capacity;
        Slots = new ArrayList<ParkingSlot>(capacity);
        for (int i = 1; i <= capacity; i++) {
            ParkingSlot parkingSLot = new ParkingSlot(i);
            Slots.add(parkingSLot);
            execution.InsertIntoParkingSlot(parkingSLot);
        }
    }
    public void park(Car car) throws SQLException {
        for(ParkingSlot slot : Slots){
            if(!slot.isParked())
            {
                slot.setParked(true);
                slot.setParkedCar(car);
                System.out.println("Slot Allocated "+slot.getSlotNumber());
                execution.setSlotAsUnavailable(slot);
                return;
            }
        }
        System.out.println("No available slots");
        return;
    }
    public void leaveSlot(int slotNumber) throws SQLException {

        Slots.get(slotNumber).setParked(false);
        Slots.get(slotNumber).setParkedCar(null);
        execution.setSlotAsAvailable(Slots.get(slotNumber));

    }
    public List<ParkingSlot> getSlots() {
        return Slots;
    }
}
