package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import model.CountryNode;
import model.GameDriver;
import model.Map;
import model.Player;
import view.CardsView;
import view.ControlsView;
import view.DiceRollView;
import view.MainView;
import view.MapView;
import view.PlayerInfoView;
import view.SetUpDialog;

public class Controller 
{
	
	private GameDriver driver;
	private CardsView cardsGUI;
	private ControlsView controlsGUI;
	private DiceRollView diceRollGUI;
	private MapView mapGUI;
	private PlayerInfoView playerInfoGUI;
	private SetUpDialog setupBox;
	private ActionListener addArmiesListner;
	private ActionListener countryListListner;
	private String countryName;
	
	public Controller(GameDriver newDriver)
	{
		this.driver = newDriver;
		setupBox = new SetUpDialog();
		driver.createMapObject(setupBox.getMapInfo("map"));
		playerInfoGUI = new PlayerInfoView();
        mapGUI = new MapView(setupBox.getMapInfo("bmp"));
        diceRollGUI = new DiceRollView();
        cardsGUI = new CardsView();
        controlsGUI = new ControlsView();
        MainView.createInstance(playerInfoGUI, mapGUI, diceRollGUI, cardsGUI, controlsGUI);
        driver.setPlayerView(playerInfoGUI);
		driver.setMapView(mapGUI);
		driver.setControlsView(controlsGUI);
	}
	
	public String[] getPlayerInfo(){
		return setupBox.getPlayerInfo();
	}

	public String placeArmyDialog(String[] countriesNamesNoArmy) 
	{
		return setupBox.placeArmyDialog(countriesNamesNoArmy);
	}
	
	public GameDriver getGameDriver()
	{
		return this.driver;
	}
	
	public void setActionListner()
	{
		
		addArmiesListner = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				CountryNode country = driver.getCountry(controlsGUI.getCountrySelected());
				int armies = controlsGUI.getArmiesValue();
				System.out.println(armies);
				System.out.println(country.getCountryName());
				country.addArmy(armies);
				System.out.println(country.getArmiesCount());
				//driver.getCurrentPlayer().removeArmies(armies);
				driver.getCurrentPlayer().removeArmies(armies);
				System.out.println(driver.getPlayerArmies());
				driver.continuePhase();
			}
		};
		//call reinforcement phase first
		controlsGUI.addArmiesButtonAction(this.addArmiesListner);
		
		controlsGUI.endPhaseAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				driver.changePhase();
			}
		});
	}
	
	public void setFortificationListeners(){
		controlsGUI.countrieslistAction(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent e) {
				String countrySelected = (String) controlsGUI.getCountrySelected();
				CountryNode countrySelect = GameDriver.getInstance().getCurrentPlayer().getCountry(countrySelected);
				if(countrySelect.getArmiesCount()>1){
					ArrayList<String> neighborList = new ArrayList<String>();
					for(String name: countrySelect.getNeighbourCountriesString()){
						neighborList.add(name);
					}
					controlsGUI.updateFortification(countrySelect.getArmiesCount(), neighborList.toArray(new String[neighborList.size()]));
				}
			}
		});
		
		controlsGUI.playButtonAction(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(controlsGUI.isNeighbourSelected()) {
					String countrySelected = (String) controlsGUI.getCountrySelected();
					int selectedArmies = controlsGUI.getArmiesValue();
					CountryNode countrySelect = GameDriver.getInstance().getCurrentPlayer().getCountry(countrySelected);
					String neighbourSelected = controlsGUI.getNeighborSelected();
					countrySelect.setArmies(countrySelect.getArmiesCount()-selectedArmies); 
					for(CountryNode j : countrySelect.getNeighbourCountries()){
						if(j.getCountryName() == neighbourSelected){
							j.setArmies(j.getArmiesCount() + selectedArmies);
						}
					}
				}
				driver.changePhase();
			}
		});
	}
	
}
