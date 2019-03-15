package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Task {
	private static final AtomicInteger count = new AtomicInteger(0);
	public Integer id;
	public String title, description, status;
	public static final String PENDING = "pending";
	public static final String COMPLETED = "completed";
	Date createdDate, modifiedDate, dueDate;
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	Date date;
	
	public Task() {
		super();
	}
	
	// Constructor to store task details in a class object
	public Task(String title, String description, Date dueDate)
	{
		this.title = title;
		this.description= description;
		this.dueDate = dueDate;
		
		id = count.incrementAndGet();
		status= PENDING;
		createdDate = new Date();
		modifiedDate= new Date();		
	}
	
	// Verify a task is pending and returns boolean value
	public boolean isPending()
	{
		return status.contains(PENDING);
		
	}
	
	// Verify a task is completed and returns boolean value
	public boolean isCompleted()
	{
		return status.contains(COMPLETED);
		
	}
	
	// Set task status to Completed
	public void markTaskComplete()
	{
		this.status=COMPLETED;
	}
	
	// Return task title
	public String getTitle()
	{
		return this.title;
		
	}
	
	// Return due date of a task
	public Date getDueDate()
	{
		return this.dueDate;
		
	}
	
	// overriding a string method to display collections as string
	public String toString()
	{
		return "{"+ 
			   "id: " + id + ", " + 
			   "title: " + title + ", " + 
			   "status: " + status + ", " + 
			   "description: " + description + ", " + 
			   "createdDate: " + format.format(createdDate) + ", " + 
			   "modifiedDate: " + format.format(modifiedDate) + ", " + 
			   (dueDate != null ? "dueDate: " + format.format(dueDate) : "") +
			   "}";
	}
}
