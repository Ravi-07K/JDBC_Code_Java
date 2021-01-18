
package Ravi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MyInsertableRes {
     public static void main(String[] args) {
        Connection conn=null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaeded successfully!");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-MTSJP1BT:1521/XE", "advjavabatch","myscholars");
            Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=st.executeQuery("Select t.* from allbooks  t");
            Scanner kb=new Scanner(System.in);
            System.out.println("Enter bookid:");
            int bookid=kb.nextInt();
            System.out.println("Enter bookname:");
            kb.nextLine();
            String bname=kb.nextLine();
            System.out.println("Enter subject:");
            String subject=kb.nextLine();
            System.out.println("Enter bookprice:");
            double amt=kb.nextDouble();
            rs.moveToInsertRow();
            rs.updateInt(1, bookid);
            rs.updateString(2, bname);
            rs.updateString(3, subject);
            rs.updateDouble(4, amt);
            rs.insertRow();
            rs.moveToCurrentRow();
            System.out.println("Rec inserted");
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
