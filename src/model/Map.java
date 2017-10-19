package model;

import java.util.ArrayList;
import java.util.Observable;

import view.MapView;
/**
 * This class perform functions related to map for example storing map data,
 * update map etc.
 * 
 * @author Gurpreet
 * @version 1.0
 */
public class Map extends Observable{
	/**
	 * ArrayList containing map data.
	 * @see MapNode
	 */
	private ArrayList<MapNode> mapData;
		
	/**
	 * This constructor create object of MapReader class and read data from map.
	 * @param filename address of the mapfile to be loaded.
	 */
	public Map(String filename)
	{
		MapReader reader = new MapReader();
		mapData = reader.readMap(filename);
	}
	
	/** 
	 * Returns the arraylist of map data.
	 * @return return map data in form of arraylist.
	 */
	public ArrayList<MapNode> getMapData()
	{
		return this.mapData;
	}
	
	/**
	 * Return map data.
	 * @return multidimensional array containing map data.
	 */
	public String[][] getMapDataObject()
	{
		ArrayList<Object[]> newData = new ArrayList<Object[]>();
		for(MapNode m : mapData)
		{
			for(CountryNode n : m.getCountries())
			{
				String[] tempObject = new String[5];
				tempObject[1] = n.getCountryName();
				if(n.getOwner()!=null){
					tempObject[3] = n.getOwner().getName();
				}
				else{
					tempObject[3] = "";
				}
				tempObject[2] = String.valueOf(n.getArmiesCount());
				newData.add(tempObject);
				tempObject[0] = Integer.toString(n.getCoordinates()[0]);
				tempObject[4] = Integer.toString(n.getCoordinates()[1]);
			}
		}
		return newData.toArray(new String[newData.size()][]);
	}
	
	/**
	 * 
	 * @return
	 */
	public String[][] getMapObject()
	{
		ArrayList<Object[]> newData = new ArrayList<Object[]>();
		for(MapNode m : mapData)
		{
			for(CountryNode n : m.getCountries())
			{
				String[] tempObject = new String[5];
				tempObject[0] = m.getContinentName() +", "+ m.getControlValue();
				tempObject[1] = n.getCountryName();
				String neighbours = "";
				for(String s: n.getNeighbourCountriesString())
				{
					neighbours = neighbours + s + ", ";
				}
				tempObject[4] = neighbours;
				if(n.getOwner()!=null){
					tempObject[3] = n.getOwner().getName();
				}
				else{
					tempObject[3] = "";
				}
				tempObject[2] = String.valueOf(n.getArmiesCount());
				newData.add(tempObject);
			}
		}
		return newData.toArray(new String[newData.size()][]);
	}
	
	/**
	 * Print map data on console.
	 */
	public void mapConsolePrint()
	{
		Object[][] map = this.getMapDataObject();
		for(Object[] m : map)
		{
			System.out.println("Continent Name: "+m[0]);
			System.out.println("\tCountry Name: "+m[1]+", Neighbours: "+m[4]+", Owner: "+m[3]+", Armies: "+m[2]);
		}
	}
	
	/**
	 * Notify Observer(MapView) of the change in Observable
	 */
	public void updateMap(){
		setChanged();
		notifyObservers(this);
	}
	/**
	 * This method check if loaded map is valid.
	 * @return true if map is valid false if map is not valid
	 * */
	public boolean mapValidation() {
		for(MapNode m: this.mapData) {
			for(CountryNode c: m.getCountries()) {
				if(c.getNeighbourCountries()==null || c.getNeighbourCountries().length==0) {
					return false;
				}
				for(CountryNode n: c.getNeighbourCountries()) {
					if(!n.getNeighbours().contains(c)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
}
