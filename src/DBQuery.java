
import java.sql.*;

public class DBQuery {
    Connection conn = DBConnection.getConnection();
    private static DBQuery instance;
    private DBQuery() throws SQLException {
        conn = DBConnection.getConnection();
    }
    
    public static synchronized DBQuery getInstance() throws SQLException {
        if (instance == null) {
            instance = new DBQuery();
        }
        return instance;
    }
    public ResultSet executeSelect(String query, Object... params) throws SQLException {

        PreparedStatement stmt = conn.prepareStatement(query);
        setParameters(stmt, params);
        return stmt.executeQuery();
    }

    public int executeUpdate(String query, Object... params) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(query);
        setParameters(stmt, params);
        return stmt.executeUpdate();
    }

    public boolean execute(String query, Object... params) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(query);
        setParameters(stmt, params);
        return stmt.execute();
    }

    private void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
        }
    }
}
