import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingSlot> Slots;
    private int capacity;

    ParkingLot(int capacity) {
        this.capacity = capacity;
        Slots = new ArrayList<ParkingSlot>(capacity);
        for (int i = 1; i <= capacity; i++) {
            ParkingSlot parkingSlot = new ParkingSlot(i);
            Slots.add(parkingSlot);
            try {
                Execution execution = new Execution();
                execution.InsertIntoParkingSlot(parkingSlot);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void park(Car car) throws SQLException {
        for (ParkingSlot slot : Slots) {
            if (!slot.isParked()) {
                slot.setParked(true);
                slot.setParkedCar(car);
                System.out.println("Slot Allocated " + slot.getSlotNumber());
                try {
                    Execution execution = new Execution();
                    execution.setSlotAsUnavailable(slot);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        System.out.println("No available slots");
    }

    public void leaveSlot(int slotNumber) throws SQLException {
        Slots.get(slotNumber).setParked(false);
        Slots.get(slotNumber).setParkedCar(null);
        try {
            Execution execution = new Execution();
            execution.setSlotAsAvailable(Slots.get(slotNumber));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ParkingSlot> getSlots() {
        return Slots;
    }
}
