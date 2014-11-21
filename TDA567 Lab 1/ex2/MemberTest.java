
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link Set#member(int)}.
 * 
 * @author Daniel B�ckstr�m & Martin Hermansson
 *
 */
public class MemberTest {
	private Set emptySet;

	@Before
	public void setUp() throws Exception {
		emptySet = new Set();
	}

	/**
	 * Tests: If the method can compute that an element that does exist in the set is in fact in the set.
	 * Expects: True returned.
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
	 * Tests: If the method can compute that an element larger than all existing elements in the set is not in the set.
	 * Expects: False returned.
	 */
	@Test
	public void testMember_LargerElementDoesNotExist() {
		emptySet.insert(2);
		emptySet.insert(3);
		emptySet.insert(4);
		emptySet.insert(5);
		boolean res = emptySet.member(6);
		assertFalse(res);
	}
	
	/**
	 * Tests: If the method can compute that an element smaller than all existing elements in the set is not in the set.
	 * Expects: False returned.
	 */
	@Test
	public void testMember_SmallerElementDoesNotExist() {
		emptySet.insert(2);
		emptySet.insert(3);
		emptySet.insert(4);
		emptySet.insert(5);
		boolean res = emptySet.member(1);
		assertFalse(res);
	}
}
