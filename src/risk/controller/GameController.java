package risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import risk.model.gamemode.GameDriver;
import risk.model.util.GameLogger;
import risk.view.CardsView;
import risk.view.ControlsView;
import risk.view.DiceRollView;
import risk.view.MainView;
import risk.view.MapView;
import risk.view.PhaseView;
import risk.view.PlayerInfoView;
import risk.view.SetUpDialog;
import risk.view.WorldDominationView;
import risk.view.mapeditor.MapFrame;

/**
 * MVC - Controller that controls the interaction between models and view.
 * @author Gunpreet
 * @author Gurpreet
 * @author Amitt
 */
public class GameController {
	
	/**
	 * Stores instance of GameDriver class.
	 */
	private GameDriver driver;

	/**
	 * PhaseView object.
	 */
	private PhaseView phaseView;
	
	/**
	 * World Domiantion object 
	 */
	private WorldDominationView dominationView;
	
	/**
	 * Stores object of CardsView class.
	 */
	private CardsView cardsGUI;
	
	/**
	 * Stores object of ControlsView class.
	 */
	private ControlsView controlsGUI;
	
	/**
	 * Stores object of MapView class.
	 */
	private MapView mapGUI;
	
	/**
	 * Stores object of PlayerInfoView class.
	 */
	private PlayerInfoView playerInfoGUI;
	
	/**
	 * Stores object of SetUpDialog class.
	 */
	private SetUpDialog setupBox;
	
	/**
	 * ActionListener to add listener to "Add Armies" button.
	 */
	private ActionListener addArmiesListner;
	
	/**
	 * Stores object of GameLogger
	 */
	private GameLogger gameLogger;
	
	/**
	 * Constructor for object creation
	 * @param newSetupBox SetUpDialog object
	 */
	public GameController(SetUpDialog newSetupBox){
		this(new GameDriver(), newSetupBox);
	}
	
	/**
	 * Controller class constructor to initialize GameDriver and SetUpDialog class objects.
	 * @param newDriver GameDriver instance.
	 * @param newSetupBox SetUpDialog object
	 */
	public GameController(GameDriver newDriver, SetUpDialog newSetupBox) {
		this.setupBox = newSetupBox;
		this.driver = newDriver;
		driver.setController(this);
		init();
	}
	
	/**
	 * Controller class constructor to initialize GameDriver and SetUpDialog class objects.
	 * @param newDriver GameDriver instance.
	 * @param newSetupBox SetUpDialog object
	 */
	public GameController(String newMap, String newMapImage, String[] playerNames, String[] behaviors, int moveLimit) {
		mapGUI = new MapView(newMapImage);
		setupBox = new SetUpDialog();
		driver = new GameDriver(newMap, moveLimit);
		driver.setController(this);
		playerInfoGUI = new PlayerInfoView();
		playerInfoGUI.setPlayerInfo(playerNames);
		init();
		driver.runGame(playerNames, behaviors);
	}
	
	/**
	 * Controller class constructor to initialize GameDriver and SetUpDialog class objects.
	 * @param newDriver GameDriver instance.
	 * @param newSetupBox SetUpDialog object
	 */
	public GameController(String newMap, String[] playerNames, String[] behaviors, int moveLimit) {
		mapGUI = new MapView();
		setupBox = new SetUpDialog();
		driver = new GameDriver(newMap, moveLimit);
		driver.setController(this);
		playerInfoGUI = new PlayerInfoView();
		playerInfoGUI.setPlayerInfo(playerNames);
		init();
		driver.runGame(playerNames, behaviors);
	}
	
	/**
	 * Initializes the game after Play Game button selection.
	 */
	public void init() {
		/*Initialize all the views for the main window and run game.*/
        cardsGUI = new CardsView();
        controlsGUI = new ControlsView();
        phaseView = new PhaseView();
        dominationView = new WorldDominationView();
        gameLogger = new GameLogger();
        MainView.createInstance(playerInfoGUI, mapGUI, controlsGUI, phaseView, dominationView);
		driver.addObserver(mapGUI);
		driver.addObserver(phaseView);
		driver.addObserver(dominationView);
		driver.addObserver(cardsGUI);
		driver.addObserver(gameLogger);
	}
	
	/**
	 * Calls the placeArmyDialog function of SetUpDialog class.
	 * @param countriesNamesNoArmy list of countries with no armies. 
	 * @return the country selected by the user to place army.
	 */
	public String placeArmyDialog(String[] countriesNamesNoArmy, String message) {
		return setupBox.placeArmyDialog(countriesNamesNoArmy, message);
	}
	
	/**
	 * Sets Action Listeners for reinforcement controls.
	 */
	public void setActionListner() {
		addArmiesListner = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int armies = controlsGUI.getArmiesValue();
				driver.shiftArmiesOnReinforcement(controlsGUI.getCountrySelected(), armies);
			}
		};
		controlsGUI.addArmiesButtonAction(this.addArmiesListner);
	}
	
	/**
	 * Sets Action Listeners for fortification controls.
	 */
	public void setFortificationListeners() {
		controlsGUI.countrieslistAction(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				String countrySelected = (String) controlsGUI.getCountrySelected();
				driver.fortificationNeighbourListUpdate(countrySelected);
			}
		});
		
		controlsGUI.playButtonAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(controlsGUI.isNeighbourSelected()) {
					driver.getArmiesShiftedAfterFortification(controlsGUI.getCountrySelected(), 
							controlsGUI.getNeighborSelected(), controlsGUI.getArmiesValue());
				}
				driver.changePhase();
			}
		});
	}
	
	/**
	 * This method updates the neighbor list combobox in controlsview
	 * @param newArmies number of armies user can move
	 * @param newNeighbourList list of neighbor counties
	 */
	public void updateControlsFortification(int newArmies, String[] newNeighbourList) {
		controlsGUI.updateFortification(newArmies, newNeighbourList);
	}
	
	/**
	 * Method set the listeners to components for attack phase in controls view
	 */
	public void setAttackListeners() {
		controlsGUI.countrieslistAction(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				String countrySelected = (String) controlsGUI.getCountrySelected();
				driver.attackNeighbourListUpdate(countrySelected);
			}
		});
		
		controlsGUI.playButtonAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(controlsGUI.isNeighbourSelected()) {
					driver.announceAttack(controlsGUI.getCountrySelected(),controlsGUI.getNeighborSelected());
				}
			}
		});
		
		controlsGUI.endPhaseAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				driver.changePhase();
			}
		});
	}
	
	/**
	 * Update list of neighbors for combobox in controls view
	 * @param neighbourList list of neighbor countries
	 */
	public void updateNeighborList(String[] neighbourList) {
		controlsGUI.setNeighborList(neighbourList);
	}
	
	/**
	 * delegate method to call getInput from SetUpDialog class. 
	 * @see SetUpDialog
	 * @param min minimum value user can select 
	 * @param max maximum value user can select
	 * @param message message explaining the purpose of input
	 * @return a number selected by user
	 */
	public int setUpBoxInput(int min, int max, String message) {
		return setupBox.getInput(min, max,message);
	}

	/**
	 * Removes all controls when Game is Over.
	 */
	public void removeAllControls() {
		controlsGUI.removeAll();
	}

	public void setReinforcementControls(int armies, String[] countryList) {
		controlsGUI.reinforcementControls(armies, countryList);
	}

	public void setAttackControls(String[] array) {
		controlsGUI.attackControls(array);
	}

	public void setFortificationControls(String[] array) {
		controlsGUI.fortificationControls(array);		
	}
	
}
