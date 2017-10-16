package model;

import java.util.ArrayList;

import controller.Controller;
import view.*;
/**
 * This class controls the turns - Startup phase, Fortification, reinfircement and attack phase.
 * 
 * @author Gurpreet
 * @version 1.0
 */
public class GameDriver {
	
	private static GameDriver driver;
	private PlayerInfoView playerInfoGUI;
	private Map map;
	private ArrayList<Player> players;
	private Controller controller;
	/**
	 * Constructor initialize the GUI and  map class object.
	 * Constructor is private so objects can not be created directly for this class.
	 */
	private GameDriver()
	{
		SetUpDialog mapBox = new SetUpDialog();
		String mapPath = mapBox.getMapInfo();
		map = new Map(mapPath);
		controller = new Controller(this);
	}
	/**
	 * <p>
	 * This method create <b>one and only one</b> instance of GameDriver class.
	 * This method is used to access only object of this class.
	 * </p>
	 * @return instance of GameDriver class.
	 */
	public static GameDriver getInstance()
	{
		if(driver==null)
		{
			driver = new GameDriver();
		}
		return driver;
	}
	/**
	 * This method starts the game.
	 */
	public void runGame()
	{
		startUpPhase();
		map.updateMap();
	}
	/**
	 * This method starts the startup phase of game.
	 * It assigns countries to players.
	 */
	public void startUpPhase()
	{
		SetUpDialog setupBox = new SetUpDialog();
        String[] newPlayerData = setupBox.getPlayerInfo();
        players = new ArrayList<Player>();
        for(String newPlayer: newPlayerData)
        {
        	players.add(new Player(newPlayer,RiskData.InitialArmiesCount.getArmiesCount(newPlayerData.length)));
        }
        updatePlayerView();
        int i = 0;
        for(MapNode m : map.getMapData()){
        	for(CountryNode c: m.getCountries()){
        		c.setOwner(players.get(i));
        		players.get(i).addCountry(c);
        		if(++i>=players.size())
        		{
        			i=0;
        		}
        	}
        }
        
        for(int i1=0;i1<players.get(0).getArmiesCount();i1++){
        	for(Player p: players){
        		String s;
        		if(p.getCountriesNamesNoArmy().length!=0){
        			s = setupBox.placeArmyDialog(p.getCountriesNamesNoArmy());
        		}
        		else{
        			s= setupBox.placeArmyDialog(p.getCountriesNames());
        		}
        		p.getCountry(s).addArmy(1);
        	}
        }
	}
	
	public void setPlayerView(PlayerInfoView newView){
		this.playerInfoGUI = newView;
	}
	
	public void setMapView(MapView newGui){
		map.addObserver(newGui);
	}
	
	/**
	 * This method show players information on GUI.
	 */
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
	
	/**
	 * @return current player 
	 */
	public Player getCurrentPlayer(){
		for(Player player:players){
			if(player.getTurn()){
				return player;
			}
		}
		return null;
	}
	
	
}
