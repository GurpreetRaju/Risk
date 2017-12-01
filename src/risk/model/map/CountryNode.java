package risk.model.map;

import java.util.ArrayList;

import risk.model.player.Player;

/**
 * This class store the information of a country.
 * @author Gurpreet
 * @version 1.0
 */
public class CountryNode {
	
	/**
	 * Stores name of country.
	 */
	private String countryName;
	
	/**
	 * Stores neighboring countries in ArrayList.
	 */
	private ArrayList<CountryNode> neighbourCountries;
	
	/**
	 * Stores the x and y coordinates of country at index 0 and 1 respectively.
	 */
	private int[] coordiantes;
	
	/**
	 * Stores the name of player to whom this country belongs.
	 */
	private Player owner;
	
	/**
	 * Stores number of armies in this country placed by owner.
	 */
	private int armies;
	
	/**
	 * Continent this country belongs to.
	 */
	private MapNode continent;
	
	/**
	 * This constructor initialize the attributes of this country.
	 * @param newName name of country.
	 * @param newNeighbours neighboring countries of this country.
	 * @param newCoordinates x and y coordinates of country.
	 * @param newContinent name of the continent.
	 */
	public CountryNode(String newName,ArrayList<CountryNode> newNeighbours, int[] newCoordinates, MapNode newContinent) {
		this.countryName = newName;
		this.neighbourCountries = newNeighbours;
		this.coordiantes = newCoordinates;
		this.continent = newContinent;
		this.owner = null;
		this.armies = 0;
	}
	
	/**
	 * Gives the name of the country node.
	 * @return Returns the name of country.
	 */
	public String getCountryName() {
		return this.countryName;
	}
	
	/**
	 * Gives the list of the neighboring country nodes of a given country node.
	 * @return ArrayList containing neighboring countries.
	 */
	public ArrayList<CountryNode> getNeighbours() {
		return this.neighbourCountries;
	}
	
	/**
	 * Gives the list of the neighboring country nodes of a given country node.
	 * @return Array containing neighboring countries.
	 */
	public CountryNode[] getNeighbourCountries() {
		if(this.neighbourCountries!=null)
			return this.neighbourCountries.toArray(new CountryNode[this.neighbourCountries.size()]);
		return null;
	}
	
	/**
	 * Gives the list of the names of the neighboring country nodes of a given country.
	 * @return Array containing names of neighboring countries.
	 */
	public String[] getNeighbourCountriesString() {
		ArrayList<String> countries = new ArrayList<String>();
		for(CountryNode c : this.neighbourCountries){
			countries.add(c.countryName);
		}
		return countries.toArray(new String[this.neighbourCountries.size()]);
	}

	/**
	 * Gets the coordinates of the country on the map.
	 * @return coordinates of country.
	 */
	public int[] getCoordinates() {
		return this.coordiantes;
	}
	
	/**
	 * Gets the player who is the owner of the country.
	 * @return player who owns this country.
	 */
	public Player getOwner() {
		return this.owner;
	}
	
	/**
	 * Gets the armies count of a particular country.
	 * @return number of armies placed in this country.
	 */
	public int getArmiesCount() {
		return this.armies;
	}
	
	/**
	 * Sets owner of this country.
	 * @param player player instance.
	 */
	public void setOwner(Player player) {
		if(this.owner != null) {
			if(!this.owner.equals(player))
			{
				this.owner.removeCountry(this);
				System.out.println("Country removed");
			}
		}
		this.owner = player;
		if(!player.getCountries().contains(this)) {
			player.addCountry(this);
		}
	}
	
	/**
	 * Places armies in this country.
	 * @param newArmies number of armies
	 */
	public void setArmies(int newArmies) {
		this.armies = newArmies;
	}
	
	/**
	 * Adds more armies in this country.
	 * @param newCount number of armies
	 */
	public void addArmy(int newCount){
		this.armies += newCount;
	}
	
	/**
	 * Adds new neighbor country.
	 * @param newNeighbour country
	 */
	public void addNeighbour(CountryNode newNeighbour) {
		if(this.neighbourCountries==null){
			this.neighbourCountries = new ArrayList<CountryNode>();
		}
		if(!this.neighbourCountries.contains(newNeighbour)){
			this.neighbourCountries.add(newNeighbour);
			newNeighbour.addNeighbour(this);
		}
	}
	
	/**
	 * Check if two objects of this class are same.
	 * @param o object of CountryNode
	 * @return true of two objects are same; false if not.
	 */
	public boolean equals(Object o) {
		if(o instanceof CountryNode){
			if(((CountryNode) o).countryName.equals(this.countryName)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if an ArrayList contains a country.
	 * @param list ArrayList of countries
	 * @param country country to be found in list
	 * @return true if list contains country; false if not.
	 */
	public static boolean contains(ArrayList<CountryNode> list, String country) {
		for(CountryNode c: list){
			if(c.countryName.equals(country)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * To get object of a CountryNode using name.
	 * @param list list of countries.
	 * @param name name of required country.
	 * @return object of required country
	 */
	public static CountryNode getCountry(ArrayList<CountryNode>list, String name) {
		for(CountryNode c: list){
			if(c.countryName.equals(name)){
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Set new coordinates of country.
	 * @param newCoordinates x and y coordinates
	 */
	public void setCoordinates(int[] newCoordinates) {
		this.coordiantes = newCoordinates;		
	}

	/**
	 * Removes a neighbor country.
	 * @param neighbourNode receives country to be deleted as neighbor.  
	 */
	public void removeNeighbour(CountryNode neighbourNode) {
		CountryNode cn1 = new CountryNode(null,null,null,null);
		for (CountryNode cn : neighbourCountries ) {
			if(cn.getCountryName().compareTo(neighbourNode.getCountryName())==0) {
				cn1 = cn;
				
			}
		}
		neighbourCountries.remove(cn1);
	}
  
	/**
	 * Decreases the count of armies by 1.
	 */
	public void removeArmy() {
		armies = armies-1;	
	}
	
	/**
	 * Return continent to which this country belongs.
	 * @return object of MapNode this country belongs to.
	 */
	public MapNode getContinent() {
		return this.continent;
	}
	
	/**
	 * Set continent this country belongs to.
	 * @param newContinent Set continent this country belongs to.
	 */
	public void setContinent(MapNode newContinent) {
		this.continent = newContinent;
	}
	
	/**
	 * Subtract multiple armies from the country
	 * @param moveArmies number of armies to subtract
	 */
	public void removeArmies(int moveArmies) {
		armies -= moveArmies;
	}
	
}
