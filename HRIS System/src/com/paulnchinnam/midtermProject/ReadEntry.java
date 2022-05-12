package com.paulnchinnam.midtermProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Scanner;

public class ReadEntry {
	//Creates a connection to the database
	static Connection connection = Database.con();
	// Creates a scanner instances so that it can receive user input. 
	static Scanner sc = new Scanner(System.in);
	static DecimalFormat df = new DecimalFormat("$#0.00");

	static void prompt() {
		/*Prompts the user either to view all rows,
		 * select a specific row, or view all rows from a specified table.
		 */
		boolean quit = false;
		//Prints out a menu for the user to choose from.
		do {
			System.out.println("\nOption 1: View all employee entries");
			System.out.println("Option 2: View a specific employee");
			System.out.println("Option 3: View all rows from a specific table");
			System.out.println("Option 0: Return to main menu");
			System.out.print("Please choose an option: ");
			/* User input is required so that the program knows which action to
			 * perform.
			 */
			String userInput = sc.nextLine().toLowerCase();
			/* If nextInt() is used instead of nextLine it will only read 
			 * the integer and as a result it will 
			 * make the next nextLine() return a blank string*/

			/* Runs the user input through a series of if/ else statements to 
			Determine which action to take.
			 */
			if (userInput.equals("1") || userInput.equals("all employees")) {
				readAllEmployees();
			} else if (userInput.equals("2") || userInput.equals("specific")) {
				readEntry();
			} else if (userInput.equals("3") || userInput.equals("all rows")) {
				readAllRows();
			} else if (userInput.equals("0") || userInput.equals("quit")) {
				quit = true;
				System.out.println("Returning to the main menu");
			}
			else {
				System.out.println("That is not a valid option\n"
						+ "Please try again!");
			}
			/* Continues to print the prompt until either the user quits or
			 * another method is called.
			 */
		} while (quit != true);
	}

	static void readAllEmployees() {
		/*
		 * Selects every employee entry in the database and 
		 * displays it in a user friendly Format
		 */
		try {
			String sql = "SELECT * FROM Employee;";
			PreparedStatement p = connection.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			// Prints out the header.
			System.out.println("\n\nEmployee ID:\tFullname:\t\n");

			while (rs.next()) {
				// Collects and prints every employee's ID, and name.
				int id = rs.getInt("employee_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				System.out.println(id + "\t\t" + firstName + " " + lastName);
			}
		} catch (SQLException e) {
			// Catches any mySQL exceptions.
			System.out.println(e);
		}
		/* Prints a blank line at the bottom to separate the employee list from
		 * the user prompt.
		 */
		System.out.println("");
	}

	static void readEntry() {
		String message;
		String benefits;
		String header;
		String footer;
		/* Selects a specific row from the database based upon the employee ID
		 * and displays it in a user friendly format 
		 */

		// Prompts the user for an employee ID.
		System.out.print("\nWhich employee ID would you like to view: ");
		// Receives the employee ID from the user
		int employeeID = Integer.parseInt(sc.nextLine());
		/* If nextInt() is used instead of nextLine it will only read 
		 * the integer and as a result it will 
		 * make the next nextLine() return a blank string
		 */

		try {
			String sql = "SELECT * FROM hris.EmployeeInfo WHERE ID " 
					+ "= " + employeeID + ";";
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			/* Initializes two strings which will later be used as the header and
			 * footer for the given output.
			 */
			header = String.format("\n\n%-20s%-15s%-15s", 
					"Name:", "Pay Rate:", "Position:");
			footer = String.format("%-15s%-15s%-15s", 
					"Health Plan:", "Dental Plan:", "Vision Plan:");

			while (rs.next()) {
				/* Collects information from the employee info table and
				 * prints it out in a user friendly format.
				 */
				String name = rs.getString("Name");
				Double p = rs.getDouble("Pay Rate");
				String pay = df.format(p);
				String position = rs.getString("Position");
				String hPlan = rs.getString("Health Plan");
				String dPlan = rs.getString("Dental Plan");
				String vPlan = rs.getString("Vision Plan");

				message = String.format("%-20s%-15s%-15s\n"
						, name, pay, position);
				benefits = String.format("%-15s%-15s%-15s\n"
						, hPlan, dPlan, vPlan);
				System.out.println(header);
				System.out.println(message);
				System.out.println(footer);
				System.out.println(benefits);
			}


		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	static void readAllRows() {
		/* Reads every row and column from a table specified by the user. 
		 * It can even display info from a new table if it exists in the DB.
		 */
		boolean pass = false;
		String table;

		do {
			// Prints out the tables for the user to choose from.
			// TODO print out every table from a DB by using mySQL metadata.
			System.out.println("Employee\nBenefits\nPayroll");
			System.out.print("Which table would you like to read from: ");
			table = sc.nextLine().toLowerCase();
			/* Receives user input and changes it to lower case to ensure that
			 * it matches what is in the if/else statements below.
			 */

			/* A set of if/else statements to ensure the table name is the same
			 * as found in the database.
			 */
			if(table.equals("employee")) {
				table = "Employee";
				pass = true;
			}else if(table.equals("benefits")) {
				table = "Benefits";
				pass = true;
			}else if(table.equals("payroll")) {
				table = "Payroll";
				pass = true;
			}else {
				System.out.println("\nThat is not a valid option.\nPlease use " 
						+ "the table name as seen above");
			}
			/* Similar to the quit option above, this ensures that before a 
			 * statement can be created for mySQL a table name must match.
			 */
		}while(pass != true);



		try {
			// Prepares the mysql statement using the input for the table name.
			String sql = "SELECT * FROM "+ table + ";";
			PreparedStatement p = connection.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			// Prints a new line for proper formating.
			System.out.println("\n");
			// Prints out the column names in a user friendly format.
			for(int i = 1; i <= rsmd.getColumnCount(); i++) {
				String colName = rsmd.getColumnName(i);
				String header = String.format("%-20s", colName);
				System.out.print(header);
			}
			System.out.println("\n");
			while(rs.next()) {
				for(int i = 1; i <= rsmd.getColumnCount(); i++) {
					// Retrieves all the data from the columns and rows.
					String colName = rsmd.getColumnName(i);
					String colType = rsmd.getColumnTypeName(i);

					/* Checks what type of data is stored in a specific column 
					 * and retrieves the data accordingly*/
					if(colType.equals("INT")) {
						String output = String.format("%-20d", 
								rs.getInt(colName));
						System.out.print(output);

					}else if(colType.equals("DOUBLE")) {
						String d = df.format(rs.getDouble(colName));
						String output = String.format("%-20s", d);
						System.out.print(output);

					}else if(colType.equals("VARCHAR")) {
						String output = String.format("%-20s", 
								rs.getString(colName));
						System.out.print(output);
					}
					// Three print statements for formating
					System.out.print("");
				}
				System.out.println();
			}

		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println();
	}

	static String displayEmployee(int id) {
		// Used to display an employee name based on the Employee ID.
		try {
			String sql = "SELECT * FROM hris.Employee WHERE "
					+ "employee_id = " + id;
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				/* Retrieves the users first and last name and 
				 * formats it. Returns the formated string
				 */
				String fName = rs.getString("first_name");
				String lName = rs.getString("last_name");
				String name = String.format("\n%s %s\n", fName,lName);
				return name;
			}
		} catch (SQLException e) {
			return "Error locating employee\nPlease try again\n";
		}
		/* Java requires that every method returns a specified variable
		 * because the try block may fail the return statement may not always 
		 * get executed.
		 */
		return null;
	}
}
