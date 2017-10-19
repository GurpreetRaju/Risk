package model;

public class Reinforcement extends Phase{
	
	@Override
	public void playMove() {
		
	}

	@Override
	public void setControls() {
		GameDriver.getInstance().getControlGUI().reinforcementConrols(GameDriver.getInstance().getPlayerArmies(), GameDriver.getInstance().getPlayerCountryNames());
		GameDriver.getInstance().setControlsActionListeners();
	}
}
