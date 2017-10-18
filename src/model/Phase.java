package model;

public class Phase {
	
	private String phase;
	public static final Phase reinforcement = new Phase("reinforcement");
	public static final Phase attack = new Phase("attack");
	public static final Phase fortification = new Phase("fortification");
	
	public Phase(String string){
		this.phase = string;
	}
	
	public void reinforcementPhase()
	{
		this.phase = "reinforcement";
		GameDriver.getInstance().getControlGUI().reinforcementConrols(GameDriver.getInstance().getPlayerArmies(), GameDriver.getInstance().getPlayerCountryNames());
		//System.out.println(getPlayerArmies());
		GameDriver.getInstance().setControlsActionListeners();
	}
	
	public void fortificationPhase()
	{
		this.phase = "fortification";
		GameDriver.getInstance().getControlGUI().fortificationControls(GameDriver.getInstance().getPlayerCountryNames());
		GameDriver.getInstance().setFortificationLiteners();
		
	}
	
	public void attackPhase()
	{
		this.phase = "attack";
		GameDriver.getInstance().changePhase();
	}
	
	public boolean equals(Object o){
		if(o instanceof Phase && ((Phase) o).phase == this.phase) {
			return true;
		}
		return false;
	}
	
}
