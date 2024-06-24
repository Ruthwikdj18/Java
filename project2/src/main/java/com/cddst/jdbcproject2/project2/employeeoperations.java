package com.cddst.jdbcproject2.project2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class employeeoperations {
	static String admin_name;
	private static Connection conn;
	private static PreparedStatement ppst;
	static Scanner sc =new Scanner(System.in);
	private static Statement stmt;
	public static void start(String admin,Connection con) {
		try {
			admin_name=admin;
			conn =con;
			System.out.println("Welcome"+" "+admin_name);
			System.out.println("Please Select the operation");
			System.out.println("1.ADD Employee\n"
					+"2.Update employee name\n"
					+"3.Update employee salary\n"
					+"4.Update employee phone\n"
					+"5.Update employee email\n"
					+"6.Update all employee salary\n"
					+"7.View all employee\n"
					+"8.Delete an employee\n"
					+"9.Quit\n");
			int choice=sc.nextInt();
			switch(choice) {
			case 1:addemployee();
					break;
			case 2:updatename();
					break;
			case 3:updatesalary();
					break;
			case 4:updatephone();
					break;
			case 5:updateemail();
					break;
			case 6:updateallsalery();
					break;
			case 7:viewemployee();
					break;
			case 8:deleteemplyoee();
					break;			
			case 9:prj2.adminops(conn);
					break;
			default:break;
			}
		}
		catch(Exception e) {
			e.printStackTrace();

				}
		
	}
private static void updateallsalery() {
	String sql="Update employee_det set emp_salary=emp_salary+(emp_salary*?)";
	try {
		ppst=conn.prepareStatement(sql);
		System.out.println("Enter the % of increase");
		int perc=sc.nextInt();
		perc=perc/100;
		ppst.setInt(1,perc);
		int x=ppst.executeUpdate();
		if(x>0) {
			System.out.println("Successfully updated");
			start(admin_name,conn);
		}
		else {
			System.out.println("Unsuccessful");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}		
	}
private static void viewemployee() {
	try {	// TODO Auto-generated method stub
	
		String sql="Select * from employee_det";
		stmt=conn.createStatement();
		ResultSet res=stmt.executeQuery(sql);
		while(res.next()!=false) {
			System.out.println("Employee Id: "+res.getInt(1));
			System.out.println("Employee Name: "+res.getString(2));
			System.out.println("Employee Experience: "+res.getInt(3));
			System.out.println("Employee Comapny Name: "+res.getString(4));
			System.out.println("Employee Department Name : "+res.getString(5));
			System.out.println("Employee Salary:"+res.getInt(6));
			System.out.println("Employee Phone number:"+res.getLong(7));
			System.out.println("Employee Email: "+res.getString(8));
		}
		start(admin_name,conn);
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
	}
private static void deleteemplyoee() {
		// TODO Auto-generated method stub
	String sql="Delete from employee_det where emp_id=?";
	try {
		ppst=conn.prepareStatement(sql);
		System.out.println("Enter the ID of employee to delete");
		int id=sc.nextInt();
		ppst.setInt(1, id);
		int x=ppst.executeUpdate();
		if(x>0) {
			System.out.println("Successfully Deleted");
			start(admin_name,conn);
		}
		else {
			System.out.println("Unsuccessful");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}			
		
	}
private static void updateemail() {
	String sql="Update employee_det set emp_email=? where emp_id=?";
	try {
		ppst=conn.prepareStatement(sql);
		System.out.println("Enter the ID of employee to change name");
		int id=sc.nextInt();
		System.out.println("Enter the new email number of the employee to change");
		String email=sc.next();
		ppst.setString(1,email);
		ppst.setInt(2, id);
		int x=ppst.executeUpdate();
		if(x>0) {
			System.out.println("Successfully updated");
			start(admin_name,conn);
		}
		else {
			System.out.println("Unsuccessful");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}			
	}

static void updatephone() {
	String sql="Update employee_det set emp_phn=? where emp_id=?";
	try {
		ppst=conn.prepareStatement(sql);
		System.out.println("Enter the ID of employee to change name");
		int id=sc.nextInt();
		System.out.println("Enter the new phone number of the employee to change");
		long phn=sc.nextLong();
		ppst.setLong(1,phn);
		ppst.setInt(2, id);
		int x=ppst.executeUpdate();
		if(x>0) {
			System.out.println("Successfully updated");
			start(admin_name,conn);
		}
		else {
			System.out.println("Unsuccessful");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}	
	
		
	}
private static void updatesalary() {
		
	String sql="Update employee_det set emp_salary=? where emp_id=?";
	try {
		ppst=conn.prepareStatement(sql);
		System.out.println("Enter the ID of employee to change name");
		int id=sc.nextInt();
		System.out.println("Enter the new salary of the employee to change");
		int sal=sc.nextInt();
		ppst.setInt(1,sal);
		ppst.setInt(2, id);
		int x=ppst.executeUpdate();
		if(x>0) {
			System.out.println("Successfully updated");
			start(admin_name,conn);
		}
		else {
			System.out.println("Unsuccessful");
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}		
	}

 static void updatename() {
		String sql="Update employee_det set emp_name=? where emp_id=?";
		try {
			ppst=conn.prepareStatement(sql);
			System.out.println("Enter the ID of employee to change name");
			int id=sc.nextInt();
			System.out.println("Enter the new name of the employee to change");
			String name=sc.next();
			ppst.setString(1,name);
			ppst.setInt(2, id);
			int x=ppst.executeUpdate();
			if(x>0) {
				System.out.println("Successfully updated");
				start(admin_name,conn);
			}
			else {
				System.out.println("Unsuccessful");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
static void addemployee() {
		String sql="insert into employee_det(emp_id,emp_name,emp_exp,emp_cname,emp_dname,emp_salary,emp_phn,emp_email) values(?,?,?,?,?,?,?,?)";
		try {
			ppst=conn.prepareStatement(sql);
			System.out.println("Enter the ID");
			int id=sc.nextInt();
			System.out.println("Enter the Name");
			String name=sc.next();
			System.out.println("Enter the Experience");
			int exp=sc.nextInt();
			System.out.println("Enter the Company Name");
			String comp_name=sc.next();
			System.out.println("Enter the Department Name");
			String dept_name=sc.next();
			System.out.println("Enter the Salary");
			int sal=sc.nextInt();
			System.out.println("Enter the phone number");
			long phn=sc.nextLong();
			System.out.println("Enter the email");
			String email=sc.next();
			ppst.setInt(1,id);
			ppst.setString(2, name);
			ppst.setInt(3, exp);
			ppst.setString(4, comp_name);
			ppst.setString(5, dept_name);
			ppst.setInt(6, sal);
			ppst.setLong(7, phn);
			ppst.setString(8, email);
			int x=ppst.executeUpdate();
			if(x>0) {
				System.out.println("Successfully added the employee details");
				start(admin_name,conn);
			}
			else {
				System.out.println("Unsucessfull!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
