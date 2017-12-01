package risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import risk.model.Stats;
import risk.model.gamemode.Mode;
import risk.model.gamemode.SingleMode;
import risk.model.gamemode.TournamentMode;
import risk.view.ResultView;
import risk.view.SetUpDialog;
import risk.view.TournamentInfo;
import risk.view.mapeditor.MapFrame;

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
	private MainController() {}
	
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
		setupBox = new SetUpDialog();
		chooseMapEditorOrPlayGame();
		mapEditorListener();
		playGameListener();
	}

	/**
	 * Sets listener for Edit Map button.
	 */
	public void mapEditorListener() {
		mapEditListener =  new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MapFrame newMapFrame = new MapFrame();
				setupBox.chooseOptionFrame().dispose();
			}
		};
		this.setupBox.mapEditAction(mapEditListener);
	}
	
	/**
	 * Sets listener for Play Game button.
	 */
	public void playGameListener() {
		playGameListener =  new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				init();
				setupBox.chooseOptionFrame().dispose();
			}
		};
		this.setupBox.playGameAction(playGameListener);
	}
	
	/**
	 * Calls chooseMapEditorOrPlayGame() function of the SetUpDialog class to display Edit Map and Play Game options.
	 */
	public void chooseMapEditorOrPlayGame() {
		this.setupBox.chooseMapEditorOrPlayGame();
	}
	
	/**
	 * This method is responsible for taking input from user to whether user wants 
	 * to play tournament or single game, and accordingly create the tournament or single game object.
	 */
	private void init() {
		String mode = this.setupBox.gameMode();
		if(mode.equals("single")) {
			System.out.println("1");
			this.setupBox.loadSaveGameOption();
		}
		else if(mode.equals("tournament")){
			getTournamentInfo();
		}
		else {
			init();
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
				System.out.print("Hello");
				gameMode = new TournamentMode(infoView.getGamesCount(), infoView.getMapDetails(),
						infoView.getPlayerBehaviorDetails(), infoView.getMovesCount(), mC);
				gameMode.start();
				infoView.dispose();
			}
		});
	}

	/**
	* string array of winners passed to initialize the winners
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

	public void singleGameLoadInit(String saveFileRead) {
		gameMode = new SingleMode();
		((SingleMode) gameMode).loadGameDataFromFile(new File(saveFileRead));
	}
	
}
