
package Ravi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyNewJdbcApp2 {
      public static void main(String [] args)
	{
	 
            Connection conn=null;
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver successfully loaded!");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-MTSJP1BT:1521/XE","advjavabatch","myscholars");
			System.out.println("Connection successfully opened!");
			Statement st=conn.createStatement();
			int ans=st.executeUpdate("Insert into allbooks values(110,'Mastering JEE','JEE',675.0)");
			System.out.println("Rec inserted:"+ans);		
			
		}
		catch(ClassNotFoundException cnf)
		{
			System.out.println("Sorry! Cannot load the driver");
			System.out.println(cnf.getMessage());
		}
		catch(SQLException ex)
		{
			System.out.println("Sorry! Problem with DB");
			System.out.println(ex.getMessage());
		}
		finally
		{
			if(conn!=null)
			{
				try
				{
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
