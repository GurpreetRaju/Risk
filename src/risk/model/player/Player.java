package risk.model.player;

import java.util.ArrayList;

import risk.model.Card;
import risk.model.gamemode.GameDriver;
import risk.model.map.CountryNode;
import risk.model.map.MapNode;

/**
 * This class represent the player.
 * @author Gurpreet
 * @version 1.0
 */
public class Player {
	
	/**
	 * name of player.
	 */
	private String name;
	
	/**
	 * list of countries owned by player.
	 */
	private ArrayList<CountryNode> countries;
	
	/**
	 * list of continents owned by player.
	 */
	private ArrayList<MapNode> continents;
	
	/**
	 * list of cards player has.
	 */
	private ArrayList<Card> cards;
	
	/**
	 * number of times player exchanged the cards.
	 */
	private int cardsUsedCount = 1;
	
	/**
	 * number of armies player has.
	 */
	private int armiesCount;
	
	/**
	 * turn of player
	 */
	private boolean turn = false;
	
	/**
	 * ArrayList of all continents in the Map.
	 */
	private ArrayList<MapNode> mapData;
	
	/**
	 * shows if player is still in game 
	 */
	private boolean lost = false;
	
	/**
	 * Stores reinforcement Country selected.
	 */
	public String countrySelected;
	
	/**
	 * Stores the armies moved in Reinforcement.
	 */
	public int armiesMoved;
	
	/**
	 * Stores Fortification Country selected .
	 */
	private CountryNode countrySelect;
	
	/**
	 * Stores Neightbour Countries of country selected.
	 */
	private CountryNode neighbourC;
	
	/**
	 * Strategy for the player that can be changed on runtime
	 */
	private PlayerStrategy strategy;
	
	/**
	 * Stores instance of GameDriver class.
	 */
	private GameDriver driver;
	
	/**
	 * Initialize player object with name.
	 * @param name name of player.
	 */
	public Player(String name, GameDriver nDriver) {
		this.name = name;
		this.driver = nDriver;
		this.countries = new ArrayList<CountryNode>();
		this.continents = new ArrayList<MapNode>();
		this.cards = new ArrayList<Card>();
	}
	
	/**
	 * Initialize player object with name and armies.
	 * @param name name of the player.
	 * @param newArmies armies of the player.
	 */
	public Player(String name, int newArmies, GameDriver nDriver) {
		this(name, nDriver);
		this.armiesCount = newArmies;
		this.mapData = new ArrayList<MapNode>();
	}
	
	/**
	 * Initialize player object with name and armies.
	 * @param name name of the player.
	 * @param newArmies armies of the player.
	 * @param countriesList ArrayList of all countries owned by player.
	 */
	public Player(String name, int newArmies, ArrayList<CountryNode> countriesList, GameDriver nDriver) {
		this(name, nDriver);
		this.armiesCount = newArmies;
		this.mapData = new ArrayList<MapNode>();
		for(CountryNode c: countriesList) {
			this.addCountry(c);
		}
	}
	
	/**
	 * return name of the player.
	 * @return name of player in string format.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Add country to the list of countries owned by player.
	 * @param country country owned by player
	 */
	public void addCountry(CountryNode country) {
		this.countries.add(country);
		if(country.getOwner()!=this) {
			country.setOwner(this);
		}
	}
	
	/**
	 * returns list of countries owned by the player.
	 * @return ArrayList containing countries.
	 */
	public ArrayList<CountryNode> getCountries() {
		return this.countries ;	
	}
	
	/**
	 * Gets the list of countries owned by the player.
	 * @return list of country names
	 */
	public String[] getCountriesNames() {
		String[] names = new String[this.countries.size()];
		for(int i=0;i<names.length;i++){
			names[i] = this.countries.get(i).getCountryName();
			System.out.println(names[i]);
		}
		return names;
	}
	
	/**
	 * Gives the countries owned by the player having no armies.
	 * @return list of country names with no army.
	 */
	public String[] getCountriesNamesNoArmy(){
		ArrayList<String> names = new ArrayList<String>();
		for(CountryNode c : this.countries){
			if(c.getArmiesCount()==0)
				names.add(c.getCountryName());
		}
		return names.toArray(new String[names.size()]);
	}
	
