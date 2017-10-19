package model;

/**
 * This class defines the card user-defined type.
 */
public class Card {
	/**
	 * Stores the name of the card.
	 */
	String name;
	/**
	 * Stores the type of the card.
	 */
	String type;
	
	/**
	 * Constructor to assign value to name and type of the card.
	 * @param name Stores the name of the card.
	 * @param type Stores the type of the card.
	 */
	Card(String name, String type)
	{
		this.name = name;
		this.type = type;
	}
	
	/**
	 * Function to get the name of the card.
	 * @return name of the card.
	 */
	String getName()
	{
		return this.name;
	}
	
	/**
	 * Function to get the type of the card.
	 * @return type of the card.
	 */
	String getType()
	{
		return this.type;
	}
}
