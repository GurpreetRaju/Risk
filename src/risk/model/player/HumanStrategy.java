package risk.model.player;

import java.util.ArrayList;

import risk.model.gamemode.GameDriver;

public class HumanStrategy implements PlayerStrategy{
	
	private GameDriver driver;
	
	public HumanStrategy(GameDriver newDriver) {
		driver = newDriver;
	}

	public void reinforcementPhase(int armies, String[] countryList) {
		driver.reinforcementControls(armies, countryList);
		driver.setControlsActionListeners();
	}

	public void attackPhase(ArrayList<String> countryList) {
		driver.attackControls(countryList.toArray(new String[countryList.size()]));
		driver.setAttackListeners();
	}

	public void fortificationPhase(ArrayList<String> countryList) {
		driver.fortificationControls(countryList.toArray(new String[countryList.size()]));
		driver.setFortificationLiteners();
	}

	public String placeArmy(String[] countries, String name) {
		return (String) driver.placeArmyDialog(countries, name+" Place your army");
	}

	@Override
	public int selectDiceNumber(int diceToRoll, String pName) {
		return driver.setUpBoxInput(1, diceToRoll, pName+"! Please select number of dice to roll.");
	}

	@Override
	public int moveArmies(int aArmies, int maxArmies, String message) {
		return driver.setUpBoxInput(aArmies, maxArmies, "Select armies to move:");
	}

	@Override
	public String getStrategyName() {
		return "human";
	}

}
