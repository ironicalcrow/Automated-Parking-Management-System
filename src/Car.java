public class Car {
private final String regestration_number;
private String color;
    public Car(String regestrationNumber, String color) {
        this.regestration_number = regestrationNumber;
        this.color = color;
    }
    public String getRegestrationNumber() {
        return regestration_number;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
}
