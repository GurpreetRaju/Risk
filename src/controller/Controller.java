package controller;

import java.util.ArrayList;

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

public class Controller {
	
	private GameDriver driver;
	private CardsView cardsGUI;
	private ControlsView controlsGUI;
	private DiceRollView diceRollGUI;
	private MapView mapGUI;
	private PlayerInfoView playerInfoGUI;
	
	public Controller(GameDriver newDriver){
		this.driver = newDriver;
		SetUpDialog setupBox = new SetUpDialog();
		driver.createMapObject(setupBox.getMapInfo("map"));
		playerInfoGUI = new PlayerInfoView();
        mapGUI = new MapView(setupBox.getMapInfo("bmp"));
        diceRollGUI = new DiceRollView();
        cardsGUI = new CardsView();
        controlsGUI = new ControlsView();
        MainView.createInstance(playerInfoGUI, mapGUI, diceRollGUI, cardsGUI, controlsGUI);
        setupBox.getPlayerInfo();
        driver.setPlayerView(playerInfoGUI);
		driver.setMapView(mapGUI);
		driver.setControlsView(controlsGUI);
		driver.runGame();
	}

	public String placeArmyDialog(String[] countriesNamesNoArmy) {
		SetUpDialog setupBox = new SetUpDialog();
		return setupBox.placeArmyDialog(countriesNamesNoArmy);
	}
	
}
