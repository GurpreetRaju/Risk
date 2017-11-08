package risk.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import risk.controller.Controller;
import risk.model.map.CountryNode;
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
	 * Pile of cards
	 */
	private ArrayList<Card> cards;
	
	/**
	 * Observer notification string.
	 */
	private String resultNotify;
	
	/**
	 * Constructor initialize the GUI and  map class object.
	 * Constructor is private so objects can not be created directly for this class.
	 */
	private GameDriver() {
		turnManager = new TurnManager("Reinforcement");
		cards = Card.generateCardPile();
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
	 * Set controller in GameDriver class.
	 */
	public void setController(Controller newController) {
		this.controller = newController;
	}
	
	/**
	 * Starts the game.
	 */
	public void runGame() {
		setChanged();
		notifyObservers("Startup");
		String[] newPlayerData = controller.getPlayerInfo();
		startUpPhase(newPlayerData);
		turnManager.startTurn(this.currentPlayer);
		setChanged();
		notifyObservers("Reinforcement");
	}
	
	/**
	 * This method starts the startup phase of game.
	 * It assigns countries to players.
	 */
	public void startUpPhase(String[] playerData) {
		players = new ArrayList<Player>();
		for(String newPlayer: playerData){
			Player temp = new Player(newPlayer,RiskData.InitialArmiesCount.getArmiesCount(playerData.length));
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
		
		int totalArmiesDiv = players.get(0).getArmiesCount();
		for(int i1=0;i1<totalArmiesDiv ;i1++){
			System.out.print("Armies divided"+players.get(0).getArmiesCount());
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
		setChanged();
		notifyObservers("Cards");
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

	/**
	 * This method call the shiftArmiesOnReinforcement method from player class, depending on the result returned by method
	 * either changes the Phase or continue with the current phase.
	 * @param countrySelected Country where armies should be placed
	 * @param armies number of armies to be placed
	 */
	public void shiftArmiesOnReinforcement(String countrySelected, int armies) {
		if(this.currentPlayer.shiftArmiesOnReinforcement(countrySelected, armies)==0) {
			changePhase();
		}
		else {
			continuePhase();
		}
	}
	
	/**
	 * This method get list of neighbor countries of the specified country owned by same player from map class
	 * and update the controls view through controller.
	 * @param countrySelected the country whose neighbors are to be listed
	 */
	public void fortificationNeighbourListUpdate(String countrySelected) {
		CountryNode countrySelect = this.currentPlayer.getCountry(countrySelected);
		if(countrySelect.getArmiesCount()>1) {
			ArrayList<String> neighborList = map.getPlayerNeighbourCountries(countrySelect,this.currentPlayer,true);
			controller.updateControlsFortification(countrySelect.getArmiesCount(), neighborList.toArray(new String[neighborList.size()])); 
		}
	}
	
	/**
	 * A delegate method to call getArmiesShiftedAfterFortification in Player class.
	 * @param newCountry country from where armies are to be moved
	 * @param newNeighbour country where armies are to be moved
	 * @param newArmies number of armies to be moved
	 */
	public void getArmiesShiftedAfterFortification(String newCountry, String newNeighbour, int newArmies) {
		this.currentPlayer.getArmiesShiftedAfterFortification(newCountry, newNeighbour, newArmies);
	}
	
	/**
	 * A delegate method to call setAttackListeners in Controller class
	 */
	public void setAttackListeners() {
		controller.setAttackListeners();
	}
	
	/**
	 * This method create a list of neighbour countries for a selected country whith different owners than the current player.
	 * Then update list on the controls view through controller.
	 * @param countrySelected selected country whose neighbors are required.
	 */
	public void attackNeighbourListUpdate(String countrySelected) {
		CountryNode countrySelect = this.currentPlayer.getCountry(countrySelected);
		if(countrySelect.getArmiesCount()>1) {
			ArrayList<String> neighborList = map.getPlayerNeighbourCountries(countrySelect,this.currentPlayer,false);
			controller.updateNeighborList(neighborList.toArray(new String[neighborList.size()]));
		}
	}
	
	/**
	 * This ethod announce the attack, get number of dice from both attacker and defender. If a country loose all its armies, the other player occupy the country.
	 * @param attackerCountry country attacking
	 * @param defenderCountry country defending against attack
	 */
	public void announceAttack(String attackerCountry, String defenderCountry) {
		this.resultNotify = "Attack Attacker Country: "+attackerCountry+"  Defender Country: "+defenderCountry+"  ";
		setChanged();
		notifyObservers(resultNotify);
		//Write code here to Announce attack on phase view
		CountryNode dCountry = map.getCountry(defenderCountry);
		Player defender = dCountry.getOwner();
		CountryNode aCountry = currentPlayer.getCountry(attackerCountry);
		//Show dialog boxes and get input from attacker and defender on how many dice to roll
		int aArmies = this.currentPlayer.selectDiceForAttack(attackerCountry);
		int dArmies = defender.selectDiceForAttack(defenderCountry);
		//Rolling dice for attacker and defender 
		ArrayList<Integer> aResults = diceRoll(aArmies);
		ArrayList<Integer> dResults = diceRoll(dArmies);
		String s = this.currentPlayer+" dice : ";
		for(int i : aResults) {
			s += i +" ";
		}
		s+= "<br>" + defender+" dice: ";
		for(int j : dResults) {
			s += j +" ";
		}
		resultNotify += "<br>" + s;
		System.out.println(resultNotify);
		setChanged();
		notifyObservers(resultNotify);
		battle(dCountry, defender, aCountry, aArmies, dArmies, aResults, dResults);
		setChanged();
		notifyObservers(resultNotify);
		//check if defender country has armies left
		if(dCountry.getArmiesCount()==0) {
			dCountry.setOwner(currentPlayer);
			turnManager.setWonCard(true);
			//phase view code to notify change in ownership of a country
			resultNotify += "<br>" + " Country "+ dCountry.getCountryName() +" won by " + dCountry.getOwner().getName() + ", new armies "+dCountry.getArmiesCount();
			setChanged();
			notifyObservers(resultNotify);
			System.out.println("Country "+ dCountry.getCountryName() +" won by " + dCountry.getOwner().getName() + ", new armies "+dCountry.getArmiesCount());
			//move counrties from attacker country to new acquired country
			int moveArmies = controller.setUpBoxInput(aArmies, aCountry.getArmiesCount()-1, "Select armies to move:");
			dCountry.addArmy(moveArmies);
			aCountry.removeArmies(moveArmies);
			if(map.continentWonByPlayer(currentPlayer, dCountry)) {
				currentPlayer.addContinent(dCountry.getContinent());
			}
		}
		map.updateMap();
		setPlayerOut(defender);
		checkGameState();
	}
	
	/**
	 * This method decides the result of battle between attacking country and defecding country and update the state of countries.
	 * @param dCountry country defending the attack
	 * @param defender player defending the attack
	 * @param aCountry attacking country
	 * @param aArmies number of dice rolled by attacker for battle
	 * @param dArmies number of dice rolled by defender
	 * @param aResults results of the dice rolled by attacker
	 * @param dResults results of dice rolled by defender
	 */
	public void battle(CountryNode dCountry, Player defender, CountryNode aCountry, int aArmies, int dArmies,ArrayList<Integer> aResults,ArrayList<Integer> dResults) {
		//Compare the results to decide battle result
		while(!aResults.isEmpty() && !dResults.isEmpty()) {
			int aMax = max(aResults);
			int dMax = max(dResults);
			if(aResults.get(aMax)>dResults.get(dMax)) {
				dCountry.removeArmy();
				resultNotify += "<br>" + " Winner Country: "+aCountry.getCountryName();
				//phase view code to show army removed from defender country
				System.out.println("Army removed from defender country, new armies "+dCountry.getArmiesCount());
			}
			else {
				aCountry.removeArmy();
				resultNotify += "<br>" + "Winner Country: "+dCountry.getCountryName();
				//phase view code to show army removed from attacker country
				System.out.println("Army removed from attacker country, new armies "+aCountry.getArmiesCount());
			}
			aResults.remove(aMax);
			dResults.remove(dMax);
		}
	}
	
	/**
	 * This method declares the game end if all the countries are owned by one player only.
	 * @param defenderPlayer A player recently defending country in a attack.
	 * @return true if game if over, false if there is atleast two players own atleat one country on map
	 */
	public boolean checkGameState() {
		//method to check if game is over
		for(Player p: players) {
			if(!p.equals(currentPlayer)) {
				System.out.println(p.getName()+ " " +p.getPlayerState());
				if(!p.getPlayerState()) {
					return false;
				}
			}
		}
		turnManager.setGameOver(true);
		return true;
	}
	/**
	 * set Player attribute lost true, if player has not country.
	 * @param defenderPlayer player to be set lost
	 */
	public void setPlayerOut(Player defenderPlayer) {
		//check if a player loose all the countries
		if(defenderPlayer.getCountries().isEmpty()) {
			defenderPlayer.setPlayerState(true);
		}
	}
	
	/**
	 * delegate method to call setUpBoxInput from controller class.
	 * @param min minimum value user can select 
	 * @param max maximum vlaue user can select
	 * @param message message explaining the purpose of input
	 * @return a number selected by user
	 */
	public int setUpBoxInput(int min, int max, String message) {
		return controller.setUpBoxInput(min, max, message);
	}
	
	/**
	 * Generate random values between 1 and 6 and add them to an arraylist.
	 * @param number of values to be generated.
	 * @return integer number that represents the value on the dice.
	 */
	public ArrayList<Integer> diceRoll(int n) {
		Random rand = new Random();
		ArrayList<Integer> diceResults = new ArrayList<Integer>();
		for(int i=0;i<n;i++) {
			diceResults.add(rand.nextInt(6) + 1);
		}		
		return diceResults;
	}
	
	/**
	 * This method return maxuimum value in a arraylist.
	 * @param array list from which max value to be searched
	 * @return index of maximum value in list
	 */
	public int max(ArrayList<Integer> array) {
        int n = array.size();
        int max = 0;
        for(int i=1;i<n;i++) {
			if(array.get(i)>array.get(max)) {
				max = i;
			}
		}
        return max;
    }
	
	/**
	 * This method returns the number of countries owned by current player.
	 * @return countries owned by current player
	 */
	public int getCurrentplayerCountryCount(){
		return getCurrentPlayer().getPlayerCountryCount();
	}
	
	/**
	 * 
	 * @return list of all players
	 */
	public ArrayList<Player> getPlayers(){
		return this.players;
	}

	/**
	 * Call Phase View to show game over
	 */
	public void announceGameOver() {
		notifyObservers("GameOver");
		controller.removeAllControls();
	}
	
	/**
	 * If a player wins a territory during a attack, at the end of the attack phase one card 
	 * is removed from pile and given to player.
	 */
	public void issueCard() {
		this.currentPlayer.addCard(cards.remove(0));
	}

	/**
	 * Set current player
	 * @param player1 player to be set as current player
	 */
	public void setCurrentPlayer(Player player1) {
		this.currentPlayer = player1;
	}

}
