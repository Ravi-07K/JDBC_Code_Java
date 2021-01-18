
package Ravi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MyNewJdbcApp3 {
     public static void main(String[] args) {
        Connection conn=null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaeded successfully!");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-MTSJP1BT:1521/XE", "advjavabatch","myscholars");
            PreparedStatement ps=conn.prepareStatement("Select bookname,bookprice from allbooks",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            Scanner kb=new Scanner(System.in);
            System.out.println("Enter subject:");
            String str=kb.nextLine();
            ps.setString(1, str);
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                String bname=rs.getString(1);
                double amt=rs.getDouble(2);
                System.out.println(bname+"\t"+amt);
            }
            System.out.println("Forward traversal finished. Now traversing backwards...");
            while(rs.previous())
            {
                String bname=rs.getString(1);
                double amt=rs.getDouble(2);
                System.out.println(bname+"\t"+amt);
            }
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
