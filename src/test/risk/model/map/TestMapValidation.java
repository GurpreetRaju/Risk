package test.risk.model.map;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import risk.model.map.Map;

/**
 * This test class tests all the functions of Map class.
 * @author Gunpreet
 * @author Amitt
 */
public class TestMapValidation {

	/**
	 * Map type reference variable to store its object.
	 */
	private Map map;
	
	/**
	 * Stores path of the map file.
	 */
	private String path;
	
	/**
	 * This function is called before each test case is run.
	 * @throws Exception any throwable exception.
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Tests the Map class function that checks for connected graph.
	 */
	@Test
	public void testIsMapConnectedGraph() {
		path = ".//data//test//testMap.map";
		map = new Map(path);
		assertTrue(map.isMapConnectedGraph());
	}
	
	/**
	 * Tests the Map class function that checks for valid map.
	 */
	@Test
	public void testIsMapInvalid() {
		path = ".//data//test//testMap2.map";
		map = new Map(path);
		Boolean b = map.mapValidation();
		System.out.print(b);
		assertFalse(b);
	}
	
	/**
	 * Tests the Map class function that checks for unique countries.
	 */
	@Test
	public void testIsMapNodesContainUniqueCountries() {
		path = ".//data//test//testMap.map";
		map = new Map(path);
		Boolean b = map.isMapNodesContainUniqueCountries();
		assertTrue(b);
	}
	
	/**
	 * Test for Map class function that checks for unique countries.
	 */
	@Test
	public void testIsMapNodesContainUniqueCountries2() {
		path = ".//data//test//testMap4.map";
		map = new Map(path);
		Boolean b = map.isMapNodesContainUniqueCountries();
		assertFalse(b);
	}
}
