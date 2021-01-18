
package Ravi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class MyScrollableRes2 {
     public static void main(String[] args) {
        Connection conn=null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaeded successfully!");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-MTSJP1BT:1521/XE", "advjavabatch","myscholars");
            Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs=st.executeQuery("Select subject,bookprice,bookname from allbooks");
            ArrayList<Book> bookList=new ArrayList<>();
            while(rs.next())
            {
             String subject=rs.getString(1);
             if(subject.equalsIgnoreCase("JEE"))
             {
                 double oldamt=rs.getDouble(2);
                 double newamt=oldamt+oldamt*0.1;
                 rs.updateDouble(2,newamt);
                 rs.updateRow();
                 Book obj=new Book(rs.getString(3),oldamt,newamt);
                 bookList.add(obj);
                                
             }
             
            }
            if(bookList.size()==0)
                System.out.println("Sorry! No books of JEE found");
            else
            {
                System.out.println(bookList.size()+" books updated!");
                System.out.println("Book details are:");
                Iterator<Book> it=bookList.iterator();
                while(it.hasNext())
                {
                    Book b=it.next();
                    System.out.println(b);
                }
                
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
