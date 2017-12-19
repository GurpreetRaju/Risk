package risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import risk.model.gamemode.Mode;
import risk.model.gamemode.SingleMode;
import risk.model.gamemode.TournamentMode;
import risk.view.ResultView;
import risk.view.SetUpDialog;
import risk.view.TournamentInfo;
import risk.view.mapeditor.MapFrame;


/**
 * Main controller for the game.
 */
public class MainController {
	
	/**
	 * setupBox variable used to store reference of class SetUpDialog
	 */
	private SetUpDialog setupBox;
	
	/**
	 * ActionListener to add listener to "Edit Map" button.
	 */
	private ActionListener mapEditListener;
	
	/**
	 * ActionListener to add listener to "Play Game" button.
	 */
	private ActionListener playGameListener;
	
	/**
	 * gameMode variable stores reference of class Mode
	 */
	private Mode gameMode;
	
	/**
	 * mController variable stores reference of class MainController
	 */
	private static MainController mController;
	
	/**
	 * private constructor for Singleton pattern imlementation
	 */
	private MainController() {
		setupBox = new SetUpDialog();
	}
	
	/**
	 * Get instance of MainController class
	 * @return initialize the instance of class MainController
	 */
	public static MainController getInstance() {
		if(mController==null) {
			mController = new MainController();
		}
		return mController;
	}
	
	/**
	 * Method to initialize setupBox and listeners.
	 */
	public void initialize() {
		String mode = this.setupBox.gameMode();
		if(mode.equals("single")) {
			this.setupBox.loadSaveGameOption();
		}
		else if(mode.equals("tournament")){
			getTournamentInfo();
		}
		else {
			initialize();
		}
	}
	
	/**
	* starting the game mode
	*/
	private void getTournamentInfo() {
		TournamentInfo infoView = new TournamentInfo();
		MainController mC = this;
		infoView.setListeners(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				gameMode = new TournamentMode(infoView.getGamesCount(), infoView.getMapDetails(),
						infoView.getPlayerBehaviorDetails(), infoView.getMovesCount(), mC);
				gameMode.start();
				infoView.dispose();
			}
		});
	}

	/**
	* string array of winners passed to initialize the winners
	* @param winners winners
	*/
	public void setResults(String[][] winners) {
		ResultView result = new ResultView(winners);
	}
	
	/**
	 * notify mode (TournamentMode/SingleMode) class about winner of game
	 * @param winnerPlayer the name of the winner or draw.
	 */
	public void notifyGameResult(String winnerPlayer) {
		if(gameMode!=null) {
			gameMode.updateResults(winnerPlayer);
		}
		else {
			System.out.print("Error here");
		}
	}
	
	/**
	* initialize mode of the game.
	* @param mode game mode to be set
	*/
	public void setMode(Mode mode) {
		this.gameMode = mode;
	}
	
	/**
	* @return game mode
	*/
	public Mode getMode(){
		return this.gameMode;
	}

	/**
	 * Initialize play game on New Game button click.
	 */
	public void singleGameInit() {
		String map = setupBox.getMapInfo("map");
		String bmp = setupBox.getMapInfo("bmp");
		String[][] players = setupBox.getPlayerInfo();
		if(bmp!=null) {
			gameMode = new SingleMode(map, bmp, players, 0, this);
		}else {
			gameMode = new SingleMode(map, players, 0, this);	
		}
		gameMode.start();
	}

	/**
	 * Function for Loading a saved game state.
	 * @param saveFileRead path of the saved game file.
	 */
	public void singleGameLoadInit(String saveFileRead) {
		gameMode = new SingleMode();
		((SingleMode) gameMode).loadGameDataFromFile(new File(saveFileRead));
	}
	
}
