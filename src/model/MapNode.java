package model;

import java.util.ArrayList;

/**
 * This class stores the map data.
 * @author Gurpreet
 * @version 1.0
 */
public class MapNode {
	
	/**
	 * name of continent.
	 */
	private String continentName;
	
	/**
	 * ArrayList containing countries of this continent.
	 */
	private ArrayList<CountryNode> countries;
	
	/**
	 * Store control value of the continent
	 */
	private int controlValue;
	
	/**
	 * Initialize attributes of a continent.
	 * @param newName name of continent
	 * @param newCountries arraylist containing countries belong to this continent
	 * @param newValue control value for the continent
	 */
	public MapNode(String newName,ArrayList<CountryNode> newCountries,int newValue) {
		this.continentName = newName;
		this.countries = newCountries;
		this.controlValue = newValue;
	}
	
	 /** 
	 * return ArrayList of countries in the continent
	 * @return countries ArrayList containing countries of this continent.
	 */
	public ArrayList<CountryNode> getCountryList(){
		return this.countries;
	}
	
	/**
	 * return name of continent.
	 * @return String continent name
	 */
	public String getContinentName() {
		return this.continentName;
	}
	
	/**
	 * return array of countries belong to this continent.
	 * @return Array of countries
	 */
	public CountryNode[] getCountries() {
		return this.countries.toArray(new CountryNode[this.countries.size()]);
	}
	
	/**
	 * return control value for this continent
	 * @return integer control value of continent
	 */
	public int getControlValue() {
		return this.controlValue;
	}
	
	/**
	 * Assign list of countries to current continent.
	 * @param newCountries ArrayList containing countries.
	 */
	public void setCountries(ArrayList<CountryNode> newCountries) {
		this.countries = newCountries;
	}
	
	/**
	 * Remove country node from the list of countries.
	 * @param countryNode the node to be removed.
	 */
	public void removeCountry(CountryNode countryNode) {
		countries.remove(countryNode);
	}
	
	/**
	 * Add country to the continent.
	 * @param newCountry country object
	 */
	public void addCountry(CountryNode newCountry) {
		if(this.countries==null){
			this.countries = new ArrayList<CountryNode>();
		}
		this.countries.add(newCountry);
	}
	
	/**
	 * Check if two MapNode objects are equal
	 * @return true if both objects are equal; false if both objects are not equal
	 */
	public boolean equals(Object o) {
		if(o instanceof MapNode) {
			MapNode temp = (MapNode) o;
			if(temp.getContinentName()==this.continentName && temp.controlValue == this.controlValue) {
				if(temp.countries.size()==this.countries.size()) {
					for(int i=0;i<this.getCountries().length;i++) {
						if(!this.getCountries()[i].equals(temp.getCountries()[i])) {
							return false;
						}
					}
					return true;
				}
			}
		}
		return false;
	}
}
