package risk.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import risk.model.GameDriver;
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
 *
 */
public class Controller {
	
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
	 * Stores object of DiceRollView class.
	 */
	private DiceRollView diceRollGUI;
	
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
	 * ActionListener to add listener to "Edit Map" button.
	 */
	private ActionListener mapEditListener;
	
	/**
	 * ActionListener to add listener to "Play Game" button.
	 */
	private ActionListener playGameListener;
	
	/**
	 * Empty constructor for object creation
	 */
	public Controller(){
		this(GameDriver.getInstance());
	}
	
	/**
	 * Controller class constructor to initialize GameDriver and SetUpDialog class objects.
	 * @param newDriver GameDriver instance.
	 */
	public Controller(GameDriver newDriver) {
		this.driver = newDriver;
		driver.setController(this);
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
	 * Gets the player name from the user (functionality in SetUpDialog class).
	 * @return a string array containing the names of players.
	 */
	public String[] getPlayerInfo() {
		return setupBox.getPlayerInfo();
	}
	
	/**
	 * Calls the placeArmyDialog function of SetUpDialog class.
	 * @param countriesNamesNoArmy list of countries with no armies. 
	 * @return the country selected by the user to place army.
	 */
	public String placeArmyDialog(String[] countriesNamesNoArmy) {
		return setupBox.placeArmyDialog(countriesNamesNoArmy);
	}
	
	/**
	 * Fetches the instance of GameDriver class.
	 * @return the GameDriver class instance.
	 */
	public GameDriver getGameDriver() {
		return this.driver;
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
	 * Calls chooseMapEditorOrPlayGame() function of the SetUpDialog class to display Edit Map and Play Game options.
	 */
	public void chooseMapEditorOrPlayGame() {
		this.setupBox.chooseMapEditorOrPlayGame();
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
	 * Initializes the game after Play Game button selection.
	 */
	public void init() {
		driver.createMapObject(setupBox.getMapInfo("map"));
		String temp = setupBox.getMapInfo("bmp");
	    if(temp!=null) {
	    		mapGUI = new MapView(temp);
	    }else {
	    		mapGUI = new MapView();
	    }
		playerInfoGUI = new PlayerInfoView();
        diceRollGUI = new DiceRollView();
        cardsGUI = new CardsView();
        controlsGUI = new ControlsView();
        phaseView = new PhaseView();
        dominationView = new WorldDominationView();
		this.driver.addObserver(phaseView);
		this.driver.addObserver(dominationView);
        MainView.createInstance(playerInfoGUI, mapGUI, diceRollGUI, cardsGUI, controlsGUI, phaseView, dominationView);
        driver.setPlayerView(playerInfoGUI);
		driver.setMapView(mapGUI);
		driver.setControlsView(controlsGUI);
		driver.runGame();
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
	 * @param max maximum vlaue user can select
	 * @param message message explaining the purpose of input
	 * @return a number selected by user
	 */
	public int setUpBoxInput(int min, int max, String message) {
		return setupBox.getInput(min, max,message);
	}

	public void removeAllControls() {
		controlsGUI.removeAll();
	}
	
}
