package risk.model.turnmanager;

import risk.model.GameDriver;

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