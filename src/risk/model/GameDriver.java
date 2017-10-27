package risk.model;

import java.util.ArrayList;
import risk.controller.Controller;
import risk.model.map.Map;
import risk.model.map.MapNode;
import risk.model.turnmanager.TurnManager;
import risk.view.*;

/**
 * This class controls the turns - Startup phase, Fortification, reinforcement and attack phase.
 * 
 * @author Gurpreet
 * @author Gunpreet
 * @author Amitt
 * @version 1.0
 */
public class GameDriver {
	
	/**
	 * Object of GameDriver class.
	 */
	private static GameDriver driver;
	
	/**
	 * Object of PlayerInfoView class.
	 */
	private PlayerInfoView playerInfoGUI;
	
	/**
	 * Object of Map class.
	 */
	private Map map;
	
	/**
	 * ArrayList to store elements of player type.
	 */
	private ArrayList<Player> players;
	
	/**
	 * Object of Controller class.
	 */
	private Controller controller;
	
	/**
	 * Object of ControlsView class.
	 */
	private ControlsView controlsGUI;
	
	/**
	 * Object of TurnManager class.
	 */
	private TurnManager turnManager;
	
	/**
	 * Object of Player class.
	 */
	private Player currentPlayer;
	
	/**
	 * ArrayList of neighbouring countries of same owner
	 */
	private ArrayList<CountryNode> playerNeighbourCountries;
	
	/**
	 * ArrayList of names of neighbouring countries of same owner
	 */
	private ArrayList<String> playerNeighbourCountryNames;
	
	/**
	 * Constructor initialize the GUI and  map class object.
	 * Constructor is private so objects can not be created directly for this class.
	 */
	private GameDriver() {
		controller = new Controller(this);
		turnManager = new TurnManager("Reinforcement"); 
	}
	
	/**
	 * <p>
	 * This method create <b>one and only one</b> instance of GameDriver class.
	 * This method is used to access only object of this class.
	 * </p>
	 * @return instance of GameDriver class.
	 */
	public static GameDriver getInstance() {
		if(driver==null){
			driver = new GameDriver();
		}
		return driver;
	}
	
	/**
	 * Starts the game.
	 */
	public void runGame() {
		startUpPhase();
		turnManager.startTurn(this.currentPlayer);
	}
	
	/**
	 * This method starts the startup phase of game.
	 * It assigns countries to players.
	 */
	public void startUpPhase() {
		String[] newPlayerData = controller.getPlayerInfo();
		players = new ArrayList<Player>();
		for(String newPlayer: newPlayerData){
			players.add(new Player(newPlayer,RiskData.InitialArmiesCount.getArmiesCount(newPlayerData.length),map.getMapData()));
		}
		players.get(0).setTurnTrue();
		this.currentPlayer = players.get(0);
		updatePlayerView();
		int i = 0;
		for(MapNode m : map.getMapData()){
			for(CountryNode c: m.getCountries()){
				c.setOwner(players.get(i));
				if(++i>=players.size()){
					i=0;
				}
			}
		}
		for(int i1=0;i1<players.get(0).getArmiesCount();i1++){
			for(Player p: players){
				String s;
				if(p.getCountriesNamesNoArmy().length!=0){
					s = controller.placeArmyDialog(p.getCountriesNamesNoArmy());
				}else{
					s= controller.placeArmyDialog(p.getCountriesNames());
				}
				p.getCountry(s).addArmy(1);
				p.removeArmies(1);
			}
		}
		updateMap();
	}

	/**
	 * Sets PlayerInfo view.
	 * @param newView PlayerInfoView object initialized.
	 */
	public void setPlayerView(PlayerInfoView newView) {
		this.playerInfoGUI = newView;
	}

	/**
	 * Sets Map view.
	 * @param newGui MapView object initialized.
	 */
	public void setMapView(MapView newGui) {
		map.addObserver(newGui);
	}

	/**
	 * Sets Controls view.
	 * @param controlView ControlsView object initialized.
	 */
	public void setControlsView(ControlsView controlView) {
		this.controlsGUI = controlView;
	}

	/**
	 * This method show players information on GUI.
	 */
	public void updatePlayerView() {
		String[] playerNames = new String[players.size()];
		int i=0;
		for(Player p: players){
			playerNames[i] = p.getName();
			i++;
		}
		playerInfoGUI.setPlayerInfo(playerNames);
	}

