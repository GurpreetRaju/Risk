package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.risk.model.turnmanager.TestTurnManager;

@RunWith(Suite.class)
@SuiteClasses({ 
				TestTurnManager.class, 
				TestMapFile.class, 
				TestMapValidation.class,
				TestPlayer.class,
				TestGameDriver.class
			})

public class AllTests {
}
