package risk.model.gamemode;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import risk.controller.GameController;
import risk.controller.MainController;
import risk.model.Card;
import risk.model.RiskData;
import risk.model.Stats;
import risk.model.map.CountryNode;
import risk.model.map.Map;
import risk.model.map.MapNode;
import risk.model.player.AggressiveStrategy;
import risk.model.player.BenevolentStrategy;
import risk.model.player.CheaterStrategy;
import risk.model.player.HumanStrategy;
import risk.model.player.Player;
import risk.model.player.PlayerStrategy;
import risk.model.player.RandomStrategy;
import risk.model.turnmanager.TurnManager;

/**
 * This class controls the turns - Startup phase, Fortification, reinforcement and attack phase.
 * @author Gurpreet
 * @author Gunpreet
 * @author Amitt
 * @version 1.0
 */
public class GameDriver extends Observable {
	
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
	private GameController controller;
		
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
	 * Number of limits for game
	 */
	private int moveLimit = 0;
	
	/**
	 * Counts the number of moves
	 */
	private int moveCounter = 0;
	
	/**
	 * Constructor initialize the GUI and  map class object.
	 * Constructor is private so objects can not be created directly for this class.
	 * @param newMoveLimit Number of moves limited to game
	 * @param newMap url of map game to be played on
	 */
	public GameDriver(String newMap, int newMoveLimit) {
		this();
		moveLimit = newMoveLimit;
		map = new Map(newMap);
	}
	
	/**
	 * Constructor initialize the GUI and  map class object.
	 * Constructor is private so objects can not be created directly for this class.
	 */
	public GameDriver() {
		turnManager = new TurnManager("Reinforcement", this);
		cards = Card.generateCardPile();
	}

	/**
	 * Set controller in GameDriver class.
	 * @param newController Used to set the Controller object.
	 */
	public void setController(GameController newController) {
		this.controller = newController;
	}
	
	/**
	 * Starts the game.
	 * @param newPlayerData String array to store elements of player name and type.
	 */
	public void runGame(String[][] playerData) {
		nottifyObservers("Startup");
		createPlayers(playerData);
		startUpPhase();
		turnManager.startTurn(this.currentPlayer);
	}
	
	/**
	 * Create player objects
	 * @param playerData name of players
	 * @param behaviors behavior of players
	 */
	public void createPlayers(String[][] playerData) {
		players = new ArrayList<Player>();
		for(int i=0; i < playerData.length; i++){
			Player temp = new Player(playerData[i][0],RiskData.InitialArmiesCount.getArmiesCount(playerData.length), this);
			temp.setStrategy(createBehavior(playerData[i][1]));
			players.add(temp);
			nottifyObservers(temp.getName());
		}
	}
	
	/**
	 * Create PlayerStartegy object from string
	 * @param strategy strategy for which object is required
	 * @return object of PlayerStrategy
	 */
	private PlayerStrategy createBehavior(String strategy) {
			PlayerStrategy pStrategy = null;
			if(strategy.equals("human")){
				pStrategy = new HumanStrategy(this);
			}
			else if(strategy.equals("benevolent")) {
				pStrategy = new BenevolentStrategy(this);
			}
			else if(strategy.equals("aggressive")){
				pStrategy = new AggressiveStrategy(this);
			}
			else if(strategy.equals("cheater")) {
				pStrategy = new CheaterStrategy(this);
			}
			else if(strategy.equals("random")) {
				pStrategy = new RandomStrategy(this);
			}
			return pStrategy;
	}

	/**
	 * This method starts the startup phase of game. It assigns countries to players.
	 * @param playerData String array to store elements of player type.
	 */
	public void startUpPhase() {

		System.out.print("checkpoint 1");
		dividingCountries(map.getMapData());
		System.out.print("checkpoint 2");
		updatePlayerView();

		System.out.print("checkpoint 3");
		/*Distribute armies to countries as per player's choice.*/
		int totalArmiesDiv = players.get(0).getArmiesCount();
		for(int i1=0;i1<totalArmiesDiv ;i1++){
			System.out.print("Armies divided"+players.get(0).getArmiesCount());
			for(Player p: players){
				String s = p.placeArmyOnStartUp();
				p.getCountry(s).addArmy(1);
				p.removeArmies(1);
			}
		}

		System.out.print("checkpoint 4");
		updateMap();
	}
	
