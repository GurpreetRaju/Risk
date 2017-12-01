package risk.model.player;

import java.util.ArrayList;

/**
 * Interface for various Player Strategies.
 */
public interface PlayerStrategy {
	
	/**
	 * Reinforcement phase of player that reinforces its strongest countries.
	 */
	public void reinforcementPhase(int armies, String[] countryList);
	
	/**
	 * Attack phase for the player.
	 */
	public void attackPhase(ArrayList<String> countryList);
	
	/**
	 * Fortification phase of the player.
	 */
	public void fortificationPhase(ArrayList<String> countryList);

	/**
	 * Distribute armies in startup phase.
	 */
	public String placeArmy(String[] strings, String string);

	/**
	 * @return number of dice rolls
	 */
	public int selectDiceNumber(int diceToRoll, String pName);

	/**
	 * Armies to be moved for fortification.
	 */
	public int moveArmies(int aArmies, int maxArmies, String message);
	
	/**
	 * Gives the name of strategy.
	 */
	public String getStrategyName();

}
