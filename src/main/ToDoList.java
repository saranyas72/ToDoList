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
		
		// Greetings from application
		System.out.println("Good day from your ToDo List!! \n");
		
		// Initiate required classes
		Datastore store = new Datastore();
		AppManager manager = new AppManager(store);
				
		// display number of pending and completed tasks
		manager.viewTaskStatus();
		
		// Display available options and accept user response
		displayMenu();
		
		// Handle user interaction here --
		int choice = scanner.nextInt();
		while (choice <= 18 || !scanner.nextLine().equals("exit")) {
			switch (choice) {
				case 1: {
					// Create a new project
					System.out.println("Enter project name: ");
					String projectName = input.nextLine();
					manager.createProject(new Project(projectName));
					break;
				}
				case 2: {
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
					
					System.out.println("Below is the available list of projects, choose a project");
					manager.viewAllProject();
					System.out.println("Enter a project name, where you need to add this task ");
					String projectName = input.nextLine();
					if(manager.projectExists(projectName)) {
						manager.addTask(new Task(projectName,taskValues[0],taskValues[1],dueDate));
						manager.saveTasks();
					}
					else {
						System.out.println(projectName +" does not exists, do you want to create this as new project? "
								+ "\n Type Y or N to respond");
						if(input.nextLine().equalsIgnoreCase("Y")){
							manager.createProject(new Project(projectName));
							manager.addTask(new Task(projectName,taskValues[0],taskValues[1],dueDate));
							manager.saveTasks();
						} else {
							System.out.println("Your choice is not 'Y', terminating the task creation.."
									+ "\n Task not created !!");
						}
						
					}
					break;
				}
				case 3: {
					// rename a project
					System.out.println("Below is the available list of projects, select a project");
					manager.viewAllProject();
					System.out.println("Enter the project name to rename");
					String currName = input.nextLine();
					System.out.println("Enter the new name for the project");
					String newName = input.nextLine();
					manager.renameProject(currName, newName);
					manager.saveTasks();
					break;
				}
				case 4: {
					// view available projects
					manager.viewAllProject();
					break;
				}
				case 5: {
					// displays number of pending task and completed tasks.
					manager.viewTaskStatus();
					break;
				}
				case 6: {
					// displays list of all pending tasks
					manager.viewPendingTasks();
					break;
				}
				case 7: {
					// displays list of all completed tasks
					manager.viewCompletedTasks();
					break;
				}
				case 8: {
					// displays list of all tasks
					manager.viewAllTasks();
					break;
				}
				case 9: {
					// view all tasks sorted by project name
					manager.sortTaskByProject();
					break;
				}
				case 10: {
					// view all tasks sorted by due date
					manager.sortTaskByDueDate();
					break;
				}
				case 11: {
					// update existing task in your list
					System.out.println("Enter field to update - Title / ");
					String currName = input.nextLine();
					System.out.println("Enter the new name for the project");
					String newName = input.nextLine();
//					manager.updateTask(fieldToUpdate,currValue, newValue);
					break;
				}
				case 12: {
					// mark all tasks as completed
					manager.markAllTasksCompleted();
					break;
				}
				case 13: {
					// search task by title
					System.out.println("Enter the title to search: \n");
					String title = input.nextLine();
					manager.searchTaskByTitle(title);
					break;
				}
				case 14: {
					// search task by due date
					System.out.println("Enter the due date to search: \n");
					String dateInput = input.nextLine();
					Date due = format.parse(dateInput);
					manager.searchTaskByDueDate(due);
					break;
				}
				case 15: {
					// search task by project
					System.out.println("Enter the project name: \n");
					String projectName = input.nextLine();
					manager.searchTaskByProject(projectName);
					break;
				}
				case 16:
					// delete a task
					
				case 17: {
					// delete a project and the associated tasks
					break;
				}
				
				case 18: {
					// exit the program
					System.out.println("Exiting...");
					System.exit(0);
				}
				case 19: {
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
				+ "Type 1 to add a new project \n" 
				+ "Type 2 to add a new task to your to do list \n" 
				+ "Type 3 to rename a project \n"
				+ "Type 4 to view available projects \n" 
				+ "Type 5 to view task status (by count) \n"
				+ "Type 6 to view list of pending tasks \n" 
				+ "Type 7 to view list of completed tasks \n"
				+ "Type 8 to view all tasks \n" 
				+ "Type 9 to view all tasks sorted by project name \n" 
				+ "Type 10 to view all tasks sorted by due date \n"
				+ "Type 11 to update existing task in your list \n"
				+ "Type 12 to mark all task as completed \n"
				+ "Type 13 to search task by title \n" 
				+ "Type 14 to search task by due date \n" 
				+ "Type 15 to search task by project \n" 
				+ "Type 16 to delete a task \n"
				+ "Type 17 to delete a project and the associated tasks \n"
				+ "Type 18 or 'exit' to exit the program \n" 
		        + "Select an option: \n");
	}
}
