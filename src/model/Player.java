package model;

import java.util.ArrayList;

public class Player {
	
	private String name;
	private ArrayList<CountryNode> countries;
	
	Player(String name)
	{
		this.name = name;
	}
	
	String getName()
	{
		return this.name;
	}
	
	void addCountry(CountryNode country)
	{
		this.countries.add(country);
	}
	
	ArrayList<CountryNode> getCountries()
	{
		
		return this.countries ;
		
	}
	
	void removeCountry(CountryNode country)
	{
		this.countries.remove(country);
	}
}
