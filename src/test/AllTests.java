package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.risk.model.gamemode.TestGameDriver;
import test.risk.model.map.TestCountryNode;
import test.risk.model.map.TestMapFile;
import test.risk.model.map.TestMapModel;
import test.risk.model.map.TestMapReader;
import test.risk.model.map.TestMapValidation;
import test.risk.model.map.TestMapWriter;
import test.risk.model.player.TestAggressiveStrategy;
import test.risk.model.player.TestBenevolentStrategy;
import test.risk.model.player.TestCheaterStrategy;
import test.risk.model.player.TestPlayer;
import test.risk.model.player.TestRandomStrategy;

/**
 * This Test Suite calls all the test classes.
 */
@RunWith(Suite.class)
@SuiteClasses({ 
				TestCountryNode.class,
				TestMapFile.class, 
				TestMapValidation.class,
				TestPlayer.class,
				TestGameDriver.class,
				TestMapModel.class,
				TestMapWriter.class,
				TestMapReader.class,
				TestAggressiveStrategy.class,
				TestBenevolentStrategy.class,
				TestCheaterStrategy.class,
				TestRandomStrategy.class
			})

public class AllTests {
}
