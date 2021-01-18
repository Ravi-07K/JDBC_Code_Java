
package Ravi;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BatchUpdateDemo2 {
    public static void main(String[] args) {
		Connection conn=null;
		boolean status=true;
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver successfully loaded!");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-MTSJP1BT:1521/XE","advjavabatch","myscholars");
			System.out.println("Connection successfully opened!");
			PreparedStatement ps=conn.prepareStatement("insert into allbooks values(?,?,?,?)");
			conn.setAutoCommit(false);
			Scanner kb=new Scanner(System.in);
			
			do
			{
				System.out.println("Enter bookid");
				int id=kb.nextInt();
				System.out.println("Enter bookname");
				kb.nextLine();
				String bname=kb.nextLine();
				System.out.println("Enter subject");
				String subject=kb.nextLine();
				System.out.println("Enter book price");
				double amt=kb.nextDouble();
				
				ps.setInt(1, id);
				ps.setString(2, bname);
				ps.setString(3,subject);
				ps.setDouble(4, amt);
				ps.addBatch();
				System.out.println("Any more?(yes/no)");
				String choice=kb.next();
				if(choice.equalsIgnoreCase("no"))
					break;
				
			}while(true);
			
			int[] result=ps.executeBatch();
			for(int i=0;i<result.length;i++)
			{
				if(result[i]==Statement.EXECUTE_FAILED)
							status=false;
				System.out.println("Query "+(i+1)+" inserted "+result[i]+" rows");
			}
		}
		catch(ClassNotFoundException cnf)
		{
			System.out.println("Sorry! Cannot load the driver");
			System.out.println(cnf.getMessage());
		}
		catch(BatchUpdateException bue)
		{
			int []arr=bue.getUpdateCounts();
			int count=arr.length+1;
			System.out.println("Query "+count+" gave the exception");
			System.out.println(bue.getMessage());
			status=false;
			
		}
		catch(SQLException ex)
		{
			System.out.println("Sorry! Problem with DB");
			System.out.println(ex.getMessage());
			status=false;
		}
		finally
		{
			if(conn!=null)
			{
				try
				{
					if(status)
					{
						System.out.println("Everyrthing executed successfully. Committing changes...");
						conn.commit();
					}
					else
					{
						System.out.println("Something went wrong Rollbacking changes...");
						conn.rollback();
					}
					conn.close();
					System.out.println("Connection successfully closed!");
				}
				catch(SQLException ex)
				{
					System.out.println("Sorry! Problem in closing the conn");
					System.out.println(ex.getMessage());
				}
			}
		}
	}
    
}
