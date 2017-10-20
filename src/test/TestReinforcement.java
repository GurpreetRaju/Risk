package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import model.CountryNode;
import model.GameDriver;
import model.MapNode;
import model.Player;

public class TestReinforcement {
	
	ArrayList<String> actualOwnedNeighbors;
	Controller controller;
	CountryNode country1 = new CountryNode("Country1", null, null);
	CountryNode country2 = new CountryNode("Country2", null, null);
	CountryNode country3 = new CountryNode("Country3", null, null);
	CountryNode country4 = new CountryNode("Country4", null, null);
	CountryNode country5 = new CountryNode("Country5", null, null);
	Player player1;
	
	@Before
	public void setPlayerAndMapData(){
		controller = new Controller(GameDriver.getInstance());
		ArrayList<MapNode> mapData = new ArrayList<MapNode>();
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
		player1 = new Player("Player1", 10, mapData);
		Player player2 = new Player("Player2", 10, mapData);
		player1.addCountry(country1);
		player1.addCountry(country2);
		player1.addCountry(country3);
		player2.addCountry(country4);
		player2.addCountry(country5);
		country1.setOwner(player1);
		country2.setOwner(player1);
		country3.setOwner(player1);
		country4.setOwner(player2);
		country5.setOwner(player2);
		country1.addArmy(1);
		country2.addArmy(1);
		country3.addArmy(1);
		country4.addArmy(1);
		country5.addArmy(1);
		player1.setTurnTrue();
		player2.setTurnFalse();
		System.out.println(player1.getArmiesCount());
		GameDriver.getInstance().setPlayerList(player1);
		GameDriver.getInstance().setPlayerList(player2);
	}
	
	@Test
	public void testLeftPlayerArmiesOnReinforcement() {
		int left = controller.shiftArmiesOnReinforcement(country1, 4);
		System.out.println("hello"+ player1.getArmiesCount());

		System.out.println(left);
		assertEquals(5, left);
		//assertEquals()
	}

}
