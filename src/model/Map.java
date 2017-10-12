package model;

import java.util.ArrayList;

import view.MapView;
/**
 * This class perform functions related to map for example storing map data,
 * update map etc.
 * 
 * @author Gurpreet
 * @version 1.0
 */
public class Map {
	/**
	 * ArrayList containing map data.
	 * @see MapNode
	 */
	private ArrayList<MapNode> mapData;
	
	private MapView mapView;
	
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
	 * @return return map data in form of arraylist.
	 */
	public ArrayList<MapNode> getMapData()
	{
		return this.mapData;
	}
	
	public void addObserver(MapView newMapView){
		this.mapView = newMapView;
	}
	
	/**
	 * This method notify MapView to update countries information on GUI.
	 */
	public void updateMapView()
	{
		mapView.setMap(this.getMapDataObject());
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
	
}
