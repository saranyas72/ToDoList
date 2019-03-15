package main;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class Datastore {
	
	private static String userHome = System.getProperty("user.home");
	static Path dir = Paths.get(userHome + "/TodoList");
	
	String projectData = "ProjectData";
	String taskData = "TaskData";
	public Datastore() throws IOException {
		
		// create application specific directory to store data/files.
		if (!Files.exists(dir)) {
			Files.createDirectory(dir);	
		}
		
		// create file to store project details
		if (fileExists(filePath(projectData)) <= 0) {
			Files.createFile(filePath(projectData));
		}
		
		// create file to store task details
		if (fileExists(filePath(taskData)) <= 0) {
			Files.createFile(filePath(taskData));
		}
	}
	
	// read data from a file
	public List<String> readFile(String file) throws IOException {
		List<String> lines = Files.readAllLines(Paths.get(file));
        return lines;
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
	
	public long fileExists(Path path) throws IOException {
		return Files.walk(dir)
	     .filter(Files::exists).count();
	}
}
