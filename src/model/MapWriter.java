package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MapWriter {

	public void writeMap(ArrayList<MapNode> map) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
		Date date = new Date();
		String FILENAME = System.getProperty("user.dir") + "\\data\\map\\map-"+ dateFormat.format(date) + ".map";
		
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

	};


	public void writeMapExisting(ArrayList<MapNode> map, String path) {
		//erase the contents of file
		
		String FILENAME = path;
		
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
}