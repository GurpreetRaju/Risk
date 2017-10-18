package model;

import java.util.ArrayList;

public class MapModel {
	
	MapNode mapNode;
	ArrayList<MapNode> continents = new ArrayList<MapNode>();
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
