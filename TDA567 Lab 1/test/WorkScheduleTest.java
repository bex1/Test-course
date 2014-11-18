

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;




/**
 * 
 */

/**
 * @author bex & marherm
 *
 */
public class WorkScheduleTest {
	private WorkSchedule workScheduleZero;
	private WorkSchedule workScheduleOne;
	private WorkSchedule workScheduleTwo;
	private WorkSchedule workScheduleThree;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		workScheduleZero = new WorkSchedule(0);
		workScheduleOne = new WorkSchedule(1);
		workScheduleTwo = new WorkSchedule(2);
		workScheduleThree = new WorkSchedule(3);
	}

	/******************* Set Required Number Tests ******************/

	/*
	 	requires: 
  			starttime >= 0, endtime < size, starttime <= endtime, nemployee >= 0 
		ensures: 
  			For i between starttime and endtime, the requiredNumber of hour i equals nemployee. 
  			For all other i the schedule is unchanged.
	 */
	
	// Input space partioning of precondition space:
	// {TODO
	
	
	
	// Uppdelning starttime = 0, starttime > 0, endtime < size, starttime < endtime, starttime = endtime, nemployee > 0, nemployee = 0 
	// 0,0,0 - 0,0,1 - 0,1,1 - 0,1,2 - 1,0,0, - 1,0,1 - 1,1,1 - 1,1,2 kanske är en bra uppdelning? TODO

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

	/*	requires: 
  			employee is a non-null string
		ensures:
  			if starttime < 0 or endtime >= size or starttime > endtime then
    			returns false and the schedule is unchanged
  			otherwise
    			if for any hour in the interval starttime to endtime the length of workingEmployees is equal to requiredNumber then
      				returns false and the schedule is unchanged
    			otherwise
      				if for any hour in the interval starttime to endtime there is a string in workingEmployees which equal employee then
        				returns false and the schedule is unchanged
      				otherwise
        				returns true,
        				for i between starttime and endtime, workingEmployees contain a string equal to employee and
        				the rest of the schedule is unchanged
	 */

	// Input space partioning of precondition space:
	// {starttime < 0, 
	// endtime == size, 
	// endtime > size, 
	// starttime > endtime, 
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

		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertArrayEquals(new String[] {"0"}, hourZero.workingEmployees);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertArrayEquals(new String[] {}, hourOne.workingEmployees);
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

		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertTrue(hourZero.requiredNumber == 1);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 1);
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

		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertArrayEquals(new String[] {"0"}, hourZero.workingEmployees);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertArrayEquals(new String[] {}, hourOne.workingEmployees);
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

		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertTrue(hourZero.requiredNumber == 2);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 2);
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

		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertArrayEquals(new String[] {"0"}, hourZero.workingEmployees);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertArrayEquals(new String[] {"0"}, hourOne.workingEmployees);
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

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertArrayEquals(new String[] {}, hourOne.workingEmployees);
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

		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertTrue(hourZero.requiredNumber == 1);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 1);
	}

	/******************* Working Employees Tests ******************/

	/*	requires:
	  		starttime >= 0 and endtime < size 
		ensures:
			if starttime <= endtime then
				returns an array with distinct strings -- a string appears in the return array if and only if 
			      it appears in the workingEmployees of at least one hour in the interval starttime to endtime
			otherwise
			   	returns an empty array
			and in either case the schedule is unchanged
	 */

	// Input space partioning of precondition space:
	// {starttime > endtime,
	// starttime == endtime and employee scheduled for hour starttime,
	// starttime == endtime and employee not scheduled for hour starttime,
	// starttime < endtime and employee scheduled for one hour in [starttime, endtime],
	// starttime < endtime and employee scheduled for more than one hour in [starttime, endtime],
	// starttime < endtime and employee scheduled for no hours in [starttime, endtime]}

	
	// starttime > endtime
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime > endtime when there are workers scheduled.
	 * Expects: Empty string array return value.
	 */
	@Test
	public void testWorkingEmployees_StartTimeGreaterThanEndTime_EmptyArrayReturnExpected() {
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 1);
		String[] ret = workScheduleTwo.workingEmployees(1, 0);
		assertArrayEquals(new String[] {}, ret); // Bug found
	}
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime > endtime when there are workers scheduled.
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testWorkingEmployees_StartTimeGreaterThanEndTime_UnchangedScheduledWorkersOfAllHours() {
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 1);
		workScheduleTwo.workingEmployees(1, 0);
		
		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertArrayEquals(new String[] {"0"}, hourZero.workingEmployees);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertArrayEquals(new String[] {"0"}, hourOne.workingEmployees);
	}
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime > endtime when there are workers scheduled.
	 * Expects: The required number of workers is unchanged for each hour.
	 */
	@Test
	public void testWorkingEmployees_StartTimeGreaterThanEndTime_UnchangedRequiredNumbersOfAllHours() {
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 1);
		workScheduleTwo.workingEmployees(1, 0);
		
		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertTrue(hourZero.requiredNumber == 2);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 2);
	}
	
	// starttime == endtime and employee scheduled for hour starttime
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime = endtime when there are workers scheduled for that hour.
	 * Expects: Array with the workers added once.
	 */
	@Test
	public void testWorkingEmployees_StartTimeEqualToEndTimeAndEmployeeScheduled_ArrayIncludingWorkerExpected() {
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 1);
		workScheduleTwo.addWorkingPeriod("1", 0, 1);
		String[] ret = workScheduleTwo.workingEmployees(0, 0);
		List<String> retList = Arrays.asList(ret);
		assertTrue(Collections.frequency(retList, "0") == 1); 
		assertTrue(Collections.frequency(retList, "1") == 1);
	}
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime = endtime when there are workers scheduled for that hour.
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testWorkingEmployees_StartTimeEqualToEndTimeAndEmployeeScheduled_UnchangedScheduledWorkersOfAllHours() {
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 1);
		workScheduleTwo.workingEmployees(0, 0);
		
		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertArrayEquals(new String[] {"0"}, hourZero.workingEmployees);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertArrayEquals(new String[] {"0"}, hourOne.workingEmployees);
	}
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime = endtime when there are workers scheduled for that hour.
	 * Expects: The required number of workers is unchanged for each hour.
	 */
	@Test
	public void testWorkingEmployees_StartTimeEqualToEndTimeAndEmployeeScheduled_UnchangedRequiredNumbersOfAllHours() {
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 1);
		workScheduleTwo.workingEmployees(0, 0);
		
		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertTrue(hourZero.requiredNumber == 2);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 2);
	}
	
	// starttime == endtime and employee not scheduled for hour starttime
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime = endtime when there are no workers scheduled for that hour.
	 * Expects: Array that does not contain the workers.
	 */
	@Test
	public void testWorkingEmployees_StartTimeEqualToEndTimeAndEmployeeNotScheduled_ArrayNotIncludingWorkerExpected() {
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 0);
		workScheduleTwo.addWorkingPeriod("1", 0, 0);
		String[] ret = workScheduleTwo.workingEmployees(1, 1);
		List<String> retList = Arrays.asList(ret);
		assertFalse(retList.contains("0")); 
		assertFalse(retList.contains("1"));
	}
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime = endtime when there are no workers scheduled for that hour.
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testWorkingEmployees_StartTimeEqualToEndTimeAndEmployeeNotScheduled_UnchangedScheduledWorkersOfAllHours() {
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 0);
		workScheduleTwo.workingEmployees(1, 1);
		
		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertArrayEquals(new String[] {"0"}, hourZero.workingEmployees);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertArrayEquals(new String[] {}, hourOne.workingEmployees);
	}
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime = endtime when there are no workers scheduled for that hour.
	 * Expects: The required number of workers is unchanged for each hour.
	 */
	@Test
	public void testWorkingEmployees_StartTimeEqualToEndTimeAndEmployeeNotScheduled_UnchangedRequiredNumbersOfAllHours() {
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 0);
		workScheduleTwo.workingEmployees(1, 1);
		
		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertTrue(hourZero.requiredNumber == 2);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 2);
	}
	
	// starttime < endtime and employee scheduled for one hour in [starttime, endtime],
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime < endtime when there are workers scheduled 
	 * for one our in the interval [starttime, endtime].
	 * Expects: Array that contains the workers once.
	 */
	@Test
	public void testWorkingEmployees_StartTimeLessThanEndTimeAndEmployeeScheduledForOneHour_ArrayIncludingWorkerExpected() {
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 0);
		workScheduleTwo.addWorkingPeriod("1", 0, 0);
		String[] ret = workScheduleTwo.workingEmployees(0, 1);
		List<String> retList = Arrays.asList(ret);
		assertTrue(Collections.frequency(retList, "0") == 1); 
		assertTrue(Collections.frequency(retList, "1") == 1);
	}
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime < endtime when there are workers scheduled 
	 * for one our in the interval [starttime, endtime].
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testWorkingEmployees_StartTimeLessThanEndTimeAndEmployeeScheduledForOneHour_UnchangedScheduledWorkersOfAllHours() {
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 0);
		workScheduleTwo.workingEmployees(0, 1);
		
		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertArrayEquals(new String[] {"0"}, hourZero.workingEmployees);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertArrayEquals(new String[] {}, hourOne.workingEmployees);
	}
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime < endtime when there are workers scheduled 
	 * for one hour in the interval [starttime, endtime].
	 * Expects: The required number of workers is unchanged for each hour.
	 */
	@Test
	public void testWorkingEmployees_StartTimeLessThanEndTimeAndEmployeeScheduledForOneHour_UnchangedRequiredNumbersOfAllHours() {
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 0);
		workScheduleTwo.workingEmployees(0, 1);
		
		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertTrue(hourZero.requiredNumber == 2);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 2);
	}
	
	// starttime < endtime and employee scheduled for more than one hour in [starttime, endtime],
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime < endtime when there are workers scheduled 
	 * for more than one hour in the interval [starttime, endtime].
	 * Expects: Array that contains the workers once.
	 */
	@Test
	public void testWorkingEmployees_StartTimeLessThanEndTimeAndEmployeeScheduledForManyHours_ArrayIncludingWorkerExpected() {
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 1);
		workScheduleTwo.addWorkingPeriod("1", 0, 1);
		String[] ret = workScheduleTwo.workingEmployees(0, 1);
		List<String> retList = Arrays.asList(ret);
		assertTrue(Collections.frequency(retList, "0") == 1); 
		assertTrue(Collections.frequency(retList, "1") == 1);
	}
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime < endtime when there are workers scheduled 
	 * for more than one hour in the interval [starttime, endtime].
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testWorkingEmployees_StartTimeLessThanEndTimeAndEmployeeScheduledForManyHours_UnchangedScheduledWorkersOfAllHours() {
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 1);
		workScheduleTwo.workingEmployees(0, 1);
		
		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertArrayEquals(new String[] {"0"}, hourZero.workingEmployees);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertArrayEquals(new String[] {"0"}, hourOne.workingEmployees);
	}
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime < endtime when there are workers scheduled 
	 * for more than one hour in the interval [starttime, endtime].
	 * Expects: The required number of workers is unchanged for each hour.
	 */
	@Test
	public void testWorkingEmployees_StartTimeLessThanEndTimeAndEmployeeScheduledForManyHours_UnchangedRequiredNumbersOfAllHours() {
		workScheduleTwo.setRequiredNumber(2, 0, 1);
		workScheduleTwo.addWorkingPeriod("0", 0, 1);
		workScheduleTwo.workingEmployees(0, 1);
		
		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertTrue(hourZero.requiredNumber == 2);

		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 2);
	}
	
	
	// starttime < endtime and employee scheduled for no hours in [starttime, endtime]}
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime < endtime when there are workers scheduled 
	 * for no hour in the interval [starttime, endtime].
	 * Expects: Array that does not contain the workers.
	 */
	@Test
	public void testWorkingEmployees_StartTimeLessThanEndTimeAndEmployeeScheduledForNoHours_ArrayNotIncludingWorkerExpected() {
		workScheduleThree.setRequiredNumber(2, 0, 2);
		workScheduleThree.addWorkingPeriod("0", 2, 2);
		workScheduleThree.addWorkingPeriod("1", 2, 2);
		String[] ret = workScheduleThree.workingEmployees(0, 1);
		List<String> retList = Arrays.asList(ret);
		assertTrue(Collections.frequency(retList, "0") == 0); 
		assertTrue(Collections.frequency(retList, "1") == 0);
	}
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime < endtime when there are workers scheduled 
	 * for no hour in the interval [starttime, endtime].
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testWorkingEmployees_StartTimeLessThanEndTimeAndEmployeeScheduledForNoHours_UnchangedScheduledWorkersOfAllHours() {
		workScheduleThree.setRequiredNumber(2, 0, 2);
		workScheduleThree.addWorkingPeriod("0", 2, 2);
		workScheduleThree.workingEmployees(0, 1);
		
		WorkSchedule.Hour hourZero = workScheduleThree.readSchedule(0);
		assertArrayEquals(new String[] {}, hourZero.workingEmployees);

		WorkSchedule.Hour hourOne = workScheduleThree.readSchedule(1);
		assertArrayEquals(new String[] {}, hourOne.workingEmployees);
		
		WorkSchedule.Hour hourTwo = workScheduleThree.readSchedule(2);
		assertArrayEquals(new String[] {"0"}, hourTwo.workingEmployees);
	}
	
	/**
	 * Test method for {@link WorkSchedule#workingEmployees(int, int)}.
	 * 
	 * Tests: Calling working employees with starttime < endtime when there are workers scheduled 
	 * for no hour in the interval [starttime, endtime].
	 * Expects: The required number of workers is unchanged for each hour.
	 */
	@Test
	public void testWorkingEmployees_StartTimeLessThanEndTimeAndEmployeeScheduledForNoHours_UnchangedRequiredNumbersOfAllHours() {
		workScheduleThree.setRequiredNumber(2, 0, 2);
		workScheduleThree.addWorkingPeriod("0", 2, 2);
		workScheduleThree.workingEmployees(0, 1);
		
		WorkSchedule.Hour hourZero = workScheduleThree.readSchedule(0);
		assertTrue(hourZero.requiredNumber == 2);

		WorkSchedule.Hour hourOne = workScheduleThree.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 2);
		
		WorkSchedule.Hour hourTwo = workScheduleThree.readSchedule(2);
		assertTrue(hourTwo.requiredNumber == 2);
	}
	
	/******************* Next Incomplete Tests ******************/
	
	/* 	requires: 
  			currenttime >= 0 and currenttime < size 
		ensures:
  			if there is an hour in the interval currenttime to (size - 1) 
  			such that the length of workingEmplyees is less that requiredNumber then
    			returns the time of the hour closest to currenttime 
    			such that the length of workingEmplyees is less than requiredNumber
  			otherwise
    			returns -1
  			in either case the schedule is unchanged

	// Input space partioning of precondition space:
	// {there is one hour in the interval currenttime to (size - 1) 
  	//	such that the length of workingEmployees is less than requiredNumber,
  	//  there is no hour in the interval currenttime to (size - 1) 
  	//  such that the length of workingEmployees is less than requiredNumber,
  	//  there are more than one hour in the interval currenttime to (size - 1) 
  	//  such that the length of workingEmployees is less than requiredNumber}
	
	
	/**
	 * Test method for {@link WorkSchedule#nextIncomplete(int)}.
	 */
	@Test
	public void testNextIncomplete() {
		fail("Not yet implemented");
	}

}

