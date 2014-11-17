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
	WorkSchedule workScheduleOne;
	WorkSchedule workScheduleTwo;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		workScheduleZero = new WorkSchedule(0);
		workScheduleOne = new WorkSchedule(1);
		workScheduleTwo = new WorkSchedule(2);
	}

	/******************* Constructor Tests ******************/

	/**
	 * Test method for {@link WorkSchedule#WorkSchedule(int)}.
	 * 
	 * Tests: If the constructor works for a integer zero argument.
	 * Expects: A non null WorkSchedule instance.
	 */
	@Test
	public void testWorkScheduleConstrucutor_Arg_ZeroSize() {
		assertNotNull(workScheduleZero);
	}

	/**
	 * Test method for {@link WorkSchedule#WorkSchedule(int)}.
	 * 
	 * Tests: If the constructor works for a integer One argument.
	 * Expects: A non null WorkSchedule instance.
	 */
	@Test
	public void testWorkScheduleConstrucutor_Arg_OneSize() {
		assertNotNull(workScheduleOne);
	}

	/******************* Read Schedule Tests ******************/

	/**
	 * Test method for {@link WorkSchedule#readSchedule(int)}.
	 * 
	 * Tests: Reading a schedule hour when required number is unset.
	 * Expects: The required number of workers of the hour is = 0.
	 */
	@Test
	public void testReadSchedule_NoRequiredNumberSet() {
		WorkSchedule.Hour h = workScheduleOne.readSchedule(0);
		assertTrue(h.requiredNumber == 0);
	}

	/**
	 * Test method for {@link WorkSchedule#readSchedule(int)}.
	 * 
	 * Tests: Reading a schedule hour when no worker is scheduled.
	 * Expects: The number of working employees of the hour is = 0.
	 */
	@Test
	public void testReadSchedule_NoScheduledWorker() {
		WorkSchedule.Hour h = workScheduleOne.readSchedule(0);
		assertTrue(h.workingEmployees.length == 0);
	}

	/******************* Set Required Number Tests ******************/

	// Uppdelning starttime = 0, starttime > 0, endtime < size, starttime < endtime, starttime = endtime, nemployee > 0, nemployee = 0 
	// 0,0,0 - 0,0,1 - 0,1,1 - 0,1,2 - 1,0,0, - 1,0,1 - 1,1,1 - 1,1,2 ?

	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for one hour to 0.
	 * Expects: The required number of workers of that hour is = 0.
	 */
	@Test
	public void testSetRequiredNumber_ForOneHourToZero_RequiredNumberSetToZero() {
		workScheduleOne.setRequiredNumber(0, 0, 0);
		WorkSchedule.Hour h = workScheduleOne.readSchedule(0);
		assertTrue(h.requiredNumber == 0);
	}

	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for many hours to 0.
	 * Expects: The required number of workers of all those hours is = 0.
	 */
	@Test
	public void testSetRequiredNumber_ForManyHoursToZero_RequiredNumbersSetToZero() {
		workScheduleTwo.setRequiredNumber(0, 0, 1);
		for (int i = 0; i < 2; i++) {
			WorkSchedule.Hour h = workScheduleTwo.readSchedule(i);
			assertTrue(h.requiredNumber == 0);
		}
	}

	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for one hour to 1.
	 * Expects: The required number of workers of that hour is = 1.
	 */
	@Test
	public void testSetRequiredNumber_ForOneHourToOne_RequiredNumberSetToOne() {
		workScheduleTwo.setRequiredNumber(1, 1, 1);
		WorkSchedule.Hour h = workScheduleTwo.readSchedule(1);
		assertTrue(h.requiredNumber == 1);
	}

	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for many hours to 1.
	 * Expects: The required number of workers of all those hours is = 1.
	 */
	@Test
	public void testSetRequiredNumber_ForManyHoursToOne_RequiredNumbersSetToOne() {
		workScheduleTwo.setRequiredNumber(1, 0, 1);
		for (int i = 0; i < 2; i++) {
			WorkSchedule.Hour h = workScheduleTwo.readSchedule(i);
			assertTrue(h.requiredNumber == 1);
		}
	}

	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number of workers for some hours to 1.
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testSetRequiredNumber_UnchangedScheduledWorkersOfAllHours() {
		workScheduleTwo.setRequiredNumber(1, 0, 1);

		for (int i = 0; i < 2; i++) {
			WorkSchedule.Hour h = workScheduleTwo.readSchedule(i);
			assertArrayEquals(new String[] {}, h.workingEmployees);
		}
	}

	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number of workers for one hour to 1.
	 * Expects: The required number of workers are unchanged for the other hours.
	 */
	@Test
	public void testSetRequiredNumber_UnchangedRequiredNumberOfOtherHours() {
		workScheduleTwo.setRequiredNumber(1, 0, 0);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 0);
	}


	/******************* Add Working Period Tests ******************/

	// Input space: 
	// {starttime < 0, endtime == size, endtime > size, starttime > endtime, 
	// adding to a schedule where [starttime, endtime] contains some hour with workingEmployees.length == requireNumber,
	// adding to a schedule where [starttime, endtime] contains some hour where there is a string in workingEmployees which equal employee,
	// the rest of the input space}
	

	// starttime < 0

	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 * 
	 * Tests: Adding a working period with nstarttime < 0.
	 * Expects: False return value.
	 */
	@Test
	public void testAddWorkingPeriod_StartTimeNegative_FalseReturnExpected() {
		boolean ret = workScheduleTwo.addWorkingPeriod("0", -1, 0);
		assertFalse(ret);
	}

	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 * 
	 * Tests: Adding a working period with starttime < 0.
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testAddWorkingPeriod_StartTimeNegative_UnchangedScheduledWorkersOfAllHours() {
		workScheduleTwo.addWorkingPeriod("0", -1, 0);

		for (int i = 0; i < 2; i++) {
			WorkSchedule.Hour h = workScheduleTwo.readSchedule(i);
			assertArrayEquals(new String[] {}, h.workingEmployees);
		}
	}

	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 * 
	 * Tests: Adding a working period with starttime < 0.
	 * Expects: The required number of workers is unchanged for each hour.
	 */
	@Test
	public void testAddWorkingPeriod_StartTimeNegative_UnchangedRequiredNumbersOfAllHours() {
		workScheduleTwo.addWorkingPeriod("0", -1, 0);

		for (int i = 0; i < 2; i++) {
			WorkSchedule.Hour h = workScheduleTwo.readSchedule(i);
			assertTrue(h.requiredNumber == 0);
		}
	}	

	// endtime == size

	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 * 
	 * Tests: Adding a working period with endtime == size
	 * Expects: False return value.
	 */
	@Test
	public void testAddWorkingPeriod_EndTimeEqualToSize_FalseReturnExpected() {
		boolean ret = workScheduleTwo.addWorkingPeriod("0", 0, 2);
		assertFalse(ret);
	}

	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 * 
	 * Tests: Adding a working period with endtime == size.
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testAddWorkingPeriod_EndTimeEqualToSize_UnchangedScheduledWorkersOfAllHours() {
		workScheduleTwo.addWorkingPeriod("0", 0, 2);

		for (int i = 0; i < 2; i++) {
			WorkSchedule.Hour h = workScheduleTwo.readSchedule(i);
			assertArrayEquals(new String[] {}, h.workingEmployees);
		}
	}

	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 * 
	 * Tests: Adding a working period with endtime == size.
	 * Expects: The required number of workers is unchanged for each hour.
	 */
	@Test
	public void testAddWorkingPeriod_EndTimeEqualToSize_UnchangedRequiredNumbersOfAllHours() {
		workScheduleTwo.addWorkingPeriod("0", 0, 2);

		for (int i = 0; i < 2; i++) {
			WorkSchedule.Hour h = workScheduleTwo.readSchedule(i);
			assertTrue(h.requiredNumber == 0);
		}
	}

	// endtime > 0

	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 * 
	 * Tests: Adding a working period with endtime > size.
	 * Expects: False return value.
	 */
	@Test
	public void testAddWorkingPeriod_EndTimeGreaterThanSize_FalseReturnExpected() {
		boolean ret = workScheduleTwo.addWorkingPeriod("0", 0, 3);
		assertFalse(ret);
	}

	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 * 
	 * Tests: Adding a working period with endtime > size.
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testAddWorkingPeriod_EndTimeGreaterThanSize_UnchangedScheduledWorkersOfAllHours() {
		workScheduleTwo.addWorkingPeriod("0", 0, 3);

		for (int i = 0; i < 2; i++) {
			WorkSchedule.Hour h = workScheduleTwo.readSchedule(i);
			assertArrayEquals(new String[] {}, h.workingEmployees);
		}
	}

	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 * 
	 * Tests: Adding a working period with endtime > size.
	 * Expects: The required number of workers is unchanged for each hour.
	 */
	@Test
	public void testAddWorkingPeriod_EndTimeGreaterThanSize_UnchangedRequiredNumbersOfAllHours() {
		workScheduleTwo.addWorkingPeriod("0", 0, 3);

		for (int i = 0; i < 2; i++) {
			WorkSchedule.Hour h = workScheduleTwo.readSchedule(i);
			assertTrue(h.requiredNumber == 0);
		}
	}

	// starttime > endtime

	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 * 
	 * Tests: Adding a working period with starttime > endtime.
	 * Expects: False return value.
	 */
	@Test
	public void testAddWorkingPeriod_StartTimeGreaterThanEndTime_FalseReturnExpected() {
		boolean ret = workScheduleTwo.addWorkingPeriod("0", 1, 0);
		assertFalse(ret); // Bug found
	}

	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 * 
	 * Tests: Adding a working period with starttime > endtime.
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testAddWorkingPeriod_StartTimeGreaterThanEndTime_UnchangedScheduledWorkersOfAllHours() {
		workScheduleTwo.addWorkingPeriod("0", 1, 0);

		for (int i = 0; i < 2; i++) {
			WorkSchedule.Hour h = workScheduleTwo.readSchedule(i);
			assertArrayEquals(new String[] {}, h.workingEmployees);
		}
	}

	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
	 * 
	 * Tests: Adding a working period with starttime > endtime.
	 * Expects: The required number of workers is unchanged for each hour.
	 */
	@Test
	public void testAddWorkingPeriod_StartTimeGreaterThanEndTime_UnchangedRequiredNumbersOfAllHours() {
		workScheduleTwo.addWorkingPeriod("0", 1, 0);

		for (int i = 0; i < 2; i++) {
			WorkSchedule.Hour h = workScheduleTwo.readSchedule(i);
			assertTrue(h.requiredNumber == 0);
		}
	}

	// adding to a schedule where [starttime, endtime] contains some hour with workingEmployees.length == requireNumber
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 *
	 * Tests: Adding a working period when there is some hour in that period that has a full schedule. 
	 * Expects: False return value.
	 */
	@Test
	public void testAddWorkingPeriod_ScheduleFullForSomeHour_FalseReturnExpected() {
		// Need 1 worker for hour 0 and 1
		workScheduleTwo.setRequiredNumber(1, 0, 1);
		// Add a worker to hour 0
		workScheduleTwo.addWorkingPeriod("0", 0, 0);
		// Try to add a worker to hour 0 and 1, should return false since 0 is full
		boolean ret = workScheduleTwo.addWorkingPeriod("1", 0, 1);
		assertFalse(ret);
	}

	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 *
	 * Tests: Adding a working period when there is some hour in that period that has a full schedule. 
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testAddWorkingPeriod_ScheduleFullForSomeHour_UnchangedScheduledWorkersOfAllHours() {
		// Need 1 worker for hour 0 and 1
		workScheduleTwo.setRequiredNumber(1, 0, 1);
		// Add a worker to hour 0
		workScheduleTwo.addWorkingPeriod("0", 0, 0);
		// Try to add a worker to hour 0 and 1, should not change the scheduled workers of all hours
		workScheduleTwo.addWorkingPeriod("1", 0, 1);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(0);
		assertArrayEquals(new String[] {"0"}, hourOne.workingEmployees);

		WorkSchedule.Hour hourTwo = workScheduleTwo.readSchedule(1);
		assertArrayEquals(new String[] {}, hourTwo.workingEmployees);
	}

	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 *
	 * Tests: Adding a working period when there is some hour in that period that has a full schedule. 
	 * Expects: The required number of workers is unchanged for each hour.
	 */
	@Test
	public void testAddWorkingPeriod_ScheduleFullForSomeHour_UnchangedRequiredNumbersOfAllHours() {
		// Need 1 worker for hour 0 and 1
		workScheduleTwo.setRequiredNumber(1, 0, 1);
		// Add a worker to hour 0
		workScheduleTwo.addWorkingPeriod("0", 0, 0);
		// Try to add a worker to hour 0 and 1, should not change required numbers of the hours
		workScheduleTwo.addWorkingPeriod("1", 0, 1);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(0);
		assertTrue(hourOne.requiredNumber == 1);

		WorkSchedule.Hour hourTwo = workScheduleTwo.readSchedule(1);
		assertTrue(hourTwo.requiredNumber == 1);
	}

	// adding to a schedule where [starttime, endtime] contains some hour where 
	// there is a string in workingEmployees which equal employee
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 *
	 * Tests: Adding a working period when there is some hour in that period that already has the worker scheduled.
	 * Expects: False return value.
	 */
	@Test
	public void testAddWorkingPeriod_WorkerAlreadyAdded_FalseReturnExpected() {
		// Need 2 workers for hour 0 and 1
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		// Add a worker to hour 0
		workScheduleTwo.addWorkingPeriod("0", 0, 0);
		// Try to add an existing worker to hour 0 and 1, should not work since hour 0 already has the worker
		boolean ret = workScheduleTwo.addWorkingPeriod("0", 0, 1);
		assertFalse(ret);
	}

	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 *
	 * Tests: Adding a working period when there is some hour in that period that already has the worker scheduled.
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testAddWorkingPeriod_WorkerAlreadyAdded_UnchangedScheduledWorkersOfAllHours() {
		// Need 2 workers for hour 0 and 1
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		// Add a worker to hour 0
		workScheduleTwo.addWorkingPeriod("0", 0, 0);
		// Try to add an existing worker to hour 0 and 1, should not change the scheduled workers of all hours
		workScheduleTwo.addWorkingPeriod("0", 0, 1);
		
		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(0);
		assertArrayEquals(new String[] {"0"}, hourOne.workingEmployees);

		WorkSchedule.Hour hourTwo = workScheduleTwo.readSchedule(1);
		assertArrayEquals(new String[] {}, hourTwo.workingEmployees);
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 *
	 * Tests: Adding a working period when there is some hour in that period that already has the worker scheduled.
	 * Expects: The required number of workers is unchanged for each hour.
	 */
	@Test
	public void testAddWorkingPeriod_WorkerAlreadyAdded_UnchangedRequiredNumbersOfAllHours() {
		// Need 2 workers for hour 0 and 1
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		// Add a worker to hour 0
		workScheduleTwo.addWorkingPeriod("0", 0, 0);
		// Try to add an existing worker to hour 0 and 1, should not change required numbers of the hours 
		workScheduleTwo.addWorkingPeriod("0", 0, 1);
		
		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(0);
		assertTrue(hourOne.requiredNumber == 2);

		WorkSchedule.Hour hourTwo = workScheduleTwo.readSchedule(1);
		assertTrue(hourTwo.requiredNumber == 2);
	}

	// the rest of the input space (valid add stuff)
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 *
	 * Tests: Adding a working period when there is room for the worker in the period.
	 * Expects: True return value.
	 */
	@Test
	public void testAddWorkingPeriod_ValidAdd_TrueReturnExpected() {
		workScheduleTwo.setRequiredNumber(1, 0, 1);
		boolean ret = workScheduleTwo.addWorkingPeriod("0", 0, 1);
		assertTrue(ret);
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 *
	 * Tests: Adding a working period when there is room for the worker in the period.
	 * Expects: The worker is scheduled for each of the hours in the period.
	 */
	@Test
	public void testAddWorkingPeriod_ValidAdd_WorkerScheduledForTheHoursAddedTo() {
		workScheduleTwo.setRequiredNumber(1, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 1);
		
		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(0);
		assertArrayEquals(new String[] {"0"}, hourOne.workingEmployees);
		
		WorkSchedule.Hour hourTwo = workScheduleTwo.readSchedule(1);
		assertArrayEquals(new String[] {"0"}, hourTwo.workingEmployees);
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 *
	 * Tests: Adding a working period when there is room for the worker in the period.
	 * Expects: The scheduled workers for each hour not in the period is unchanged.
	 */
	@Test
	public void testAddWorkingPeriod_ValidAdd__UnchangedScheduledWorkersOfOtherHours() {
		workScheduleTwo.setRequiredNumber(1, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 0);

		WorkSchedule.Hour hourTwo = workScheduleTwo.readSchedule(1);
		assertArrayEquals(new String[] {}, hourTwo.workingEmployees);
	}
	
	/**
	 * Test method for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, intassertTrue(new WorkSchedule(hoursPositiveFour).nextIncomplete(0) == workSchedulePositiveFour.nextIncomplete(0));)}.
	 *
	 * Tests: Adding a working period when there is room for the worker in the period.
	 * Expects: The required number of workers is unchanged for each hour.
	 */
	@Test
	public void testAddWorkingPeriod_ValidAdd_UnchangedRequiredNumbersOfAllHours() {
		workScheduleTwo.setRequiredNumber(1, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 1);
		
		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(0);
		assertTrue(hourOne.requiredNumber == 1);

		WorkSchedule.Hour hourTwo = workScheduleTwo.readSchedule(1);
		assertTrue(hourTwo.requiredNumber == 1);
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

}

