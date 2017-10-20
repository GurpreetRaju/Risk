package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.Map;

public class TestMapValidation {

	private Map map;
	private String path;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testIsMapConnectedGraph() {
		path = ".//data//test//testMap.map";
		map = new Map(path);
		assertTrue(map.isMapConnectedGraph());
	}
	
	@Test
	public void testIsMapInvalid() {
		path = ".//data//test//testMap2.map";
		map = new Map(path);
		Boolean b = map.mapValidation();
		System.out.print(b);
		assertFalse(b);
	}
	
	@Test
	public void testIsMapNodesContainUniqueCountries() {
		path = ".//data//test//testMap.map";
		map = new Map(path);
		Boolean b = map.isMapNodesContainUniqueCountries();
		assertTrue(b);
	}
	
	@Test
	public void testIsMapNodesContainUniqueCountries2() {
		path = ".//data//test//testMap4.map";
		map = new Map(path);
		Boolean b = map.isMapNodesContainUniqueCountries();
		assertFalse(b);
	}

}
