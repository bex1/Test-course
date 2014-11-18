import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Daniel
 *
 */
public class SetTest {
	
	private Set emptySet;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		emptySet = new Set();
	}
	
	/******************* Insert Tests ******************/

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

	/******************* Member Tests ******************/

	/**
	 * Test method for {@link Set#member(int)}.
	 */
	@Test
	public void testMember_ElementExists() {
		emptySet.insert(2);
		emptySet.insert(3);
		emptySet.insert(4);
		emptySet.insert(5);
		boolean res = emptySet.member(3);
		assertTrue(res);
	}
	
	/**
	 * Test method for {@link Set#member(int)}.
	 */
	@Test
	public void testMember_HigherElementDoesNotExist() {
		emptySet.insert(2);
		emptySet.insert(3);
		emptySet.insert(4);
		emptySet.insert(5);
		boolean res = emptySet.member(6);
		assertFalse(res);
	}
	
	/**
	 * Test method for {@link Set#member(int)}.
	 */
	@Test
	public void testMember_LowerElementDoesNotExist() {
		emptySet.insert(2);
		emptySet.insert(3);
		emptySet.insert(4);
		emptySet.insert(5);
		boolean res = emptySet.member(1);
		assertFalse(res);
	}

	/******************* Section Tests ******************/
	
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
	
	/******************* Contains Arith Triple Tests ******************/

	/**
	 * Test method for {@link Set#containsArithTriple()}.
	 */
	@Test
	public void testContainsArithTriple_NoTrueCase() {
		emptySet.insert(100);
		emptySet.insert(10);
		emptySet.insert(9);
		boolean res = emptySet.containsArithTriple();
		assertFalse(res);
	}
	
	/**
	 * y - x = z - y
	 * y = (z + x) / 2     
	 * x = 2y - z
	 * z = 2y - x 
	 * 
	 * Test method for {@link Set#containsArithTriple()}.
	 */
	@Test
	public void testContainsArithTriple_TrueCaseYFixed() {
		// y = 50
		emptySet.insert(50);
		// x = 49 or z = 49
		emptySet.insert(49);
		// z = 51 or x = 51
		emptySet.insert(51);
		
		boolean res = emptySet.containsArithTriple();
		assertTrue(res);
	}
	
	/**
	 * Test method for {@link Set#containsArithTriple()}.
	 * 
	 * 
	 */
	@Test
	public void testContainsArithTriple_ManyTrueCases() {
		emptySet.insert(0);
		emptySet.insert(1);
		emptySet.insert(-1);
		emptySet.insert(-2);
		emptySet.insert(2);
		
		boolean res = emptySet.containsArithTriple();
		assertTrue(res);
	}
}
