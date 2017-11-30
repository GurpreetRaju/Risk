package test.risk.model.map;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import risk.model.map.MapModel;
import risk.model.map.MapNode;
import risk.model.map.MapReader;

/**
 * This class has tests for reading different kinds of map files.
 * It tests the UnConnectedContinent.map, Twin Volcano.map,
 * 3D Cliff.map and World.map files.
 * @author Harinder
 *
 */
public class TestMapReader {

	/**
	 * Reference variable for MapReader object.
	 */
	private MapReader reader;
	
	/**
	 * ArrayList of MapNode type to hold the information of map file
	 */
	private ArrayList<MapNode> map ;
	
	/**
	 * String variable to hold the path of mapfile
	 */
	private String fileName = "" ; 
	
	/**
	 * Reference variable of MapModel type to store its instance
	 */
	private MapModel model ;
	
	/**
	 * Boolean variable to hold value returned by checkOnSaveMap function.
	 */
	boolean actual1;
	
	/**
	 * Boolean variable to hold value returned by checkConnectedContinent function.
	 */
	boolean actual2;
	
	/**
	 * Boolean variable to store the expected result.
	 */
	boolean expected;
	
	/**
	 * Boolean variable to store the expected result.
	 */
	boolean expected2;
	
	/**
	 * This function is called before every test case.
	 * @throws Exception any throwable exception.
	 */
	@Before
	public void setUp(){
		reader = new MapReader();
		model = new MapModel();
		map = new ArrayList<MapNode>();
		
	}
	
	/**
	 * This test case tests the UnconnectedContinent.map file
	 * It tests for two conditions, one for Connected graph,
	 * second for Unconnected continent.
	 */
	@Test
	public void testUnconnectedContinentMapFile() {
		fileName = System.getProperty("user.dir") + "\\data\\map\\UnconnectedContinent.map";
		map = reader.readMap(fileName);
		model.writeExistingMap(map);
		actual1 = model.checkOnSaveMap();
		expected  = true;
		/*It forms a connected graph
		 * so returns true for checkOnSaveMap call
		 */
		Assert.assertEquals(expected, actual1);
		actual2 = model.checkConnectedContinent();
		expected2 = false;
		/*It has an unconnected continent
		 * so returns false for checkConnectedContinent call
		 */
		Assert.assertEquals(expected2, actual2);
	}
	
	/**
	 * This test case tests the TwinVolcano.map file
	 * It tests for two conditions, one for Connected graph,
	 * second for Unconnected continent.
	 */
	@Test
	public void testTwinVolcanoMapFile() {
		fileName = System.getProperty("user.dir") + "\\data\\map\\Twin Volcano.map";
		map = reader.readMap(fileName);
		model.writeExistingMap(map);
		actual1 = model.checkOnSaveMap();
		expected  = true;
		/*It forms a connected graph
		 * so returns true for checkOnSaveMap call
		 */
		Assert.assertEquals(expected, actual1);
		actual2 = model.checkConnectedContinent();
		expected2 = false;
		/*It has an unconnected continent
		 * so returns false for checkConnectedContinent call
		 */
		Assert.assertEquals(expected2, actual2);
	}
	
	/**
	 * This test case tests the World.map file
	 * It tests for two conditions, one for Connected Graph,
	 * second for Connected Continent.
	 */
	@Test
	public void testWorldMapFile() {
		fileName = System.getProperty("user.dir") + "\\data\\map\\World.map";
		map = reader.readMap(fileName);
		model.writeExistingMap(map);
		actual1 = model.checkOnSaveMap();
		expected  = true;
		/*It forms a connected graph
		 * so returns true for checkOnSaveMap call
		 */
		Assert.assertEquals(expected, actual1);
		actual2 = model.checkConnectedContinent();
		expected2 = true;
		/*It has all connected continents
		 * so returns true for checkConnectedContinent call
		 */
		Assert.assertEquals(expected2, actual2);
	}
	
	/**
	 * This test case tests the 3DCliff.map file
	 * It tests for two conditions, one for Connected Graph,
	 * second for Connected Continent.
	 */
	@Test
	public void test3DCliffMapFile() {
		fileName = System.getProperty("user.dir") + "\\data\\map\\3D Cliff.map";
		map = reader.readMap(fileName);
		model.writeExistingMap(map);
		actual1 = model.checkOnSaveMap();
		expected  = true;
		/*It forms a connected graph
		 * so returns true for checkOnSaveMap call
		 */
		Assert.assertEquals(expected, actual1);
		actual2 = model.checkConnectedContinent();
		expected2 = true;
		/*It has all connected continents
		 * so returns true for checkConnectedContinent call
		 */
		Assert.assertEquals(expected2, actual2);
	}
	
	
}
