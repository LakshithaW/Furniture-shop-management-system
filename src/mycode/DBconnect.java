
package mycode;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;


public class DBconnect {
    
    public static Connection connect()
    {
        Connection conn = null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/furnitureshopmanagementsystem","root","");
        }
        catch (Exception e)
        {
           System.out.println(e);         
        }
        
        return conn;
    }
    
    
}
