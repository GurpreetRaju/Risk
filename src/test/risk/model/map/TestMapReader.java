package test.risk.model.map;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import risk.model.map.MapModel;
import risk.model.map.MapNode;
import risk.model.map.MapReader;

public class TestMapReader {

	private MapReader reader;
	private ArrayList<MapNode> map ;
	private String fileName = "" ; 
	private MapModel model ;
	
	@Before
	public void setUp(){
		reader = new MapReader();
		model = new MapModel();
		map = new ArrayList<MapNode>();
	}
	
	@Test
	public void testUnconnectedContinentMapFile() {
		fileName = System.getProperty("user.dir") + "\\data\\map\\UnconnectedContinent.map";
		map = reader.readMap(fileName);
		model.writeExistingMap(map);
		boolean actual1 = model.checkOnSaveMap();
		boolean expected  = true;
		Assert.assertEquals(expected, actual1);
		boolean actual2 = model.checkConnectedContinent();
		boolean expected2 = false;
		Assert.assertEquals(expected2, actual2);
	}
	
	@Test
	public void testTwinVolcanoMapFile() {
		fileName = System.getProperty("user.dir") + "\\data\\map\\Twin Volcano.map";
		map = reader.readMap(fileName);
		model.writeExistingMap(map);
		boolean actual1 = model.checkOnSaveMap();
		boolean expected  = true;
		Assert.assertEquals(expected, actual1);
		boolean actual2 = model.checkConnectedContinent();
		boolean expected2 = false;
		Assert.assertEquals(expected2, actual2);
	}
	
	@Test
	public void testWorldMapFile() {
		fileName = System.getProperty("user.dir") + "\\data\\map\\World.map";
		map = reader.readMap(fileName);
		model.writeExistingMap(map);
		boolean actual1 = model.checkOnSaveMap();
		boolean expected  = true;
		Assert.assertEquals(expected, actual1);
		boolean actual2 = model.checkConnectedContinent();
		boolean expected2 = true;
		Assert.assertEquals(expected2, actual2);
	}
	
	@Test
	public void test3DCliffMapFile() {
		fileName = System.getProperty("user.dir") + "\\data\\map\\3D Cliff.map";
		map = reader.readMap(fileName);
		model.writeExistingMap(map);
		boolean actual1 = model.checkOnSaveMap();
		boolean expected  = true;
		Assert.assertEquals(expected, actual1);
		boolean actual2 = model.checkConnectedContinent();
		boolean expected2 = true;
		Assert.assertEquals(expected2, actual2);
	}
	
	
}
