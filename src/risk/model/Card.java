package risk.model;

import java.util.ArrayList;

/**
 * This class defines the card user-defined type.
 */
public class Card {
	
	/**
	 * Stores the name of the card.
	 */
	private String name;
	
	/**
	 * Stores the type of the card.
	 */
	private String type;
	
	/**
	 * Constructor to assign value to name and type of the card.
	 * @param name Stores the name of the card.
	 * @param type Stores the type of the card.
	 */
	public Card(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	/**
	 * Function to get the name of the card.
	 * @return name of the card.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Function to get the type of the card.
	 * @return type of the card.
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Generate a pile of 44 cards
	 * @return ArrayList containing 44 cards.
	 */
	public static ArrayList<Card> generateCardPile() {
		ArrayList<Card> cardPile = new ArrayList<Card>();
		for(int i=0;i<44;i++) {
			cardPile.add(new Card("Artillery","Normal"));
		}
		return cardPile;
	}
}
