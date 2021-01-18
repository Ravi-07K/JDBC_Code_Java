
package Ravi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyJdbcApp1 {
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
ResultSet rs=st.executeQuery("Select bookid,bookname,subject,bookprice from allbooks");
while(rs.next())
{
String bookid=rs.getString(1);
String bname=rs.getString(2);
String subject=rs.getString(3);
double amt=rs.getDouble(4);
System.out.println(bookid+"\t"+bname +"\t"+subject+"\t"+amt);
}
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
