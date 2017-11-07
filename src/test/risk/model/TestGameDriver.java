package test.risk.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import risk.controller.Controller;
import risk.model.GameDriver;
import risk.model.Player;
import risk.model.map.CountryNode;
import risk.model.map.MapNode;

/**
 * This class tests all the functions of GameDriver.
 *
 */
public class TestGameDriver {
	
	/**
	 * GameDriver class object to access GameDriver class
	 */
	private GameDriver driver;
	
	/**
	 * ArrayList to store owned neighbors.
	 */
	private ArrayList<String> actualOwnedNeighbors;
	
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
	CountryNode country4;
	
	/**
	 * CountryNode reference.
	 */
	CountryNode country5;
	
	/**
	 * Reference for player object.
	 */
	Player player1;
	
	/**
	 * Reference for Player object.
	 */
	Player player2;
	
	/**
	 * This function is called before each test case is run.
	 */
	@Before
	public void setPlayerAndMapData(){
		driver = GameDriver.getInstance();
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
		player1 = new Player("Player1", 15, countries);
		player2 = new Player("Player2", 10, countries1);
		country1.addArmy(1);
		country2.addArmy(1);
		country3.addArmy(1);
		country4.addArmy(1);
		country5.addArmy(1);
		player1.setTurnTrue();
		player2.setTurnFalse();
		driver.setPlayerList(player1);
		driver.setPlayerList(player2);
		driver.setCurrentPlayer(player1);
	}
	
	
	
	/**
	 * This method tests the battle method from GameDriver class.
	 */
	@Test
	public void testBattle() {
		ArrayList<Integer> aResults = new ArrayList<Integer>();
		aResults.add(4);
		aResults.add(2);
		aResults.add(6);
		ArrayList<Integer> dResults = new ArrayList<Integer>();
		dResults.add(4);
		dResults.add(2);
		country1.addArmy(3);
		country4.addArmy(1);
		driver.battle(country4, player2, country1, 3, 2, aResults, dResults);
		assertEquals(0, country4.getArmiesCount());
	}
	
	/**
	 * This method tests the max method from GameDriver class.
	 */
	@Test
	public void testMax() {
		ArrayList<Integer> aResults = new ArrayList<Integer>();
		aResults.add(4);
		aResults.add(2);
		aResults.add(3);
		aResults.add(6);
		assertEquals(3,driver.max(aResults));
	}
	
}