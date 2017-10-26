package test;

import org.junit.Test;
import risk.model.CountryNode;
import risk.model.MapNode;
import risk.model.MapReader;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;

public class TestMapFile {
	
	private MapReader mapread;
	
	@Before
	public void setUp() throws Exception {
		mapread = new MapReader();
	}
	
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
