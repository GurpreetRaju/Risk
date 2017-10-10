package model;

import java.util.ArrayList;
/**
 * This class represent the player.
 * @author Gurpreet
 * @version 1.0
 */
public class Player {
	/**
	 * name of player.
	 */
	private String name;
	/**
	 * list of countries owned by player.
	 */
	private ArrayList<CountryNode> countries;
	/**
	 * list of continents owned by player.
	 */
	private ArrayList<MapNode> continents;
	/**
	 * list of cards player has.
	 */
	private ArrayList<Card> cards;
	/**
	 * number of times player exchanged the cards.
	 */
	private int cardsusedCount = 1;
	
	/**
	 * Initialize player object with name.
	 * @param name name of player.
	 */
	public Player(String name)
	{
		this.name = name;
		this.countries = new ArrayList<CountryNode>();
		this.continents = new ArrayList<MapNode>();
		this.cards = new ArrayList<Card>();
	}
	
	/**
	 * return name of the player.
	 * @return name of player in string format.
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Add country to the list of countries owned by player.
	 * @param country coutry owned by player
	 */
	public void addCountry(CountryNode country)
	{
		this.countries.add(country);
	}
	
	/**
	 * returns list of countries owned by the player.
	 * @return ArrayList containing coutries.
	 */
	public ArrayList<CountryNode> getCountries()
	{
		return this.countries ;	
	}
	
	/**
	 * Removes country from list of coutries owned by player.
	 * @param country Country object to be removed from list
	 */
	public void removeCountry(CountryNode country)
	{
		this.countries.remove(country);
	}
	
	/**
	 * Add new card to list of cards player has.
	 * @param card new card to be added to list.
	 */
	public void addCard(Card card)
	{
		this.cards.add(card);
	}

	/**
	 * Removes the card from the list of cards.
	 * @param card card to be removed from list. 
	 */
	public void removeCard(Card card)
	{
		this.cards.remove(card);
	}
	
	/**
	 * Add new continent to the list of continents.
	 * @param continent continent to be added to list of continent owned by player.
	 */
	public void addContinent(MapNode continent)
	{
		this.continents.add(continent);
	}
	
	/**
	 * remove continent from the list of continent owned by the player.
	 * @param continent continent to be removed from list of continents owned by player.
	 */
	public void removeContinent(MapNode continent)
	{
		this.continents.remove(continent);
	}
	
	/**
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
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
	
	/**
	 * 
	 * @return
	 */
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

