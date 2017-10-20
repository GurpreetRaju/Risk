package model;

import java.util.ArrayList;

/**
 * This implements the model code for map editor.
 * It contains the main logic for map editor
 * @author Harinder
 * @author Jyotsna
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
	
	public void writeExistingMap(ArrayList<MapNode> continents) {
		this.continents = continents;
	}
	
	public boolean checkContinentExist(String cn) {
		Boolean continentExist = false;
		for (MapNode con: continents){
			if(con.getContinentName().compareTo(cn)==0){
				continentExist = true;
			}
		}
		return continentExist;
	}
	
	public void addContinents(String cn1,ArrayList<CountryNode> countryArr,int cv1) {
		continents.add(new MapNode(cn1, countryArr, cv1));
	}
	
	public ArrayList<MapNode> getContinents() {
		return continents;
	}
	
	public boolean checkOnSaveMap() {
		Boolean saveMap = true;
		for (MapNode i :continents) {	
			if(i.getCountries().length == 0) {
				saveMap = false;
			}
		}
		return saveMap;
	}
	
	public void saveMapFile() {
		mapWriter.writeMap(continents);
	}
	
	public boolean checkCountryExist(String cn1) {
		Boolean countryExist = false;
		for (MapNode node: continents) {
			for (CountryNode country : node.getCountries()) {
				if(country.getCountryName().compareTo(cn1)==0) {
					countryExist=true;
				}
			}
		}
		return countryExist;
	}
}
