
package Ravi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class MyJdbcApp8 {
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
ResultSet rs=st.executeQuery("Select subject,bookprice,bookname from allbooks");
ArrayList <Book>bookList=new ArrayList<>();
while(rs.next())
{
String subject=rs.getString(1);
if(subject.equalsIgnoreCase("JEE"))
{
double oldamt=rs.getDouble(2);
double newamt=oldamt+oldamt*0.1;
rs.updateDouble(2,newamt);
rs.updateRow();
Book obj=new Book(rs.getString(3),oldamt,newamt);
bookList.add(obj);
}
}
if(bookList.size()==0)
System.out.println("Sorry! No books of JEE found");
else
System.out.println(bookList.size()+"books Updated");
System.out.println("Books Are:");
Iterator <Book>it=bookList.iterator();
while(it.hasNext())
{
    Book b =it.next();
System.out.println(b);
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
