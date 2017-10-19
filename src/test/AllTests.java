package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestFortification.class, TestMapFile.class, TestMapFileChooser.class, TestMapValidation.class,
		TestPlayer.class })
public class AllTests {

}