	/**
	 * Removes country from list of countries owned by player.
	 * @param country Country object to be removed from list
	 */
	public void removeCountry(CountryNode country) {
		this.countries.remove(country);
	}
	
	/**
	 * Add new card to list of cards player has.
	 * @param card new card to be added to list.
	 */
	public void addCard(Card card) {
		this.cards.add(card);
	}

	/**
	 * Removes the card from the list of cards.
	 * @param card card to be removed from list. 
	 */
	public void removeCard(Card card) {
		this.cards.remove(card);
	}
	
	/**
	 * Add new continent to the list of continents.
	 * @param continent continent to be added to list of continent owned by player.
	 */
	public void addContinent(MapNode continent) {
		this.continents.add(continent);
	}
	
	/**
	 * remove continent from the list of continent owned by the player.
	 * @param continent continent to be removed from list of continents owned by player.
	 */
	public void removeContinent(MapNode continent) {
		this.continents.remove(continent);
	}
	
	/**
	 * Checks for the continents owned by the player.
	 */
	public void checkContinent() {
		for (MapNode continent : this.mapData) {
			System.out.println("Inside ForLoop");
			System.out.println(continent.getContinentName());
			if (this.countries.containsAll(continent.getCountryList())) {
				System.out.println("Inside If Stametment");
				addContinent(continent);
				System.out.println("Added" + continent.getContinentName());
			}
		}
	}
	
	/**
	 * Calculates the armies to be alloted to the player at each turn.
	 * @return army count
	 */
	public int getArmies() {
		checkContinent();
		int countriesCount = this.countries.size();
		int continentsCount = this.continents.size();
		int cardsCount = this.cards.size();
		int armyCount = countriesCount/3;
		if(armyCount<3) {
			armyCount = 3;
		}
		if (continentsCount > 0) {
			continentsCount = 0;
			for (MapNode continent : this.continents){
				continentsCount =+ continent.getControlValue();
			}
		}
		armyCount += continentsCount;
		System.out.println(armyCount);
		
		return armyCount;
	}
	
	/**
	 *  Assign armies to the player.
	 *  @param newCount armies to be assigned.
	 */
	public void assignArmies(int newCount) {
		this.armiesCount += newCount;
	}
	
	/**
	 *  Remove Armies from player.
	 *  @param newCount armies to be removed.
	 */
	public void removeArmies(int newCount) {
		this.armiesCount -= newCount;
	}
	
	/**
	 * Gives the number of armies the player has.
	 * @return number of armies player has.
	 */
	public int getArmiesCount() {
		return this.armiesCount;
	}
	
	/**
	 * Gives the country node for the given country name.
	 * @param newCountry country name whose country node is required.
	 * @return country node matching the string 
	 */
	public CountryNode getCountry(String newCountry) {
		return CountryNode.getCountry(countries, newCountry);
	}
	
	/**
	 * Sets player turn to true
	 */
	public void setTurnTrue() {
		this.turn = true;
	}
	
	/**
	 * Sets player turn to false
	 */
	public void setTurnFalse() {
		this.turn = false;
	}
	
	/**
	 * Gives the value of the turn of the player (True or False).
	 * @return turn of player
	 */
	public boolean getTurn() {
		return this.turn;
	}
	
	/**
	 * Sets the armies of the player to the new value.
	 * @param newArmies new value of the army to be set.
	 */
	public void setArmies(int newArmies) {
		this.armiesCount = newArmies;
	}
	
	/**
	 * This method runs the reinforcement phase
	 */
	public void reinforcementPhase(){
		System.out.print("Checkpoint 1");
		strategy.reinforcementPhase(armiesCount, getCountriesNames());
	}
	
	/**
	 * This method runs attack phase.
	 */
	public void attackPhase(){
		ArrayList<String> countriesList = new ArrayList<String>();
		for(CountryNode c : this.countries) {
			if(c.getArmiesCount()>1) {
				for(CountryNode n: c.getNeighbourCountries()) {
					if(!n.getOwner().equals(this)) {
						countriesList.add(c.getCountryName());
						break;
					}
				}
			}
		}
		if(countriesList.isEmpty()) {
			driver.changePhase();
		}
		else {
			strategy.attackPhase(countriesList);
		}
	}
	
