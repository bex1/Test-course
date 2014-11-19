

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;


/**
 * Tests for {@link WorkSchedule#addWorkingPeriod(java.lang.String, int, int)}.
 * 
 ******************* Specification ******************
 *
 * requires: 
 * 		employee is a non-null string
 * ensures:
 * 		if starttime < 0 or endtime >= size or starttime > endtime then
 *   		returns false and the schedule is unchanged
 * 		otherwise
 *   		if for any hour in the interval starttime to endtime the length of workingEmployees is equal to requiredNumber then
 *     			returns false and the schedule is unchanged
 *   		otherwise
 *     			if for any hour in the interval starttime to endtime there is a string in workingEmployees which equal employee then
 *       			returns false and the schedule is unchanged
 *     			otherwise
 *       			returns true,
 *       			for i between starttime and endtime, workingEmployees contain a string equal to employee and
 *       			the rest of the schedule is unchanged
 *
 *******************  Input Space  ******************
 * 
 * {All integers starttime and endtime and all non null strings employee}
 * 
 ***********  Partioning Of Input Space  ************
 *
 * TODO
 *
 * @author Daniel
 *
 */
public class AddWorkingPeriodTest {
	private WorkSchedule workScheduleTwo;

	@Before
	public void setUp() throws Exception {
		workScheduleTwo = new WorkSchedule(2);
	}

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
	 * Tests: Adding a working period with starttime < 0.
	 * Expects: False return value.
	 */
	@Test
	public void testAddWorkingPeriod_StartTimeNegative_FalseReturnExpected() {
		boolean ret = workScheduleTwo.addWorkingPeriod("0", -1, 0);
		assertFalse(ret);
	}

	/**
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
	 * Tests: Adding a working period with endtime == size
	 * Expects: False return value.
	 */
	@Test
	public void testAddWorkingPeriod_EndTimeEqualToSize_FalseReturnExpected() {
		boolean ret = workScheduleTwo.addWorkingPeriod("0", 0, 2);
		assertFalse(ret);
	}

	/**
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
	 * Tests: Adding a working period with endtime > size.
	 * Expects: False return value.
	 */
	@Test
	public void testAddWorkingPeriod_EndTimeGreaterThanSize_FalseReturnExpected() {
		boolean ret = workScheduleTwo.addWorkingPeriod("0", 0, 3);
		assertFalse(ret);
	}

	/**
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
	 * Tests: Adding a working period with starttime > endtime.
	 * Expects: False return value.
	 */
	@Test
	public void testAddWorkingPeriod_StartTimeGreaterThanEndTime_FalseReturnExpected() {
		boolean ret = workScheduleTwo.addWorkingPeriod("0", 1, 0);
		assertFalse(ret); // Bug found
	}

	/**
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
}
