
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Daniel Bäckström & Martin Hermansson
 *
 */
public class MemberTest {
	private Set emptySet;

	@Before
	public void setUp() throws Exception {
		emptySet = new Set();
	}

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
}
