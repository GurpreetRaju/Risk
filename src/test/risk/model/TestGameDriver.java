package test.risk.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import risk.controller.Controller;
import risk.model.CountryNode;
import risk.model.GameDriver;
import risk.model.Player;
import risk.model.map.MapNode;

public class TestGameDriver {
	
	ArrayList<String> actualOwnedNeighbors;
	Controller controller;
	CountryNode country1;
	CountryNode country2;
	CountryNode country3;
	CountryNode country4;
	CountryNode country5;
	Player player1;
	Player player2;
	
	@Before
	public void setPlayerAndMapData(){
		controller = new Controller(GameDriver.getInstance());
		ArrayList<MapNode> mapData = new ArrayList<MapNode>();
		country1 = new CountryNode("Country1", null, null);
		country2 = new CountryNode("Country2", null, null);
		country3 = new CountryNode("Country3", null, null);
		country4 = new CountryNode("Country4", null, null);
		country5 = new CountryNode("Country5", null, null);
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
		countries.add(country4);
		countries.add(country5);
		MapNode continent = new MapNode("Continent1", countries, 6);
		mapData.add(continent);
		player1 = new Player("Player1", 15, mapData);
		player2 = new Player("Player2", 10, mapData);
		player1.addCountry(country1);
		player1.addCountry(country2);
		player1.addCountry(country3);
		player2.addCountry(country4);
		player2.addCountry(country5);
		country1.addArmy(1);
		country2.addArmy(1);
		country3.addArmy(1);
		country4.addArmy(1);
		country5.addArmy(1);
		player1.setTurnTrue();
		player2.setTurnFalse();
		GameDriver.getInstance().setPlayerList(player1);
		GameDriver.getInstance().setPlayerList(player2);
	}
	
	@Test
	public void testLeftPlayerArmiesOnReinforcement() {
//		GameDriver.getInstance().setNextPlayerTurn();
//		int left = controller.shiftArmiesOnReinforcement(country1, 4);
//		assertEquals(11, left);
//		int left2 = controller.shiftArmiesOnReinforcement(country2, 2);
//		assertEquals(9, left2);
	}
	
	@Test
	public void testArmyCountOfCountryAfterReinforcemnt() {
//		GameDriver.getInstance().setNextPlayerTurn();
//		controller.shiftArmiesOnReinforcement(country4, 2);
//		controller.shiftArmiesOnReinforcement(country5, 1);
//		assertEquals(3, country4.getArmiesCount());
//		assertEquals(2, country5.getArmiesCount());
	}
	
}