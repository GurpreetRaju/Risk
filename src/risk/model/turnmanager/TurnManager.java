package risk.model.turnmanager;

import java.util.Observable;

import risk.model.GameDriver;
import risk.model.Player;

/**
 * This class manages the turn and its phases.
 * @author Gurpreet
 * @author Gunpreet
 * @version 1.2
 */
public class TurnManager extends Observable{
	
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
		this.phase = string;
	}
	
	public void startTurn(Player currentPlayer) {
		currentPlayer.reinforcementPhase();
		setChanged();
		notifyObservers("Reinforcement");
	}
	
	/**
	 * Function to switch between different phases and notify observers.
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
		setChanged();
		notifyObservers(this.phase);
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
		setChanged();
		notifyObservers(this.phase);
	}

}
