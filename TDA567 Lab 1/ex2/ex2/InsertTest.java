package ex2;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class InsertTest {
	private Set emptySet;

	@Before
	public void setUp() throws Exception {
		emptySet = new Set();
	}

	/**
	 * Test method for {@link Set#insert(int)}.
	 */
	@Test
	public void testInsert_OneElement() {
		emptySet.insert(4);
		int[] res = emptySet.toArray();
		assertArrayEquals(new int[] {4}, res);
	}
	
	/**
	 * Test method for {@link Set#insert(int)}.
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
	 * Test method for {@link Set#insert(int)}.
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
	 * Test method for {@link Set#insert(int)}.
	 */
	@Test
	public void testInsert_DuplicateElements() {
		emptySet.insert(5);
		emptySet.insert(5);
		int[] res = emptySet.toArray();
		assertArrayEquals(new int[] {5}, res);
	}
}
