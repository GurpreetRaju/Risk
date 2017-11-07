package test.risk.model;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import risk.model.GameDriver;
import risk.model.Player;
import risk.model.map.CountryNode;
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
	 * CountryNode reference.
	 */
	private CountryNode country1;
	
	/**
	 * CountryNode reference.
	 */
	private CountryNode country2;
	
	/**
	 * CountryNode reference.
	 */
	private CountryNode country3;
	
	/**
	 * CountryNode reference.
	 */
	private CountryNode country4;
	
	/**
	 * Called before each test case of this class is executed.
	 * @throws Exception any throwable exception.
	 */
	@Before
	public void setUp() throws Exception {
		country1 = new CountryNode("Country1", null, null, null);
		country2 = new CountryNode("Country2", null, null, null);
		country3 = new CountryNode("Country3", null, null, null);
		country4 = new CountryNode("Country4", null, null, null);
		
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
		testPlayer.addCountry(new CountryNode("test", null, null, null));
		testPlayer.addCountry(new CountryNode("test2", null, null, null));
		testPlayer.addCountry(new CountryNode("test3", null, null, null));
		testPlayer.addCountry(new CountryNode(null, null, null, null));
		testPlayer.addCountry(new CountryNode(null, null, null, null));
		testPlayer.addCountry(new CountryNode(null, null, null, null));
		testPlayer.addCountry(new CountryNode(null, null, null, null));
		testPlayer.addCountry(new CountryNode(null, null, null, null));
		assertEquals(4,testPlayer.getArmies());
	}
	
	/**
	 * Tests the shiftArmiesOnReinforcement function of Game driver.
	 * @see GameDriver
	 */
	@Test
	public void testShiftArmiesOnReinforcement() {
		testPlayer.setArmies(10);
		int left = testPlayer.shiftArmiesOnReinforcement("Country1", 4);
		assertEquals(6, left);
		assertEquals(4, country1.getArmiesCount());
	}
	
	/**
	 *  Test method getCountriesNamesNoArmy()
	 */
	@Test
	public void testGetCountriesNamesNoArmy() {
		country1.addArmy(1);
		country2.addArmy(7);
		country4.addArmy(3);
		assertEquals("Country3",testPlayer.getCountriesNamesNoArmy()[0]);
	}
	
	/**
	 * Testing method getArmiesShiftedAfterFortification
	 */
	@Test
	public void testGetArmiesShiftedAfterFortification() {
		country1.setArmies(3);
		country2.setArmies(1);
		assertEquals(3,testPlayer.getArmiesShiftedAfterFortification(country1.getCountryName(), country2.getCountryName(), 2));
	}
}
