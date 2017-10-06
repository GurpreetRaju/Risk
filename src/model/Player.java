package model;

import java.util.ArrayList;

public class Player {
	
	private String name;
	private ArrayList<CountryNode> countries;
	private ArrayList<MapNode> continents;
	private ArrayList<Card> cards;
	private int cardsusedCount = 1;
	
	
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
	
	void checkContinent() throws InstantiationException, IllegalAccessException
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
	
	int getArmies()
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

