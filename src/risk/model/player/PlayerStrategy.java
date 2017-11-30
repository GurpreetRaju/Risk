package risk.model.player;

import java.util.ArrayList;

public interface PlayerStrategy {
	
	public void reinforcementPhase(int armies, String[] countryList);
	
	public void attackPhase(ArrayList<String> countryList);
	
	public void fortificationPhase(ArrayList<String> countryList);

	public String placeArmy(String[] strings, String string);

	public int selectDiceNumber(int diceToRoll, String pName);

	public int moveArmies(int aArmies, int maxArmies, String message);

}
