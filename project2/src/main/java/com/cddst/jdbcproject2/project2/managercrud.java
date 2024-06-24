package com.cddst.jdbcproject2.project2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class managercrud {
 private static PreparedStatement pstmt;
private static Connection conn;
private static String name;
private static int id;
static Scanner sc=new Scanner(System.in);
private static String department;

public static void login(Connection con) {
	try {
		conn=con;
		String sql="Select *from employee_det where emp_email=? and pwd=? and emp_desg='manager'";
		pstmt=conn.prepareStatement(sql);
		System.out.println("Enter the email");
		String email=sc.next();
		System.out.println("Enter the password");
		String pwd=sc.next();
		pstmt.setString(1,email);
		pstmt.setString(2,pwd);
		ResultSet res=pstmt.executeQuery();
		while(res.next()==true) {
			id=res.getInt("emp_id");
			name=res.getString("emp_name");
			department=res.getString("emp_dname");
		}
		if(id==0 && name==null) {
			System.out.println("Invalid Credentials,Please Try again");
			prj2.managerops(conn);
		}
		else {
			Manageroperations();
		}
		
		
	}catch(Exception e) {
		e.printStackTrace();
	}
 }

private static void Manageroperations() {
	try {
		System.out.println("Welcome"+name);
		System.out.println("Please Select the operation");
		System.out.println("1.View Manager Details\n"+
				"2.View all employees in his Department and their details\n"+
				"3.Update Phone Number\n"+
				"4.Update email\n"+
				"5.Update Password\n"+
				"6.View tasks\n"+
				"7.Update tasks\n"+
				"8.Quit");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:viewalldetails();
				break;
		case 2:Viewempdetails();
				break;
		case 3:updatephone();
				break;
		case 4:updateemail();
				break;
		case 5:Updatepassword();
				break;
		case 6:Viewtasks();
				break;
		case 7:Updatetasks();
				break;
		case 8:prj2.main(null);
		   break;
		}
	}catch(Exception e) {
		e.printStackTrace();
	}
	
}
static void Updatetasks() {
	try {
		String sql1="select * from employee_det where emp_dname=? and emp_desg='employee";
		pstmt=conn.prepareStatement(sql1);
		pstmt.setString(1,department);
		ResultSet res=pstmt.executeQuery();
		System.out.println("Dear Manager,here is the list of employees who are from the Department "+department);
		while(res.next()!=false) {
			System.out.println("Employee Id: "+res.getInt(1)+"\t"+"Employee Name :"+res.getString(2));
			}
		System.out.println("\n\n");
		String sql2="Select * from tasks where manager=?";
		pstmt=conn.prepareStatement(sql2);
		pstmt.setInt(1, id);
		ResultSet res2=pstmt.executeQuery();
		System.out.println("Here is the list of tasks assigned to department "+department);
		while(res2.next()==true) {
			System.out.println("Task id = "+res.getInt(1));
			System.out.println("Task Title = "+res.getString(2));
			System.out.println("Task Description = "+res.getString(3));
			System.out.println("Allocated to = "+res.getString(6));
			System.out.println("Status = "+res.getString(7));
	}
		System.out.println("Do you wish to Allocate the Task?(yes/no) ");
		
		String choice=sc.next();
		if(choice.equalsIgnoreCase("yes")) {
			try {
				String sql="update tasks set assigned_to=? where task_id=?";
				pstmt=conn.prepareStatement(sql);
				System.out.println("Enter the Employee id for allocation");
				int id=sc.nextInt();
				System.out.println("Enter the Task id for allocation");
				int task_id=sc.nextInt();
				pstmt.setInt(1, id);
				pstmt.setInt(2,task_id);
				int x=pstmt.executeUpdate();
				if(x>0) {
					System.out.println("Sucessfully Updated");
				}
				else
					System.out.println("Uncessfull");
				Manageroperations();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		else {
			Manageroperations();
		}
		
	}
	catch(Exception e) {
		
	}
	
}

private static void Viewtasks() {
	try {
		String sql="Select * from tasks where manager=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet res=pstmt.executeQuery();
		while(res.next()==true) {
			System.out.println("Task id = "+res.getInt(1));
			System.out.println("Task Title = "+res.getString(2));
			System.out.println("Task Description = "+res.getString(3));
			System.out.println("Allocated to = "+res.getString(6));
			System.out.println("Status = "+res.getString(7));
		}
		System.out.println("\n\n");
		Manageroperations();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	
}

private static void Updatepassword() {
	// TODO Auto-generated method stub
	try {
		String sql="update employee_det set pwd=? where emp_id=?";
		pstmt=conn.prepareStatement(sql);
		System.out.println("Enter the New Password");
		String pwd=sc.next();
		pstmt.setString(1, pwd);
		pstmt.setInt(2,id);
		int x=pstmt.executeUpdate();
		if(x>0) {
			System.out.println("Sucessfully Updated");
		}
		else
			System.out.println("Uncessfull");
		Manageroperations();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	
}

private static void updateemail() {
	// TODO Auto-generated method stub
	try {
		String sql="update employee_det set emp_email=? where emp_id=?";
		pstmt=conn.prepareStatement(sql);
		System.out.println("Enter the New Email");
		String email=sc.next();
		pstmt.setString(1, email);
		pstmt.setInt(2,id);
		int x=pstmt.executeUpdate();
		if(x>0) {
			System.out.println("Sucessfully Updated");
		}
		else
			System.out.println("Uncessfull");
		Manageroperations();
		}
	catch(Exception e) {
		e.printStackTrace();
	}
	
}

private static void updatephone() {
	// TODO Auto-generated method stub
	try {
		String sql="update employee_det set emp_phn=? where emp_id=?";
		pstmt=conn.prepareStatement(sql);
		System.out.println("Enter the New Phone Number");
		long phn=sc.nextLong();
		pstmt.setLong(1, phn);
		pstmt.setInt(2,id);
		int x=pstmt.executeUpdate();
		if(x>0) {
			System.out.println("Sucessfully Updated");
		}
		else
			System.out.println("Uncessfull");
		Manageroperations();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}

private static void Viewempdetails() {
	// TODO Auto-generated method stub
	try {
		String sql="select * from employee_det where emp_dname=? and emp_desg='employee'";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,department);
		ResultSet res=pstmt.executeQuery();
		while(res.next()!=false) {
			System.out.println("Employee Id: "+res.getInt(1));
			System.out.println("Employee Name: "+res.getString(2));
			System.out.println("Employee Experience: "+res.getInt(3));
			System.out.println("Employee Comapny Name: "+res.getString(4));
			System.out.println("Employee Department Name : "+res.getString(5));
			System.out.println("Employee Department Designation"+res.getString(6));
			System.out.println("Employee Salary:"+res.getInt(7));
			System.out.println("Employee Phone number:"+res.getLong(8));
			System.out.println("Employee Email: "+res.getString(9));
		}
		Manageroperations();
	}catch(Exception e) {
		e.printStackTrace();
	}
}

private static void viewalldetails() {
	// TODO Auto-generated method stub
	try {
		String sql="select * from employee_det where emp_id=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1,id);
		ResultSet res=pstmt.executeQuery();
		while(res.next()!=false) {
			System.out.println("Employee Id: "+res.getInt(1));
			System.out.println("Employee Name: "+res.getString(2));
			System.out.println("Employee Experience: "+res.getInt(3));
			System.out.println("Employee Comapny Name: "+res.getString(4));
			System.out.println("Employee Department Name : "+res.getString(5));
			System.out.println("Employee Department Designation: "+res.getString(6));
			System.out.println("Employee Salary:"+res.getInt(7));
			System.out.println("Employee Phone number:"+res.getLong(8));
			System.out.println("Employee Email: "+res.getString(9));
		}
		Manageroperations();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}
