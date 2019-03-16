/**
 * @author saranyaseetharaman
 */
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.Task;

public class TaskTest {
	private static final AtomicInteger count = new AtomicInteger(0); 
	int taskid;
	String title, description, status ;
	public static final String PENDING = "pending";
	public static final String COMPLETED = "completed";
	Date createdDate, modifiedDate, dueDate;
	static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	Date date;
	
	TaskTest(String title, String description, Date dueDate){
		this.title = title;
		this.description= description;
		this.dueDate = dueDate;
		
		taskid = count.incrementAndGet();
		status= PENDING;
		createdDate = new Date();
		modifiedDate= new Date();	
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		String title = "title";
		String description = "";
		Date dueDate =format.parse("10-03-2019");
		
		new Task(title, description, dueDate);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link main.Task#Task(java.lang.String, java.lang.String, java.util.Date)}.
	 */
	@Test
	public void testTask() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link main.Task#isPending()}.
	 */
	@Test
	public void testIsPending() {
		assertTrue(status.contains(PENDING));
	}

	/**
	 * Test method for {@link main.Task#isCompleted()}.
	 */
	@Test
	public void testIsCompleted() {
		assertTrue(status.contains(COMPLETED));
	}

	/**
	 * Test method for {@link main.Task#markTaskComplete()}.
	 */
	@Test
	public void testMarkTaskComplete() {
		this.status=COMPLETED;
		assertTrue(status.contains(COMPLETED));
	}

	/**
	 * Test method for {@link main.Task#getTitle()}.
	 */
	@Test
	public void testGetTitle() {
		assertTrue(this.title.contentEquals("customTaskTitle"));
	}

	/**
	 * Test method for {@link main.Task#getDueDate()}.
	 */
	@Test
	public void testGetDueDate() {
		String dueDate = this.dueDate.toString();
		String dateToCheck = "10-03-2019";
		assertTrue(dueDate.contentEquals(dateToCheck));
	}

	/**
	 * Test method for {@link main.Task#toString()}.
	 */
	@Test
	public void testToString() {
		String tasksAsString = "{"+ 
				   "id: " + taskid + ", " + 
				   "title: " + title + ", " + 
				   "status: " + status + ", " + 
				   "description: " + description + ", " + 
				   "createdDate: " + format.format(createdDate) + ", " + 
				   "modifiedDate: " + format.format(modifiedDate) + ", " + 
				   (dueDate != null ? "dueDate: " + format.format(dueDate) : "") +
				   "}";
		
		String strToTest = "";
		
		assertEquals("Incorrect formatting in toString method", tasksAsString, strToTest);
		
		
	}

}
