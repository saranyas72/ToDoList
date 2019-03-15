/*******************************************************************
 * This class is to manage the tasks
 * author : saranya seetharaman
 *******************************************************************/

package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppManager {
	
	String taskData = "TaskData";
	ArrayList<Task> taskList = new ArrayList<>();
	ArrayList<String> projectList = new ArrayList<>();
	
	Datastore store;
	
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	Date date;
	
	// This constructor will initiate Datastore
	public AppManager(Datastore store) {
		this.store = store;
	}
	
	// This method will take Task class as parameter and create task.
	public void addTask(Task task) {	
		taskList.add(task);

	}
	
	// This method updates the Task.
	public void updateTask() {

	}
	
	// This method will delete the Task.
	public void deleteTask() {

	}
	
	// This method sets status as 'complete' for all the tasks.
	public void markAllTasksCompleted() {
		taskList.stream()
			.map(task -> {
			   task.markTaskComplete();
			   return task; 
			}).collect(Collectors.toList());
	}
	
	// This method filters task status with value as Pending and display result on screen
	public void viewPendingTasks() {
		taskList.stream().map(task -> {
			if(task.isPending()) {
				System.out.println(task.toString());
			}
			return task;
		}).collect(Collectors.toList());
	}

	// This method filters task status with value as completed and display result on screen
	public void viewCompletedTasks() {
		taskList.stream()
		.map(task -> {
			if(task.isCompleted()) {
				System.out.println(task.toString());
			}
			return task;
		}).collect(Collectors.toList());
	}
	
	// This method display all the available task on screen
	public void viewAllTasks() {
		for(Task task : taskList) {
			System.out.println(task.toString());
		}
	}
	
	// This method shows number of pending and completed tasks.
	public void viewTaskStatus() {
		List<Task> pendingTasks = taskList.stream()
				.filter(task -> task.isPending())
				.collect(Collectors.toList());
		
		List<Task> completedTasks = taskList.stream()
				.filter(task -> task.isCompleted())
				.collect(Collectors.toList());
		
		System.out.println("Task Status :\n"+
				           "***********\n" +
				            "Number of Pending Tasks   : " + pendingTasks.size() + "\n" +
				            "Number of Completed Tasks : " + completedTasks.size() + "\n");
	}
	
	// This method allows users to search tasks by title
	public void searchTaskByTitle(String titleToSearch) {
		
		List<Task> resultTask = taskList.stream()
									.filter(task-> task.getTitle().contentEquals(titleToSearch))
									.collect(Collectors.toList());
		printTask(resultTask);
	}
	
	// This method allows users to search tasks by due date
	public void searchTaskByDueDate(Date dueDate) {
		
		List<Task> resultTask = taskList.stream().filter(task -> task.getDueDate().equals(dueDate))
				.collect(Collectors.toList());
		printTask(resultTask);
	}
	
	// Print list of all the Tasks
	public void printTask(List<Task> taskList) {	
		for(Task task : taskList) {
			System.out.println(task.toString());
		}
	}

	// Save tasks to file
	public boolean saveTasks() throws Exception {
		System.out.println("Saving Tasks to a file");
		if(taskList.isEmpty()) {
			System.out.println("No Tasks to Save");
			return false;
		}
		
		ObjectMapper mapper = new ObjectMapper(); 
		String jsonResult = mapper.writeValueAsString(taskList);
		
		this.store.writeToFile(taskData, jsonResult);
		
		System.out.println(jsonResult);
		return true;
	}
	
	// Retrieve tasks from file
	public void loadTasks() {
		
	}
}