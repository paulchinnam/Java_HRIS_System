package com.paulnchinnam.midtermProject;

import java.sql.Connection;
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
	

		try {
			
			stmt = (Statement) con.createStatement();
			String query1 = String.format("INSERT INTO Employee "+  "(first_name, last_name, position) " + "VALUES ( '%s', '%s', '%s');", fName, lName, position);
			stmt.executeUpdate(query1);
			
			System.out.println("Entry added successfully.......");
		} 
		
		catch (SQLException e) {
			
            System.out.println(e);
		}
	}
}
