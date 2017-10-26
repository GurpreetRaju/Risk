package risk.model.turnmanager;

import risk.model.GameDriver;

public class Attack extends Phase{
	
	/**
	 * Sets Controls for fortification phase.
	 */
	public void setControls()
	{
//		GameDriver.getInstance().getControlGUI().fortificationControls(GameDriver.getInstance().getPlayerCountryNames());
//		GameDriver.getInstance().setFortificationLiteners();
		GameDriver.getInstance().changePhase();
	}
	
	public void playMove() {
		
	}
	
}
