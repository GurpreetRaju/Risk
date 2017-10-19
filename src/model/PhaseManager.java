package model;
/**
 * Calls the phases of the game: Reinforcement, Attack and Fortification.
 */
public class PhaseManager {

	/**
	 * Stores the current phase name.
	 */
	private String phase;
	/**
	 * Defines object for the Reinforcement phase.
	 */
	public static final PhaseManager reinforcement = new PhaseManager("Reinforcement");
	/**
	 * Defines object for the Attack phase.
	 */
	public static final PhaseManager attack = new PhaseManager("Attack");
	/**
	 * Defines object for the Fortification phase.
	 */
	public static final PhaseManager fortification = new PhaseManager("Fortification");
	
	private Phase reinforcementPhase;
	private Phase attackPhase;
	private Phase fortificationPhase;
	
	/**
	 * Constructor to set the phase name.
	 */
	public PhaseManager(String string){
		this.phase = string;
	}
	
	public PhaseManager(){
		reinforcementPhase = new Reinforcement();
		fortificationPhase = new Fortification();
	}
	
	public void startTurn() {
		reinforcementPhase.run();
	}
	
	/**
	 * Function to switch between different phases.
	 */
	public void changePhase() {
		if(this.equals(PhaseManager.reinforcement)) {
			this.phase = "Attack";
			attackPhase.run();
		}
		else if(this.equals(PhaseManager.attack)) {
			this.phase = "Fortification";
			fortificationPhase.run();
		}
		else if(this.equals(PhaseManager.fortification)) {
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
		if(this.equals(PhaseManager.reinforcement)) {
			reinforcementPhase.run();
		}
		else if(this.equals(PhaseManager.attack)) {
			attackPhase.run();
		}
		else if(this.equals(PhaseManager.fortification)) {
			fortificationPhase.run();
		}
		GameDriver.getInstance().updateMap();
	}
	
	/**
	 * Compares the phase object called with the phase object calling for changePhase functionality.
	 */
	public boolean equals(Object o){
		if(o instanceof PhaseManager && ((PhaseManager) o).phase == this.phase) {
			return true;
		}
		return false;
	}

}
