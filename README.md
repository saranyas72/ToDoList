# ToDoList
ToDo List is a command line application to help users manage their tasks.

## What is this?

The application will allow a user to create new tasks, assign them a title and due date, and choose a project for that task to belong to.

## Design Diagram

![class_diagram](https://github.com/saranyas72/ToDoList/blob/master/docs/ClassDiagram.jpeg)

## User Instructions

	1. Clone the repository
	2. To run the project 
	
		in IDE
		======
		- open the IDE
		- create a new java project
		- file import -> select existing project into workspace
		- run ToDoList.java
	
		in command line
		===============
		
		<<Install maven if it is not there already>> 
		
		- Open the terminal and navigate to the repository
		- run mvn clean package 
		- run java -jar target/ToDoList-0.0.1-SNAPSHOT-jar-with-dependencies.jar
		
		to run in docker container
		==========================
		
		-  install docker 
		- ensure the docker is running
		- Open the command line and navigate to the repository
		- to build docker image run the following command 
			`docker build -t <tagname> .`
		   	example `docker build -t todolist .`
			
		     -- note down the . at the end of the build command --
		     
		- to run docker image 
			`docker run -it <tagname>`
			example `docker run -it todolistdocker run -it todolist`
			       
	3. Follow the menu options displayed on the screen and key in correct menu item number
