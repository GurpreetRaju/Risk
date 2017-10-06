package model;

import java.util.ArrayList;

public class CountryNode {
	
	private String countryName;
	private ArrayList<CountryNode> neighbourCountries;
	private int[] coordiantes;
	private String owner;
	private int armies;
	
	public CountryNode(String newName,ArrayList<CountryNode> newNeighbours, int[] newCoordinates)
	{
		this.countryName = newName;
		this.neighbourCountries = newNeighbours;
		this.coordiantes = newCoordinates;
		this.owner = null;
		this.armies = 0;
	}
	
	public String getCountryName()
	{
		return this.countryName;
	}
	
	public CountryNode[] getNeighbourCountries()
	{
		return this.neighbourCountries.toArray(new CountryNode[this.neighbourCountries.size()]);
	}
	
	public String[] getNeighbourCountriesString()
	{
		ArrayList<String> countries = new ArrayList<String>();
		for(CountryNode c : this.neighbourCountries){
			countries.add(c.countryName);
		}
		return countries.toArray(new String[this.neighbourCountries.size()]);
	}

	public int[] getCoordinates()
	{
		return this.coordiantes;
	}
	
	public String getOwner()
	{
		return this.owner;
	}
	
	public int getArmiesCount()
	{
		return this.armies;
	}
	
	public void setOwner(String newOwner)
	{
		this.owner = newOwner;
	}
	
	public void setArmies(int newArmies)
	{
		this.armies = newArmies;
	}
	
	public void addNeighbour(CountryNode newNeighbour)
	{
		if(this.neighbourCountries==null)
		{
			this.neighbourCountries = new ArrayList<CountryNode>();
		}
		this.neighbourCountries.add(newNeighbour);
	}
	
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

	public void setCoordinates(int[] newCoordinates) 
	{
		this.coordiantes = newCoordinates;		
	}
	
}
