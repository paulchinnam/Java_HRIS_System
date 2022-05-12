package com.paulnchinnam.midtermProject;

import java.sql.Connection;


public class MidtermProject {

	public static void main(String[] args) {
		
		boolean quit = false;
		
		//Connects to the database and prints out a message if successful.
		Connection connection = Database.con();
		
		if (connection != null) {
			
			System.out.println("Successfully connected to the database!");
		}
		
		while (quit != true) {
			
			quit = Database.prompt();
		}
	}
}
