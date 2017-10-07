package controller;

import java.util.ArrayList;

import model.CountryNode;
import model.Map;
import model.MapNode;
import model.Player;
import view.*;

public class GameDriver {
	
	private static GameDriver driver;
	private MainView gameView;
	private CardsView cardsGUI;
	private ControlsView controlsGUI;
	private DiceRollView diceRollGUI;
	private MapView mapGUI;
	private PlayerInfoView playerInfoGUI;
	private Map map;
	private ArrayList<Player> players;
	
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
        String[] newPlayerData = setupBox.getPlayerInfo();
        players = new ArrayList<Player>();
        for(String newPlayer: newPlayerData)
        {
        	players.add(new Player(newPlayer));
        }
        updatePlayerView();
        int i = 0;
        for(MapNode m : map.getMapData())
        {
        	for(CountryNode c: m.getCountries())
        	{
        		c.setOwner(players.get(i));
        		players.get(i).addCountry(c);
        		if(++i>=players.size())
        		{
        			i=0;
        		}
        	}
        }
	}
	
	public void refreshMap()
	{
		mapGUI.setMap(map.getMapDataObject());
	}
	
	public void updatePlayerView(){
		String[] playerNames = new String[players.size()];
		int i=0;
		for(Player p: players)
		{
			playerNames[i] = p.getName();
			i++;
		}
		playerInfoGUI.setPlayerInfo(playerNames);
	}
	
}
