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
        	 
        //update the salary for the basic pay for Employee Terrisa to 3000000.00 and sync it with Database
        	 String whereCondition =" basic_pay=? where department=? ";
        	 String query="update employee_payroll set" + whereCondition;

        	 //Using prepared statement to update the basic_pay in the table
        	 PreparedStatement statement = connection.prepareStatement(query);
        	 statement.setDouble(1,30000000);
        	 statement.setString(2,"sales");
        	 Integer recordUpdated=statement.executeUpdate();
        	 System.out.println("Records updated : "+recordUpdated);
        	
       //Employee Payroll Service to retrieve the Employee Payroll from the Database
        	 String query1="Select * from employee_payroll";
        	 PreparedStatement statement1 = connection.prepareStatement(query1);
        	 ResultSet result1=statement1.executeQuery();
        	 System.out.println("id\t\tname\t\tphone_number\t\taddress\t\tdepartment\t\tgender\t\tbasic_pay");
        	 while(result1.next())
        	 {
        	 	int id=result1.getInt("id");
        	 	String name=result1.getString("name");
        	 	double phoneNumber=result1.getInt("phone_number");
        	 	String address=result1.getString("address");
        	 	String department=result1.getString("department");
        	 	String gender=result1.getString("gender");
        	 	double basicpay=result1.getInt("basic_pay");
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
