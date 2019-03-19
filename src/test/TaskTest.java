/**
 * @author saranyaseetharaman
 */
package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import main.Task;

public class TaskTest {
	
	Task task;
	 // Initialize set up before every test method
	@Before
	public void setUp() throws Exception {
		task = new Task("testproject", "asdf", "asdf", null);
	}

	// verify isPending() returns correct status of task
	@Test
	public void testIsPending() {
		assertTrue(task.isPending());
		task.markTaskComplete();
		assertFalse(task.isPending());
	}

	// verify isCompleted() returns correct status of task
	@Test
	public void testIsCompleted() {
		assertFalse(task.isCompleted());
		task.markTaskComplete();
		assertTrue(task.isCompleted());
	}

	// verify pending task can be mark as completed
	@Test
	public void testMarkTaskComplete() {
		task.markTaskComplete();
		assertTrue(task.isCompleted());
	}

}
