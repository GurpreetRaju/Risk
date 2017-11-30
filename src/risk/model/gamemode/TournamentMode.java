package risk.model.gamemode;

import risk.controller.GameController;
import risk.controller.MainController;

public class TournamentMode implements Mode{
	
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
	 * List of players names.
	 */
	private String[] playerInfo;
	
	/**
	 * List of Winners
	 */
	private String[][] winners;
	
	/**
	 * Current map on which game is playing
	 */
	private int currentMap;
	
	/**
	 *  Current game number playing
	 */
	private int currentGame;
	
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
		winners = new String[mapDetails.length][gamesCount];
		currentMap = 0;
		currentGame = 1;
	}
	
	public void start() {
		GameController gController = new GameController(maps[currentMap],behaviors,behaviors,moveLimit);
	}
	
	

	public void updateResults(String winner) {
		winners[currentMap][currentGame] = winner;
		if(currentGame==games) {
			if(currentMap<(maps.length-1)) {
				currentMap++;
				currentGame = 1;
				start();
			}
			else {
				//Show results
			}
		}
		else {
			currentGame++;
		}
	}

}
