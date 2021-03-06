/*******************************************************************
 * This class is to manage the tasks
 * author : saranya seetharaman
 *******************************************************************/

package main;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AppManager {
	
	String taskData = "TaskData";
	ArrayList<Task> taskList = new ArrayList<>();
	ArrayList<Project> projectList = new ArrayList<>();
	
	Datastore store;
	
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	Date date;
	
	public AppManager(Datastore store) throws Exception {
		this.store = store;
		loadTasks();
		loadProject();
	}
	
	// create a project and add it to project list
	public void createProject(Project project) {
			projectList.add(project);
	}
	
	public void addTask(Task task) {	
		taskList.add(task);
	}
	
	// updates the Task
	public void updateTask(String projectName, String taskTitle, String fieldToUpdate, String newValue) throws Exception {
		taskList.stream()
			.filter(task-> task.getTitle().contentEquals(taskTitle) && task.getProjectName().contentEquals(projectName))
			.map(task -> {			
				switch (fieldToUpdate) {
					case "Title": 
					{
						task.setTitle(newValue);
						System.out.println("Title updated !!");
					}
					case "Description": 
					{
						task.setDescription(newValue);
						System.out.println("Description updated !!");
					}
					case "Due Date": 
					{
						Date dueDate;
						try {
							dueDate = format.parse(newValue);
							task.setDueDate(dueDate);
							System.out.println("Due date updated !!");
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
					case "Status": 
					{
						if(newValue.equalsIgnoreCase("pending")) {
							task.markTaskPending();
							System.out.println("Status updated !!");
						} 
						else if(newValue.equalsIgnoreCase("completed") || newValue.equalsIgnoreCase("complete")) {
							task.markTaskComplete();
							System.out.println("Status updated !!");
						} 
						else {
							System.out.println("Invalid value !! \nValue for status field can either be pending or completed");
						}	
					}
				}
				return task;
			}).collect(Collectors.toList());
		saveTasks();
	}
	
	// delete the Task
	public void deleteTask(String projectName, String taskTitle) throws Exception {
		Iterator<Task> itr = taskList.iterator();
		while(itr.hasNext()) {
			Task task = itr.next();
			if(projectName.equalsIgnoreCase(task.getProjectName()) && taskTitle.equalsIgnoreCase(task.getTitle())) {
				itr.remove();
			}
		}
		saveTasks();
		System.out.println("Task removed !!");
	}
	
	// delete a project and related tasks
	public void deleteProject(String projectName) throws Exception {	
		Iterator<Task> itr = taskList.iterator();
		while(itr.hasNext()) {
			String projName = itr.next().getProjectName();
			if(projName.equalsIgnoreCase(projectName)) {
				itr.remove();
			}
		}
		saveTasks();
		loadProject();
		System.out.println("Project "+ projectName + " and its related tasks are removed !!");	
	}
	
	// set status as 'complete' for all the tasks.
	public void markAllTasksCompleted() throws Exception {
		taskList.stream()
			.map(task -> {
			   task.markTaskComplete();
			   return task; 
			}).collect(Collectors.toList());
		saveTasks();
	}
	
	// filter task with pending status and print
	public void viewPendingTasks() {
		taskList.stream().map(task -> {
			if(task.isPending()) {
				System.out.println(task.toString());
			}
			return task;
		}).collect(Collectors.toList());
	}

	// filter task with completed status and print
	public void viewCompletedTasks() {
		taskList.stream()
		.map(task -> {
			if(task.isCompleted()) {
				System.out.println(task.toString());
			}
			return task;
		}).collect(Collectors.toList());
	}
	
	// display all tasks
	public void viewAllTasks() {
		for(Task task : taskList) {
			System.out.println(task.toString());
		}
	}
	
	// display all project
	public void viewAllProject() {
		for(Project project : projectList) {
			System.out.println(project.getName());
		}
	}
	
	// print number of pending and completed tasks.
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
	
	// search tasks by title
	public void searchTaskByTitle(String titleToSearch) {	
		List<Task> resultTask = taskList.stream()
									.filter(task-> task.getTitle().contentEquals(titleToSearch))
									.collect(Collectors.toList());
		if(resultTask.isEmpty()) {
			System.out.println("No results found");
		} 
		else {
			printTask(resultTask);
		}
	}
	
	// search tasks by due date
	public void searchTaskByDueDate(Date dueDate) {
		List<Task> resultTask = taskList.stream()
				.filter(task -> {
					if(task.getDueDate() != null)
						{
						return task.getDueDate().equals(dueDate);
						}
					return false;
				}).collect(Collectors.toList());
			printTask(resultTask);
	}

	// search tasks by project
	public void searchTaskByProject(String projectName) {
		List<Task> resultTask = taskList.stream()
				.filter(task -> task.getProjectName().equals(projectName))
				.collect(Collectors.toList());
			printTask(resultTask);
	}
	
	// sort tasks by title
	public void sortTaskByProject() {
		taskList.sort((task1, task2) -> task1.getProjectName().compareTo(task2.getProjectName()));
		viewAllTasks();
	}
	
	// sort tasks by due date
	public void sortTaskByDueDate() {
		taskList.sort((task1, task2) -> task1.getDueDate().compareTo(task2.getDueDate()));
		viewAllTasks();	
	}
	
	// print list of all the Tasks
	public void printTask(List<Task> taskList) {
		if(taskList.isEmpty())
		{
			System.out.println("No results found");
		} else {
			for(Task task : taskList) {
				System.out.println(task.toString());
			}
		}
	}
	
	// verify project name exists
	public boolean projectExists(String projectName) {	
		 return projectList.stream()
			.anyMatch(project -> project.getName().contentEquals(projectName));
	}
	
	// verify project name exists
	public boolean taskExists(String taskTitle) {	
		return taskList.stream()
			.anyMatch(task -> task.getTitle().equalsIgnoreCase(taskTitle));
	}
	
	// rename a project name
	public void renameProject(String oldName, String newName) throws Exception {	
		projectList.stream()
			.map(project -> 
			{
				if(project.getName().contentEquals(oldName)) {
					project.setName(newName);
				}
			return project;
			}).collect(Collectors.toList());
		
		// rename the project in Tasklist
		taskList.stream()
			.map(task -> 
			{
				if(task.getProjectName().contentEquals(oldName)) {
					task.setProjectName(newName);
				}
			return task;
		}).collect(Collectors.toList());
		saveTasks();
	}
	
	// save tasks to file
	public boolean saveTasks() throws Exception {
//		System.out.println("Saving Tasks to a file..");
		if(taskList.isEmpty()) {
			System.out.println("No Tasks to Save");
			return false;
		}
		
		ObjectMapper mapper = new ObjectMapper(); 
		String jsonResult = mapper.writeValueAsString(taskList);
		
		this.store.writeToFile(taskData, jsonResult);
		
//		System.out.println("Tasks Saved");
		return true;
	}
	
	// retrieve tasks from file
	public void loadTasks() throws IOException {
		System.out.println("Retrieving Tasks from a file");
		if(store.fileExists(taskData)<=0) {
			System.out.println("File '"+taskData+"' does not exists");
		}
		
		String taskJson = store.readFile(taskData);

		if (!taskJson.isEmpty()) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			Task[] taskArray = mapper.readValue(taskJson, Task[].class);
			taskList = new ArrayList<>(Arrays.asList(taskArray));
		
		}
	}
	
	// retrieve project list from tasklist read from file
	public void loadProject() throws Exception {
		taskList.stream()
		.map(tasks -> {
			String projectName = tasks.getProjectName();
			if(!projectExists(projectName)) {
				createProject(new Project(projectName));
			}
		return projectName;
		})
		.collect(Collectors.toList());
	}
}