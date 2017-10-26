package risk.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * This class writes the map file.
 * @author Harinder
 * @author jyotsna
 */
public class MapWriter {
	
	/**
	 * stores the path of map file
	 */
	private String FILENAME;

	/**
	 * This function implements the file write operation
	 * @param map receives the MapNode with all the map details.
	 */
	public void writeMap(ArrayList<MapNode> map) {
		
		/**
		 * Date object for writing the date and time of creation map file
		 */
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		
		/**
		 * Stores the Date Object.
		 */
		Date date = new Date();
		
		/**
		 * FILENAME Stores the file name of the newly created map file.
		 */
		FILENAME = System.getProperty("user.dir") + "\\data\\map\\map-"+ dateFormat.format(date) + ".map";
		
		/**
		 * Stores BufferedWriter object.
		 */
		BufferedWriter bw = null;
		
		/**
		 * Stores FileWriter object.
		 */
		FileWriter fw = null;

		try {
			/*mapInfo stores basic map information to be written in map file.*/
			String mapInfo = "[Map]\r\n" + 
					"author=Iceworm72\r\n" + 
					"image=001_I72_Ghtroc 720.bmp\r\n" + 
					"wrap=no\r\n" + 
					"scroll=vertical\r\n" + 
					"warn=no\r\n\r\n";
			
			/*FileWriter object for writing character files.*/
			fw = new FileWriter(FILENAME);
			
			/*BufferedReader object to write text to character output stream.*/
			bw = new BufferedWriter(fw);

			bw.write(mapInfo);
			bw.write("[Continents]\r\n");
			for (MapNode node : map){
				bw.write(node.getContinentName() + "=" + Integer.toString(node.getControlValue()) + "\r\n");
			}
			bw.write("\r\n[Territories]\r\n");
			for (MapNode node : map) {
				for (CountryNode country : node.getCountries()) {
					String nCountryNames = "";
					for (CountryNode nCountry : country.getNeighbourCountries()) {
						nCountryNames = nCountryNames + "," + nCountry.getCountryName();
					}
					bw.write(country.getCountryName()+","+ Integer.toString(250) + "," + Integer.toString(250) + "," +
							node.getContinentName() + nCountryNames + "\r\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	};

	/**
	 * Function to write to existing map file
	 * @param map Continent MapNode containing map information 
	 * @param path contains path of existing map file
	 */
	public void writeMapExisting(ArrayList<MapNode> map, String path) {
		FILENAME = path;
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			String mapInfo = "[Map]\r\n" + 
					"author=Iceworm72\r\n" + 
					"image=001_I72_Ghtroc 720.bmp\r\n" + 
					"wrap=no\r\n" + 
					"scroll=vertical\r\n" + 
					"warn=no\r\n\r\n";

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write("");
			bw.write(mapInfo);
			bw.write("[Continents]\r\n");

			for (MapNode node : map) {
				bw.write(node.getContinentName() + "=" + Integer.toString(node.getControlValue()) + "\r\n");
			}
			
			bw.write("\r\n[Territories]\r\n");
			for (MapNode node : map) {
				for (CountryNode country : node.getCountries()) {
					String nCountryNames = "";
					for (CountryNode nCountry : country.getNeighbourCountries()) {
						nCountryNames = nCountryNames + "," + nCountry.getCountryName();
					}
					bw.write(country.getCountryName()+","+ Integer.toString(250) + "," + Integer.toString(250) + "," +
							node.getContinentName() + nCountryNames + "\r\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public String getMapFilePath() {
		return FILENAME;
	}
}