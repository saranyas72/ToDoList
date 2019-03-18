package main;

import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.concurrent.atomic.AtomicInteger;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {
//	private static final AtomicInteger count = new AtomicInteger(0);
//	public Integer id;
	public String projectName, title, description, status;
	public static final String PENDING = "pending";
	public static final String COMPLETED = "completed";
	public Date createdDate, modifiedDate, dueDate;
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	Date date;
	
	public Task() {
		super();
	}
	
	// Constructor to store task details in a class object
	public Task(String projectName, String title, String description, Date dueDate)
	{
		this.projectName = projectName;
		this.title = title;
		this.description= description;
		this.dueDate = dueDate;
		
//		id = count.incrementAndGet();
		status= PENDING;
		createdDate = new Date();
		modifiedDate= new Date();		
	}
	
	// return task title
	public String getTitle()
	{
		return this.title;	
	}
	
	// set task title
	public void setTitle(String title)
	{
		this.title = title;	
	}
	
	// return project description
	public String getDescription()
	{
		return this.description;	
	}

	// set project description
	public void setDescription(String description)
	{
		this.description = description;	
	}
	
	// return due date of a task
	public Date getDueDate()
	{
		return this.dueDate;
	}
	
	// set due date of a task
	public void setDueDate(Date date)
	{
		this.dueDate = date;	
	}
	
	// return associated project name
	public String getProjectName()
	{
		return this.projectName;	
	}
	
	// set associated project name
	public void setProjectName(String name)
	{
		this.projectName = name;	
	}

	// Verify a task is pending and returns boolean value
	@JsonProperty(value="isPending")
	public boolean isPending()
	{
		return status.contains(PENDING);	
	}
	
	// Verify a task is completed and returns boolean value
	@JsonProperty(value="isCompleted")
	public boolean isCompleted()
	{
		return status.contains(COMPLETED);	
	}
	
	// set task status to completed
	public void markTaskComplete()
	{
		this.status=COMPLETED;
	}
	
	// overriding a string method to display collections as string
	public String toString()
	{
		return "{"+ 
			   "project: " + projectName + ", " + 
			   "title: " + title + ", " + 
			   "status: " + status + ", " + 
			   "description: " + description + ", " + 
			   "createdDate: " + format.format(createdDate) + ", " + 
			   "modifiedDate: " + format.format(modifiedDate) + ", " + 
			   (dueDate != null ? "dueDate: " + format.format(dueDate) : "") +
			   "}";
	}
}
