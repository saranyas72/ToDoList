package main;

import java.io.IOException;
import java.nio.file.*;

public class Datastore {
	
	private static String userHome = System.getProperty("user.home");
	static Path dir = Paths.get(userHome + "/TodoList");
	String taskData = "TaskData";
	public Datastore() throws IOException {
		
		// create application specific directory to store data/files.
		if (!Files.exists(dir)) {
			Files.createDirectory(dir);	
		}
		
		// create file to store task details
		if (fileExists(taskData) <= 0) {
			Files.createFile(filePath(taskData));
		}
	}
	
	// read data from a file
	public String readFile(String file) throws IOException {
		return Files.readString(filePath(file));
	}
		
	// Write data to a file, create file if the file does not exists
	public void writeToFile(String file, String content) throws IOException {
	    Files.write(filePath(file), content.getBytes(), StandardOpenOption.CREATE);
	}
	
	// Write data to an existing file
	public void addToFile(String file, String content) throws IOException {
		Files.write(filePath(file), content.getBytes(), StandardOpenOption.APPEND);
	}
	
	public void deleteFile(String file) throws IOException {
	    Files.deleteIfExists(filePath(file));
	}
	
	public Path filePath(String file) throws IOException {
		return Paths.get(dir + "/"+file) ;

	}
	
	public long fileExists(String filename) throws IOException {
		return Files.walk(dir)
	     .filter(file -> {
	    	 return file.getFileName().toString().equals(filename);
	     }).count();
	}
}
