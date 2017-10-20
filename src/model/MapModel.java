package model;

import java.util.ArrayList;

import controller.Controller;
import view.mapeditor.MapFrame;

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
	
	private String newFilePath;
	
	private String existingFilePath;
	
	/**
	 * MapNode arraylist containing all the map information
	 */
	ArrayList<MapNode> continents = new ArrayList<MapNode>();
		
	/**
	 * MapWriter object for writing the map contents to the map file
	 */
	MapWriter mapWriter = new MapWriter();
	
	/**
	 * New controller object.
	 */
	Controller controller = new Controller();
	
	
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
			for (CountryNode country : i.getCountries()) {
				if(country.getNeighbourCountries().length == 0) {
					saveMap = false;
				}
			}
		}
		return saveMap;
	}
	
	public void saveMapFile() {
		mapWriter.writeMap(continents);
		newFilePath = mapWriter.getMapFilePath();
		System.out.println(newFilePath);
		controller.init(newFilePath);
	}
	
	public void saveToExistingMapFile(String path) {
		mapWriter.writeMapExisting(continents, path);
		existingFilePath = mapWriter.getMapFilePath();
		System.out.println(existingFilePath);
		controller.init(existingFilePath);
	}
	
	public String newFilePath() {
		return newFilePath;
	}
	
	public String existingFilePath() {
		return existingFilePath;
	}
	
	public String getFinalPath() {
		if(MapFrame.selectedAction().compareTo("new")==0) {
			System.out.println(newFilePath());
			return newFilePath();
		}
		else if(MapFrame.selectedAction().compareTo("existing")==0){
			System.out.println(existingFilePath());
			return existingFilePath();
		}
		return null;
	}
	
	public String getExistingFilePath() {
		return existingFilePath;
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
