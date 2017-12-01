package risk.model.turnmanager;

import risk.model.gamemode.GameDriver;
import risk.model.player.Player;

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
	 * Shows if game is over
	 */
	private boolean gameOver = false;
	
	/**
	 * Variable stores the information if a player win a territory in attack phase.
	 * Value is true if player win atleast one territory and false if none.
	 */
	private boolean wonCard = false;
	
	/**
	 * Stores instance of GameDriver class.
	 */
	private GameDriver driver;
	
	/**
	 * Empty constructor to create object for observer.
	 * @param nDriver GameDriver instance.
	 */
	public TurnManager(GameDriver nDriver){
		driver = nDriver;
	}
	
	/**
	 * Constructor to set the phase name.
	 * @param string Phase name.
	 * @param nDriver GameDriver instance.
	 */
	public TurnManager(String string, GameDriver nDriver){
		this(nDriver);
		this.setPhase(string);
	}
	/**
	 * This method starts the turn from reinforcement phase
	 * @param currentPlayer player having first turn
	 */
	public void startTurn(Player currentPlayer) {
		currentPlayer.setArmies(this.getCurrentPlayer().getArmies());
		currentPlayer.reinforcementPhase();
	}
	
	/**
	 * Function to switch between different phases and notify observers. Also if a player win a territory during attack phase
	 * calls <code>issueCard()</code> method from <code>GameDriver</code>.
	 */
	public void changePhase() {
		if(!isGameOver()) {
			if(this.getPhase().equals("Reinforcement")) {
				this.setPhase("Attack");
				getCurrentPlayer().attackPhase();
			}
			else if(this.getPhase().equals("Attack")) {
				if(wonCard) {
					driver.issueCard();
					wonCard = false;
				}
				this.setPhase("Fortification");
				getCurrentPlayer().fortificationPhase();
			}
			else if(this.getPhase().equals("Fortification")) {
				driver.setNextPlayerTurn();
				if(!isGameOver()) {
					this.setPhase("Reinforcement");
					getCurrentPlayer().reinforcementPhase();
				}
			}
			else {
				driver.announceGameOver(getCurrentPlayer().getName());
			}
		}
	}
	
	/**
	 * Returns the current player object.
	 * @return current player.
	 */
	private Player getCurrentPlayer() {
		return driver.getCurrentPlayer();
	}

	/**
	 * Refreshes the phases.
	 */
	public void continuePhase() {
		if(!isGameOver()) {
			if(this.getPhase().equals("Reinforcement")) {
				getCurrentPlayer().reinforcementPhase();
			}
			else if(this.getPhase().equals("Attack")) {
				getCurrentPlayer().attackPhase();
			}
			else if(this.getPhase().equals("Fortification")) {
				getCurrentPlayer().fortificationPhase();
			}
		}
	}

	/**
	 * Get the current phase name.
	 * @return the phase
	 */
	public String getPhase() {
		return this.phase;
	}

	/**
	 * @param phase the phase to set
	 */
	public void setPhase(String phase) {
		this.phase = phase;
	}

	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @param gameOver the gameOver to set
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	/**
	 * @return value of wonCard
	 */
	public boolean isWonCard() {
		return wonCard;
	}

	/**
	 * @param wonCard the wonCard to set
	 */
	public void setWonCard(boolean wonCard) {
		this.wonCard = wonCard;
	}

}
