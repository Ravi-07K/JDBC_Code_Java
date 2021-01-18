
package Ravi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MyJdbcApp3 {
    public static void main(String [] rags)
{
Connection conn=null;
try
{
Class.forName("oracle.jdbc.OracleDriver");
System.out.println("Driver loaded successfully");
conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-MTSJP1BT:1521/XE","advjavabatch","myscholars");
System.out.println("Connection successfully opened");
Statement st=conn.createStatement();
int ans=st.executeUpdate("Insert into allbooks values(102,'Mastering Jee','JEE',675.0)");
System.out.println("Records inserted "+ans);
}
catch(ClassNotFoundException cnf)
{
System.out.println("Sorry! can not load the Driver");
System.out.println(cnf.getMessage());
}
catch(SQLException ex)
{
System.out.println("Sorry! problem with DB");
System.out.println(ex.getMessage());
}
finally
{
if(conn!=null)
{
try
{
conn.close();
}
catch(SQLException ex)
{
System.out.println("Sorry! problem in closing the conn");
System.out.println(ex.getMessage());
}
}
}
}    
}
