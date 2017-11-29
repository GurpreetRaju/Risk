package risk.model;

import risk.model.gamemode.Mode;

public class Stats {
	
	/**
	 * reference to Mode object 
	 */
	private Mode mode;
	
	/**
	 * 
	 */
	public Stats(Mode newMode) {
		mode = newMode;
	}
	
	public void notifyGameResult() {
		mode.updateResults();
	}
	
}
