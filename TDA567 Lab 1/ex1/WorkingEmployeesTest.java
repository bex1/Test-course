
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link WorkSchedule#workingEmployees(int, int)}.
 * 
 ******************* Specification ******************
 *
 * requires:
 * 		starttime >= 0 and endtime < size 
 * ensures:
 *		if starttime <= endtime then
 *			returns an array with distinct strings -- a string appears in the return array if and only if 
 *			it appears in the workingEmployees of at least one hour in the interval starttime to endtime
 *		otherwise
 *			returns an empty array
 *		and in either case the schedule is unchanged
 *
 *******************  Input Space  ******************
 * 
 * {All integers starttime and endtime such that starttime >= 0 and endtime < size}
 * 
 ***********  Partitioning Of Input Space  ************
 *
 * starttime > endtime or not
 * starttime == endtime and employee scheduled for hour starttime, not scheduled for hour startime or starttime < endtime
 * starttime < endtime and number of hours in [starttime, endtime] the employee is scheduled for is 0, 1 or >1 
 * 
 * @author Daniel
 *
 */
public class WorkingEmployeesTest {
	private WorkSchedule workScheduleTwo;
	private WorkSchedule workScheduleThree;

	@Before
	public void setUp() throws Exception {
		workScheduleTwo = new WorkSchedule(2);
		workScheduleThree = new WorkSchedule(3);
	}

	/* ****************** starttime > endtime ****************** */


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

	/* ****************** starttime == endtime and employee scheduled for hour starttime ****************** */

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

	/* ****************** starttime == endtime and employee not scheduled for hour starttime ****************** */

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

	/* ****************** starttime < endtime and number of hours in [starttime, endtime] ****************** 
	 ****************** the employee is scheduled for is 0     						  ****************** */

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



	/* ****************** starttime < endtime and number of hours in [starttime, endtime] ****************** 
	 ****************** the employee is scheduled for is 1     						  ****************** */

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


	/* ****************** starttime < endtime and number of hours in [starttime, endtime] ****************** 
	 ****************** the employee is scheduled for is > 1     						  ****************** */

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
}
