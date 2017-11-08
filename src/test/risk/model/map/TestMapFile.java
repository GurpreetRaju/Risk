package test.risk.model.map;

import org.junit.Test;

import risk.model.map.CountryNode;
import risk.model.map.MapNode;
import risk.model.map.MapReader;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;

/**
 * This test class tests the MapReader class functions.
 * @author Gurpreet
 */
public class TestMapFile {
	
	/**
	 * MapReader type reference variable to store its object.
	 */
	private MapReader mapread;
	
	/**
	 * This function is called before each test case is run.
	 * @throws Exception any throwable exception.
	 */
	@Before
	public void setUp() throws Exception {
		mapread = new MapReader();
	}
	
	/**
	 * Tests the read function that reads the map file loaded from conquest game site.
	 */
	@Test
	public void testRead() {
		ArrayList<MapNode> actual = mapread.readMap(".//data//test//testMap3.map");
		
		String expectedResult = "AmericaN,5,Alaska,Canada,Canada,Alaska,Montana,Groenlandia,Montana,Montana,Canada,Groenlandia,";
		String actualResult = "";
		
		for(MapNode m1: actual) {
			actualResult += (m1.getContinentName()+","+m1.getControlValue()+",");
			for(CountryNode tempCountry: m1.getCountries()) {
				actualResult += (tempCountry.getCountryName()+",");
				for(String ncountry: tempCountry.getNeighbourCountriesString()) {
					actualResult += (ncountry+",");
				}
			}
		}
		assertEquals(expectedResult,actualResult);
	}

}
