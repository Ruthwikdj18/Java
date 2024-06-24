package com.cddst.jdbcproject2.project2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import java.sql.ResultSet;

public class empoperations {
	static Scanner sc=new Scanner(System.in);
	private static Connection conn;
	private static PreparedStatement pstmt;
	public static void start(Connection con,int id,String name) {
		try {
			int empid=id;
			conn=con;
			String emp_name=name;
			
			System.out.println("Welcome"+" "+emp_name);
			System.out.println("Please Select the operation");
			System.out.println("1.View All Details\n"+
					"2.Update Phone Number\n"+
					"3.Update email\n"+
					"4.Update Password\n"+
					"5.View Tasks\n"+	
					"6.Quit");
			int choice=sc.nextInt();
			switch(choice) {
			case 1:viewdetails(empid,emp_name);
				break;
			case 2:updatephone(empid,emp_name);
					break;
			case 3:updateemail(empid,emp_name);
					break;
			case 4:updatepassword(empid,emp_name);
			break;
			case 6:prj2.main(null);
				   break;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void updatepassword(int id,String name) {
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
			start(conn,id,name);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void updateemail(int id,String name) {
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
			start(conn,id,name);
			}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	private static void updatephone(int id,String name) {
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
			start(conn,id,name);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	private static void viewdetails(int id,String name) {
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
		start(conn,id,name);
	}catch(Exception e) {
		e.printStackTrace();
	}
	}

}
