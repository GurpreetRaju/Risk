package model;

import java.util.ArrayList;
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
	 * Stores neighbouring countries in ArrayList.
	 */
	private ArrayList<CountryNode> neighbourCountries;
	/**
	 * Stores the x nd y coordnates of country at index 0 and 1 respectively.
	 */
	private int[] coordiantes;
	/**
	 * Store name of player to whom this country belongs.
	 */
	private Player owner;
	/**
	 * Stores number of armies in this country placed by owner.
	 */
	private int armies;
	/**
	 * This constructor initialize the attributes of this country.
	 * @param newName name of country.
	 * @param newNeighbours neighbouring countries of this country.
	 * @param newCoordinates x and y coordinates of country.
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
	 * @return Returns the name of country.
	 */
	public String getCountryName()
	{
		return this.countryName;
	}
	
	/**
	 * 
	 * @return Array containing neighbouring countries.
	 */
	public CountryNode[] getNeighbourCountries()
	{
		return this.neighbourCountries.toArray(new CountryNode[this.neighbourCountries.size()]);
	}
	
	/**
	 * @return Array containing names of neighbouring countries.
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
	 * @return coordinates of country.
	 */
	public int[] getCoordinates()
	{
		return this.coordiantes;
	}
	
	/**
	 * @return player who owns this country.
	 */
	public Player getOwner()
	{
		return this.owner;
	}
	
	/**
	 * @return number of armies placed in this country.
	 */
	public int getArmiesCount()
	{
		return this.armies;
	}
	/**
	 * Set owner of this country.
	 * @param player player instance.
	 */
	public void setOwner(Player player)
	{
		this.owner = player;
	}
	
	/**
	 * Place armies in this country.
	 * @param newArmies number of armies
	 */
	public void setArmies(int newArmies)
	{
		this.armies = newArmies;
	}
	/**
	 * Adds new neighbour country.
	 * @param newNeighbour country
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
	 * Check if an ArrayList contains a country.
	 * @param list ArrayList of countries
	 * @param country country to be found in list
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
	 * @param list list of countries.
	 * @param name name of required country.
	 * @return object of required country
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
	 * Set new coordinates of country.
	 * @param newCoordinates x and y coordinates
	 */
	public void setCoordinates(int[] newCoordinates) 
	{
		this.coordiantes = newCoordinates;		
	}
	
}
