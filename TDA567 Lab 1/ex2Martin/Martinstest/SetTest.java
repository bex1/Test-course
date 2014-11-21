package Martinstest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SetTest {

	Set s1;
	Set s2;
	
	@Before
	public void setUp() throws Exception {
		s1 = new Set();
		s2 = new Set();
	}

	@Test
	public void testSet() {
//		fail("Not yet implemented");
	}

	@Test
	public void testToArrayEmpty() {
		assertTrue(s1.toArray().length == 0);
	}

	@Test
	public void testInsertArrayLengthOne() {
		s1.insert(0);
		assertTrue(s1.toArray().length == 1);
	}
	
	@Test
	public void testInsertArraySortOrderUp() {
		s1.insert(0);
		s1.insert(1);
		assertTrue(s1.toArray()[0] == 0);
		assertTrue(s1.toArray()[1] == 1);
	}
	
	@Test
	public void testInsertArraySortOrderDown() {
		s1.insert(2);
		s1.insert(1);
		assertTrue(s1.toArray()[0] == 1);
		assertTrue(s1.toArray()[1] == 2);
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	public void testInsertTwice() {
		s1.insert(1);
		s1.insert(1);
		assertTrue(s1.toArray()[0] == 1);
		assertTrue(s1.toArray().length == 1);
		assertFalse(s1.toArray()[1] == 1);
	}
	
	@Test
	public void testInsertManyNumbers() {
		int x = 312352;
		int y = 143232;
		int z = 524324;
		s1.insert(x);
		s1.insert(y);
		s1.insert(z);
		assertTrue(s1.toArray().length == 3);
	}
	
	@Test
	public void testMemberEmpty() {
		assertFalse(s1.member(1));
	}

	@Test
	public void testMemberIn() {
		s1.insert(0);
		assertTrue(s1.member(0));
	}
	
	@Test
	public void testMemberNotIn() {
		s1.insert(0);
		assertFalse(s1.member(1));
	}
	
	@Test
	public void testMemberInMany() {
		s1.insert(0);
		s1.insert(2);
		s1.insert(4);
		assertTrue(s1.member(4));
	}
	
	@Test
	public void testMemberNotInMany() {
		s1.insert(0);
		s1.insert(2);
		assertFalse(s1.member(1));
	}
	
	@Test
	public void testSectionEmpty() {
		s1.section(s2);
		assertTrue(s1.toArray().length == 0);
		assertTrue(s2.toArray().length == 0);
	}
	
	@Test
	public void testSectionThisEmpty() {
		s2.insert(1);
		s1.section(s2);
		assertArrayEquals(new int[]{}, s1.toArray());
		assertArrayEquals(new int[]{1}, s2.toArray());
	}
	
	@Test
	public void testSectionParameterEmpty() {
		s1.insert(1);
		s1.section(s2);
		assertArrayEquals(new int[]{1}, s1.toArray());
		assertArrayEquals(new int[]{}, s2.toArray());
	}
	
	@Test
	public void testSectionSame() {
		s1.insert(1);
		s1.insert(2);
		s2.insert(1);
		s2.insert(2);
		s1.section(s2);
		assertArrayEquals(new int[]{}, s1.toArray());
		assertArrayEquals(new int[]{1,2}, s2.toArray());
	}
	
	@Test
	public void testSectionNotSameThisStartsWithLower() {
		s1.insert(0);
		s1.insert(1);
		s1.insert(2);
		s1.insert(3);
		s2.insert(1);
		s2.insert(2);
		s2.insert(4);
		s1.section(s2);
		assertArrayEquals(new int[]{0,3}, s1.toArray());
		assertArrayEquals(new int[]{1,2,4}, s2.toArray());
	}
	
	@Test
	public void testSectionNotSameThisStartsWithHigher() {
		s1.insert(1);
		s1.insert(2);
		s1.insert(3);
		s2.insert(0);
		s2.insert(1);
		s2.insert(2);
		s2.insert(4);
		s1.section(s2);
		assertArrayEquals(new int[]{3}, s1.toArray());
		assertArrayEquals(new int[]{0,1,2,4}, s2.toArray());
	}

	// y-x = z-y
	
	@Test
	public void testContainsArithTripleEmpty() {
		assertFalse(s1.containsArithTriple());
	}
	
	@Test
	public void testContainsArithTripleFalse() {
		int x = 312352;
		int y = 143232;
		int z = 524324;
		s1.insert(x);
		s1.insert(y);
		s1.insert(z);
		assertFalse(s1.containsArithTriple());
	}
	
	@Test
	public void testContainsArithTripleFalsePlusNoise() {
		int x = 312352;
		int y = 143232;
		int z = 524324;
		s1.insert(1);
		s1.insert(x);
		s1.insert(92);
		s1.insert(y);
		s1.insert(43432);
		s1.insert(z);
		s1.insert(4321);
		assertFalse(s1.containsArithTriple());
	}
	
	@Test
	public void testContainsArithTripleTrue() {
		int x = 1;
		int y = 2;
		int z = 3;
		s1.insert(x);
		s1.insert(y);
		s1.insert(z);
		//2-1=3-2
		assertTrue(s1.containsArithTriple());
	}
	
	@Test
	public void testContainsArithTripleTruePlusNoise() {
		int x = 4;
		int y = 7;
		int z = 10;
		s1.insert(12);
		s1.insert(x);
		s1.insert(32);
		s1.insert(y);
		s1.insert(54);
		s1.insert(z);
		s1.insert(432);
		//7-4=10-7
		assertTrue(s1.containsArithTriple());
	}

}
