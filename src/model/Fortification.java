package model;

public class Fortification extends Phase{
	
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
