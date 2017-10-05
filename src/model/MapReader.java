package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapReader {
	
	public ArrayList<MapNode> readMap(String filename)
	{	
		BufferedReader br = null;
		FileReader fr = null;
		ArrayList<MapNode> map = new ArrayList<MapNode>();
		try 
		{
			String sCurrentLine;
			fr = new FileReader(filename);
			br = new BufferedReader(fr);
			boolean t = false;
			boolean c = false;
			while ((sCurrentLine = br.readLine()) != null) 
			{
				if(!sCurrentLine.equals(""))
				{
					if(sCurrentLine.contains("[Territories]"))
					{
						t = true;
						c = false;
						continue;
					}
					if(sCurrentLine.contains("[Continents]"))
					{
						c = true;
						t = false;
						continue;
					}
					if(c)
					{
						int indexEqualTo = sCurrentLine.indexOf("=");
						map.add(new MapNode(sCurrentLine.substring(0, indexEqualTo), null, Integer.parseInt(sCurrentLine.substring(indexEqualTo+1).trim())));
					}
					else if(t)
					{
						String[] temp = sCurrentLine.split(",");
						for(MapNode n : map)
						{
							if(n.getContinentName().equals(temp[3]))
							{
								int[] newCoordinates = {Integer.parseInt(temp[1]),Integer.parseInt(temp[2])};
								CountryNode newCountry = new CountryNode(temp[0], null, newCoordinates);
								for(int i=4;i<temp.length;i++){
									newCountry.addNeighbour(temp[i]);
								}
								n.addCountry(newCountry);
								break;
							}
						}
					}
				}
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} 
			catch (IOException ex) 
			{
				ex.printStackTrace();
			}
		}
		return map;
	}
		
}
