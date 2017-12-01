package risk.model.player;

import java.util.ArrayList;

import risk.model.gamemode.GameDriver;

/**
 * PlayerStrategy for human players.
 */
public class HumanStrategy implements PlayerStrategy{
	
	/**
	 * GameDriver instance.
	 */
	private GameDriver driver;
	
	/**
	 * Constructor to initialize gamedriver.
	 * @param nDriver GameDriver Instance.
	 */
	public HumanStrategy(GameDriver newDriver) {
		driver = newDriver;
	}

	/**
	 * Human strategy Reinforcement.
	 * @param armies armies to be reinforced.
	 * @param countryList countries of the player.
	 */
	public void reinforcementPhase(int armies, String[] countryList) {
		System.out.print("Checkpoint 2");
		driver.reinforcementControls(armies, countryList);		
		driver.setControlsActionListeners();
	}

	/**
	 * Human strategy Reinforcement.
	 * @param countryList countries of the player.
	 */
	public void attackPhase(ArrayList<String> countryList) {
		driver.attackControls(countryList.toArray(new String[countryList.size()]));
		driver.setAttackListeners();
	}

	/**
	 * Human strategy fortification.
	 * @param countryList countries of the player.
	 */
	public void fortificationPhase(ArrayList<String> countryList) {
		driver.fortificationControls(countryList.toArray(new String[countryList.size()]));
		driver.setFortificationLiteners();
	}

	/**
	 * Distribute armies in startup phase.
	 */
	public String placeArmy(String[] countries, String name) {
		return (String) driver.placeArmyDialog(countries, name+" Place your army");
	}

	/**
	* @return number of dice rolls
	*/
	@Override
	public int selectDiceNumber(int diceToRoll, String pName) {
		return driver.setUpBoxInput(1, diceToRoll, pName+"! Please select number of dice to roll.");
	}

	/**
	 * Armies to be moved for fortification.
	 */
	@Override
	public int moveArmies(int aArmies, int maxArmies, String message) {
		return driver.setUpBoxInput(aArmies, maxArmies, "Select armies to move:");
	}

	/**
	 * Gives the name of strategy.
	 */
	@Override
	public String getStrategyName() {
		return "human";
	}

}