	/**
	 * Gets the player with the current turn.
	 * @return current player 
	 */
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	/**
	 * Sets the next player's turn.
	 */
	public void setNextPlayerTurn() {
		int currentPlayerIndex = players.indexOf(getCurrentPlayer());
		this.currentPlayer.setTurnFalse();
		if (currentPlayerIndex == players.size()-1){
			this.currentPlayer = players.get(0);
		}else{
			this.currentPlayer = players.get(currentPlayerIndex+1);
		}
		this.currentPlayer.setTurnTrue();
	}

	/**
	 * Creates the object of Map Class by passing the map file path.
	 * @param mapPath stores the path of the map file.
	 */
	public void createMapObject(String mapPath) {
		map = new Map(mapPath);
	}
	
	/**
	 * Gives the list of the neighbors of the country passed as a parameter.
	 * @param countryname Name of the country.
	 * @return Neighbors of the country.
	 */
	public String [] getNeighbourCountryNames(String countryname) {
		for(CountryNode country: getCurrentPlayer().getCountries()){
			if(country.getCountryName().equals(countryname)){
				return country.getNeighbourCountriesString();
			}
		}
		return null;
	}

	/**
	 * Gets the army count of the current player.
	 * @return army count of the current player.
	 */
	public int getPlayerArmies() {
		return getCurrentPlayer().getArmiesCount();
	}

	/**
	 * Gives the countries owned by a player.
	 * @return The list of country nodes.
	 */
	public ArrayList<CountryNode> getPlayerCountries() {
		return getCurrentPlayer().getCountries();
	}

	/**
	 * Gives the neighbors of a particular country.
	 * @param countrynode Country whose neighbors are to be fetched.
	 * @return list of neighbor countries.
	 */
	public CountryNode [] getNeighbourCountries(CountryNode countrynode) {
		for(CountryNode country: getCurrentPlayer().getCountries()){
			if(country.getCountryName().equals(countrynode.getCountryName())){
				return country.getNeighbourCountries();
			}
		}
		return null;
	}
	
	/**
	 * Gives the country node of the given country name.
	 * @param countryname name of a country
	 * @return country node for the given country name
	 */
	public CountryNode getCountry(String countryname) {
		for(CountryNode country : getCurrentPlayer().getCountries()){
			if(country.getCountryName().equals(countryname)){
				return country;
			}
		}
		return null;
	}
	
	/**
	 * Sets action listener for reinforcement phase.
	 */
	public void setControlsActionListeners() {
		this.controller.setActionListner();
	}
	
	/**
	 * Gives the instance of ControlsView class.
	 * @return ControlsView class object.
	 */
	public ControlsView getControlGUI() {
		return this.controlsGUI;
	}

	/**
	 * Delegate method to call method from TurnManager class to continue phases.
	 */
	public void continuePhase() {
		turnManager.continuePhase();
		updateMap();
	}
	
	/**
	 * Delegate method to call method from TurnManager class to change between phases.
	 */
	public void changePhase() {
		turnManager.changePhase();
		updateMap();
	}
	
	/**
	 * Delegate method to call updateMap method from map class.
	 */
	public void updateMap() {
		map.updateMap();
	}
	
	/**
	 * Adds listener for fortification phase.
	 */
	public void setFortificationLiteners() {
		this.controller.setFortificationListeners();
	}
	
	/**
	 * Returns object of Map class
	 *  @return map 
	 */
	public Map getMap(){
		return this.map;
	}
	
	/**
	 * return Arraylist of neighbouring countries owned by same player
	 * @param countrynode Country node whose neighbors are required.
	 * @return playerNeighbouringCountries returns neighbouring countries of the country of same owner
	 */
	public ArrayList<CountryNode> getPlayerNeighbourCountries(CountryNode countrynode){
		playerNeighbourCountries = new ArrayList<CountryNode>();
		for (CountryNode country : getNeighbourCountries(countrynode)){
			if (countrynode.getOwner().equals(country.getOwner())){
				playerNeighbourCountries.add(country);
			}
		}
		return playerNeighbourCountries;
	}
	
	/**
	 * Gives the list of the neighbors of the country passed as a parameter with same owner.
	 * @param countryname Name of the country.
	 * @return Neighbors of the country with same owner.
	 */
	public String [] getPlayerNeighbourCountryNames(String countryname) {
		playerNeighbourCountryNames = new ArrayList<String>();
		for(CountryNode country: getPlayerNeighbourCountries(getCountry(countryname))){
			playerNeighbourCountryNames.add(country.getCountryName());
		}
		return (String[]) playerNeighbourCountryNames.toArray();
	}
	
	/**
	 * Adds the new player to the arraylist of players.
	 * @param newPlayer Player object.
	 */
	public void setPlayerList(Player newPlayer){
		if(this.players==null) {
			this.players = new ArrayList<Player>();
		}
		this.players.add(newPlayer);
	}
}
