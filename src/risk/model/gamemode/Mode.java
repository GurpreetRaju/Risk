package risk.model.gamemode;

/**
 * Mode interface for Single or Tournament mode.
 */
public interface Mode {
	
	/**
	 * Shows result on ResultView.
	 * @param winner Declares winner 
	 */
	public void updateResults(String winner);

	/**
	 * Starts the game.
	 */
	public void start();
	
}
