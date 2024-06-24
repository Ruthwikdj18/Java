package com.cddst.jdbcproject2.project2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
public class operation {
	static Scanner sc=new Scanner(System.in);
	private static PreparedStatement pstmt;
	private static ResultSet res;
	private static String name;
	private static int id;
	private static Connection conn;
	public static void register(Connection con) {
		try {
			String sql= "insert into registration values(?,?,?,?,?) ";
			pstmt=con.prepareStatement(sql);
			System.out.println("Enter the ID");
			int id=sc.nextInt();
			System.out.println("Enter your name");
			String name=sc.next();
			System.out.println("Enter your email");
			String email=sc.next();
			System.out.println("Enter your username");
			String username=sc.next();
			System.out.println("Enter your password");
			String password=sc.next();
		pstmt.setInt(1,id);
		pstmt.setString(2,name);
		pstmt.setString(3,email);
		pstmt.setString(4,username);
		pstmt.setString(5,password);
		int x=pstmt.executeUpdate();
		if(x>0) {
			System.out.println("User is Registered");
		}
		else {
			System.out.println("Error");
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void login(Connection con) {
		try {
			String sql= "select * from registration where username=? and password=?";
			pstmt=con.prepareStatement(sql);
			System.out.println("Enter your username");
			String username=sc.next();
			System.out.println("Enter your password");
			String password=sc.next();
		pstmt.setString(1,username);
		pstmt.setString(2,password);
		res=pstmt.executeQuery();
		if(res!=null) {
			System.out.println("User login Successfull");
			employeeoperations.start(username,con);
		}
		else {
			System.out.println("User login failed");
			prj2.adminops(conn);
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void emplogin(Connection con) {
		try {
			conn=con;
			String sql="select * from employee_det where emp_email=? and pwd=?";
			pstmt=con.prepareStatement(sql);
			System.out.println("Enter Employee email");
			String email=sc.next();
			System.out.println("Enter Password");
			String pwd=sc.next();
			pstmt.setString(1,email);
			pstmt.setString(2,pwd);
			res=pstmt.executeQuery();
			if(res!=null) {
			System.out.println("Employee login Successful");
				while(res.next()!=false) {
					id=res.getInt(1);
					name=res.getString(2);
				}
				empoperations.start(conn,id,name);
			}
			else {
				System.out.println("User login failed");
				prj2.empops(conn);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
