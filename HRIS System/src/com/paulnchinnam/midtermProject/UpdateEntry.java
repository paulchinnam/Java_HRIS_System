package com.paulnchinnam.midtermProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;

public class UpdateEntry {
	
	// Creates a database connection
	static Connection connection = Database.con();
	
	// Creates a scanner instances so that it can receive user input.
	static Scanner sc = new Scanner(System.in);

	static void prompt() {
		
		/*
		 * Prompts the user either to view all rows, select a specific row, or view all
		 * rows from a specified table.
		 */
		boolean quit = false;

		do {
			
			System.out.println("Select an option below to update an entry.");
			System.out.println("Option 1: Update an employee using their ID");
			System.out.println("Option 0: quit");
			System.out.print("Please choose an option: ");
			
			String userInput = sc.nextLine().toLowerCase();

			if (userInput.equals("1") || userInput.equals("all employees")) {
				
				updateById();
			}
			
			else if (userInput.equals("0") || userInput.equals("quit")) {
				
				quit = true;
				System.out.println("Returning to the main menu");
			}
		} while (quit != true);
	}

	// updates a single entry in selected table using ID
	static void updateById() {

		// variables
		String tableIdName, tableName, columnName;
		int ID;

		// Prompt
		// user input table name
		System.out.print("What is the table name? ");
		tableName = sc.nextLine().toLowerCase();

		// set the table ID
		if (tableName.equalsIgnoreCase("benefits"))
			tableIdName = "benefit_id";
		
		else
			tableIdName = "employee_id";
		
		// user input column name
		System.out.print("What is the column name? ");
		columnName = sc.nextLine().toLowerCase();
		
		// user input ID of entry they wish to edit
		System.out.print("What is the ID number? ");
		ID = Integer.valueOf(sc.nextLine());
		
		// user input new value
		System.out.print("Add new value. ");
		
		// if statement below determines data
		// type and assigns user input to newValue variable
		if (columnName == "employee_id" || columnName == "benefit_id") {
			
			int newValue = Integer.valueOf(sc.nextLine());
			
			try {

				String sql = String.format("UPDATE %s SET %s = %s where %s = %s", tableName, columnName, newValue,
						tableIdName, ID);
				Statement statement = connection.createStatement();

				int rows = statement.executeUpdate(sql);

				if (rows > 0) {
					
					System.out.println(
							String.format("The %s  column in %s has updated successfully ", columnName, tableName));
				}

			}
			
			catch (SQLException e) {
				
				System.out.println(e);
			}
		}
		
		else if (columnName == "pay_rate") {
			
			double newValue = Double.parseDouble(sc.nextLine());
			
			try {

				String sql = String.format("UPDATE %s SET %s=%s where %s=%s", tableName, columnName, newValue,
						tableIdName, ID);
				Statement statement = connection.createStatement();

				int rows = statement.executeUpdate(sql);

				if (rows > 0) {
					
					System.out.println(
							String.format("The %s  column in %s has updated successfully ", columnName, tableName));
				}
			}
			
			catch (SQLException e) {
				
				System.out.println(e);
			}
		}
		
		else {
			
			String newValue = sc.nextLine();
			
			try {

				String sql = String.format("UPDATE %s SET %s = '%s' where %s = %s", tableName, columnName, newValue,
						tableIdName, ID);
				Statement statement = connection.createStatement();

				int rows = statement.executeUpdate(sql);

				if (rows > 0) {
					
					System.out.println(
							String.format("The '%s' column in %s has updated successfully ", columnName, tableName));
				}
			}
			
			catch (SQLException e) {
				
				System.out.println(e);
			}
		}
	}
}
