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

	static Scanner input = new Scanner(System.in);
	static Scanner scanner = new Scanner(System.in);
	static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	public static void main(String[] args) throws ParseException {
		
		Datastore store = new Datastore();
		TaskManager manager = new TaskManager(store);
		
		System.out.println("Good day from your ToDo List!! \n"
				+ "Your ToDo Status - \n" + "Pending Tasks in hand:" + 5 + "\n" + "completed Tasks:" + 5 + "\n");
		

		displayMenu();
		int choice = scanner.nextInt();
		
		while (choice <= 10) {
			
			switch (choice) {
				case 1: {
					// add a new task to your list 
					System.out.println("\nEnter the task details, except title other fields can be blank \n");
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
				default: {
					System.out.println("Your choice is invalid, enter choice 1 to 10 \n");
					break;
				}
			}
			displayMenu();
			choice = scanner.nextInt();
		}
	}
	public static void displayMenu()
	{
	System.out.println("\n Menu \n **** \n"
			+ "Type 1 to add a new task to your to do list \n" + "Type 2 to view task status \n"
			+ "Type 3 to to view list of pending tasks \n" + "Type 4  to view list of completed tasks \n"
			+ "Type 5 to to view all tasks \n" + "Type 6 to update existing task in your list \n"
			+ "Type 7 to mark all task as completed \n" + "Type 8 to Delete a task \n"
			+ "Type 9 to exit the program \n" + "Type 10 to search task by title \n" + "Select an option: \n");
	
	}
}
