
package JDBC;

import java.sql.*;

/**
 * Final Assignment (Group assignment)
 * @version 2016-04-20
 * @author Haeyeon Kang, Cindy Diaz
 */

public class DBConnect {
    private static final String DB_URL =
                "jdbc:mysql://sql.computerstudi.es:3306/jdbcdb"; //jdbc:mysql://localhost
    private static final String userName = "userName";
    private static final String password = "password";
    private static Connection conn;
    
    public static Connection getConn(){
        try{
            conn = DriverManager.getConnection(DB_URL, userName, password);
            
        }
        catch(SQLException error){
            //handle the log file here
            
            
            System.out.println("Error Occurred connecting to the database.");
        }
        return conn;
    }
}
