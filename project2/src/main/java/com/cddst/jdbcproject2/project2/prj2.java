package com.cddst.jdbcproject2.project2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class prj2 {

	private static Connection con;
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args)throws Exception {
		System.out.println("Welcome To Employee Management");
		con=DriverManager.getConnection(credentials.url, credentials.user, credentials.pwd);
		System.out.println("Select the User Type \n 1.Employee \n 2.Admin \n 3.Manager\n 4.exit");
		int choice=sc.nextInt();
		switch(choice) {
		case 1:empops(con);
				main(null);
				break;
		case 2:adminops(con);
				main(null);
				break;
		case 3:managerops(con);
				break;
		case 4:System.exit(0);
		default:System.out.println("Invalid choice");
				break;
		}
		
		
		}
	static void managerops(Connection con) {
		// TODO Auto-generated method stub
		 try {
			 System.out.println("Dear Manager Select one option \n 1.Login \n 2.Quit");
				int n=sc.nextInt();
				switch(n) {
				case 1:managercrud.login(con);
						main(null);
						
				case 2:main(null);
				
				default:System.out.println("wrong option");
					main(null);

				}
				}
				catch(Exception e) {
					e.printStackTrace();
				}	
		 }
		
	
	static void empops(Connection con) {
		try{
			System.out.println("Dear Employee Select one option \n 1.Login \n 2.Quit");
		
		int n=sc.nextInt();
		switch(n) {
		case 1:operation.emplogin(con);
				main(null);
				
		case 2:main(null);
		
		default:System.out.println("wrong option");
			main(null);

		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		
	}
		public static void adminops(Connection con) {
			try{
				System.out.println("Dear Admin Select one option \n 1.register \n 2.login \n 3.Quit");
			
			int n=sc.nextInt();
			switch(n) {
			case 1:operation.register(con);
					main(null);
			case 2:operation.login(con);
					main(null);
			case 3:main(null);
			default:System.out.println("wrong option");
				main(null);

			}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
}



