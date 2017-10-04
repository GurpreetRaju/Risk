package model;

import java.util.ArrayList;

public class Map {
		
	private ArrayList<MapNode> mapData;
	
	public Map(String filename){
		MapReader reader = new MapReader();
		mapData = reader.readMap(filename);
	}
	
	public ArrayList<MapNode> getMapData(){
		return this.mapData;
	}
	
	public void updateMapData(){
		
	}
	
	public Object[][] getMapDataObject(){
		ArrayList<Object[]> newData = new ArrayList<Object[]>();
		for(MapNode m : mapData)
		{
			Object[] tempObject = new Object[5];
			tempObject[0] = m.getContinentName() +", "+ m.getControlValue();
			for(CountryNode n : m.getCountries())
			{
				tempObject[1] = n.getCountryName();
				String neighbours = "";
				for(String s: n.getNeighbourCountries())
				{
					neighbours = s + ", ";
				}
				tempObject[4] = neighbours;
				if(n.getOwner()!=null){
					tempObject[3] = n.getOwner();
				}
				else{
					tempObject[3] = "";
				}
				tempObject[2] = n.getArmiesCount();
			}
			
		}
		return newData.toArray(new Object[newData.size()][]);
	}
	
//	public static void main(String[] arg){
//		MapReader mr = new MapReader();
//		ArrayList<MapNode> map = mr.readMap("D:\\Gurpreet\\Study\\M eng\\SEM6\\SOEN6441\\project\\Equalizer.map");
//		for(MapNode m : map)
//		{
//			System.out.println("Continent Name: "+m.getContinentName()+", Control Value: "+m.getControlValue());
//			for(CountryNode n : m.getCountries())
//			{
//				System.out.println("\tCountry Name: "+n.getCountryName()+", Coordinates: "+ n.getCoordinates()[0] +":"+n.getCoordinates()[1]+", Neighbours: ");
//				for(String s: n.getNeighbourCountries())
//				{
//					System.out.println("\t\t"+s);
//				}
//			}
//		}
//	}
	
}
