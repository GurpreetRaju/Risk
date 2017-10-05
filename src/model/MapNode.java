package model;

import java.util.ArrayList;

public class MapNode {
	
	private String continentName;
	private ArrayList<CountryNode> countries;
	private int controlValue;
	
	public MapNode(String newName,ArrayList<CountryNode> newCountries,int newValue)
	{
		this.continentName = newName;
		this.countries = newCountries;
		this.controlValue = newValue;
	}
	
	public String getContinentName()
	{
		return this.continentName;
	}
	
	public CountryNode[] getCountries()
	{
		return this.countries.toArray(new CountryNode[this.countries.size()]);
	}
	
	public int getControlValue(){
		return this.controlValue;
	}
	
	public void setCountries(ArrayList<CountryNode> newCountries){
		this.countries = newCountries;
	}
	
	public void addCountry(CountryNode newCountry){
		if(this.countries==null){
			this.countries = new ArrayList<CountryNode>();
		}
		this.countries.add(newCountry);
	}
	
}
