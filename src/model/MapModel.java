package model;

import java.util.ArrayList;

/**
 * This implements the model code for map editor.
 * It contains the main logic for map editor
 * @author Harinder
 *
 */
public class MapModel {
	
	/**
	 * Reference to the MapNode object
	 */
	MapNode mapNode;
	
	/**
	 * MapNode arraylist containing all the map information
	 */
	ArrayList<MapNode> continents = new ArrayList<MapNode>();
	
	/**
	 * MapWriter object for writing the map contents to the map file
	 */
	MapWriter mapWriter = new MapWriter();
	
	public boolean checkContinentExist(String cn) {
		Boolean continentExist = false;
		for (MapNode con: continents) {
			if(con.getContinentName().compareTo(cn)==0) {
				continentExist = true;
			}
		}
		return continentExist;
	}
}
