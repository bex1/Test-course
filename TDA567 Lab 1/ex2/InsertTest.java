
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Martinstest.Set;

/**
 * Tests for {@link Set#insert(int)}.
 * 
 * @author Daniel Bäckström & Martin Hermansson
 *
 */
public class InsertTest {
	private Set emptySet;

	@Before
	public void setUp() throws Exception {
		emptySet = new Set();
	}

	/**
	 * Tests: Checking if one can insert one element into the set.
	 * Expects: The set contains only the inserted element once.
	 */
	@Test
	public void testInsert_OneElement() {
		emptySet.insert(4);
		int[] res = emptySet.toArray();
		assertArrayEquals(new int[] {4}, res);
	}
	
	/**
	 * Tests: Checking if one can insert many distinct elements into the set in ascending order.
	 * Expects: The set contains only the inserted distinct elements once ordered ascending.
	 */
	@Test
	public void testInsert_ManyDistinctElements_AscendingOrder() {
		emptySet.insert(2);
		emptySet.insert(3);
		emptySet.insert(4);
		emptySet.insert(5);
		int[] res = emptySet.toArray();
		assertArrayEquals(new int[] {2, 3, 4, 5}, res);
	}
	
	/**
	 * Tests: Checking if one can insert many distinct elements into the set in descending order.
	 * Expects: The set contains only the inserted distinct elements once ordered ascending.
	 */
	@Test
	public void testInsert_ManyDistinctElements_DescendingOrder() {
		emptySet.insert(5);
		emptySet.insert(4);
		emptySet.insert(3);
		emptySet.insert(2);
		int[] res = emptySet.toArray();
		assertArrayEquals(new int[] {2, 3, 4, 5}, res);
	}
	
	/**
	 * Tests: Checking if one can insert duplicate elements into the set.
	 * Expects: The set contains only the element once.
	 */
	@Test
	public void testInsert_DuplicateElements() {
		emptySet.insert(5);
		emptySet.insert(5);
		int[] res = emptySet.toArray();
		assertArrayEquals(new int[] {5}, res);
	}
}
