package test.risk.model.map;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import risk.model.CountryNode;
import risk.model.map.MapModel;
import risk.model.map.MapNode;
import risk.model.map.MapReader;

/**
 * This class tests all MapModel functions.
 * @author Harinder
 * 
 */
public class TestMapModel {
	
	/**
	 * MapModel reference variable to store its object.
	 */
	private MapModel mapModel;
	
	/**
	 * This function is called before every test case.
	 * @throws Exception any throwable exception.
	 */
	@Before
	public void setUp() throws Exception {
		mapModel = new MapModel();
	}
	
	/**
	 * Tests the MapModel function that checks that the map is a connected graph.
	 */
	@Test 
	public void testCheckConnectedMap() {
		String cn = "Asia";
		int cv = 2;
		int[] coordinates = {250, 250};
		ArrayList<CountryNode> countryArr = new ArrayList<CountryNode>();
		ArrayList<CountryNode> neighbourArr = new ArrayList<CountryNode>();
		ArrayList<CountryNode> neighbourArr1 = new ArrayList<CountryNode>();
		ArrayList<CountryNode> neighbourArr2 = new ArrayList<CountryNode>();
		ArrayList<CountryNode> neighbourArr3 = new ArrayList<CountryNode>();
		neighbourArr1.add(new CountryNode("Japan", neighbourArr, coordinates));
		neighbourArr2.add(new CountryNode("China", neighbourArr, coordinates));
		neighbourArr3.add(new CountryNode("India", neighbourArr, coordinates));
		countryArr.add(new CountryNode("India", neighbourArr1, coordinates));
		countryArr.add(new CountryNode("Japan", neighbourArr2, coordinates));
		countryArr.add(new CountryNode("China", neighbourArr3, coordinates));
		mapModel.addContinents(cn, countryArr, cv);
		boolean actual = mapModel.checkOnSaveMap();
		boolean expected = true;
		Assert.assertEquals(expected, actual);
	}
	
	/**
	 * Tests the MapModel function that checks for unique continents.
	 */
	@Test
	public void testCheckContinentExist() {
		String continent1 = "Asia";
		int cv1 = 1;
		ArrayList<CountryNode> countryArr = new ArrayList<CountryNode>();
		String continent2 = "America";
		int cv2 = 2;
		mapModel.addContinents(continent1, countryArr, cv1);
		mapModel.addContinents(continent2, countryArr, cv2);
		boolean actual = mapModel.checkContinentExist(continent1);
		boolean expected = true;
		Assert.assertEquals(actual, expected);
	}
	
	/**
	 * Tests the MapModel function that adds a new continent to the map.
	 */
	@Test
	public void testCheckAddContinent() {
		String cn = "Europe";
		String cn2 = "Africa";
		int cv = 2;
		int cv2 = 4;
		String continent = "";
		ArrayList<CountryNode> countryArr = new ArrayList<CountryNode>();
		mapModel.addContinents(cn, countryArr, cv);
		mapModel.addContinents(cn2, countryArr, cv2);
		ArrayList<MapNode> testContinents = mapModel.getContinents();
		for (MapNode mapNode : testContinents) {
			continent = mapNode.getContinentName();
			break;
		}
		Assert.assertEquals(continent, "Europe");
	}
	
	/**
	 * Tests the MapModel function that checks for unique countries.
	 */
	@Test
	public void testCheckCountryExist() {
		String cn = "Europe";
		int cv = 2;
		ArrayList<CountryNode> countryArr = new ArrayList<CountryNode>();
		ArrayList<CountryNode> neighbourArr = new ArrayList<CountryNode>();
		int[] coordinates = {250, 250};
		countryArr.add(new CountryNode("Britain", neighbourArr, coordinates));
		mapModel.addContinents(cn, countryArr, cv);
		boolean actual = mapModel.checkCountryExist("Britain");
		boolean expected = true;
		Assert.assertEquals(expected, actual);
	}
	
}
