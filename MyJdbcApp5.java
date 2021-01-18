
package Ravi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MyJdbcApp5 {
    public static void main(String [] rags)
{
Connection conn=null;
Scanner kb=new Scanner(System.in);
try
{
Class.forName("oracle.jdbc.OracleDriver");
System.out.println("Driver loaded successfully");
conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-MTSJP1BT:1521/XE","advjavabatch","myscholars");
System.out.println("Connection successfully opened");
PreparedStatement ps=conn.prepareStatement("Update allbooks set bookprice=bookprice+? where subject = ? ");
System.out.println("Enter Subject:");
String subject=kb.nextLine();
System.out.println("Enter price:");
double amt=kb.nextDouble();
ps.setDouble(1,amt);
ps.setString(2,subject);
int ans=ps.executeUpdate();
if(ans==0)
System.out.println("Sorry! no books Upadted");
else
System.out.println(ans+"books updated");
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
