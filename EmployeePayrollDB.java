package bridgeLabz.MYSQL;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Scanner;

import com.mysql.cj.jdbc.Driver;

public class EmployeePayrollDB {

	public static void main(String[] args) {
		/*setting the database url to localhost database
		 *checking the username and password set in mysql
		 */
         String jdbcURL="jdbc:mysql://localhost:3306/Payroll_Service?useSSL=false";
         String userName="root";
         String password="root";
         Connection connection;
         //Checking if MySQL JDBC driver class is loaded
         try {
        	 Class.forName("com.mysql.cj.jdbc.Driver");
        	 System.out.println("Driver Loaded");
        	 
         }catch(ClassNotFoundException e) {
        	 throw new IllegalStateException("Cannot find the driver in the classpath!,e");
         }
         /*Getting the SQL Connection from the JDBC Driver passing the DB URL, User name and Password
          *printing the output to check the connection is established
          */
         try {
        	 System.out.println("Connecting to database : "+jdbcURL);
        	 connection=DriverManager.getConnection(jdbcURL,userName,password);
        	 System.out.println("Connection is successfull...!!!    "+connection);
        	 String query="Select * from employee_payroll";
        	 PreparedStatement statement = connection.prepareStatement(query);
        	 ResultSet result=statement.executeQuery();
        	 System.out.println("id\t\tname\t\tphone_number\t\taddress\t\tdepartment\t\tgender\t\tbasic_pay");
        	 while(result.next())
        	 {
        	 	int id=result.getInt("id");
        	 	String name=result.getString("name");
        	 	double phoneNumber=result.getInt("phone_number");
        	 	String address=result.getString("address");
        	 	String department=result.getString("department");
        	 	String gender=result.getString("gender");
        	 	double basicpay=result.getInt("basic_pay");
        	 	System.out.println(id+ "\t\t" +name+ "\t\t" +phoneNumber + "       \t\t" +address+ "\t\t" +department+ "      \t\t" +gender+ "\t\t" +basicpay);
        	 }
        	 listDrivers();
	}catch(Exception e)
         {
		e.printStackTrace();
         }
	}
	
	    //Listing the MySQL JDBC Drivers
           private static void listDrivers() {
	       Enumeration<java.sql.Driver> driverList = DriverManager.getDrivers();
              while(driverList.hasMoreElements()) {
		            Driver driverClass=(Driver)driverList.nextElement();
		            System.out.println(" "+driverClass.getClass().getName());
	          }
            }
}
