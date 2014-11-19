
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Daniel Bäckström & Martin Hermansson
 *
 */
public class ContainsArithTripleTest {
	private Set emptySet;

	@Before
	public void setUp() throws Exception {
		emptySet = new Set();
	}

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
