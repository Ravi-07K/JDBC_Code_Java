
package Ravi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class DateDisplay {
     public static void main(String[] args) {
        Connection conn=null;
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Driver loaeded successfully!");
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-MTSJP1BT:1521/XE","advjavabatch","myscholars");
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("Select ename,hiredate from emp");
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
            SimpleDateFormat sdf2=new SimpleDateFormat("E");
            while(rs.next())
            {
                String ename=rs.getString(1);
                Date hd=rs.getDate(2);
                String str=sdf.format(hd);
                String day=sdf2.format(hd);
                if(day.equalsIgnoreCase("sat")||day.equalsIgnoreCase("sun"))
                    ename="*"+ename;                
                System.out.println(ename+"\t"+str);
                
                
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
