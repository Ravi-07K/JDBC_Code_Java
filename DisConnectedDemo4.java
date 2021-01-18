
package Ravi;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import java.util.Scanner;

public class DisConnectedDemo4 {
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
	                Scanner kb=new Scanner(System.in);
	                boolean status=false;
	                while(obj.next())
	                {
	                    String bname=obj.getString(1);
	                    double amt=obj.getDouble(2);
	                    System.out.println(bname+"\t"+amt);
	                    String choice;
	                    System.out.println("Do you want to delete it (yes/no)?");
	                    choice=kb.next();
	                    if(choice.equalsIgnoreCase("yes"))
	                    {
	                    	obj.deleteRow();
	                    	status=true;
	                    }
	                    
	                }
	                if(status)
	                {
	                	obj.acceptChanges();
	                	System.out.println("Recorde deleted from the DB");
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
