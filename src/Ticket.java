public class Ticket {
    private static int ticket_number=1;
    private int slotNo;
    private Car car;
    Ticket(int slotNo, Car car) {
        this.slotNo = slotNo;
        this.car = car;
        ticket_number++;
    }
    public int getSlotNo() {
        return slotNo;
    }
    public Car getCar() {
        return car;
    }
    public void print() {
        System.out.println("Ticket No "+ ticket_number+" :");
        System.out.println("Slot No: "+slotNo);
        System.out.println("Registration No: "+car.getRegestrationNumber());
        System.out.println("Color: "+car.getColor());
    }
}
