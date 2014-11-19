
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
 * 
 ******************* Specification ******************
 *
 * requires: 
 * 		starttime >= 0, endtime < size, starttime <= endtime, nemployee >= 0 
 * ensures: 
 * 		For i between starttime and endtime, the requiredNumber of hour i equals nemployee. 
 * 		For all other i the schedule is unchanged.
 *
 *******************  Input Space  ******************
 * 
 * {All integers nemployee, starttime and endtime such that 0 <= starttime <= endtime and endtime < size and nemployee >= 0}
 * 
 ***********  Partitioning Of Input Space  ************
 *
 * TODO
 *
 * @author Daniel
 *
 */
public class SetRequiredNumberTest {
	private WorkSchedule workScheduleOne;
	private WorkSchedule workScheduleTwo;

	@Before
	public void setUp() throws Exception {
		workScheduleOne = new WorkSchedule(1);
		workScheduleTwo = new WorkSchedule(2);
	}
	
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
}
