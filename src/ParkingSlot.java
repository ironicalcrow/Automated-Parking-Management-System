public class ParkingSlot {
    private final int slotNumber;
    private Car parkedCar;
    private boolean parked;
    public ParkingSlot(int slotNumber, Car parkedCar) {
        this.slotNumber = slotNumber;
        this.parkedCar = parkedCar;
        this.parked = false;
    }
    ParkingSlot(int slotNumber) {
        this.slotNumber = slotNumber;
        this.parked = false;
    }
    public int getSlotNumber() {
        return slotNumber;
    }
    public Car getParkedCar() {
        return parkedCar;
    }
    public void setParkedCar(Car parkedCar) {
        this.parkedCar = parkedCar;
    }
    public boolean isParked() {
        return parked;
    }
    public void setParked(boolean parked) {
        this.parked = parked;
    }

}
