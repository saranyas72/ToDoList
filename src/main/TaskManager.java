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

public class TaskManager {
	
	ArrayList<Task> taskList = new ArrayList<>();
	Datastore store;
	
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	Date date;
	
	public TaskManager(Datastore store) {
		this.store = store;
	}

	public void addTask(Task task) {
		
		taskList.add(task);

	}

	public void updateTask() {

	}

	public void deleteTask() {

	}
	
	public void markAllTasksCompleted() {
		taskList.stream()
			.map(task -> {
			   task.markTaskComplete();
			   return task; 
			}).collect(Collectors.toList());
	}
	
	public void viewPendingTasks() {
		taskList.stream().map(task -> {
			if(task.isPending()) {
				System.out.println(task.toString());
			}
			return task;
		}).collect(Collectors.toList());
	}

	public void viewCompletedTasks() {
		taskList.stream()
		.map(task -> {
			if(task.isCompleted()) {
				System.out.println(task.toString());
			}
			return task;
		}).collect(Collectors.toList());
	}
	
	public void viewAllTasks() {
		for(Task task : taskList) {
			System.out.println(task.toString());
		}
	}
	
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
	
	
	public void searchTaskByTitle(String titleToSearch) {
		
		List<Task> resultTask = taskList.stream()
									.filter(task-> task.getTitle().contentEquals(titleToSearch))
									.collect(Collectors.toList());
		printTask(resultTask);
	}
	
	// Search Task by Due Date
	public void searchTaskByDueDate(Date dueDate) {
		
		List<Task> resultTask = taskList.stream().filter(task -> task.getDueDate().equals(dueDate))
				.collect(Collectors.toList());
		printTask(resultTask);
	}
	
	// Print list of Tasks
	public void printTask(List<Task> taskList) {	
		for(Task task : taskList) {
			System.out.println(task.toString());
		}
	}

	public void saveTasks() {
		
	}
	
	public void loadTasks() {
		
	}
}