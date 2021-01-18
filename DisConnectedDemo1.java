
package Ravi;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;

public class DisConnectedDemo1 {
     public static void main(String[] args) {
	try
	{
		Class.forName("oracle.jdbc.OracleDriver");
		System.out.println("Driver successfully loaded!");
		CachedRowSetImpl obj=new CachedRowSetImpl();
                obj.setUrl("jdbc:oracle:thin:@//LAPTOP-MTSJP1BT:1521/XE");
                obj.setUsername("advjavabatch");
                obj.setPassword("myscholars");
                obj.setCommand("Select bookname,bookprice from allbooks");
                obj.execute();
                while(obj.next())
                {
                    String bname=obj.getString(1);
                    double amt=obj.getDouble(2);
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
}
    
}
