
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SectionTest {
	private Set emptySet;

	@Before
	public void setUp() throws Exception {
		emptySet = new Set();
	}

	/**
	 * Test method for {@link Set#section(Set)}.
	 */
	@Test
	public void testSection_BothEmpty() {
		Set set2 = new Set();
		emptySet.section(set2);
		int[] res = emptySet.toArray();
		assertArrayEquals(new int[] {}, res);
	}
	
	/**
	 * Test method for {@link Set#section(Set)}.
	 */
	@Test
	public void testSection_ThisEmptyOtherNot() {
		Set set2 = new Set();
		set2.insert(2);
		set2.insert(3);
		emptySet.section(set2);
		int[] res = emptySet.toArray();
		assertArrayEquals(new int[] {}, res);
	}
	
	/**
	 * Test method for {@link Set#section(Set)}.
	 */
	@Test
	public void testSection_OtherEmptyThisNot() {
		Set set2 = new Set();
		emptySet.insert(2);
		emptySet.insert(3);
		emptySet.section(set2);
		int[] res = emptySet.toArray();
		assertArrayEquals(new int[] {2, 3}, res);
	}
	
	/**
	 * Test method for {@link Set#section(Set)}.
	 */
	@Test
	public void testSection_OtherSetDisjunctAndLarger() {
		Set set2 = new Set();
		emptySet.insert(2);
		emptySet.insert(3);
		set2.insert(5);
		set2.insert(1);
		set2.insert(4);
		emptySet.section(set2);
		int[] res = emptySet.toArray();
		assertArrayEquals(new int[] {2, 3}, res);
	}
	
	/**
	 * Test method for {@link Set#section(Set)}.
	 */
	@Test
	public void testSection_OtherSetDisjunctAndSmaller() {
		Set set2 = new Set();
		emptySet.insert(2);
		emptySet.insert(3);
		emptySet.insert(5);
		set2.insert(1);
		set2.insert(4);
		emptySet.section(set2);
		int[] res = emptySet.toArray();
		assertArrayEquals(new int[] {2, 3, 5}, res);
	}

	/**
	 * Test method for {@link Set#section(Set)}.
	 */
	@Test
	public void testSection_OtherSetIntersectsAndLarger() {
		Set set2 = new Set();
		emptySet.insert(2);
		emptySet.insert(3);
		set2.insert(5);
		set2.insert(2);
		set2.insert(4);
		emptySet.section(set2);
		int[] res = emptySet.toArray();
		assertArrayEquals(new int[] {3}, res);
	}
	
	/**
	 * Test method for {@link Set#section(Set)}.
	 */
	@Test
	public void testSection_OtherSetIntersectsAndSmaller() {
		Set set2 = new Set();
		emptySet.insert(2);
		emptySet.insert(3);
		emptySet.insert(5);
		set2.insert(5);
		set2.insert(1);
		emptySet.section(set2);
		int[] res = emptySet.toArray();
		assertArrayEquals(new int[] {2, 3}, res);
	}
}
