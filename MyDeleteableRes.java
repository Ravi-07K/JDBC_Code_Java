package Ravi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class MyDeleteableRes {
       public static void main(String[] args) {
        Connection conn=null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaeded successfully!");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-MTSJP1BT:1521/XE", "advjavabatch","myscholars");
            Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=st.executeQuery("Select bookid,bookname from allbooks ");
            int count=0;
            Scanner kb=new Scanner(System.in);
            while(rs.next())
            {
             int bookid=rs.getInt(1);
             String bname=rs.getString(2);
                System.out.println(bookid+","+bname);
                System.out.println(":Do you want to delete it (yesy/no)?");
                String choice=kb.next();
                if(choice.equalsIgnoreCase("yes"))
                {
                    rs.deleteRow();
                    ++count;
                }
             
            }
            if(count==0)
                System.out.println("No books were deleted");
            else
                System.out.println(count+" books deleted");
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
