package controller;

import Model.Map;
import View.*;

public class GameDriver {
	
	private static GameDriver driver;
	private MainView gameView;
	private CardsView cardsGUI;
	private ControlsView controlsGUI;
	private DiceRollView diceRollGUI;
	private MapView mapGUI;
	private PlayerInfoView playerInfoGUI;
	private Map map;
	
	private GameDriver()
	{
        playerInfoGUI = new PlayerInfoView();
        mapGUI = new MapView();
        diceRollGUI = new DiceRollView();
        cardsGUI = new CardsView();
        controlsGUI = new ControlsView();
        MainView.createInstance(playerInfoGUI, mapGUI, diceRollGUI, cardsGUI, controlsGUI);
		gameView = MainView.getInstance();
		map = new Map("D:\\Gurpreet\\Study\\M eng\\SEM6\\SOEN6441\\project\\Equalizer.map");
	}
	
	public static GameDriver getInstance()
	{
		if(driver==null)
		{
			driver = new GameDriver();
		}
		return driver;
	}
	
	public void runGame()
	{
		refreshMap();
		startUpPhase();
		refreshMap();
	}
	
	public void startUpPhase()
	{
		SetUpDialog setupBox = new SetUpDialog();
        setupBox.getPlayerInfo();
	}
	
	public void refreshMap()
	{
		mapGUI.setMap(map.getMapDataObject());
	}
	
}
