
package Ravi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class MyJdbcApp11 {
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
PreparedStatement ps=conn.prepareStatement("Insert into emp values(?,?,?,?)");
System.out.println("Enter Empid:");
int empid=kb.nextInt();
System.out.println("Enter Empname:");
kb.nextLine();
String ename=kb.nextLine();
System.out.println("Enter hireDate(dd/MM/yyyy):");
String hdatestr=kb.nextLine();
System.out.println("Enter Salary:");
double sal=kb.nextDouble();
ps.setInt(1,empid);
ps.setDouble(4,sal);
ps.setString(2,ename);
SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
java.util.Date d1=sdf.parse(hdatestr);
long ms=d1.getTime();
java.sql.Date d2=new java.sql.Date(ms);
ps.setDate(3,d2);
int result =ps.executeUpdate();
System.out.println("Record inserted"+result);
}
catch(ParseException pe)
{
System.out.println("Error in date!");
System.out.println(pe.getMessage());
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
