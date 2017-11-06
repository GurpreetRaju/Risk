package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.risk.model.TestGameDriver;
import test.risk.model.TestPlayer;
import test.risk.model.map.TestMapFile;
import test.risk.model.map.TestMapModel;
import test.risk.model.map.TestMapValidation;
import test.risk.model.turnmanager.TestTurnManager;

@RunWith(Suite.class)
@SuiteClasses({ 
				TestTurnManager.class, 
				TestMapFile.class, 
				TestMapValidation.class,
				TestPlayer.class,
				TestGameDriver.class,
				TestMapModel.class,
			})

public class AllTests {
}
