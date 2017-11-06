package risk.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

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
public class GameDriver extends Observable {
	
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
		setChanged();
		notifyObservers("Startup");
		startUpPhase();
		setChanged();
		notifyObservers("Reinforcement");
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
			Player temp = new Player(newPlayer,RiskData.InitialArmiesCount.getArmiesCount(newPlayerData.length),map.getMapData());
			players.add(temp);
			setChanged();
			notifyObservers(temp.getName());
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
		this.getCurrentPlayer().setArmies(this.getCurrentPlayer().getArmies());
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
		return this.currentPlayer.getCountry(countryname);
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
		setChanged();
		notifyObservers(turnManager.getPhase());
	}
	
	/**
	 * Delegate method to call method from TurnManager class to change between phases.
	 */
	public void changePhase() {
		turnManager.changePhase();
		updateMap();
		setChanged();
		notifyObservers(turnManager.getPhase());
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
	 * Adds the new player to the arraylist of players.
	 * @param newPlayer Player object.
	 */
	public void setPlayerList(Player newPlayer){
		if(this.players==null) {
			this.players = new ArrayList<Player>();
		}
		this.players.add(newPlayer);
	}

	public void shiftArmiesOnReinforcement(String countrySelected, int armies) {
		if(this.currentPlayer.shiftArmiesOnReinforcement(countrySelected, armies)==0) {
			changePhase();
		}
		else {
			continuePhase();
		}
	}

	public void fortificationNeighbourListUpdate(String countrySelected) {
		CountryNode countrySelect = this.currentPlayer.getCountry(countrySelected);
		if(countrySelect.getArmiesCount()>1) {
			ArrayList<String> neighborList = map.getPlayerNeighbourCountries(countrySelect,this.currentPlayer,true);
			controller.updateControlsFortification(countrySelect.getArmiesCount(), neighborList.toArray(new String[neighborList.size()])); 
		}
	}

	public void getArmiesShiftedAfterFortification(String newCountry, String newNeighbour, int newArmies) {
		this.currentPlayer.getArmiesShiftedAfterFortification(newCountry, newNeighbour, newArmies);
	}

	public void setAttackListeners() {
		controller.setAttackListeners();
	}

	public void attackNeighbourListUpdate(String countrySelected) {
		CountryNode countrySelect = this.currentPlayer.getCountry(countrySelected);
		if(countrySelect.getArmiesCount()>1) {
			ArrayList<String> neighborList = map.getPlayerNeighbourCountries(countrySelect,this.currentPlayer,false);
			controller.updateNeighborList(neighborList.toArray(new String[neighborList.size()]));
		}
	}
	
	public void announceAttack(String attackerCountry, String defenderCountry) {
		//Write code here to Announce attack on phase view
		CountryNode dCountry = map.getCountry(defenderCountry);
		Player defender = dCountry.getOwner();
		CountryNode aCountry = currentPlayer.getCountry(attackerCountry);
		//Show dialog boxes and get input from attacker and defender on how many dice to roll
		int aArmies = this.currentPlayer.selectDiceForAttack(attackerCountry);
		int dArmies = defender.selectDiceForAttack(defenderCountry);
		//Rolling dice for attacker and defender 
		battle(dCountry, defender, aCountry, aArmies, dArmies);
		map.updateMap();
		checkGameState(defender);
	}

	private void battle(CountryNode dCountry, Player defender, CountryNode aCountry, int aArmies, int dArmies) {
		ArrayList<Integer> aResults = diceRoll(aArmies);
		ArrayList<Integer> dResults = diceRoll(dArmies);
		//Compare the results to decide battle result
		while(!aResults.isEmpty() && !dResults.isEmpty()) {
			int aMax = max(aResults);
			int dMax = max(dResults);
			if(aResults.get(aMax)>dResults.get(dMax)) {
				dCountry.removeArmy();
				//phase view code to show army removed from defender country
				System.out.println("Army removed from defender country, new armies "+dCountry.getArmiesCount());
			}
			else {
				aCountry.removeArmy();
				//phase view code to show army removed from attacker country
				System.out.println("Army removed from attacker country, new armies "+aCountry.getArmiesCount());
			}
			aResults.remove(aMax);
			dResults.remove(dMax);
		}
		//check if attacker country has armies left
		//????after battle movement of countries from winner's country to new country owned
		if(aCountry.getArmiesCount()==0) {
			aCountry.setOwner(defender);
			//phase view code to notify change in ownership of a country
			System.out.println("Country "+ aCountry.getCountryName() +" won by " + aCountry.getOwner().getName() + ", new armies "+aCountry.getArmiesCount());
			if(map.continentWonByPlayer(defender, aCountry)) {
				defender.addContinent(aCountry.getContinent());
			}				
		}
		//check if defender country has armies left
		if(dCountry.getArmiesCount()==0) {
			dCountry.setOwner(currentPlayer);
			//phase view code to notify change in ownership of a country
			System.out.println("Country "+ dCountry.getCountryName() +" won by " + dCountry.getOwner().getName() + ", new armies "+dCountry.getArmiesCount());
			if(map.continentWonByPlayer(currentPlayer, dCountry)) {
				currentPlayer.addContinent(dCountry.getContinent());
			}
		}
	}

	private void checkGameState(Player defenderPlayer) {
		//check if a player loose all the countries
		if(defenderPlayer.getCountries().isEmpty()) {
			defenderPlayer.setPlayerState(true);
		}
		//method to check if game is over
		for(Player p: players) {
			if(p!=currentPlayer && !p.getPlayerState()) {
				turnManager.setGameOver(true);
				break;
			}
		}
	}

	public int setUpBoxInput(int min, int max, String message) {
		return controller.setUpBoxInput(min, max, message);
	}
	
	/**
	 * Generate random values between 1 and 6 and add them to an arraylist.
	 * @param number of values to be generated.
	 * @return integer number that represents the value on the dice.
	 */
	private ArrayList<Integer> diceRoll(int n) {
		Random rand = new Random();
		ArrayList<Integer> diceResults = new ArrayList<Integer>();
		for(int i=0;i<n;i++) {
			diceResults.add(rand.nextInt(6) + 1);
		}		
		return diceResults;
	}
	
	private int max(ArrayList<Integer> array) {
        int n = array.size();
        int max = 0;
        for(int i=1;i<n;i++) {
			if(array.get(i)>array.get(max)) {
				max = i;
			}
		}
        return max;
    }
	
	public int getCurrentplayerCountryCount(){
		return getCurrentPlayer().getPlayerCountryCount();
	}
	
	public ArrayList<Player> getPlayers(){
		return this.players;
	}

	/**
	 * Call Phase View to show game over
	 */
	public void announceGameOver() {
		notifyObservers("GameOver");
	}

}
