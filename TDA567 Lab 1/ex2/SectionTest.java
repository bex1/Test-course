
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Set#section(Set)}.
 * 
 * @author Daniel B�ckstr�m & Martin Hermansson
 *
 */
public class SectionTest {
	private Set emptySet;

	@Before
	public void setUp() throws Exception {
		emptySet = new Set();
	}

	/**
	 * Tests: If the method can handle intersection of two empty sets.
	 * Expects: Set is empty.
	 */
	@Test
	public void testSection_BothEmpty() {
		Set set2 = new Set();
		emptySet.section(set2);
		int[] res = emptySet.toArray();
		assertArrayEquals(new int[] {}, res);
	}
	
	/**
	 * Tests: If the method can handle intersection of when the called set is empty and the argument is not.
	 * Expects: Set is empty.
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
	 * Tests: If the method can handle intersection of when the called set is not empty but the argument is.
	 * Expects: Set is unchanged.
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
	 * Tests: If the method can handle intersection of two disjunct non empty sets when the
	 * called set has fewer elements than the argument set.
	 * Expects: Set is unchanged.
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
	 * Tests: If the method can handle intersection of two disjunct non empty sets when the
	 * called set has more elements than the argument set.
	 * Expects: Set is unchanged.
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
	 * Tests: If the method can handle intersection of two intersecting non empty sets when the
	 * called set has less elements than the argument set.
	 * Expects: The set contains only the non-intersecting elements.
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
	 * Tests: If the method can handle intersection of two intersecting non empty sets when the
	 * called set has more elements than the argument set.
	 * Expects: The set contains only the non-intersecting elements.
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
	
	/**
	 * Tests: If two sets have the same values and section is done between them.
	 * Expects: The set that calls for section should be empty.
	 */
	@Test
	public void testSectionSame() {
		Set set2 = new Set();
		emptySet.insert(1);
		emptySet.insert(2);
		set2.insert(1);
		set2.insert(2);
		emptySet.section(set2);
		assertArrayEquals(new int[]{}, emptySet.toArray());
	}
	
	/**
	 * Tests: If two sets have the some same values and section is done between them.
	 * The set tested starts with a lower number than the argument.
	 * Expects: The intersecting values are removed.
	 */
	@Test
	public void testSectionNotSameThisStartsWithLower() {
		Set set2 = new Set();
		emptySet.insert(0);
		emptySet.insert(1);
		emptySet.insert(2);
		emptySet.insert(3);
		set2.insert(1);
		set2.insert(2);
		set2.insert(4);
		emptySet.section(set2);
		assertArrayEquals(new int[]{0,3}, emptySet.toArray());
	}
	
	/**
	 * Tests: If two sets have the some same values and section is done between them.
	 * The set tested starts with a higher number than the argument.
	 * Expects: The intersecting values are removed.
	 */
	@Test
	public void testSectionNotSameThisStartsWithHigher() {
		Set set2 = new Set();
		emptySet.insert(1);
		emptySet.insert(2);
		emptySet.insert(3);
		set2.insert(0);
		set2.insert(1);
		set2.insert(2);
		set2.insert(4);
		emptySet.section(set2);
		assertArrayEquals(new int[]{3}, emptySet.toArray());
	}
}
