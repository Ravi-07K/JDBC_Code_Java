 
package Ravi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class MyJdbcApp6 {
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
PreparedStatement ps=conn.prepareStatement("Insert into allbooks values(?,?,?,?)");
int count=0;
String choice;
do
{
System.out.println("Enter bookid:");
int bid=kb.nextInt();
System.out.println("Enter bookname:");
kb.nextLine();
String bname=kb.nextLine();
System.out.println("Enter Subject:");
String subject=kb.nextLine();
System.out.println("Enter price:");
double amt=kb.nextDouble();
ps.setString(3,subject);
ps.setDouble(4,amt);
ps.setString(2,bname);
ps.setInt(1,bid);
int ans=ps.executeUpdate();
count+=ans;
System.out.println("Any more(yes/no)?");
choice = kb.next();
}
while(choice.equalsIgnoreCase("yes"));
System.out.println("Total records Inserted "+count);
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
