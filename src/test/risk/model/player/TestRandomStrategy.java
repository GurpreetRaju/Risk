package test.risk.model.player;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import risk.model.gamemode.GameDriver;
import risk.model.map.CountryNode;
import risk.model.map.MapNode;
import risk.model.player.AggressiveStrategy;
import risk.model.player.BenevolentStrategy;
import risk.model.player.CheaterStrategy;
import risk.model.player.Player;
import risk.model.player.PlayerStrategy;
import risk.model.player.RandomStrategy;

/**
 * TestCase for RandomStrategy
 * @author Gurpreet
 *
 */

public class TestRandomStrategy {

	/**
	 * GameDriver class object to access GameDriver class
	 */
	private GameDriver driver;
	
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
	 * CountryNode reference.
	 */
	private CountryNode country5;
	
	/**
	 * Reference for player object.
	 */
	private Player player1;
	
	/**
	 * Reference for Player object.
	 */
	private Player player2;
	
	/**
	 * Reference for MapNode object
	 */
	private ArrayList<MapNode> mapData;
	
	/**
	 * TestStubs
	 */
	@Before
	public void setUp() throws Exception {
		driver = new GameDriver();
		
		country1 = new CountryNode("Country1", null, null, null);
		country2 = new CountryNode("Country2", null, null, null);
		country3 = new CountryNode("Country3", null, null, null);
		country4 = new CountryNode("Country4", null, null, null);
		country5 = new CountryNode("Country5", null, null, null);
		country1.addNeighbour(country2);
		country2.addNeighbour(country1);
		country1.addNeighbour(country3);
		country3.addNeighbour(country1);
		country2.addNeighbour(country3);
		country3.addNeighbour(country2);
		country4.addNeighbour(country2);
		country4.addNeighbour(country3);
		country5.addNeighbour(country1);
		country1.addNeighbour(country5);
		ArrayList<CountryNode> countries = new ArrayList<CountryNode>();
		countries.add(country1);
		countries.add(country2);
		countries.add(country3);
		ArrayList<CountryNode> countries1 = new ArrayList<CountryNode>();
		countries1.add(country4);
		countries1.add(country5);
		
		
	}
	
	/**
	 * test reiforcement method
	 */
	@Test
	public void testeinforcement() {
		mapData = new ArrayList<MapNode>();
		ArrayList<CountryNode> countries = new ArrayList<CountryNode>();
		countries.add(country1);
		countries.add(country2);
		countries.add(country3);
		ArrayList<CountryNode> countries1 = new ArrayList<CountryNode>();
		countries1.add(country4);
		countries1.add(country5);
		MapNode continent = new MapNode("Continent", countries, 6);
		mapData.add(continent);
		MapNode continent1 = new MapNode("Continent1", countries1, 6);
		mapData.add(continent1);
		player1 = new Player("Player1", 15, countries, driver);
		player2 = new Player("Player2", 10, countries1, driver);
		player2.setTurnTrue();
		player1.setTurnFalse();
		driver.setPlayerList(player1);
		driver.setPlayerList(player2);
		driver.setCurrentPlayer(player2);
		PlayerStrategy cheater = new CheaterStrategy(driver);
		PlayerStrategy random = new RandomStrategy(driver);
		player1.setStrategy(cheater);
		player2.setStrategy(random);
		country1.addArmy(4);
		country2.addArmy(6);
		country3.addArmy(3);
		country4.addArmy(5);
		country5.addArmy(4);
		driver.getCurrentPlayer().setArmies(driver.getCurrentPlayer().getArmies());
		((RandomStrategy) random).reinforcement(driver.getPlayerArmies(),driver.getCurrentPlayer().getCountriesNames());
		int count = 0;
		if(country4.getArmiesCount() == 8 || country5.getArmiesCount()==7) {
			count++;
		}
		assertEquals(1, count);
		
		
	}
	
	/**
	 * test placearmy method
	 */
	@Test
	public void testPlaceArmy() {
		int count = 0;
		String [] countries = new String [5];
		for (int i = 0; i<countries.length;i++) {
			countries[i] = "country" + i;
		}
		PlayerStrategy aggressive = new AggressiveStrategy(driver);
		String value = ((AggressiveStrategy) aggressive).placeArmy(countries, "player1");
		for (String country: countries) {
			if (country.equals(value)) {
				count++;
			}
		}
		assertEquals(1, count);
	}
	
}