	/**
	 * This method runs the fortification phase
	 */
	public void fortificationPhase(){
		ArrayList<String> countriesList = new ArrayList<String>();
		for(CountryNode c : this.countries) {
			if(c.getArmiesCount()>1) {
				for(CountryNode n: c.getNeighbourCountries()) {
					if(n.getOwner().equals(this)) {
						countriesList.add(c.getCountryName());
						break;
					}
				}
			}
		}
		if(countriesList.isEmpty()) {
			driver.nottifyObservers(driver.getTurnManager().getPhase());
			driver.changePhase();
		}
		else {
			strategy.fortificationPhase(countriesList);
		}
	}
	
	/**
	 * Shifts(or places) the armies of the player on each reinforcement.
	 * @param country the country name to which armies are added.
	 * @param armies the number of armies to be reinforced.
	 * @return the army count left for the player.
	 */
	public int shiftArmiesOnReinforcement(String country, int armies) {
		this.countrySelected = country;
		this.armiesMoved = armies;
		getCountry(this.countrySelected).addArmy(this.armiesMoved);
		removeArmies(this.armiesMoved);
		return this.armiesCount;
	}
	
	/**
	 * Shifts the armies of the player from one country to another.
	 * @param sCountry the country name from which armies are moved.
	 * @param sNeighbour the country name to which armies are added.
	 * @param selectedArmies the number of armies to be moved.
	 * @return the army count left in sNeighbour country.
	 */
	public int getArmiesShiftedAfterFortification(String sCountry, String sNeighbour, int selectedArmies){
		this.countrySelect = getCountry(sCountry);
		this.neighbourC = getCountry(sNeighbour);
		countrySelect.setArmies(countrySelect.getArmiesCount()-selectedArmies);
		neighbourC.setArmies(neighbourC.getArmiesCount() + selectedArmies);
		return neighbourC.getArmiesCount();
	}
	
	/**
	 * Get the country selected to move armies from.
	 * @return country selected to move armies from.
	 */
	public CountryNode getCountrySelected(){
		return this.countrySelect;
	}
	
	/**
	 * Get the neighbour selected to move armies to.
	 * @return neighbour selected to move armies to.
	 */
	public CountryNode getNeighbourSelected(){
		return this.neighbourC;
	}
	
	/**
	 * This method calculate the number of dice a player can roo during attack phase
	 * @param country country selected as attacking or attacked
	 * @return number of dice to roll
	 */
	public int selectDiceForAttack(String country) {
		CountryNode aCountry = getCountry(country);
		int aArmies = aCountry.getArmiesCount();
		if(turn && aArmies>4) {
			aArmies = 3;
		}
		else if(turn) {
			aArmies -= 1;
		}
		else if(aArmies>2) {
			aArmies = 2;
		}
		return this.strategy.selectDiceNumber(aArmies,name);
	}
	
	/**
	 * Get the number of countries owned by player.
	 * @return number of countries owned by player
	 */
	public int getPlayerCountryCount(){
		return getCountries().size();
	}

	/**
	 * This methods returns value of lost attribute. 
	 * @return value of lost
	 */
	public boolean getPlayerState() {
		return lost;
	}
	
