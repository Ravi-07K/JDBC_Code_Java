
package Ravi;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdateDemo1 {
    public static void main(String[] args) {
		Connection conn=null;
		boolean status=true;
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver successfully loaded!");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-MTSJP1BT:1521/XE","advjavabatch","myscholars");
			System.out.println("Connection successfully opened!");
			Statement st=conn.createStatement();
			conn.setAutoCommit(false);
			st.addBatch("insert into allbooks values(211,'Oracle Cert','Oracle',790)");
			st.addBatch("delete from emp where ename='Vivek'");
			st.addBatch("update emp set sal=sal+1000 where empno>=108");
			int []results=st.executeBatch();
			for(int i=0;i<results.length;i++)
			{
				if(results[i]==Statement.EXECUTE_FAILED)
					status=false;
				System.out.println("Query "+(i+1)+" effected "+results[i]+" rows");
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
