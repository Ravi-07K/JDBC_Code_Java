
package Ravi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMovement {
    public static void main(String[] args) {
        Connection conn=null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaeded successfully!");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-MTSJP1BT:1521/XE", "advjavabatch","myscholars");
            Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=st.executeQuery("Select * from allbooks");
            rs.first();
            boolean x=rs.previous();
            System.out.println(x);
            
        }
        catch(ClassNotFoundException cnf)
        {
            System.out.println("Cannot lad the driver class:"+cnf);
            cnf.getMessage();
        }
        catch(SQLException ex)
        {
            System.out.println("Error in DB:"+ex);
            ex.getMessage();
        }
        finally
        {
            if(conn!=null)
            {
                try
                {
                    conn.close();
                    System.out.println("Connection closed successfully!");
                }
                catch(SQLException ex)
        {
            System.out.println("Error in closing the conn:"+ex);
            ex.getMessage();
        }
            }
        }
    }
    
}
