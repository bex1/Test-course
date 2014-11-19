import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ AddWorkingPeriodTest.class, NextIncompleteTest.class,
		SetRequiredNumberTest.class, WorkingEmployeesTest.class })
public class WorkScheduleTestSuite {

}
