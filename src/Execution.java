import java.sql.ResultSet;
import java.sql.SQLException;

public class Execution {
    DBQuery dbQuery;

    public Execution() throws SQLException {
        DBQuery dbQuery= DBQuery.getInstance();
    }

    public void GetCarList() throws SQLException {
        String stmt = "select * from ParkingSlot";
        ResultSet rs = dbQuery.executeSelect(stmt,null);

        try {
            while (rs.next()) {
                boolean available = rs.getBoolean("available");
                int slotNumber = rs.getInt("SlotNumber"); // or rs.getInt(1) if it's the first column
                String registrationNumber = rs.getString("reg_num"); // adjust column name as needed
                String carColor = rs.getString("color");

                if (available)
                {
                    System.out.println(slotNumber+" "+registrationNumber+" "+carColor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void getSlotListByColor(String color) throws SQLException {
        String stmt = "select * from ParkingSlot where color = ?";
        ResultSet rs = dbQuery.executeSelect(stmt,color);
        System.out.println("Slot Numbers Are: ");
        try {
            while (rs.next()) {
                int slotNumber = rs.getInt("SlotNumber");
                System.out.print(slotNumber+" ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public void getCarInfoByRegisterNumber(String registerNumber) throws SQLException {
        String stmt= "select * from ParkingSlot where reg_num = ?";
        ResultSet rs= dbQuery.executeSelect(stmt,registerNumber);
        System.out.println("Slot Numbers is: ");
        try {
            while (rs.next()) {
                int slotNumber = rs.getInt("SlotNumber");
                System.out.print(slotNumber+" ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void InsertIntoParkingSlot(ParkingSlot slot) throws SQLException {
        String stmt = "insert into ParkingSlot values(?,?,?)";
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
                    null,  // reg_num
                    null,   // color
                    false   // available
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
        dbQuery.executeUpdate(sql,slot.getParkedCar().getRegestrationNumber().trim(),slot.getParkedCar().getColor().trim(),slot.getSlotNumber());
    }
}
