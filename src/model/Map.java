package Model;

import java.util.ArrayList;

public class Map {
		
	private ArrayList<MapNode> mapData;
	
	public Map(String filename)
	{
		MapReader reader = new MapReader();
		mapData = reader.readMap(filename);
	}
	
	public ArrayList<MapNode> getMapData()
	{
		return this.mapData;
	}
	
	public void updateMapData()
	{
		
	}
	
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
				for(String s: n.getNeighbourCountries())
				{
					neighbours = neighbours + s + ", ";
				}
				tempObject[4] = neighbours;
				if(n.getOwner()!=null){
					tempObject[3] = n.getOwner();
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
