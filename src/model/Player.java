package model;

import java.util.ArrayList;

public class Player {
	
	private String name;
	private ArrayList<CountryNode> countries;
	private ArrayList<MapNode> continents;
	private ArrayList<Card> cards;
	
	
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
	
	void addCard(Card card)
	{
		this.cards.add(card);
	}

	
	void removeCard(Card card)
	{
		this.cards.remove(card);
	}
	
	void addContinent(MapNode continent)
	{
		this.continents.add(continent);
	}
	
	void removeContinent(MapNode continent)
	{
		this.continents.remove(continent);
	}
}

