import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingSlot> Slots;
    private int capacity;
    ParkingLot(int capacity) {
        this.capacity = capacity;
        Slots = new ArrayList<ParkingSlot>(capacity);
        for (int i = 1; i <= capacity; i++) {
            ParkingSlot parkingSLot = new ParkingSlot(i);
            Slots.add(parkingSLot);
        }
    }
    public void park(Car car) {
        for(ParkingSlot slot : Slots){
            if(!slot.isParked())
            {
                slot.setParked(true);
                slot.setParkedCar(car);
                System.out.println("Slot Allocated "+slot.getSlotNumber());
                return;
            }
        }
        System.out.println("No available slots");
        return;
    }
    public void leaveSlot(int slotNumber) {
        Slots.get(slotNumber).setParked(false);
        Slots.get(slotNumber).setParkedCar(null);
    }

    public List<ParkingSlot> getSlots() {
        return Slots;
    }
}
