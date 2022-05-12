package com.paulnchinnam.midtermProject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateEntry {
	
	static Connection con = Database.con();
	static Statement stmt;
	
	static void addEmployee() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\nWhat is the employee's first name?");
		String fName = sc.nextLine();
		
		System.out.print("\nWhat is the employee's last name?");
		String lName = sc.nextLine();

		System.out.println("\nWhat is the employee's position?");
		String position = sc.nextLine();
		
		System.out.println("\nWhat is the employee's pay rate?");
		int payRate = sc.nextInt();
		
		System.out.println("\nWhat is the employee's benefitId?");
		int benefitId = sc.nextInt();
	

		try {
			
			stmt = (Statement) con.createStatement();
			String query1 = String.format("INSERT INTO Employee "+  "(first_name, last_name, position) " + "VALUES ( '%s', '%s', '%s');", fName, lName, position);
			stmt.executeUpdate(query1);
			
			String getId = "SELECT employee_id FROM hris.Employee ORDER BY employee_id DESC LIMIT 1";
			Statement stmt2 = con.createStatement();
			ResultSet rs = stmt2.executeQuery(getId);
			
			System.out.println(payRate + " " + benefitId);
			rs.next();
			// System.out.println(rs.getString("employee_id"));1
			
			int employeeId = Integer.valueOf(rs.getString("employee_id"));
			System.out.println(employeeId);
			
			Statement stmt3 = con.createStatement();
			stmt3 = (Statement) con.createStatement();
			String query2 = String.format("INSERT INTO Payroll " + "(employee_id, pay_rate, benefit_id) " + "VALUES (%s, %s, %s);", employeeId, payRate, benefitId);
			stmt3.executeUpdate(query2);
			
			System.out.println("Entry added successfully.......");
		} 
		
		catch (SQLException e) {
			
            System.out.println(e);
		}
	}
}
