package model;

import java.util.ArrayList;

public class Player {
	
	private String name;
	private ArrayList<CountryNode> countries;
	private ArrayList<MapNode> continents;
	private ArrayList<Card> cards;
	private int cardsusedCount = 1;
	
	
	public Player(String name)
	{
		this.name = name;
		this.countries = new ArrayList<CountryNode>();
		this.continents = new ArrayList<MapNode>();
		this.cards = new ArrayList<Card>();
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void addCountry(CountryNode country)
	{
		this.countries.add(country);
	}
	
	public ArrayList<CountryNode> getCountries()
	{
		return this.countries ;	
	}
	
	public void removeCountry(CountryNode country)
	{
		this.countries.remove(country);
	}
	
	public void addCard(Card card)
	{
		this.cards.add(card);
	}

	
	public void removeCard(Card card)
	{
		this.cards.remove(card);
	}
	
	public void addContinent(MapNode continent)
	{
		this.continents.add(continent);
	}
	
	public void removeContinent(MapNode continent)
	{
		this.continents.remove(continent);
	}
	
	public void checkContinent() throws InstantiationException, IllegalAccessException
	{
		for (MapNode continent : Map.class.newInstance().getMapData())
		{
			if (this.countries.contains(continent.getCountries()))
			{
				addContinent(continent);
				//Should we remove the countries in the continent from the list of 
				//all countries owned by player?
			}
		}
	}
	
	public int getArmies()
	{
		int countriesCount = this.countries.size();
		int continentsCount = this.continents.size();
		int cardsCount = this.cards.size();
		if (continentsCount > 0)
		{
			for (MapNode continent : this.continents)
			{
				continentsCount =+ continent.getControlValue();
			}
		}
		
		int armycount = (int) Math.ceil(countriesCount/3) + continentsCount;
		
		if (cardsCount > 5)
		{
			//do something here with the cards count
			armycount =+ 5* this.cardsusedCount;
			this.cardsusedCount++;
			//remove cards here
		}
		return armycount;
	}
}

