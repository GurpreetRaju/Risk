package risk.model.turnmanager;

import risk.model.GameDriver;
import risk.model.Player;

/**
 * This class manages the turn and its phases.
 * @author Gurpreet
 * @version 1.2
 */
public class TurnManager {
	
	/**
	 * Stores the current phase name.
	 */
	private String phase;
	
	/**
	 * Constructor to set the phase name.
	 */
	public TurnManager(String string){
		this.phase = string;
	}
	
	public void startTurn(Player currentPlayer) {
		currentPlayer.reinforcementPhase();
	}
	
	/**
	 * Function to switch between different phases.
	 */
	public void changePhase() {
		if(this.phase.equals("Reinforcement")) {
			this.phase = "Attack";
			getCurrentPlayer().attackPhase();
		}
		else if(this.phase.equals("Attack")) {
			this.phase = "Fortification";
			getCurrentPlayer().fortificationPhase();
		}
		else if(this.phase.equals("Fortification")) {
			GameDriver.getInstance().setNextPlayerTurn();
			this.phase = "Reinforcement";
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
		if(this.phase.equals("Reinforcement")) {
			getCurrentPlayer().reinforcementPhase();
		}
		else if(this.phase.equals("Attack")) {
			getCurrentPlayer().attackPhase();;
		}
		else if(this.phase.equals("Fortification")) {
			getCurrentPlayer().fortificationPhase();
		}
	}

}
