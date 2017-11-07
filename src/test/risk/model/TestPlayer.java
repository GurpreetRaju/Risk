package test.risk.model;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import risk.model.CountryNode;
import risk.model.Player;
import risk.model.map.MapNode;

/**
 * Tests the Player class functions.
 *
 */
public class TestPlayer {
	
	/**
	 * Player reference.
	 */
	private Player testPlayer;
	
	/**
	 * Called before each test case of this class is executed.
	 * @throws Exception any throwable exception.
	 */
	@Before
	public void setUp() throws Exception {
		CountryNode country1 = new CountryNode("Country1", null, null, null);
		CountryNode country2 = new CountryNode("Country2", null, null, null);
		CountryNode country3 = new CountryNode("Country3", null, null, null);
		CountryNode country4 = new CountryNode("Country4", null, null, null);
		
		ArrayList<MapNode> mapData = new ArrayList<MapNode>();
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
		countries.add(country4);
		
		MapNode continent = new MapNode("Continent1", countries, 6);
		mapData.add(continent);
		testPlayer = new Player("TestPlayer",0,countries);
	}

	/**
	 * Tests the getArmies function of Player class.
	 * @see Player
	 */
	@Test
	public void testGetArmies() {
		assertEquals(3,testPlayer.getArmies());
	}
}
