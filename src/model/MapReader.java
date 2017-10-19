package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * This class read map file.
 * 
 * @author Gurpreet
 * @version 1.0
 *
 */
public class MapReader {
	
	/**
	 * This method reads the map file.
	 * @see MapNode
	 * @param filename URL of map file.
	 * @return map data in form of <code>ArrayList</code>
	 */
	public ArrayList<MapNode> readMap(String filename) {	
		BufferedReader br = null;
		FileReader fr = null;
		ArrayList<MapNode> map = new ArrayList<MapNode>();
		ArrayList<CountryNode> stack = new ArrayList<CountryNode>();
		
		try {
			String sCurrentLine;
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			boolean t = false;
			boolean c = false;
			while ((sCurrentLine = br.readLine()) != null){
				if(!sCurrentLine.equals("")){
					if(sCurrentLine.contains("[Territories]")){
						t = true;
						c = false;
						continue;
					}
					if(sCurrentLine.contains("[Continents]")){
						c = true;
						t = false;
						continue;
					}
					if(c){
						int indexEqualTo = sCurrentLine.indexOf("=");
						map.add(new MapNode(sCurrentLine.substring(0, indexEqualTo), null, Integer.parseInt(sCurrentLine.substring(indexEqualTo+1).trim())));
					}else if(t){
						String[] temp = sCurrentLine.split(",");
						for(MapNode n : map){
							if(n.getContinentName().equals(temp[3])){
								if(!CountryNode.contains(stack, temp[0])){
									stack.add(new CountryNode(temp[0], null, null));
								}
								
								CountryNode newCountry = CountryNode.getCountry(stack, temp[0]);
								int[] newCoordinates = {Integer.parseInt(temp[1]),Integer.parseInt(temp[2])};
								newCountry.setCoordinates(newCoordinates);
								if(temp.length>4) {
									for(int i=4;i<temp.length;i++){
										if(!CountryNode.contains(stack, temp[i])){
											stack.add(new CountryNode(temp[i], null, null));
										}
										newCountry.addNeighbour(CountryNode.getCountry(stack, temp[i]));
									}
								}
								n.addCountry(newCountry);
								break;
							}
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			}catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return map;		
	}
}