	/**
	 * This method set value of lost attribute.
	 * @param value Boolean value for lost attribute.
	 */
	public void setPlayerState(boolean value) {
		this.lost = value;

	}
	
	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return name;
	}
	
	/**
	 * Checks if player have Infantry Card
	 * @return true if player have Infantry Card otherwise false
	 */
	public boolean haveInfantryCard(){
		for (Card card: this.cards){
			if (card.getName().equals("Infantry")){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if player have Cavalry Card
	 * @return true if player have Cavalry Card otherwise false
	 */
	public boolean haveCavalryCard(){
		for (Card card: this.cards){
			if (card.getName().equals("Cavalry")){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if player have Artillery Card
	 * @return true if player have Artillery Card otherwise false
	 */
	public boolean haveArtilleryCard(){
		for (Card card: this.cards){
			if (card.getName().equals("Artillery")){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if player have Infantry, Artillery and Cavalry Cards
	 * @return true if player have Infantry, Artillery and Cavalry Cards otherwise false
	 */
	public boolean haveDistinctCards(){
		if (this.haveInfantryCard() && this.haveArtilleryCard() && this.haveCavalryCard()){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Checks if player have three Artillery cards
	 * @return true if player have three Artillery cards otherwise false
	 */
	public boolean haveThreeArtilleryCards(){
		int artillery = 0;
		for (Card card :this.cards){
			if (card.getName().equals("Artillery")){
				artillery++;
			}
		}
		if(artillery == 3){
			return true;
		}
		else{
			return false;
		}
			
	}
	
	/**
	 * Checks if player have three Cavalry cards
	 * @return true if player have three Cavalry cards otherwise false
	 */
	public boolean haveThreeCavalryCards(){
		int cavalry = 0;
		for (Card card :this.cards){
			if (card.getName().equals("Cavalry")){
				cavalry++;
			}
		}
		if(cavalry == 3){
			return true;
		}
		else{
			return false;
		}
			
	}
	
	/**
	 * Checks if player have three Infantry cards
	 * @return true if player have three Infantry Cards otherwise false
	 */
	public boolean haveThreeInfantryCards(){
		int infantry = 0;
		for (Card card :this.cards){
			if (card.getName().equals("Infantry")){
				infantry++;
			}
		}
		if(infantry == 3){
			return true;
		}
		else{
			return false;
		}
			
	}
	
	/**
	 * Checks if player have either three Cavalry, Artillery or Infantry cards
	 * @return true if player have either three Cavalry, Artillery or Infantry cards otherwise false
	 */
	public boolean haveThreeSameTypeCards(){
		if(this.haveThreeCavalryCards() || this.haveThreeArtilleryCards() || this.haveThreeInfantryCards()){
			return true;
		}
		else{
			return false;
		}
	}
	

	/**
	 * 
	 * @return list of cards player has.
	 */
	public ArrayList<Card> getCards(){
		return this.cards;
	}
	
	/**
	 * Removes one Infantry, Artillery and Cavalry cards
	 */
	public void removeDistinctCards(){
		this.removeCard(this.getCard("Cavalry"));
		this.removeCard(this.getCard("Infantry"));
		this.removeCard(this.getCard("Artillery"));
		this.assignArmies(5*this.cardsUsedCount++);
	}
	
	/**
	 * Returns the card from player cardlist
	 * @param cardname name of the card
	 * @return card with cardname equals to parameter
	 */
	public Card getCard(String cardname){
		for (Card card : this.cards){
			if ( card.getName().equals(cardname)){
				return card;
			}
		}
		return null;
	}
	
	/**
	 * Removes either of three Infantry or Artillery or Cavalry cards
	 */
	public void removeSimilarThreeCards(){
		if (this.haveThreeArtilleryCards()){
			this.removeCard(this.getCard("Artillery"));
			this.removeCard(this.getCard("Artillery"));
			this.removeCard(this.getCard("Artillery"));
		}
		else if (this.haveThreeCavalryCards()){
			this.removeCard(this.getCard("Cavalry"));
			this.removeCard(this.getCard("Cavalry"));
			this.removeCard(this.getCard("Cavalry"));
		}
		else if (this.haveThreeInfantryCards()){
			this.removeCard(this.getCard("Infantry"));
			this.removeCard(this.getCard("Infantry"));
			this.removeCard(this.getCard("Infantry"));
		}
		this.assignArmies(5*this.cardsUsedCount++);
		
	}

	/**
	 * Checks if two objects are equal.
	 */
	public boolean equals(Object o) {
		if(o instanceof Player) {
			if(((Player) o).getName().equals(this.getName())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Set a new strategy for player
	 */
	public void setStrategy(PlayerStrategy newStrategy) {
		this.strategy = newStrategy;
	}

	public String placeArmyOnStartUp() {
		if(getCountriesNamesNoArmy().length!=0){
			return this.strategy.placeArmy(getCountriesNamesNoArmy(), getName());
		}else{
			return this.strategy.placeArmy(getCountriesNames(), getName());
		}
	}
	
	/**
	 * 
	 * @param aArmies
	 * @param i
	 * @param string
	 * @return
	 */
	public int moveArmies(int aArmies, int maxArmies, String message) {
		return this.strategy.moveArmies(aArmies, maxArmies, message);
	}
	
	public String getPlayerStrategy(){
		return this.strategy.getStrategyName();
	}
	
}