	/**
	 * This method create player objects and divide countries among them.
	 * @param playerData list of players
	 * @param mapData arraylist containing MapNode Objects representing continents
	 */
	public void dividingCountries(ArrayList<MapNode> mapData) {
		players.get(0).setTurnTrue();
		this.currentPlayer = players.get(0);
		int i = 0;
		/*Random distribution of countries among the players.*/
		for(MapNode m : mapData){
			for(CountryNode c: m.getCountries()){
				c.setOwner(players.get(i));
				if(++i>=players.size()){
					i=0;
				}
			}
		}
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
		nottifyObservers("Cards");
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
	 * Delegate method to call method from TurnManager class to continue phases.
	 */
	public void continuePhase() {
		if(moveCounter()) {
			updateMap();
			turnManager.continuePhase();
		}
	}

	/**
	 * Delegate method to call method from TurnManager class to change between phases.
	 */
	public void changePhase() {
		if(moveCounter()) {
			turnManager.changePhase();
			updateMap();
		}
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
	 * This method announce the attack, get number of dice from both attacker and defender. If a country loose all its armies, the other player occupy the country.
	 * @param attackerCountry country attacking
	 * @param defenderCountry country defending against attack
	 */
	public void announceAttack(String attackerCountry, String defenderCountry) {
		this.resultNotify = "Attack Attacker Country: "+attackerCountry+"  Defender Country: "+defenderCountry+"  ";
		nottifyObservers(resultNotify);
		/*Announce attack on phase view.*/
		CountryNode dCountry = map.getCountry(defenderCountry);
		Player defender = dCountry.getOwner();
		CountryNode aCountry = currentPlayer.getCountry(attackerCountry);
		/*Show dialog boxes and get input from attacker and defender on how many dice to roll.*/
		int aArmies = this.currentPlayer.selectDiceForAttack(attackerCountry);
		int dArmies = defender.selectDiceForAttack(defenderCountry);
		/*Rolling dice for attacker and defender.*/
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
		nottifyObservers(resultNotify);
		battle(dCountry, defender, aCountry, aArmies, dArmies, aResults, dResults);
		nottifyObservers(resultNotify);
		/*check if defender country has armies left.*/
		if(dCountry.getArmiesCount()==0) {
			dCountry.setOwner(currentPlayer);
			turnManager.setWonCard(true);
			/*Notify change in ownership of a country.*/
			resultNotify += "<br>" + " Country "+ dCountry.getCountryName() +" won by " + dCountry.getOwner().getName() + ", new armies "+dCountry.getArmiesCount();
			nottifyObservers(resultNotify);
			/*move countries from attacker country to new acquired country.*/
			int moveArmies = currentPlayer.moveArmies(aArmies, aCountry.getArmiesCount()-1, "Select armies to move:");
			dCountry.addArmy(moveArmies);
			aCountry.removeArmies(moveArmies);
			if(map.continentWonByPlayer(currentPlayer, dCountry)) {
				currentPlayer.addContinent(dCountry.getContinent());
			}
		}
		map.updateMap();
		setPlayerOut(defender);
		if(!checkGameState()) {
			continuePhase();
		}
		else {
			announceGameOver(players.get(0).getName());
		}
	}
	
	/**
	 * This method decides the result of battle between attacking country and defending country and update the state of countries.
	 * @param dCountry country defending the attack
	 * @param defender player defending the attack
	 * @param aCountry attacking country
	 * @param aArmies number of dice rolled by attacker for battle
	 * @param dArmies number of dice rolled by defender
	 * @param aResults results of the dice rolled by attacker
	 * @param dResults results of dice rolled by defender
	 */
	public void battle(CountryNode dCountry, Player defender, CountryNode aCountry, int aArmies, int dArmies,ArrayList<Integer> aResults,ArrayList<Integer> dResults) {
		/*Compare the results to decide battle result.*/
		while(!aResults.isEmpty() && !dResults.isEmpty()) {
			int aMax = max(aResults);
			int dMax = max(dResults);
			if(aResults.get(aMax)>dResults.get(dMax)) {
				dCountry.removeArmy();
				/*Show army removed from defender country.*/
				resultNotify += "<br>" + " Winner Country: "+aCountry.getCountryName();
				System.out.println("Army removed from defender country, new armies "+dCountry.getArmiesCount());
			}
			else {
				aCountry.removeArmy();
				resultNotify += "<br>" + "Winner Country: "+dCountry.getCountryName();
				/*Show army removed from attacker country*/
				System.out.println("Army removed from attacker country, new armies "+aCountry.getArmiesCount());
			}
			aResults.remove(aMax);
			dResults.remove(dMax);
		}
	}
	
	/**
	 * This method declares the game end if all the countries are owned by one player only.
	 * @return true if game if over, false if there is at least two players own at least one country on map
	 */
	public boolean checkGameState() {
		if(players.size()<2) {
			turnManager.setGameOver(true);
			return true;
		}
		return false;
	}
	
	/**
	 * remove player from players list, if player has not country.
	 * @param defenderPlayer player to be removed
	 */
	public void setPlayerOut(Player defenderPlayer) {
		if(defenderPlayer.getCountries().isEmpty()) {
			players.remove(defenderPlayer);
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
	 * @param n number of values to be generated.
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
	public void announceGameOver(String winner) {
		nottifyObservers("GameOver");
		controller.removeAllControls();
		System.out.print("Winner "+winner);
		MainController.getInstance().notifyGameResult(winner);
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

	public Object placeArmyDialog(String[] countries, String string) {
		return controller.placeArmyDialog(countries, string);
	}

	public void reinforcementControls(int armies, String[] countryList) {
		controller.setReinforcementControls(armies, countryList);
	}

	public void attackControls(String[] array) {
		controller.setAttackControls(array);
	}

	public void fortificationControls(String[] array) {
		controller.setFortificationControls(array);
	}
	
	private boolean moveCounter() {
		if(moveLimit!=0) {
			if(moveCounter==moveLimit) {
				turnManager.setGameOver(true);
				announceGameOver("draw");
				return false;
			}
			else {
				moveCounter++;
			}
		}
		return true;
	}
	
	public TurnManager getTurnManager() {
		return this.turnManager;
	}
	
	public void nottifyObservers(String msg) {
		setChanged();
		notifyObservers(msg);
	}

}
