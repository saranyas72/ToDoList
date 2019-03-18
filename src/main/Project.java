package main;

public class Project {
	public String name;
	
	// Constructor to store task details in a class object
	public Project(String name)
	{
		this.name = name;		
	}
	
	// Return project name
	public String getName()
	{
		return this.name;
	}
	
	// Set project name
	public void setName(String name)
	{
		this.name = name;
	}
	

}
