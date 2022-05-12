package com.paulnchinnam.midtermProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public class Database {

	static Connection con(){
		
		// Establish database connection
		String url = "jdbc:mysql://localhost:3306/hris";
		String user = "root";
		String password = "Paul2002";
		
		/* Declaring variable since java cannot return an undeclared. 
		*If an error occurs prior to assigning the connection variable 
		*the program would crash because java cannot return an empty variable*/
		Connection connection = null;
		
		try {
					
			Class.forName("com.mysql.cj.jdbc.Driver");
			 connection = DriverManager.getConnection(url, user, password);
		}
		
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		catch (SQLException ex) {
			
			SQLException newEx = new SQLException("could not connect");
			System.out.println(newEx);
					
		}
		return connection;
	}
	
	static boolean prompt() {
		boolean quit = false;
		Scanner sc = new Scanner(System.in);
		//*Print out options for the user to choose from.
		System.out.println("\nWhich operation would you like to complete");
		System.out.println("Option 1: Add a new row");
		System.out.println("Option 2: Read row or table");
		System.out.println("Option 3: Update a row");
		System.out.println("Option 4: Delete a row");
		System.out.println("Option 0: Quit");

		//* Receive user input of which database to connect to.
		System.out.print("Please select an option: ");
		String userInput = sc.nextLine();
		//* Ensure that both Create or CrEaTe will work for option 1.
		userInput = userInput.toLowerCase();

		if(userInput.equals("1") || userInput.equals("create")) {
			
			CreateEntry.addEmployee();
		}

		else if (userInput.equals("2") || userInput.equals("read")) {
			ReadEntry.prompt();
		}

		else if (userInput.equals("3") || userInput.equals("update")) {
			UpdateEntry.prompt();
		}
		else if (userInput.equals("4") || userInput.equals("delete")) {
			DeleteEntry.deleteRow();
		}
		//quits the program if the user chooses to
		else if  (userInput.equals("0") || userInput.equals("quit")) {
			//*Sets quit to true so that the program knows to exit.
			quit = true;
			sc.close();
			System.out.println("Program is quiting................");
		}

		else {
			System.out.println("\nThat is not a valid option.\nPlease use "
					+ "the option number");
		}
		return quit;
	}
	
}