package model;

import java.util.ArrayList;
import controller.Controller;
import view.*;

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
	 * Object of Phase class.
	 */
	private Phase currentPhase;
	
	/**
	 * Object of Player class.
	 */
	private Player currentPlayer;
	
	/**
	 * ArrayList of neighbouring countries of same owner
	 */
	private ArrayList<CountryNode> playerNieghbourCountries;
	
	/**
	 * ArrayList of names of neighbouring countries of same owner
	 */
	private ArrayList<String> playerNieghbourCountryNames;
	
	/**
	 * Constructor initialize the GUI and  map class object.
	 * Constructor is private so objects can not be created directly for this class.
	 */
	private GameDriver() {
		controller = new Controller(this);
		currentPhase = new Phase("reinforcement"); 
		this.chooseMapEditorOrPlayGame();
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
		currentPhase.reinforcementPhase();
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
			}
		}
		map.updateMap();
	}

	/**
	 * Sets PlayerInfo view.
	 */
	public void setPlayerView(PlayerInfoView newView) {
		this.playerInfoGUI = newView;
	}

	/**
	 * Sets Map view.
	 */
	public void setMapView(MapView newGui) {
		map.addObserver(newGui);
	}

	/**
	 * Sets Controls view.
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
		for(Player player:players){
			if(player.getTurn()){
				return player;
			}
		}
		return null;
	}

	/**
	 * Sets the next player's turn.
	 * @return current player 
	 */
	public void setNextPlayerTurn() {
		int currentPlayerIndex = players.indexOf(getCurrentPlayer());
		getCurrentPlayer().setTurnFalse();
		if (currentPlayerIndex == players.size()-1){
			players.get(0).setTurnTrue();
		}else{
			players.get(currentPlayerIndex+1).setTurnTrue();
		}
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
	 * Gets the player with the current turn.
	 * @return current player 
	 */
	public String [] getPlayerCountryNames() {
		return getCurrentPlayer().getCountriesNames();
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
	 * Function to switch between different phases.
	 */
	public void changePhase() {
		if(this.currentPhase.equals(Phase.reinforcement)){
			currentPhase.attackPhase();
		}else if(this.currentPhase.equals(Phase.attack)){
			currentPhase.fortificationPhase();
		}else if(this.currentPhase.equals(Phase.fortification)){
			this.setNextPlayerTurn();
			currentPhase.reinforcementPhase();
		}
		map.updateMap();
	}
	
	/**
	 * Refreshes the phases.
	 */
	public void continuePhase() {
		if(this.currentPhase.equals(Phase.reinforcement)){
			currentPhase.reinforcementPhase();;
		}else if(this.currentPhase.equals(Phase.attack)){
			currentPhase.attackPhase();
		}else if(this.currentPhase.equals(Phase.fortification)){
			currentPhase.fortificationPhase();
		}
		map.updateMap();
	}
	
	/**
	 * Adds listener for fortification phase.
	 */
	public void setFortificationLiteners() {
		this.controller.setFortificationListeners();
	}

	/**
	 * Adds listener to the "Edit Map" and "Play Game" buttons at the start.
	 */
	public void chooseMapEditorOrPlayGame() {
		this.controller.chooseMapEditorOrPlayGame();
		this.controller.mapEditorListener();
		this.controller.playGameListener();
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
	 * @return playerNeighbouringCountries returns neighbouring countries of the country of same owner
	 */
	public ArrayList<CountryNode> getPlayerNeighbourCountries(CountryNode countrynode){
		playerNieghbourCountries = new ArrayList<CountryNode>();
		for (CountryNode country : getNeighbourCountries(countrynode)){
			if (countrynode.getOwner().equals(country.getOwner())){
				playerNieghbourCountries.add(country);
			}
		}
		return playerNieghbourCountries;
	}
	
	/**
	 * Gives the list of the neighbors of the country passed as a parameter with same owner.
	 * @param countryname Name of the country.
	 * @return Neighbors of the country with same owner.
	 */
	public String [] getPlayerNeighbourCountryNames(String countryname) {
		playerNieghbourCountryNames = new ArrayList<String>();
		for(CountryNode country: getPlayerNeighbourCountries(getCountry(countryname))){
			playerNieghbourCountryNames.add(country.getCountryName());
		}
		return (String[]) playerNieghbourCountryNames.toArray();
	}
	
	/**
	 * Adds the new player to the arraylist of players.
	 */
	public void setPlayerList(Player newPlayer){
		this.players = new ArrayList<Player>();
		this.players.add(newPlayer);
	}
}
