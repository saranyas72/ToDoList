/*******************************************************************
 * Task viewer/ main class to handle user interactions
 * author : saranya seetharaman
 *******************************************************************/

package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ToDoList {
	
	// set classes to read user input
	static Scanner input = new Scanner(System.in);
	static Scanner scanner = new Scanner(System.in);
	
	// Initial settings for the project
	static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	
	public static void main(String[] args) throws Exception {
		
		// Initiate required classes
		Datastore store = new Datastore();
		AppManager manager = new AppManager(store);
		
		// Greetings from application
		System.out.println("Good day from your ToDo List!! \n");
		
		// display number of pending and completed tasks
		manager.viewTaskStatus();
		
		// Display available options and accept user response
		displayMenu();
		
		// Handle user interaction here --
		int choice = scanner.nextInt();
		while (choice <= 12) {
			switch (choice) {
				case 1: {
					// add a new task to your list 
					System.out.println("Enter the task details, except title other fields can be blank");
					String[] taskProperties = {"Title", "Description", "Due Date"};
					String[] taskValues= new String[taskProperties.length];
					for (int i=0;i<taskProperties.length; i++)
					{
						System.out.println("Enter Task "+taskProperties[i]);
						taskValues[i] = input.nextLine();
					}
					
					Date dueDate;
					try {
						dueDate = format.parse(taskValues[2]);
					}
					catch (ParseException p) {
						dueDate = null;
					}
					
					manager.addTask(new Task(taskValues[0],taskValues[1],dueDate));
					break;
				}
				case 2: {
					// displays number of pending task and completed tasks.
					manager.viewTaskStatus();
					break;
				}
				case 3: {
					// displays list of all pending tasks
					manager.viewPendingTasks();
					break;
				}
				case 4: {
					// displays list of all completed tasks
					manager.viewCompletedTasks();
					break;
				}
				case 5: {
					// displays list of all tasks
					manager.viewAllTasks();
					break;
				}
				case 6: {
					// update existing task in your list 
					break;
				}
				case 7: {
					// mark all tasks as completed
					manager.markAllTasksCompleted();
					break;
				}
				case 8:
					// Delete a task
					break;
				case 9:
					break;
				case 10: {
					// Search a Task by Title
					System.out.println("\nEnter the title to search: \n");
					String title = input.nextLine();
					manager.searchTaskByTitle(title);
					break;
				}
				
				case 11: {
					// Search a Task by Title
					System.out.println("\nEnter the due date to search: \n");
					String dateInput = input.nextLine();;
					Date due = format.parse(dateInput);
					manager.searchTaskByDueDate(due);
					break;
				}
				case 12: {
					// Save the Task
					
					manager.saveTasks();
					break;
				}
				
				default: {
					System.out.println("Your choice is invalid, enter choice 1 to 10 \n");
					break;
				}
			}
			displayMenu();
			choice = scanner.nextInt();
		}
	}
	
	// Available Menu items to display
	public static void displayMenu()
	{
	System.out.println("\n Menu \n **** \n"
			+ "Type 1 to add a new task to your to do list \n" + "Type 2 to view task status \n"
			+ "Type 3 to to view list of pending tasks \n" + "Type 4  to view list of completed tasks \n"
			+ "Type 5 to to view all tasks \n" + "Type 6 to update existing task in your list \n"
			+ "Type 7 to mark all task as completed \n" + "Type 8 to Delete a task \n"
			+ "Type 9 to exit the program \n" + "Type 10 to search task by title \n" 
			+ "Type 11 to search task by due date \n" + "Select an option: \n");
	
	}
}
