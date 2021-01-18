
package Ravi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MyJdbcApp4 {
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
Statement st=conn.createStatement();
System.out.println("Enter bookid:");
int bid=kb.nextInt();
System.out.println("Enter bookname:");
kb.nextLine();
String bname=kb.nextLine();
System.out.println("Enter Subject:");
String subject=kb.nextLine();
System.out.println("Enter price:");
double amt=kb.nextDouble();
String str="Insert into allbooks values("+bid+",'"+bname+"','"+subject+"',"+amt+")";
System.out.println(str);
int ans=st.executeUpdate(str);
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
