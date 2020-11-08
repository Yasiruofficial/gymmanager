package Junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        testAddFunc.class,testDltFunc.class,testSaveFunc.class,testPrintFunc.class,testSortFunc.class,testPrintFunc.class
})


public class JunitTestSuite {

}