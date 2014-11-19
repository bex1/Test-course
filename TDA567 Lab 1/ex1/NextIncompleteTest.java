
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * Tests for {@link WorkSchedule#nextIncomplete(int)}.
 * 
 ******************* Specification ******************
 *
 * requires: 
 * 		currenttime >= 0 and currenttime < size 
 * ensures:
 * 		if there is an hour in the interval currenttime to (size - 1) 
 * 		such that the length of workingEmplyees is less that requiredNumber then
 *   		returns the time of the hour closest to currenttime 
 *   		such that the length of workingEmplyees is less than requiredNumber
 * 		otherwise
 *   		returns -1
 *		in either case the schedule is unchanged
 *
 *******************  Input Space  ******************
 * 
 * {All integers currenttime such that 0 =< currenttime < size}
 * 
 ***********  Partitioning Of Input Space  ************
 *
 * TODO
 *
 * @author Daniel
 *
 */
public class NextIncompleteTest {
	private WorkSchedule workScheduleThree;

	@Before
	public void setUp() throws Exception {
		workScheduleThree = new WorkSchedule(3);
	}

	// Input space partioning of precondition space:
	// {there is one hour in the interval currenttime to (size - 1) 
  	//	such that the length of workingEmployees is less than requiredNumber,
  	//  there is no hour in the interval currenttime to (size - 1) 
  	//  such that the length of workingEmployees is less than requiredNumber,
  	//  there are more than one hour in the interval currenttime to (size - 1) 
  	//  such that the length of workingEmployees is less than requiredNumber}
	
	
	// there is one hour in the interval currenttime to (size - 1) 
  	//	such that the length of workingEmployees is less than requiredNumber,
	
	/**
	 * Tests: Calling nextIncomplete with an argument currenttime such that
	 * there is one hour in the interval currenttime to (size - 1) 
  	 * where the length of workingEmployees is less than requiredNumber.
  	 * 
	 * Expects: First hour with an unfilled worker schedule is returned.  
	 */
	@Test
	public void testNextIncomplete_OneHourInInterval_FirstUnFilledScheduleHourExpected() {
		workScheduleThree.setRequiredNumber(1, 0, 2);
		workScheduleThree.addWorkingPeriod("0", 0, 0);
		workScheduleThree.addWorkingPeriod("1", 2, 2);
		int ret = workScheduleThree.nextIncomplete(0);
		assertTrue(ret == 1);
	}
	
	/**
	 * Tests: Calling nextIncomplete with an argument currenttime such that
	 * there is one hour in the interval currenttime to (size - 1) 
  	 * where the length of workingEmployees is less than requiredNumber.
  	 * 
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testWorkingEmployees_OneHourInInterval_UnchangedScheduledWorkersOfAllHours() {
		workScheduleThree.setRequiredNumber(1, 0, 2);
		workScheduleThree.addWorkingPeriod("0", 0, 0);
		workScheduleThree.addWorkingPeriod("1", 2, 2);
		workScheduleThree.nextIncomplete(0);
		
		WorkSchedule.Hour hourZero = workScheduleThree.readSchedule(0);
		assertArrayEquals(new String[] {"0"}, hourZero.workingEmployees);

		WorkSchedule.Hour hourOne = workScheduleThree.readSchedule(1);
		assertArrayEquals(new String[] {}, hourOne.workingEmployees);
		
		WorkSchedule.Hour hourTwo = workScheduleThree.readSchedule(2);
		assertArrayEquals(new String[] {"1"}, hourTwo.workingEmployees);
	}
	
	/**
	 * Tests: Calling nextIncomplete with an argument currenttime such that
	 * there is one hour in the interval currenttime to (size - 1) 
  	 * where the length of workingEmployees is less than requiredNumber.
  	 * 
	 * Expects: The required number of workers is unchanged for each hour.
	 */
	@Test
	public void testWorkingEmployees_OneHourInInterval_UnchangedRequiredNumbersOfAllHours() {
		workScheduleThree.setRequiredNumber(1, 0, 2);
		workScheduleThree.addWorkingPeriod("0", 0, 0);
		workScheduleThree.addWorkingPeriod("1", 2, 2);
		workScheduleThree.nextIncomplete(0);
		
		WorkSchedule.Hour hourZero = workScheduleThree.readSchedule(0);
		assertTrue(hourZero.requiredNumber == 1);

		WorkSchedule.Hour hourOne = workScheduleThree.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 1);
		
		WorkSchedule.Hour hourTwo = workScheduleThree.readSchedule(2);
		assertTrue(hourTwo.requiredNumber == 1);
	}
	
	
	//  there is no hour in the interval currenttime to (size - 1) 
  	//  such that the length of workingEmployees is less than requiredNumber,
	
	/**
	 * Tests: Calling nextIncomplete with an argument currenttime such that
	 * there is no hour in the interval currenttime to (size - 1) 
  	 * where the length of workingEmployees is less than requiredNumber,
  	 * 
	 * Expects: Minus one is returned indicating that no unfilled schedule hour 
	 * was found in the interval.
	 */
	@Test
	public void testNextIncomplete_NoHourInInterval_MinusOneExpected() {
		workScheduleThree.setRequiredNumber(1, 0, 2);
		workScheduleThree.addWorkingPeriod("0", 0, 0);
		workScheduleThree.addWorkingPeriod("1", 1, 2);
		int ret = workScheduleThree.nextIncomplete(0);
		assertTrue(ret == -1);
	}
	
	/**
	 * Tests: Calling nextIncomplete with an argument currenttime such that
	 * there is no hour in the interval currenttime to (size - 1) 
  	 * where the length of workingEmployees is less than requiredNumber.
  	 * 
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testWorkingEmployees_NoHourInInterval_UnchangedScheduledWorkersOfAllHours() {
		workScheduleThree.setRequiredNumber(1, 0, 2);
		workScheduleThree.addWorkingPeriod("0", 0, 0);
		workScheduleThree.addWorkingPeriod("1", 1, 2);
		workScheduleThree.nextIncomplete(0);
		
		WorkSchedule.Hour hourZero = workScheduleThree.readSchedule(0);
		assertArrayEquals(new String[] {"0"}, hourZero.workingEmployees);

		WorkSchedule.Hour hourOne = workScheduleThree.readSchedule(1);
		assertArrayEquals(new String[] {"1"}, hourOne.workingEmployees);
		
		WorkSchedule.Hour hourTwo = workScheduleThree.readSchedule(2);
		assertArrayEquals(new String[] {"1"}, hourTwo.workingEmployees);
	}
	
	/**
	 * Tests: Calling nextIncomplete with an argument currenttime such that
	 * there is no hour in the interval currenttime to (size - 1) 
  	 * where the length of workingEmployees is less than requiredNumber.
  	 * 
	 * Expects: The required number of workers is unchanged for each hour.
	 */
	@Test
	public void testWorkingEmployees_NoHourInInterval_UnchangedRequiredNumbersOfAllHours() {
		workScheduleThree.setRequiredNumber(1, 0, 2);
		workScheduleThree.addWorkingPeriod("0", 0, 0);
		workScheduleThree.addWorkingPeriod("1", 1, 2);
		workScheduleThree.nextIncomplete(0);
		
		WorkSchedule.Hour hourZero = workScheduleThree.readSchedule(0);
		assertTrue(hourZero.requiredNumber == 1);

		WorkSchedule.Hour hourOne = workScheduleThree.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 1);
		
		WorkSchedule.Hour hourTwo = workScheduleThree.readSchedule(2);
		assertTrue(hourTwo.requiredNumber == 1);
	}
	
	
	//  there are more than one hour in the interval currenttime to (size - 1) 
  	//  such that the length of workingEmployees is less than requiredNumber
	
	/**
	 * Tests: Calling nextIncomplete with an argument currenttime such that
	 * there are more than one hour in the interval currenttime to (size - 1) 
  	 * where the length of workingEmployees is less than requiredNumber,
  	 * 
	 * Expects: First hour with an unfilled worker schedule is returned.  
	 */
	@Test
	public void testNextIncomplete_MoreThanOneHourInInterval_FirstUnFilledScheduleHourExpected() {
		workScheduleThree.setRequiredNumber(1, 0, 2);
		workScheduleThree.addWorkingPeriod("0", 1, 1);
		int ret = workScheduleThree.nextIncomplete(0);
		assertTrue(ret == 0);
	}
	
	/**
	 * Tests: Calling nextIncomplete with an argument currenttime such that
	 * there are more than one hour in the interval currenttime to (size - 1) 
  	 * where the length of workingEmployees is less than requiredNumber,
  	 * 
	 * Expects: The scheduled workers for each hour is unchanged.
	 */
	@Test
	public void testWorkingEmployees_MoreThanOneHourInInterval_UnchangedScheduledWorkersOfAllHours() {
		workScheduleThree.setRequiredNumber(1, 0, 2);
		workScheduleThree.addWorkingPeriod("0", 1, 1);
		workScheduleThree.nextIncomplete(0);
		
		WorkSchedule.Hour hourZero = workScheduleThree.readSchedule(0);
		assertArrayEquals(new String[] {}, hourZero.workingEmployees);

		WorkSchedule.Hour hourOne = workScheduleThree.readSchedule(1);
		assertArrayEquals(new String[] {"0"}, hourOne.workingEmployees);
		
		WorkSchedule.Hour hourTwo = workScheduleThree.readSchedule(2);
		assertArrayEquals(new String[] {}, hourTwo.workingEmployees);
	}
	
	/**
	 * Tests: Calling nextIncomplete with an argument currenttime such that
	 * there are more than one hour in the interval currenttime to (size - 1) 
  	 * where the length of workingEmployees is less than requiredNumber,
  	 * 
	 * Expects: The required number of workers is unchanged for each hour.
	 */
	@Test
	public void testWorkingEmployees_MoreThanOneHourInInterval_UnchangedRequiredNumbersOfAllHours() {
		workScheduleThree.setRequiredNumber(1, 0, 2);
		workScheduleThree.addWorkingPeriod("0", 1, 1);
		workScheduleThree.nextIncomplete(0);
		
		WorkSchedule.Hour hourZero = workScheduleThree.readSchedule(0);
		assertTrue(hourZero.requiredNumber == 1);

		WorkSchedule.Hour hourOne = workScheduleThree.readSchedule(1);
		assertTrue(hourOne.requiredNumber == 1);
		
		WorkSchedule.Hour hourTwo = workScheduleThree.readSchedule(2);
		assertTrue(hourTwo.requiredNumber == 1);
	}
}
