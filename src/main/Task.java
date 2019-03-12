package main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Task {
	private static final AtomicInteger count = new AtomicInteger(0); 
	int id;
	String title, description, status ;
	public static final String PENDING = "pending";
	public static final String COMPLETED = "completed";
	Date createdDate, modifiedDate, dueDate;
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	Date date;
	
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
	
	public boolean isPending()
	{
		return status.contains(PENDING);
		
	}
	
	public boolean isCompleted()
	{
		return status.contains(COMPLETED);
		
	}
	
	public void markTaskComplete()
	{
		this.status=COMPLETED;
	}
	
	public String getTitle()
	{
		return this.title;
		
	}
	
	public Date getDueDate()
	{
		return this.dueDate;
		
	}

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
