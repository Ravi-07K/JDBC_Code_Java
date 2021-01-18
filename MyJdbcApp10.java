
package Ravi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MyJdbcApp10 {
    public static void main(String [] rags)
{
Connection conn=null;
try
{
Class.forName("oracle.jdbc.OracleDriver");
System.out.println("Driver loaded successfully");
conn=DriverManager.getConnection("jdbc:oracle:thin:@//LAPTOP-MTSJP1BT:1521/XE","advjavabatch","myscholars");
System.out.println("Connection successfully opened");
Statement st=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
ResultSet rs=st.executeQuery("Select a.* from allbooks a");
Scanner kb=new Scanner(System.in);
System.out.println("Enter BookId:");
int bookid=kb.nextInt();
System.out.println("Enter bookname:");
kb.nextLine();
String bname=kb.next();
System.out.println("Enter Subject:");
String subject=kb.next();
System.out.println("Enter price:");
double amt=kb.nextDouble();
rs.moveToInsertRow();
rs.updateInt(1,bookid);
rs.updateString(2,bname);
rs.updateString(3,subject);
rs.updateDouble(4,amt);
rs.insertRow();
rs.moveToCurrentRow();
System.out.println("Record Inserted");
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
