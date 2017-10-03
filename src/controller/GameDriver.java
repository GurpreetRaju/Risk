package controller;

import view.*;

public class GameDriver {
	
	private static GameDriver driver;
	private MainView gameView;
	private CardsView cards;
	private ControlsView controls;
	private DiceRollView diceRoll;
	private MapView map;
	private PlayerInfoView playerInfo;
	
	private GameDriver()
	{
        playerInfo = new PlayerInfoView();
        map = new MapView();
        diceRoll = new DiceRollView();
        cards = new CardsView();
        controls = new ControlsView();
        MainView.createInstance(playerInfo, map, diceRoll, cards, controls);
		gameView = MainView.getInstance();
	}
	
	public static GameDriver getInstance()
	{
		if(driver==null)
		{
			driver = new GameDriver();
		}
		return driver;
	}
	
	public void runGame(){
		startUpPhase();
	}
	
	public void startUpPhase()
	{
		SetUpDialog setupBox = new SetUpDialog();
        setupBox.getPlayerInfo();
	}
	
}
