package risk.model.player;

import java.util.ArrayList;

import risk.model.gamemode.GameDriver;

/**
 * This class add  user interaction with reinforcement, attack and fortification phase.
 * @author Gurpreet
 *
 */
public class HumanStrategy implements PlayerStrategy{
	
	/**
	 * Reference to gameDriver object
	 */
	private GameDriver driver;
	/**
	 * Constructor for HumanStrategy
	 * @param newDriver object of GameDriver class
	 */
	public HumanStrategy(GameDriver newDriver) {
		driver = newDriver;
	}
	
	/**
	 * Reinforcement phase implemntation
	 * @param armies number of armies to be placed
	 * @param countryList list of countries player owns
	 */
	public void reinforcementPhase(int armies, String[] countryList) {
		System.out.print("Checkpoint 2");
		driver.reinforcementControls(armies, countryList);		
		driver.setControlsActionListeners();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void attackPhase(ArrayList<String> countryList) {
		driver.attackControls(countryList.toArray(new String[countryList.size()]));
		driver.setAttackListeners();
	}
	
	/**
	 * Fortification implementation
	 * @param countryList list of countries that can be fortfied
	 */
	public void fortificationPhase(ArrayList<String> countryList) {
		driver.fortificationControls(countryList.toArray(new String[countryList.size()]));
		driver.setFortificationLiteners();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public String placeArmy(String[] countries, String name) {
		return (String) driver.placeArmyDialog(countries, name+" Place your army");
	}

	/**
	 * {@inheritDoc}
	 */
	public int selectDiceNumber(int diceToRoll, String pName) {
		return driver.setUpBoxInput(1, diceToRoll, pName+"! Please select number of dice to roll.");
	}

	/**
	 * {@inheritDoc}
	 */
	public int moveArmies(int aArmies, int maxArmies, String message) {
		return driver.setUpBoxInput(aArmies, maxArmies, "Select armies to move:");
	}

	/**
	 * {@inheritDoc}
	 */
	public String getStrategyName() {
		return "human";
	}

}
