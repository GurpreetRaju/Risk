package risk.model.player;

import java.util.ArrayList;

import risk.model.gamemode.GameDriver;

public class CheaterStrategy implements PlayerStrategy {

	private GameDriver driver;
	
	@Override
	public void reinforcementPhase(int armies, String[] countryList) {
		driver.reinforcementControls(2 * armies, countryList);
		driver.setControlsActionListeners();

	}

	@Override
	public void attackPhase(ArrayList<String> countryList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void fortificationPhase(ArrayList<String> countryList) {
		// TODO Auto-generated method stub

	}

	@Override
	public String placeArmy(String[] strings, String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
