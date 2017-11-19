package risk.model.player;

import java.util.ArrayList;

import risk.model.GameDriver;

public class HumanStrategy implements PlayerStrategy{

	public void reinforcementPhase(int armies, String[] countryList) {
		GameDriver.getInstance().getControlGUI().reinforcementControls(armies, countryList);
		GameDriver.getInstance().setControlsActionListeners();
	}

	public void attackPhase(ArrayList<String> countryList) {
		GameDriver.getInstance().getControlGUI().attackControls(countryList.toArray(new String[countryList.size()]));
		GameDriver.getInstance().setAttackListeners();
	}

	public void fortificationPhase(ArrayList<String> countryList) {
		GameDriver.getInstance().getControlGUI().fortificationControls(countryList.toArray(new String[countryList.size()]));
		GameDriver.getInstance().setFortificationLiteners();
	}

}
