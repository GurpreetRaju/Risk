package risk.model.player;

import java.util.ArrayList;

/**
 * Interface for various Player Strategies.
 */
public interface PlayerStrategy {

	/**
	 * Execute the reinforcement phase according to strategy.
	 * @param armies number of armies player can use to reinforce
	 * @param countryList list of countries player owns
	 */
	public void reinforcementPhase(int armies, String[] countryList);
	
	/**
	 * Attack phase implementation
	 * @param countryList list of countries attacker owns
	 */
	public void attackPhase(ArrayList<String> countryList);
	
	/**
	 * Execute fortification move, moving some armies from one country to another
	 * @param countryList List of countries player can fortify
	 */
	public void fortificationPhase(ArrayList<String> countryList);
	
	/**
	 * This method implements the player choosing a country to place a army on.
	 * @param strings list of countries player can place army on
	 * @param string message to be shown to player
	 * @return chosen country on which army has to be placed
	 */
	public String placeArmy(String[] strings, String string);
	
	/**
	 * Player selecting number of dice to roll in attack phase as either attacker or defender
	 * @param diceToRoll max number of dice plaer can roll
	 * @param pName name of player choosing the dice
	 * @return return the chosen number of dice
	 */
	public int selectDiceNumber(int diceToRoll, String pName);

	/**
	 * Moving armies after attacker won a battle
	 * @param aArmies minimum number of armies player must move
	 * @param maxArmies maximum number of armies player can move
	 * @param message message for the player
	 * @return number of armies chosen by player for move
	 */
	public int moveArmies(int aArmies, int maxArmies, String message);
	
	/**
	 * Name of strategy player currently use
	 * @return name of strategy
	 */
	public String getStrategyName();

}
