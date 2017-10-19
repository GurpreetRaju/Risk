package model;

import java.util.Random;

/**
 * Defines the dice as a use-defined datatype.
 */
public class Dice {
	
	/**
	 * Gives a random value of the dice. 
	 * @return integer number that represents the value on the dice.
	 */
	public int roll() {
		Random rand = new Random();
		return rand.nextInt(6) + 1;
	}
}
