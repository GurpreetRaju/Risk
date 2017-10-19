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
	public void testMapValidation() {
		path = ".//data//test//testMap.map";
		map = new Map(path);
		assertTrue(map.mapValidation());
	}
	
	@Test
	public void testMapInvalid() {
		path = ".//data//test//testMap2.map";
		map = new Map(path);
		assertFalse(map.mapValidation());
	}

}
