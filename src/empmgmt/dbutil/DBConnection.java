package empmgmt.dbutil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author Prasant
 */
public class DBConnection {
    private static Connection con;
    
    private static final String url = "jdbc:oracle:thin:@//localhost:1521/xe";
    private static final String user = "JDBC";
    private static final String password = "myjdbc";
    
    static {
        
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected successfully!");
        } catch (SQLException ex) {
            System.out.println("Can't connected!: " + ex);
        }
        
    }
    
    public static Connection getConnection() {
        return con;
    }
    
    public static void closeConnection() {
        try {
            con.close();
            System.out.println("Disconnected successfully!");
        } catch (SQLException e) {
             System.out.println("Can't disconnected successfully!: " + e);
        }
    }
}
