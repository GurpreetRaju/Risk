package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.CountryNode;
import model.MapNode;
import model.Player;

public class TestPlayer {
	
	private Player testPlayer;
	
	@Before
	public void setUp() throws Exception {
		testPlayer = new Player("TestPlayer");
		CountryNode country1 = new CountryNode("Country1", null, null);
		CountryNode country2 = new CountryNode("Country2", null, null);
		CountryNode country3 = new CountryNode("Country3", null, null);
		CountryNode country4 = new CountryNode("Country4", null, null);
		country1.addNeighbour(country2);
		country2.addNeighbour(country1);
		country1.addNeighbour(country3);
		country3.addNeighbour(country1);
		country2.addNeighbour(country3);
		country3.addNeighbour(country2);
		country4.addNeighbour(country2);
		country4.addNeighbour(country3);
		ArrayList<CountryNode> countries = new ArrayList<CountryNode>();
		countries.add(country1);
		countries.add(country2);
		countries.add(country3);
		MapNode continent = new MapNode("Continent1", countries, 0);
		testPlayer.addCountry(country1);
		testPlayer.addCountry(country2);
		testPlayer.addCountry(country3);
		//country1.setOwner(testPlayer);
		//country2.setOwner(testPlayer);
	}

	@Test
	public void testGetArmies() {
		assertEquals(1,testPlayer.getArmies());
	}

}
