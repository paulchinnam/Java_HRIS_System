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
			System.out.println("Option 2: Update an employee using a column name and attribute");
			System.out.println("Option 0: quit");
			System.out.print("Please choose an option: ");
			
			String userInput = sc.nextLine().toLowerCase();

			if (userInput.equals("1") || userInput.equals("all employees")) {
				
				updateById();
			}
			
			else if (userInput.equals("2") || userInput.equals("specific")) {
				
				updateByAttr();
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

	// updates a single entry in selected table using table Attributes
	static void updateByAttr() {

		// variables
		String tableName, columnName, field;

		// Prompt
		System.out.print("What is the table name? ");
		tableName = sc.nextLine().toLowerCase();
		System.out.print("What is the name of the column where you will make the update? ");
		columnName = sc.nextLine().toLowerCase();
		System.out.print("What column are you using as the condition? ");
		field = sc.nextLine().toLowerCase();
		System.out.print("What is the conditional value? ");

		
		/*
		 * To my future self and other developers:
		 * 
		 * the code below are three sets of nested conditionals. The outer conditional determines the 
		 * data type for the field value used to create the SQL 'where' clause. The inner
		 * conditional determines the data type for the new value the user wishes to store.
		 * Finally, each inner conditional has a separate try catch statement because variables
		 * defined within the conditionals were not accessible outside the scope of the 
		 * if statement. Therefore, to make use of inner scoped variables, each if conditional
		 * was given their own separate try catch. 
		 * 
		 * This code is meant to allow the user to store different data
		 * types without first defining what data type is being held in the
		 * selected column.
		 * 
		 * Note: Since no SQL table from HRIS schema possess more than two data types,
		 * every decision path may not be needed. However, they could become useful
		 * if new table columns/data types are ever added in the future.  
		 * 
		 * Conditional Structure:
		 * if(fieldValue == integer){
		 *   if(newValue == integer)
		 *   else if (newValue == double)
		 *   else (newValue == String )
		 * }
		 * else if (fieldValue == double){
		 *   if(newValue == integer)
		 *   else if (newValue == double)
		 *   else (newValue == String )
		 * }
		 * if else (fieldValue == String){
		 *   if(newValue == integer)
		 *   else if (newValue == double)
		 *   else (newValue == String )
		 * }
		 * 
		 * 
		 */
		
		
		// if statement determines data type and assigns user input to fieldValue
		if (field.equals("employee_id") || field == "benefit_id") {
			int fieldValue = Integer.valueOf(sc.nextLine());
			// if statement determines data type and assigns user input to newValue
			if (columnName == "employee_id" || columnName == "benefit_id") {
				System.out.print("What is the new value you wish to store? ");
				int newValue = Integer.valueOf(sc.nextLine());
				try {

					String sql = String.format("UPDATE %s SET %s = %s where %s = %s", tableName, columnName, newValue,
							field, fieldValue);
					Statement statement = connection.createStatement();

					int rows = statement.executeUpdate(sql);

					if (rows > 0) {
						System.out.println(
								String.format("The %s column in %s has updated successfully ", columnName, tableName));
					}

				} catch (SQLException e) {
					System.out.println(e);
				}
			} else if (columnName == "pay_rate") {
				System.out.print("What is the new value you wish to store? ");
				double newValue = Double.parseDouble(sc.nextLine());
				try {

					String sql = String.format("UPDATE %s SET %s = %s where %s = %s", tableName, columnName, newValue,
							field, fieldValue);
					Statement statement = connection.createStatement();

					int rows = statement.executeUpdate(sql);

					if (rows > 0) {
						System.out.println(
								String.format("The %s column in %s has updated successfully ", columnName, tableName));
					}

				} catch (SQLException e) {
					System.out.println(e);
				}
			} else {
				System.out.print("What is the new value you wish to store? ");
				String newValue = sc.nextLine();
				try {

					String sql = String.format("UPDATE %s SET %s = '%s' where %s = %s", tableName, columnName, newValue,
							field, fieldValue);
					Statement statement = connection.createStatement();

					int rows = statement.executeUpdate(sql);

					if (rows > 0) {
						System.out.println(
								String.format("The %s column in %s has updated successfully ", columnName, tableName));
					}

				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		} else if (field == "pay_rate") {
			double fieldValue = Double.parseDouble(sc.nextLine());
			// if statement determines data type and assigns user input to newValue
			if (columnName == "employee_id" || columnName == "benefit_id") {
				System.out.print("What is the new value you wish to store? ");
				int newValue = Integer.valueOf(sc.nextLine());
				try {

					String sql = String.format("UPDATE %s SET %s = %s where %s = %s", tableName, columnName, newValue,
							field, fieldValue);
					Statement statement = connection.createStatement();

					int rows = statement.executeUpdate(sql);

					if (rows > 0) {
						System.out.println(
								String.format("The %s column in %s has updated successfully ", columnName, tableName));
					}

				} catch (SQLException e) {
					System.out.println(e);
				}
			} else if (columnName == "pay_rate") {
				System.out.print("What is the new value you wish to store? ");
				double newValue = Double.parseDouble(sc.nextLine());
				try {

					String sql = String.format("UPDATE %s SET %s = %s where %s = %s", tableName, columnName, newValue,
							field, fieldValue);
					Statement statement = connection.createStatement();

					int rows = statement.executeUpdate(sql);

					if (rows > 0) {
						System.out.println(
								String.format("The %s column in %s has updated successfully ", columnName, tableName));
					}

				} catch (SQLException e) {
					System.out.println(e);
				}
			} else {
				System.out.print("What is the new value you wish to store? ");
				String newValue = sc.nextLine();
				try {

					String sql = String.format("UPDATE %s SET %s = '%s' where %s = %s", tableName, columnName, newValue,
							field, fieldValue);
					Statement statement = connection.createStatement();

					int rows = statement.executeUpdate(sql);

					if (rows > 0) {
						System.out.println(
								String.format("The %s column in %s has updated successfully ", columnName, tableName));
					}

				} catch (SQLException e) {
					System.out.println(e);
				}
			}
		} else {
			String fieldValue = sc.nextLine();
			// if statement determines data type and assigns user input to newValue
			if (columnName == "employee_id" || columnName == "benefit_id") {
				System.out.print("What is the new value you wish to store? ST ");
				int newValue = Integer.valueOf(sc.nextLine());
				
				try {

					String sql = String.format("UPDATE %s SET %s = %s where %s = '%s'", tableName, columnName, newValue, field, fieldValue);
					Statement statement = connection.createStatement();

					int rows = statement.executeUpdate(sql);

					if (rows > 0) {
						
						System.out.println(String.format("The '%s' column in %s has updated successfully ", columnName, tableName));
					}
				}
				
				catch (SQLException e) {
					
					System.out.println(e);
				}
			}
			
			else if (columnName == "pay_rate") {
				
				System.out.print("What is the new value you wish to store? ");
				double newValue = Double.parseDouble(sc.nextLine());
				
				try {

					String sql = String.format("UPDATE %s SET %s = %s where %s = '%s'", tableName, columnName, newValue, field, fieldValue);
					Statement statement = connection.createStatement();

					int rows = statement.executeUpdate(sql);

					if (rows > 0) {
						
						System.out.println(String.format("The '%s' column in %s has updated successfully ", columnName, tableName));
					}
				}
				
				catch (SQLException e) {
					
					System.out.println(e);
				}
			}
			
			else {
				
				System.out.print("What is the new value you wish to store?  STRING STRING ");
				String newValue = sc.nextLine();
				
				try {

					String sql = String.format("UPDATE %s SET %s = '%s' where %s = '%s'", tableName, columnName, newValue, field, fieldValue);
					System.out.println(sql);
					Statement statement = connection.createStatement();

					int rows = statement.executeUpdate(sql);

					if (rows > 0) {
						
						System.out.println(String.format("The '%s' column in %s has updated successfully ", columnName, tableName));
					}
				}
				
				catch (SQLException e) {
					
					System.out.println(e);
				}
			}
		}
	}
}
