package model;

import java.util.ArrayList;
/**
 * This class store the information of a territory.
 * @author Gurpreet
 * @version 1.0
 */
public class CountryNode {
	
	/**
	 * Stores name of territory.
	 */
	private String countryName;
	/**
	 * Stores neighbouring territories in ArrayList.
	 */
	private ArrayList<CountryNode> neighbourCountries;
	/**
	 * Stores the x nd y coordnates of territory at index 0 and 1 respectively.
	 */
	private int[] coordiantes;
	/**
	 * Store name of player to whom this territory belongs.
	 */
	private Player owner;
	/**
	 * Stores number of armies in this territory placed by owner.
	 */
	private int armies;
	/**
	 * This constructor initialize the attributes of this territory.
	 * @param newName name of territory.
	 * @param newNeighbours neighbouring countries of this territory.
	 * @param newCoordinates x and y coordinates of territory.
	 */
	public CountryNode(String newName,ArrayList<CountryNode> newNeighbours, int[] newCoordinates)
	{
		this.countryName = newName;
		this.neighbourCountries = newNeighbours;
		this.coordiantes = newCoordinates;
		this.owner = null;
		this.armies = 0;
	}
	
	/**
	 * @return Returns the name of territory.
	 */
	public String getCountryName()
	{
		return this.countryName;
	}
	
	/**
	 * 
	 * @return Array containing neighbouring territories.
	 */
	public CountryNode[] getNeighbourCountries()
	{
		return this.neighbourCountries.toArray(new CountryNode[this.neighbourCountries.size()]);
	}
	
	/**
	 * @return Array containing names of neighbouring territories.
	 */
	public String[] getNeighbourCountriesString()
	{
		ArrayList<String> countries = new ArrayList<String>();
		for(CountryNode c : this.neighbourCountries){
			countries.add(c.countryName);
		}
		return countries.toArray(new String[this.neighbourCountries.size()]);
	}

	/**
	 * @return coordinates of territory.
	 */
	public int[] getCoordinates()
	{
		return this.coordiantes;
	}
	
	/**
	 * @return player who owns this territory.
	 */
	public Player getOwner()
	{
		return this.owner;
	}
	
	/**
	 * @return number of armies placed in this territory.
	 */
	public int getArmiesCount()
	{
		return this.armies;
	}
	/**
	 * Set owner of this territory.
	 * @param player player instance.
	 */
	public void setOwner(Player player)
	{
		this.owner = player;
	}
	
	/**
	 * Place armies in this territory.
	 * @param newArmies number of armies
	 */
	public void setArmies(int newArmies)
	{
		this.armies = newArmies;
	}
	/**
	 * Adds new neighbour territory.
	 * @param newNeighbour Territory
	 */
	public void addNeighbour(CountryNode newNeighbour)
	{
		if(this.neighbourCountries==null)
		{
			this.neighbourCountries = new ArrayList<CountryNode>();
		}
		this.neighbourCountries.add(newNeighbour);
	}
	/**
	 * Check if two objects of this class are same.
	 * @param o object of CountryNode
	 * @return true of two objects are same; false if not.
	 */
	public boolean equal(Object o)
	{
		if(o instanceof CountryNode)
		{
			if(((CountryNode) o).countryName.equals(this.countryName))
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * Check if an ArrayList contains a Territory.
	 * @param list ArrayList of territories
	 * @param country territory to be found in list
	 * @return ture if list conatins country; false if not.
	 */
	public static boolean contains(ArrayList<CountryNode> list, String country)
	{
		for(CountryNode c: list)
		{
			if(c.countryName.equals(country))
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * To get object of a CountryNode using name.
	 * @param list list of territories.
	 * @param name name of required territory.
	 * @return object of required Territory
	 */
	public static CountryNode getCounrty(ArrayList<CountryNode>list, String name)
	{
		for(CountryNode c: list)
		{
			if(c.countryName.equals(name))
			{
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Set new coordinates of territory.
	 * @param newCoordinates x and y coordinates
	 */
	public void setCoordinates(int[] newCoordinates) 
	{
		this.coordiantes = newCoordinates;		
	}
	
}
