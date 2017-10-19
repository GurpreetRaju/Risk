package model;

/**
 * Generalization of the phases of the game: Reinforcement, Attack and Fortification.
 */
public abstract class Phase {
	
	public void run() {
		this.setControls();
	}

	public abstract void playMove();

	public abstract void setControls();
}
