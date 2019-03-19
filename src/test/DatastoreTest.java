package test;

import static org.junit.Assert.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import main.Datastore;

public class DatastoreTest {
	
	// Initialize required variables
	private static String userHome = System.getProperty("user.home");
	static Path dir = Paths.get(userHome + "/TodoList");
	
	static String taskData = "TaskData";
	static String testFile = "testFile";
	
	Datastore store;
	
	// Executes before every test method
	@Before
	public void setUp() throws Exception {
		Files.deleteIfExists(Paths.get(dir + "/" + testFile));
		store = new Datastore();
	}

	//	Executes before every test method
	@After
	public void tearDown() throws Exception {
		Files.deleteIfExists(Paths.get(dir + "/" + testFile));
	}

	//	verify Datastore constructor creates required files to store Tasks.
	@Test
	public void testDatastoreSetup() throws IOException {
		assertTrue(Files.exists(Paths.get(dir + "/" + taskData)));
	}
	
	// verify reading data from a file
	@Test
	public void testReadFile() throws IOException {
		String text = "writing some data to read from this file";
		Files.write(store.filePath(testFile), text.getBytes(), StandardOpenOption.CREATE);
		store.readFile(testFile);
		assertEquals(store.readFile(testFile), text);
	}
	
	// verify write data to a file
	@Test
	public void testWriteToFile() throws IOException {
		String text = "I want to write this data to file";
		store.writeToFile(testFile, text);
		assertEquals(store.readFile(testFile), text);
	}
	
	// verify append data to a file
	@Test
	public void testAddToFile() throws IOException {
		String text = "I want to write this data to file";
		store.writeToFile(testFile, text);
		String additionalText = "Added some text to the file";
		store.addToFile(testFile, additionalText);
		assertTrue(store.readFile(testFile).contains(text+additionalText));
	}

}
