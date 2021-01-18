
package Ravi;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.SQLException;
import java.util.Scanner;

public class DisconnectedDemo3 {
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
	                Scanner kb=new Scanner(System.in);
	                System.out.println("Enter book id");
	                int bid=kb.nextInt();
	                System.out.println("Enter book name");
	                kb.nextLine();
	                String bname=kb.nextLine();
	                System.out.println("Enter subject");
	                String subject=kb.nextLine();
	                System.out.println("Enter book price");
	                double amt=kb.nextDouble();
	                obj.moveToInsertRow();
	                obj.updateInt(1,bid);
	                obj.updateString(2, bname);
	                obj.updateString(3,subject);
	                obj.updateDouble(4, amt);
	                obj.insertRow();
	                obj.moveToCurrentRow();
	                obj.acceptChanges();
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
	}   
}
