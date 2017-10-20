package model;

/**
 * Calls the phases of the game: Reinforcement, Attack and Fortification.
 */
public class Phase {
	
	/**
	 * Stores the current phase name.
	 */
	private String phase;
	
	/**
	 * Defines object for the Reinforcement phase.
	 */
	public static final Phase reinforcement = new Phase("reinforcement");
	
	/**
	 * Defines object for the Attack phase.
	 */
	public static final Phase attack = new Phase("attack");
	
	/**
	 * Defines object for the Fortification phase.
	 */
	public static final Phase fortification = new Phase("fortification");
	
	/**
	 * Constructor to set the phase name.
	 */
	public Phase(String string){
		this.phase = string;
	}
	
	/**
	 * Calls reinforcement phase.
	 */
	public void reinforcementPhase() {
		this.phase = "reinforcement";
		GameDriver.getInstance().getControlGUI().reinforcementConrols(GameDriver.getInstance().getPlayerArmies(), GameDriver.getInstance().getPlayerCountryNames());
		GameDriver.getInstance().setControlsActionListeners();
	}
	
	/**
	 * Calls fortification phase.
	 */
	public void fortificationPhase() {
		this.phase = "fortification";
		GameDriver.getInstance().getControlGUI().fortificationControls(GameDriver.getInstance().getPlayerCountryNames());
		GameDriver.getInstance().setFortificationLiteners();
		
	}
	
	/**
	 * Calls attack phase.
	 */
	public void attackPhase() {
		this.phase = "attack";
		GameDriver.getInstance().changePhase();
	}
	
	/**
	 * Compares the phase object called with the phase object calling for changePhase functionality.
	 */
	public boolean equals(Object o){
		if(o instanceof Phase && ((Phase) o).phase == this.phase) {
			return true;
		}
		return false;
	}
}
