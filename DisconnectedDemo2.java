
package Ravi;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;

public class DisconnectedDemo2 {
    public static void main(String[] args) {
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver successfully loaded!");
			CachedRowSetImpl obj=new CachedRowSetImpl();
	                obj.setUrl("jdbc:oracle:thin:@//LAPTOP-MTSJP1BT:1521/XE");
	                obj.setUsername("advjavabatch");
	                obj.setPassword("myscholars");
	                obj.setCommand("Select * from allbooks");
	                obj.execute();
	                boolean found=false;
	                while(obj.next())
	                {
	                    String subject=obj.getString(3);
	                    if(subject.equalsIgnoreCase("jse"))
	                    {
	                        double amt=obj.getDouble(4);
	                        amt=amt+amt*0.1;
	                        obj.updateDouble(4, amt);
	                        obj.updateRow();
	                        found=true;
	                        
	                    }
	                }
	                if(found)
	                {
	                    obj.acceptChanges();
	                    System.out.println("Record updated in DB");
	                }
	                else
	                {
	                    System.out.println("No record found and updated!");
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
