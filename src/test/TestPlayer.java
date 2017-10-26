package test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import risk.model.CountryNode;
import risk.model.MapNode;
import risk.model.Player;

public class TestPlayer {
	
	private Player testPlayer;
	
	@Before
	public void setUp() throws Exception {
		CountryNode country1 = new CountryNode("Country1", null, null);
		CountryNode country2 = new CountryNode("Country2", null, null);
		CountryNode country3 = new CountryNode("Country3", null, null);
		CountryNode country4 = new CountryNode("Country4", null, null);
		
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
		testPlayer = new Player("TestPlayer",0,mapData);
		testPlayer.addCountry(country1);
		testPlayer.addCountry(country2);
		testPlayer.addCountry(country3);
	}

	@Test
	public void testGetArmies() {
		assertEquals(3,testPlayer.getArmies());
	}
}
