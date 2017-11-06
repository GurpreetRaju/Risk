package risk.model.turnmanager;

import risk.model.GameDriver;
import risk.model.Player;

/**
 * This class manages the turn and its phases.
 * @author Gurpreet
 * @author Gunpreet
 * @version 1.2
 */
public class TurnManager {
	
	/**
	 * Stores the current phase name.
	 */
	private String phase;
	
	/**
	 * Empty constructor to create object for observer.
	 */
	public TurnManager(){
	}
	
	/**
	 * Constructor to set the phase name.
	 */
	public TurnManager(String string){
		this.setPhase(string);
	}
	
	public void startTurn(Player currentPlayer) {
		currentPlayer.reinforcementPhase();
	}
	
	/**
	 * Function to switch between different phases and notify observers.
	 */
	public void changePhase() {
		if(this.getPhase().equals("Reinforcement")) {
			this.setPhase("Attack");
			getCurrentPlayer().attackPhase();
		}
		else if(this.getPhase().equals("Attack")) {
			this.setPhase("Fortification");
			getCurrentPlayer().fortificationPhase();
		}
		else if(this.getPhase().equals("Fortification")) {
			GameDriver.getInstance().setNextPlayerTurn();
			this.setPhase("Reinforcement");
			getCurrentPlayer().reinforcementPhase();
		}
	}

	private Player getCurrentPlayer() {
		return GameDriver.getInstance().getCurrentPlayer();
	}
	

	/**
	 * Refreshes the phases.
	 * @param currentPlayer 
	 */
	public void continuePhase() {
		if(this.getPhase().equals("Reinforcement")) {
			getCurrentPlayer().reinforcementPhase();
		}
		else if(this.getPhase().equals("Attack")) {
			getCurrentPlayer().attackPhase();;
		}
		else if(this.getPhase().equals("Fortification")) {
			getCurrentPlayer().fortificationPhase();
		}
	}

	/**
	 * @return the phase
	 */
	public String getPhase() {
		return phase;
	}

	/**
	 * @param phase the phase to set
	 */
	public void setPhase(String phase) {
		this.phase = phase;
	}

}
