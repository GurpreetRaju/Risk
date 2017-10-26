package risk.model.turnmanager;

import risk.model.GameDriver;

public class Reinforcement extends Phase {
		
	public void setControls() {
		GameDriver.getInstance().getControlGUI().reinforcementConrols(GameDriver.getInstance().getPlayerArmies(), GameDriver.getInstance().getPlayerCountryNames());
		GameDriver.getInstance().setControlsActionListeners();
	}
	
	public void playMove() {
		
	}

}
