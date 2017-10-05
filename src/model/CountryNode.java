package model;

import java.util.ArrayList;

public class CountryNode {
	
	private String countryName;
	private ArrayList<String> neighbourCountries;
	private int[] coordiantes;
	private String owner;
	private int armies;
	
	public CountryNode(String newName,ArrayList <String> newNeighbours, int[] newCoordinates)
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
	
	public String[] getNeighbourCountries()
	{
		return this.neighbourCountries.toArray(new String[this.neighbourCountries.size()]);
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
	
	public void setOwner(String newOwner){
		this.owner = newOwner;
	}
	
	public void setArmies(int newArmies)
	{
		this.armies = newArmies;
	}
	
	public void addNeighbour(String newNeighbour)
	{
		if(this.neighbourCountries==null){
			this.neighbourCountries = new ArrayList<String>();
		}
		this.neighbourCountries.add(newNeighbour);
	}
	
}
