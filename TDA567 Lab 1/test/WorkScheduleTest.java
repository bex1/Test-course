import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;




/**
 * 
 */

/**
 * @author marherm
 *
 */
public class WorkScheduleTest {
	WorkSchedule workScheduleZero;
	WorkSchedule workSchedulePositiveOne;
	WorkSchedule workSchedulePositiveFour;
	int hoursZero;
	int hoursPositiveOne;
	int hoursPositiveFour;
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		hoursZero = 0;
		hoursPositiveOne = 1;
		hoursPositiveFour = 4;
		workScheduleZero = new WorkSchedule(hoursZero);
		workSchedulePositiveOne = new WorkSchedule(hoursPositiveOne);
		workSchedulePositiveFour = new WorkSchedule(hoursPositiveFour);
	}

	/**
	 * Test method for {@link WorkSchedule#WorkSchedule(int)}.
	 */
	@Test
	public void testWorkScheduleZeroHours() {
		assertNotNull(workScheduleZero);
	}
	
	/**
	 * Test method for {@link WorkSchedule#WorkSchedule(int)}.
	 */
	@Test
	public void testWorkSchedulePositiveHours() {
		assertNotNull(workSchedulePositiveOne);
	}

	/**
	 * Test method for {@link WorkSchedule#readSchedule(int)}.
	 */
	@Test
	public void testReadScheduleRequiredWorkersZero() {
		WorkSchedule.Hour h = workSchedulePositiveFour.readSchedule(0);
		assertTrue(h.requiredNumber == 0);
	}
	
	/**
	 * Test method for {@link WorkSchedule#readSchedule(int)}.
	 */
	@Test
	public void testReadScheduleWorkingEmployeesZero() {
		WorkSchedule.Hour h = workSchedulePositiveFour.readSchedule(0);
		assertTrue(h.workingEmployees.length == 0);
	}

	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 */
	@Test
	public void testSetRequiredNumberOne() {
		workSchedulePositiveFour.setRequiredNumber(1, 0, 0);
		WorkSchedule.Hour h = workSchedulePositiveFour.readSchedule(0);
		assertTrue(h.requiredNumber == 1);
	}
	
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 */
	@Test
	public void testSetRequiredNumberOneEmployeesIsZero() {
		workSchedulePositiveFour.setRequiredNumber(1, 0, 0);
		WorkSchedule.Hour h = workSchedulePositiveFour.readSchedule(0);
		assertTrue(h.workingEmployees.length == 0);
	}
	
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 */
	@Test
	public void testSetRequiredNumberFourEmployeesHourTwo() {
		workSchedulePositiveFour.setRequiredNumber(4, 2, 3);
		WorkSchedule.Hour h = workSchedulePositiveFour.readSchedule(2);
		assertTrue(h.requiredNumber == 4);
	}
	
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 */
	@Test
	public void testSetRequiredNumberFourEmployeesHourThree() {
		workSchedulePositiveFour.setRequiredNumber(4, 2, 3);
		WorkSchedule.Hour h = workSchedulePositiveFour.readSchedule(3);
		assertTrue(h.requiredNumber == 4);
	}
	
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 */
	@Test
	public void testSetRequiredNumberFourEmployeesHourOneNotChanged() {
		workSchedulePositiveFour.setRequiredNumber(4, 2, 3);
		WorkSchedule.Hour h = workSchedulePositiveFour.readSchedule(1);
		assertTrue(h.requiredNumber == 0);
	}
	
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 */
	@Test
	public void testSetRequiredNumberFourEmployeesHourZeroNotChanged() {
		workSchedulePositiveFour.setRequiredNumber(4, 2, 3);
		WorkSchedule.Hour h = workSchedulePositiveFour.readSchedule(0);
		assertTrue(h.requiredNumber == 0);
	}

	//TODO: testa om anställda <= behovet
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 */
	@Test
	public void testAddWorkingPeriodStartTimeNegative() {
		boolean ret = workSchedulePositiveFour.addWorkingPeriod("0", -1, 0);
		assertFalse(ret);
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 */
	@Test
	public void testAddWorkingPeriodEndTimeGreaterThanSize() {
		boolean ret = workSchedulePositiveFour.addWorkingPeriod("0", 0, 5);
		assertFalse(ret);
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 */
	@Test
	public void testAddWorkingPeriodEndTimeEqualToSize() {
		boolean ret = workSchedulePositiveFour.addWorkingPeriod("0", 0, 4);
		assertFalse(ret);
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 */
	@Test
	public void testAddWorkingPeriodStartTimeGreaterThanEndTime() {
		boolean ret = workSchedulePositiveFour.addWorkingPeriod("0", 2, 1);
		assertFalse(ret); // TODO: BUG!!!!
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 */
	@Test
	public void testAddWorkingPeriodScheduleUnchangedStartTimeNegative() {
		workSchedulePositiveFour.addWorkingPeriod("0", -1, 0);
		assertUnchanged(workSchedulePositiveFour, hoursPositiveFour);
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 */
	@Test
	public void testAddWorkingPeriodScheduleUnchangedEndTimeGreaterThanSize() {
		workSchedulePositiveFour.addWorkingPeriod("0", 0, 5);
		assertUnchanged(workSchedulePositiveFour, hoursPositiveFour);
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 */
	@Test
	public void testAddWorkingPeriodScheduleUnchangedEndTimeEqualToSize() {
		workSchedulePositiveFour.addWorkingPeriod("0", 0, 4);
		assertUnchanged(workSchedulePositiveFour, hoursPositiveFour);
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 */
	@Test
	public void testAddWorkingPeriodScheduleUnchangedStartTimeGreaterThanEndTime() {
		workSchedulePositiveFour.addWorkingPeriod("0", 2, 1);
		assertUnchanged(workSchedulePositiveFour, hoursPositiveFour);
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 */
	@Test
	public void testAddWorkingPeriodScheduleFull() {
		int scheduledWorkers = 2;
		int requiredNbr = 2;
		workSchedulePositiveFour.setRequiredNumber(requiredNbr, 0, 3);
		for (int i = 0; i < scheduledWorkers; i++) {
			workSchedulePositiveFour.addWorkingPeriod(String.valueOf(i), 0, 3);
		}
		boolean ret = workSchedulePositiveFour.addWorkingPeriod("2", 0, 3);
		
		assertFalse(ret);
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 */
	@Test
	public void testAddWorkingPeriodScheduleFullUnchanged() {
		int scheduledWorkers = 2;
		int requiredNbr = 2;
		workSchedulePositiveFour.setRequiredNumber(requiredNbr, 0, 3);
		for (int i = 0; i < scheduledWorkers; i++) {
			workSchedulePositiveFour.addWorkingPeriod(String.valueOf(i), 0, 3);
		}
		workSchedulePositiveFour.addWorkingPeriod("2", 0, 3);
		assertUnchanged(workSchedulePositiveFour, hoursPositiveFour, scheduledWorkers, requiredNbr);
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 */
	@Test
	public void testAddWorkingPeriodAddWorkerTwice() {
		int requiredNbr = 2;
		workSchedulePositiveFour.setRequiredNumber(requiredNbr, 0, 3);
		workSchedulePositiveFour.addWorkingPeriod("0", 0, 3);
		boolean ret = workSchedulePositiveFour.addWorkingPeriod("0", 0, 3);
		assertFalse(ret);
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 */
	@Test
	public void testAddWorkingPeriodAddWorkerTwiceUnchanged() {
		int scheduledWorkers = 1;
		int requiredNbr = 2;
		workSchedulePositiveFour.setRequiredNumber(requiredNbr, 0, 3);
		workSchedulePositiveFour.addWorkingPeriod("0", 0, 3);
		workSchedulePositiveFour.addWorkingPeriod("0", 0, 3);
		
		assertUnchanged(workSchedulePositiveFour, hoursPositiveFour, scheduledWorkers, requiredNbr);
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 */
	@Test
	public void testAddWorkingPeriodValidAdd() {
		int requiredNbr = 2;
		workSchedulePositiveFour.setRequiredNumber(requiredNbr, 0, 3);
		boolean ret = workSchedulePositiveFour.addWorkingPeriod("0", 0, 3);
		assertTrue(ret);
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 */
	@Test
	public void testAddWorkingPeriodValidAddAllHours() {
		int requiredNbr = 2;
		workSchedulePositiveFour.setRequiredNumber(requiredNbr, 0, 3);
		
		workSchedulePositiveFour.addWorkingPeriod("0", 0, 3);
		for (int hour = 0; hour < hoursPositiveFour; hour++) {
			WorkSchedule.Hour h = workSchedulePositiveFour.readSchedule(hour);
			assertTrue(h.workingEmployees[0].equals("0"));
		}
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 */
	@Test
	public void testAddWorkingPeriodValidAddRestUnchanged() {
		int nbrWorkers = 1;
		int requiredNbr = 2;
		workSchedulePositiveFour.setRequiredNumber(requiredNbr, 0, 3);
		workSchedulePositiveFour.addWorkingPeriod("0", 0, 3);
		
		assertUnchanged(workSchedulePositiveFour, hoursPositiveFour, nbrWorkers, requiredNbr);
	}

	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 */
	@Test
	public void testWorkingEmployees() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link WorkSchedule#nextIncomplete(int)}.
	 */
	@Test
	public void testNextIncomplete() {
		fail("Not yet implemented");
	}
	
	// For no workers
	public static void assertUnchanged(WorkSchedule workSchedule, int hours) {
		for (int i = 0; i < hours; i++) {
			WorkSchedule.Hour h = workSchedule.readSchedule(i);
			assertTrue(h.requiredNumber == new WorkSchedule(hours).readSchedule(i).requiredNumber);
			assertArrayEquals(new WorkSchedule(hours).readSchedule(i).workingEmployees, h.workingEmployees);
		}
		assertArrayEquals(new WorkSchedule(hours).workingEmployees(0, hours-1), workSchedule.workingEmployees(0, hours-1));
		assertTrue(new WorkSchedule(hours).nextIncomplete(0) == workSchedule.nextIncomplete(0));
	}
	
	public static void assertUnchanged(WorkSchedule workSchedule, int hours, int nbrWorkers, int requiredNbr) {
		WorkSchedule expected;
		for (int i = 0; i < hours; i++) {
			WorkSchedule.Hour h = workSchedule.readSchedule(i);
			expected = workScheduler(nbrWorkers, hours, requiredNbr);
			assertTrue(h.requiredNumber == expected.readSchedule(i).requiredNumber);
			expected = workScheduler(nbrWorkers, hours, requiredNbr);
			assertArrayEquals(expected.readSchedule(i).workingEmployees, h.workingEmployees);
		}
		expected = workScheduler(nbrWorkers, hours, requiredNbr);
		assertArrayEquals(expected.workingEmployees(0, hours-1), workSchedule.workingEmployees(0, hours-1));
		expected = workScheduler(nbrWorkers, hours, requiredNbr);
		assertTrue(expected.nextIncomplete(0) == workSchedule.nextIncomplete(0));
	}

	private static WorkSchedule workScheduler(int nbrWorkers, int hours, int requiredNbr) {
		WorkSchedule w = new WorkSchedule(hours);
		w.setRequiredNumber(requiredNbr, 0, hours-1);
		for (int i = 0; i < nbrWorkers; i++) {
			w.addWorkingPeriod(String.valueOf(i), 0, hours-1);
		}
		return w;
	}

}
