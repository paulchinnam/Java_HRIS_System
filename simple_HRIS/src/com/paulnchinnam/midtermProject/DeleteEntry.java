package com.paulnchinnam.midtermProject;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// Author - Joseph Huntley.

public class DeleteEntry {
	
	static Connection con = Database.con();
	static Scanner sc = new Scanner(System.in);

	static void deleteRow() {
		
		/* A method that receives an employee ID from the user and deletes that
		 * employee from both the Employee & Payroll tables.
		 */
		Statement stmt;
		boolean quit = false;

		while (quit != true) {
			
			try {
				
				/*Ask the user which row they would like to delete based on the 
				 * primary key (employee ID)*/
				System.out.println("\nPlease use the employee ID!");
				System.out.print("Which employee would you like to delete: ");
				
				// Retrieves the empID from the user.
				String input = sc.nextLine().toLowerCase();
				
				/* If nextInt() is used instead of nextLine it will only read 
				 * the integer and as a result it will 
				 * make the next nextLine() return a blank string*/

				//An if statement to allow the user to quit if the choose
				if (input.equals("quit") || input.equals("q") || input.equals("0")) {
					
					System.out.println("Returning to the main menu......");
					quit = true;
				}
				
				else {
					
					/* If the user doesn't want to quit it checks the input 
					 * for an integer value and uses that as the employee ID.
					 */
					int id = Integer.parseInt(input);

					// Print out the employee name prior to deleting.
					String name = ReadEntry.displayEmployee(id);
					
					/* If the user is not in the DB it will return null. 
					 * prints out a message accordingly.
					 */
					if (name == null) {
						
						System.out.println("User not found\nPlease try again "
								+ "or use a different employee ID");
					}
					
					else {
						
						/* Verify that the user is sure they want to delete 
						 * the employee.
						 */
						System.out.println(name);
						System.out.println("Are you sure you would like to " + "delete this user?(y/n): ");
						String y_n = sc.nextLine().toLowerCase();

						if (y_n.equals("yes") || y_n.equals("y")) {
							//tries to delete the employee
							
							try {
								
								stmt = (Statement) con.createStatement();
								String query = String.format("DELETE FROM " + "Employee WHERE employee_id = %d;", id);
								String query2 = String.format("DELETE FROM " + "Payroll WHERE employee_id = %d", id);
								
								/*The payroll row gets deleted first because 
								 * it relies on the employee row*/
								stmt.executeUpdate(query2);
								stmt.executeUpdate(query);	
								System.out.println("Employee has been deleted " + "succesfully!");
								quit = true;
							}
							
							catch(SQLException e) {
								
								System.out.print(e);
								System.out.println("Please try again or " + "type quit if you want to quit\n");
							}
						}
						
						else if (y_n.equals("n") || y_n.equals("no")) {
							
							System.out.println("\nThe employee was not deleted");
							System.out.println("Please try again or " + "type quit if you want to quit\n");
						}
						
						else {
							
							System.out.println("\nInvalid option!\n " + "Please either type y or n " + "\nor type quit\n");
						}
					}
				}
			}
			
			catch(NumberFormatException e) {
				
				System.out.println("\nThat is not an employee ID!");
				System.out.println("Please try again or " + "type quit if you want to quit\n");
			}
		}
	}
}