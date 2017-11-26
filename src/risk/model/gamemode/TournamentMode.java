package risk.model.gamemode;

import risk.controller.GameController;
import risk.controller.MainController;

public class TournamentMode {
	
	/**
	 * Object reference to MainController object
	 */
	private MainController mController;
	
	/**
	 * Number of games to be played on each map;
	 */
	private int games = 0;
	
	/**
	 * Number of moves limit for every game.
	 */
	private int moveLimit = 0;
	
	/**
	 * List of players behaviors.
	 */
	private String[] behaviors;
	
	/**
	 * List of URLs of Maps 
	 */
	private String[] maps;
	
	/**
	 *  Constructor for TournamentMode class.
	 *  @param newController object of MainController class.
	 */
	public TournamentMode(MainController newController) {
		this.mController = newController;
	}

	public TournamentMode(int gamesCount, String[] mapDetails, String[] playerBehaviorDetails, int movesCount) {
		games = gamesCount;
		maps = mapDetails;
		behaviors = playerBehaviorDetails;
		moveLimit = movesCount;
	}
	
	public void start() {
		for(int m = 0; m < maps.length; m++) {
			for(int g = 0; g < games; g++) {
				GameController gController = new GameController(maps[m],behaviors,moveLimit);
			}
		}
	}
	
	
	
}
