
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
 * nemployee == 0 and starttime == 0 and starttime == endtime or not
 * nemployee == 0 and starttime == 0 and starttime <  endtime or not
 * nemployee == 0 and starttime > 0  and starttime == endtime or not 
 * nemployee == 0 and starttime > 0  and starttime <  endtime or not
 * nemployee >  0 and starttime == 0 and starttime == endtime or not
 * nemployee >  0 and starttime == 0 and starttime <  endtime or not
 * nemployee >  0 and starttime > 0  and starttime == endtime or nemployee > 0 and starttime > 0 and starttime < endtime
 *
 * @author Daniel
 *
 */
public class SetRequiredNumberTest {
	private WorkSchedule workScheduleTwo;
	private WorkSchedule workScheduleThree;
	private WorkSchedule workScheduleFour;
	
	@Before
	public void setUp() throws Exception {
		workScheduleTwo = new WorkSchedule(2);
		workScheduleThree = new WorkSchedule(3);
		workScheduleFour = new WorkSchedule(4);
	}
	
	/* ****************** nemployee == 0 and starttime == 0 and starttime == endtime ****************** */
	 
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 0 to 0.
	 * Expects: The required number of workers of hour 0 is = 0.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeZeroStarttimeZeroStarttimeEqualToEndtime_RequiredNumberSet() {
		workScheduleTwo.setRequiredNumber(0, 0, 0);
		
		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertTrue(hourZero.requiredNumber == 0);
	}
	
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 0 to 0.
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeZeroStarttimeZeroStarttimeEqualToEndtime_UnchangedScheduledWorkersOfAllHours() {
		workScheduleTwo.setRequiredNumber(0, 0, 0);

		for (int i = 0; i < 2; i++) {
			WorkSchedule.Hour h = workScheduleTwo.readSchedule(i);
			assertArrayEquals(new String[] {}, h.workingEmployees);
		}
	}

	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 0 to 0.
	 * Expects: The required number of workers are unchanged for the other hours.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeZeroStarttimeZeroStarttimeEqualToEndtime_UnchangedRequiredNumberOfOtherHours() {
		workScheduleTwo.setRequiredNumber(0, 0, 0);
		
		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 0);
	}
	
	/* ****************** nemployee == 0 and starttime == 0 and starttime <  endtime ****************** */
	 
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 0 and 1 to 0.
	 * Expects: The required number of workers of hour 0 and 1 is = 0.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeZeroStarttimeZeroStarttimeUnderEndtime_RequiredNumbersSet() {
		workScheduleThree.setRequiredNumber(0, 0, 1);
		
		WorkSchedule.Hour hourZero = workScheduleThree.readSchedule(0);
		assertTrue(hourZero.requiredNumber == 0);
		
		WorkSchedule.Hour hourOne = workScheduleThree.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 0);
	}
	
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 0 and 1 to 0.
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeZeroStarttimeZeroStarttimeUnderEndtime_UnchangedScheduledWorkersOfAllHours() {
		workScheduleThree.setRequiredNumber(0, 0, 1);

		for (int i = 0; i < 3; i++) {
			WorkSchedule.Hour h = workScheduleThree.readSchedule(i);
			assertArrayEquals(new String[] {}, h.workingEmployees);
		}
	}

	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 0 and 1 to 0.
	 * Expects: The required number of workers are unchanged for the other hours.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeZeroStarttimeZeroStarttimeUnderEndtime_UnchangedRequiredNumberOfOtherHours() {
		workScheduleThree.setRequiredNumber(0, 0, 1);
		
		WorkSchedule.Hour hourTwo = workScheduleThree.readSchedule(2);
		assertTrue(hourTwo.requiredNumber == 0);
	}
	
	/* ****************** nemployee == 0 and starttime > 0  and starttime == endtime ****************** */
	 
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 1 to 0.
	 * Expects: The required number of workers of hour 1 is = 0.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeZeroStarttimeOverZeroStarttimeEqualEndtime_RequiredNumberSet() {
		workScheduleThree.setRequiredNumber(0, 1, 1);
		
		WorkSchedule.Hour hourOne = workScheduleThree.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 0);
	}
	
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 1 to 0.
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeZeroStarttimeOverZeroStarttimeEqualEndtime_UnchangedScheduledWorkersOfAllHours() {
		workScheduleThree.setRequiredNumber(0, 1, 1);

		for (int i = 0; i < 3; i++) {
			WorkSchedule.Hour h = workScheduleThree.readSchedule(i);
			assertArrayEquals(new String[] {}, h.workingEmployees);
		}
	}

	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 1 to 0.
	 * Expects: The required number of workers are unchanged for the other hours.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeZeroStarttimeOverZeroStarttimeEqualEndtime_UnchangedRequiredNumberOfOtherHours() {
		workScheduleThree.setRequiredNumber(0, 1, 1);

		WorkSchedule.Hour hourOne = workScheduleThree.readSchedule(0);
		assertTrue(hourOne.requiredNumber == 0);
		
		WorkSchedule.Hour hourTwo = workScheduleThree.readSchedule(2);
		assertTrue(hourTwo.requiredNumber == 0);
	}
	
	/* ****************** nemployee == 0 and starttime > 0  and starttime <  endtime ****************** */
	 
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 1 and 2 to 0.
	 * Expects: The required number of workers of hour 1 and 2 is = 0.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeZeroStarttimeOverZeroStarttimeUnderEndtime_RequiredNumbersSet() {
		workScheduleFour.setRequiredNumber(0, 1, 2);
		
		WorkSchedule.Hour hourOne = workScheduleFour.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 0);
		
		WorkSchedule.Hour hourTwo = workScheduleFour.readSchedule(2);
		assertTrue(hourTwo.requiredNumber == 0);
	}
	
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 1 and 2 to 0.
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeZeroStarttimeOverZeroStarttimeUnderEndtime_UnchangedScheduledWorkersOfAllHours() {
		workScheduleFour.setRequiredNumber(0, 1, 2);

		for (int i = 0; i < 4; i++) {
			WorkSchedule.Hour h = workScheduleFour.readSchedule(i);
			assertArrayEquals(new String[] {}, h.workingEmployees);
		}
	}

	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 1 and 2 to 0.
	 * Expects: The required number of workers are unchanged for the other hours.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeZeroStarttimeOverZeroStarttimeUnderEndtime_UnchangedRequiredNumberOfOtherHours() {
		workScheduleFour.setRequiredNumber(0, 1, 2);
		
		WorkSchedule.Hour hourZero = workScheduleFour.readSchedule(0);
		assertTrue(hourZero.requiredNumber == 0);
		
		WorkSchedule.Hour hourThree = workScheduleFour.readSchedule(3);
		assertTrue(hourThree.requiredNumber == 0);
	}
	
	/* ****************** nemployee > 0 and starttime == 0 and starttime == endtime ****************** */
	 
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 0 to 1.
	 * Expects: The required number of workers of hour 0 is = 1.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeOverZeroStarttimeZeroStarttimeEqualToEndtime_RequiredNumberSet() {
		workScheduleTwo.setRequiredNumber(1, 0, 0);
		
		WorkSchedule.Hour hourZero = workScheduleTwo.readSchedule(0);
		assertTrue(hourZero.requiredNumber == 1);
	}
	
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 0 to 1.
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeOverZeroStarttimeZeroStarttimeEqualToEndtime_UnchangedScheduledWorkersOfAllHours() {
		workScheduleTwo.setRequiredNumber(1, 0, 0);

		for (int i = 0; i < 2; i++) {
			WorkSchedule.Hour h = workScheduleTwo.readSchedule(i);
			assertArrayEquals(new String[] {}, h.workingEmployees);
		}
	}

	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 0 to 1.
	 * Expects: The required number of workers are unchanged for the other hours.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeOverZeroStarttimeZeroStarttimeEqualToEndtime_UnchangedRequiredNumberOfOtherHours() {
		workScheduleTwo.setRequiredNumber(1, 0, 0);
		
		WorkSchedule.Hour hourOne = workScheduleTwo.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 0);
	}
	
	/* ****************** nemployee > 0 and starttime == 0 and starttime <  endtime ****************** */
	 
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 0 and 1 to 1.
	 * Expects: The required number of workers of hour 0 and 1 is = 1.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeOverZeroStarttimeZeroStarttimeUnderEndtime_RequiredNumbersSet() {
		workScheduleThree.setRequiredNumber(1, 0, 1);
		
		WorkSchedule.Hour hourZero = workScheduleThree.readSchedule(0);
		assertTrue(hourZero.requiredNumber == 1);
		
		WorkSchedule.Hour hourOne = workScheduleThree.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 1);
	}
	
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 0 and 1 to 1.
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeOverZeroStarttimeZeroStarttimeUnderEndtime_UnchangedScheduledWorkersOfAllHours() {
		workScheduleThree.setRequiredNumber(1, 0, 1);

		for (int i = 0; i < 3; i++) {
			WorkSchedule.Hour h = workScheduleThree.readSchedule(i);
			assertArrayEquals(new String[] {}, h.workingEmployees);
		}
	}

	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 0 and 1 to 1.
	 * Expects: The required number of workers are unchanged for the other hours.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeOverZeroStarttimeZeroStarttimeUnderEndtime_UnchangedRequiredNumberOfOtherHours() {
		workScheduleThree.setRequiredNumber(1, 0, 1);
		
		WorkSchedule.Hour hourTwo = workScheduleThree.readSchedule(2);
		assertTrue(hourTwo.requiredNumber == 0);
	}
	
	/* ****************** nemployee > 0 and starttime > 0  and starttime == endtime ****************** */
	 
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 1 to 1.
	 * Expects: The required number of workers of hour 1 is = 1.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeOverZeroStarttimeOverZeroStarttimeEqualEndtime_RequiredNumberSet() {
		workScheduleThree.setRequiredNumber(1, 1, 1);
		
		WorkSchedule.Hour hourOne = workScheduleThree.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 1);
	}
	
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 1 to 1.
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeOverZeroStarttimeOverZeroStarttimeEqualEndtime_UnchangedScheduledWorkersOfAllHours() {
		workScheduleThree.setRequiredNumber(1, 1, 1);

		for (int i = 0; i < 3; i++) {
			WorkSchedule.Hour h = workScheduleThree.readSchedule(i);
			assertArrayEquals(new String[] {}, h.workingEmployees);
		}
	}

	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 1 to 1.
	 * Expects: The required number of workers are unchanged for the other hours.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeOverZeroStarttimeOverZeroStarttimeEqualEndtime_UnchangedRequiredNumberOfOtherHours() {
		workScheduleThree.setRequiredNumber(1, 1, 1);

		WorkSchedule.Hour hourOne = workScheduleThree.readSchedule(0);
		assertTrue(hourOne.requiredNumber == 0);
		
		WorkSchedule.Hour hourTwo = workScheduleThree.readSchedule(2);
		assertTrue(hourTwo.requiredNumber == 0);
	}
	
	/* ****************** nemployee > 0 and starttime > 0  and starttime <  endtime ****************** */
	 
	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 1 and 2 to 1.
	 * Expects: The required number of workers of hour 1 and 2 is = 1.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeOverZeroStarttimeOverZeroStarttimeUnderEndtime_RequiredNumbersSet() {
		workScheduleFour.setRequiredNumber(1, 1, 2);
		
		WorkSchedule.Hour hourOne = workScheduleFour.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 1);
		
		WorkSchedule.Hour hourTwo = workScheduleFour.readSchedule(2);
		assertTrue(hourTwo.requiredNumber == 1);
	}

	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 1 and 2 to 1.
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeOverZeroStarttimeOverZeroStarttimeUnderEndtime_UnchangedScheduledWorkersOfAllHours() {
		workScheduleFour.setRequiredNumber(1, 1, 2);

		for (int i = 0; i < 4; i++) {
			WorkSchedule.Hour h = workScheduleFour.readSchedule(i);
			assertArrayEquals(new String[] {}, h.workingEmployees);
		}
	}

	/**
	 * Test method for {@link WorkSchedule#setRequiredNumber(int, int, int)}.
	 * 
	 * Tests: Setting the required number for hour 1 and 2 to 1.
	 * Expects: The required number of workers are unchanged for the other hours.
	 */
	@Test
	public void testSetRequiredNumber_NemployeeOverZeroStarttimeOverZeroStarttimeUnderEndtime_UnchangedRequiredNumberOfOtherHours() {
		workScheduleFour.setRequiredNumber(1, 1, 2);

		WorkSchedule.Hour hourOne = workScheduleFour.readSchedule(0);
		assertTrue(hourOne.requiredNumber == 0);
		
		WorkSchedule.Hour hourThree = workScheduleFour.readSchedule(3);
		assertTrue(hourThree.requiredNumber == 0);
	}
}
