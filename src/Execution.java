import java.sql.ResultSet;
import java.sql.SQLException;

public class Execution {
    private DBQuery dbQuery;

    public Execution() throws SQLException {
        this.dbQuery = DBQuery.getInstance();
    }

    public void GetCarList() throws SQLException {
        String stmt = "select * from ParkingSlot";
        ResultSet rs = dbQuery.executeSelect(stmt, (Object[]) null);

        try {
            while (rs.next()) {
                boolean available = rs.getBoolean("available");
                int slotNumber = rs.getInt("SlotNumber");
                String registrationNumber = rs.getString("reg_num");
                String carColor = rs.getString("color");

                if (available) {
                    System.out.println(slotNumber + " " + registrationNumber + " " + carColor);
                }
            }
        } finally {
            if (rs != null) rs.close();
        }
    }

    public void getSlotdataByColor(String color) throws SQLException {
        String stmt = "select * from ParkingSlot where color = ?";
        ResultSet rs = dbQuery.executeSelect(stmt, color);
        System.out.println("Slot and Registration numbers: ");
        try {
            while (rs.next()) {
                int slotNumber = rs.getInt("SlotNumber");
                String registrationNumber = rs.getString("reg_num");
                System.out.println(slotNumber + " " + registrationNumber);
            }
        } finally {
            if (rs != null) rs.close();
        }
    }

    public void getSlotListByColor(String color) throws SQLException {
        String stmt = "select * from ParkingSlot where color = ?";
        ResultSet rs = dbQuery.executeSelect(stmt, color);
        System.out.println("Slot numbers: ");
        try {
            while (rs.next()) {
                int slotNumber = rs.getInt("SlotNumber");
                System.out.print(slotNumber + " ");
            }
        } finally {
            if (rs != null) rs.close();
        }
    }

    public void getCarInfoByRegisterNumber(String registerNumber) throws SQLException {
        String stmt = "select * from ParkingSlot where reg_num = ?";
        ResultSet rs = dbQuery.executeSelect(stmt, registerNumber);
        System.out.println("Slot Numbers is: ");
        try {
            while (rs.next()) {
                int slotNumber = rs.getInt("SlotNumber");
                System.out.print(slotNumber + " ");
            }
        } finally {
            if (rs != null) rs.close();
        }
    }

    public void InsertIntoParkingSlot(ParkingSlot slot) throws SQLException {
        String stmt = "insert into ParkingSlot values(?,?,?,?)";
        Object[] params;
        if (slot.getParkedCar() != null) {
            params = new Object[]{
                    slot.getSlotNumber(),
                    slot.getParkedCar().getRegestrationNumber(),
                    slot.getParkedCar().getColor(),
                    slot.isParked()
            };
        } else {
            params = new Object[]{
                    slot.getSlotNumber(),
                    null,
                    null,
                    false
            };
        }
        dbQuery.executeUpdate(stmt, params);
    }

    public void setSlotAsAvailable(ParkingSlot slot) throws SQLException {
        String sql = "UPDATE ParkingSlot SET available = true, reg_num = NULL, color = NULL WHERE SlotNumber = ?";
        dbQuery.executeUpdate(sql, slot.getSlotNumber());
    }

    public void setSlotAsUnavailable(ParkingSlot slot) throws SQLException {
        String sql = "UPDATE ParkingSlot SET available = false, reg_num= ?, color= ? WHERE SlotNumber = ?";
        dbQuery.executeUpdate(sql,
                slot.getParkedCar().getRegestrationNumber().trim(),
                slot.getParkedCar().getColor().trim(),
                slot.getSlotNumber());
    }

    public void tableDatadrop() throws SQLException {
        String stmt = "drop table ParkingSlot";
        dbQuery.executeUpdate(stmt, (Object[]) null);
    }
}
