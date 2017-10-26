package risk.model.turnmanager;

import risk.model.GameDriver;

public class TurnManager {
	
	/**
	 * Stores the current phase name.
	 */
	private String phase;
	/**
	 * Defines object for the Reinforcement phase.
	 */
	public static final TurnManager reinforcement = new TurnManager("Reinforcement");
	/**
	 * Defines object for the Attack phase.
	 */
	public static final TurnManager attack = new TurnManager("Attack");
	/**
	 * Defines object for the Fortification phase.
	 */
	public static final TurnManager fortification = new TurnManager("Fortification");
	
	private Phase reinforcementPhase;
	private Phase attackPhase;
	private Phase fortificationPhase;
	
	/**
	 * Constructor to set the phase name.
	 */
	public TurnManager(String string){
		this();
		this.phase = string;
	}
	
	public TurnManager(){
		reinforcementPhase = new Reinforcement();
		attackPhase = new Attack();
		fortificationPhase = new Fortification();
	}
	
	public void startTurn() {
		reinforcementPhase.run();
	}
	
	/**
	 * Function to switch between different phases.
	 */
	public void changePhase() {
		if(this.equals(TurnManager.reinforcement)) {
			this.phase = "Attack";
			attackPhase.run();
		}
		else if(this.equals(TurnManager.attack)) {
			this.phase = "Fortification";
			fortificationPhase.run();
		}
		else if(this.equals(TurnManager.fortification)) {
			GameDriver.getInstance().setNextPlayerTurn();
			this.phase = "Reinforcement";
			reinforcementPhase.run();
		}
		GameDriver.getInstance().updateMap();
	}
	

	/**
	 * Refreshes the phases.
	 */
	public void continuePhase() {
		if(this.equals(TurnManager.reinforcement)) {
			reinforcementPhase.run();
		}
		else if(this.equals(TurnManager.attack)) {
			attackPhase.run();
		}
		else if(this.equals(TurnManager.fortification)) {
			fortificationPhase.run();
		}
		GameDriver.getInstance().updateMap();
	}
	
	/**
	 * Compares the phase object called with the phase object calling for changePhase functionality.
	 */
	public boolean equals(Object o){
		if(o instanceof TurnManager && ((TurnManager) o).phase == this.phase) {
			return true;
		}
		return false;
	}

}
