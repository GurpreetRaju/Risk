package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
		controlsGUI.addArmiesButtonAction(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controlsGUI.countrieslistAction(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String countryName = controlsGUI.getCountrySelected();
						CountryNode country = driver.getCountry(countryName);
						int armies = controlsGUI.getArmiesValue();
						country.addArmy(armies);
						driver.getCurrentPlayer().removeArmies(armies);
						
					}
				});
				
			}
		});
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
	
}
