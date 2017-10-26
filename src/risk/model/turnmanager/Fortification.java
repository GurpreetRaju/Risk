package risk.model.turnmanager;

import risk.model.GameDriver;

public class Fortification extends Phase {
	
	/**
	 * Sets Controls for fortification phase.
	 */
	public void setControls()
	{
		GameDriver.getInstance().getControlGUI().fortificationControls(GameDriver.getInstance().getPlayerCountryNames());
		GameDriver.getInstance().setFortificationLiteners();
	}
	
	public void playMove() {
		
	}
	
}
