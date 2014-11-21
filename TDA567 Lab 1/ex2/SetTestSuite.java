

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import Martinstest.Set;

/**
 * Testsuite for {@link Set}
 * 
 * @author Daniel Bäckström & Martin Hermansson
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ ContainsArithTripleTest.class, InsertTest.class,
		MemberTest.class, SectionTest.class })
public class SetTestSuite {

}
