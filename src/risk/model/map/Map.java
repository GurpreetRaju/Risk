package risk.model.map;

import java.util.ArrayList;
import java.util.Observable;

import risk.model.Player;
import risk.view.MapView;

/**
 * This class perform functions related to map for example storing map data,
 * update map etc.
 * 
 * @author Gurpreet
 * @version 1.0
 */
public class Map extends Observable {
	
	/**
	 * ArrayList containing map data.
	 * @see MapNode
	 */
	private ArrayList<MapNode> mapData;
	
	/**
	 * Number of total countries in the Map
	 */
	private int countryCount;
		
	/**
	 * This constructor create object of MapReader class and read data from map.
	 * @param filename address of the mapfile to be loaded.
	 */
	public Map(String filename) {
		MapReader reader = new MapReader();
		mapData = reader.readMap(filename);
	}
	
	/** 
	 * Returns the arraylist of map data.
	 * @return return map data in form of arraylist.
	 */
	public ArrayList<MapNode> getMapData() {
		return this.mapData;
	}
	
	/**
	 * Return map data.
	 * @return multidimensional array containing map data.
	 */
	public String[][] getMapDataObject() {
		ArrayList<Object[]> newData = new ArrayList<Object[]>();
		for(MapNode m : mapData){
			for(CountryNode n : m.getCountries()){
				String[] tempObject = new String[5];
				tempObject[1] = n.getCountryName();
				if(n.getOwner()!=null){
					tempObject[3] = n.getOwner().getName();
				}else{
					tempObject[3] = "";
				}
				tempObject[2] = String.valueOf(n.getArmiesCount());
				newData.add(tempObject);
				tempObject[0] = Integer.toString(n.getCoordinates()[0]);
				tempObject[4] = Integer.toString(n.getCoordinates()[1]);
			}
		}
		return newData.toArray(new String[newData.size()][]);
	}
	
	/**
	 * Return map data
	 * @return multidimensional array containing map data.
	 */
	public String[][] getMapObject() {
		ArrayList<Object[]> newData = new ArrayList<Object[]>();
		for(MapNode m : mapData){
			for(CountryNode n : m.getCountries()){
				String[] tempObject = new String[5];
				tempObject[0] = m.getContinentName() +", "+ m.getControlValue();
				tempObject[1] = n.getCountryName();
				String neighbours = "";
				for(String s: n.getNeighbourCountriesString()){
					neighbours = neighbours + s + ", ";
				}
				tempObject[4] = neighbours;
				if(n.getOwner()!=null){
					tempObject[3] = n.getOwner().getName();
				}else{
					tempObject[3] = "";
				}
				tempObject[2] = String.valueOf(n.getArmiesCount());
				newData.add(tempObject);
			}
		}
		return newData.toArray(new String[newData.size()][]);
	}
	
	/**
	 * Print map data on console.
	 */
	public void mapConsolePrint() {
		Object[][] map = this.getMapDataObject();
		for(Object[] m : map){
			System.out.println("Continent Name: "+m[0]);
			System.out.println("\tCountry Name: "+m[1]+", Neighbours: "+m[4]+", Owner: "+m[3]+", Armies: "+m[2]);
		}
	}
	
	/**
	 * Notify Observer(MapView) of the change in Observable
	 */
	public void updateMap(){
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * This method check if loaded map is valid.
	 * @return true if map is valid false if map is not valid
	 * */
	public boolean mapValidation() {
		if(isMapConnectedGraph() && isMapNodesContainUniqueCountries()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Check if map is connected graph.
	 * @return true if map is connected graph false if map is not connected graph
	 */
	public boolean isMapConnectedGraph()
	{
		for(MapNode m: this.mapData){
			for(CountryNode c: m.getCountries()){
				if(c.getNeighbourCountries()==null || c.getNeighbourCountries().length==0){
					return false;
				}
				for(CountryNode n: c.getNeighbourCountries()){
					if(!n.getNeighbours().contains(c)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Check if every country belongs to only one mapNode.
	 * @return true if all continents have unique countries; false if one country belongs to more than one continent
	 */
	public boolean isMapNodesContainUniqueCountries() {
		ArrayList<CountryNode> stack = new ArrayList<CountryNode>();
		for(MapNode m: this.mapData) {
			for(CountryNode c: m.getCountries()) {
				if(stack.contains(c)) {
					return false;
				}
				else {
					stack.add(c);
				}
			}
		}
		return true;
	}
	
	/**
	 * return Arraylist of neighbouring countries owned by a player or not owned by player, depending on flag value
	 * @param countryName Country node whose neighbors are required.
	 * @param newPlayer player who owns the required neighbours.
	 * @param flag if true, method returns countries owned by newPlayer, if false return countries owned by other players except newPlayer.
	 * @return playerNeighbouringCountries returns neighbouring countries of the country of same owner
	 */
	public ArrayList<String> getPlayerNeighbourCountries(CountryNode newCountry, Player newPlayer,boolean flag) {
		ArrayList<String> playerNeighbourCountries = new ArrayList<String>();
		for (CountryNode country : newCountry.getNeighbourCountries()){
			if(flag) {
				if (country.getOwner().equals(newPlayer)){
					playerNeighbourCountries.add(country.getCountryName());
				}
			}
			else {
				if(!country.getOwner().equals(newPlayer)) {
					playerNeighbourCountries.add(country.getCountryName());
				}
			}
		}
		return playerNeighbourCountries;
	}
	/**
	 * Get object of country from its name
	 * @param countryName name of country
	 * @return object of CountryNode required
	 */
	public CountryNode getCountry(String countryName) {
		CountryNode c = null;
		for(MapNode m: this.mapData) {
			c = CountryNode.getCountry(m.getCountryList(), countryName);
			if(c!=null) {
				break;
			}
		}
		return c;
	}

	/**
	 * Returns the number of countries in map
	 * @return number of countries in map
	 */
	public int getCountryCount(){
		this.countryCount = 0;
		for (MapNode continent : this.mapData){
			countryCount += continent.getCountriesCount();
		}
		return this.countryCount;
	}

	/**
	 * Check if a continent is owned by a player to which country belongs
	 * @param player player for which the continent to be checked
	 * @param country a country from continent to be checked
	 * @return true if continent belongs to player, false if continent not belongs to player
	 */
	public boolean continentWonByPlayer(Player player,CountryNode country) {
		MapNode m = country.getContinent();	
		for(CountryNode c : m.getCountries()) {
			if(c.getOwner()!=player) {
					return false;
			}
			return true;
		}
		return false;

	}
}
