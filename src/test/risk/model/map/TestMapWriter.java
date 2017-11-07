package test.risk.model.map;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import risk.model.CountryNode;
import risk.model.map.MapModel;
import risk.model.map.MapNode;
import risk.model.map.MapWriter;

/**
 * This test class tests the MapWriter class
 * @author Harinder
 *
 */
public class TestMapWriter {

	/**
	 * MapModel reference variable to store its object.
	 */
	private MapWriter mapWriter;
	
	/**
	 * This function is called before every test case.
	 * @throws Exception any throwable exception.
	 */
	@Before
	public void setUp() throws Exception {
		mapWriter = new MapWriter();
	}
	
	/**
	 * Tests that map write function of MapWriter.
	 */
	@Test
	public void testWriteMap() {
		ArrayList<MapNode> continents = new ArrayList<MapNode>();
		String cn = "Asia";
		int cv = 2;
		int[] coordinates = {250, 250};
		ArrayList<CountryNode> countryArr = new ArrayList<CountryNode>();
		ArrayList<CountryNode> neighbourArr = new ArrayList<CountryNode>();
		ArrayList<CountryNode> neighbourArr1 = new ArrayList<CountryNode>();
		ArrayList<CountryNode> neighbourArr2 = new ArrayList<CountryNode>();
		ArrayList<CountryNode> neighbourArr3 = new ArrayList<CountryNode>();
		neighbourArr1.add(new CountryNode("Japan", neighbourArr, coordinates,null));
		neighbourArr2.add(new CountryNode("China", neighbourArr, coordinates,null));
		neighbourArr3.add(new CountryNode("India", neighbourArr, coordinates,null));
		countryArr.add(new CountryNode("India", neighbourArr1, coordinates,null));
		countryArr.add(new CountryNode("Japan", neighbourArr2, coordinates,null));
		countryArr.add(new CountryNode("China", neighbourArr3, coordinates,null));
		continents.add(new MapNode(cn, countryArr, cv));
		mapWriter.writeMap(continents);
		String path = mapWriter.getMapFilePath();
		Assert.assertTrue(path!=null);
	}
}
