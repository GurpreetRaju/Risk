package model;

import java.util.Random;

public class Dice {
	
	public void dice()
	{
		
	}
	
	public int roll()
	{
		Random rand = new Random();
		return rand.nextInt(6) + 1;
	}
	
}
