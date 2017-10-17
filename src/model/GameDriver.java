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
public class GameDriver 
{
	private static GameDriver driver;
	private PlayerInfoView playerInfoGUI;
	private Map map;
	private ArrayList<Player> players;
	private Controller controller;
	private ControlsView controlsGUI;
	/**
	 * Constructor initialize the GUI and  map class object.
	 * Constructor is private so objects can not be created directly for this class.
	 */
	private GameDriver()
	{
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
		this.controlsGUI.reinforcementConrols(getCurrentPlayer().getArmies(), getCurrentPlayer().getCountriesNames());
	}
	/**
	 * This method starts the startup phase of game.
	 * It assigns countries to players.
	 */
	public void startUpPhase()
	{
		String[] newPlayerData = controller.getPlayerInfo();
        players = new ArrayList<Player>();
        for(String newPlayer: newPlayerData)
        {
        	players.add(new Player(newPlayer,RiskData.InitialArmiesCount.getArmiesCount(newPlayerData.length)));
        }
        players.get(0).setTurnTrue();
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
        
        for(int i1=0;i1<players.get(0).getArmiesCount();i1++)
        {
        	for(Player p: players)
        	{
        		String s;
        		if(p.getCountriesNamesNoArmy().length!=0)
        		{
        			s = controller.placeArmyDialog(p.getCountriesNamesNoArmy());
        		}
        		else
        		{
        			s= controller.placeArmyDialog(p.getCountriesNames());
        		}
        		p.getCountry(s).addArmy(1);
        	}
        }
        map.updateMap();
	}
	
	public void setPlayerView(PlayerInfoView newView)
	{
		this.playerInfoGUI = newView;
	}
	
	public void setMapView(MapView newGui)
	{
		map.addObserver(newGui);
	}
	
	public void setControlsView(ControlsView controlView)
	{
		this.controlsGUI = controlView;
	}
	
	/**
	 * This method show players information on GUI.
	 */
	public void updatePlayerView()
	{
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
	public Player getCurrentPlayer()
	{
		for(Player player:players)
		{
			if(player.getTurn())
			{
				return player;
			}
		}
		return null;
	}
	
	public void setNextPlayerTurn()
	{
		int currentPlayerIndex = players.indexOf(getCurrentPlayer());
		getCurrentPlayer().setTurnFalse();
		if (currentPlayerIndex == players.size()-1)
		{
			players.get(0).setTurnTrue();
		}
		else
		{
			players.get(currentPlayerIndex+1).setTurnTrue();
		}
	}
	
	public void createMapObject(String mapPath)
	{
		map = new Map(mapPath);
	}
	
	public String [] getNeighbourCountryNames(String countryname)
	{
		for(CountryNode country: getCurrentPlayer().getCountries())
		{
			if(country.getCountryName().equals(countryname))
			{
				return country.getNeighbourCountriesString();
			}
		}
		return null;
	}
	
	public int getPlayerArmies()
	{
		return getCurrentPlayer().getArmies();
	}
	
	public String [] getPlayerCountryNames()
	{
		return getCurrentPlayer().getCountriesNames();
	}
	
	public ArrayList<CountryNode> getPlayerCountries()
	{
		return getCurrentPlayer().getCountries();
	}
	
	public CountryNode [] getNeighbourCountries(CountryNode countrynode)
	{
		for(CountryNode country: getCurrentPlayer().getCountries())
		{
			if(country.getCountryName().equals(countrynode.getCountryName()))
			{
				return country.getNeighbourCountries();
			}
		}
		return null;
	}
	
	public CountryNode getCountry(String countryname)
	{
		for(CountryNode country : getCurrentPlayer().getCountries())
		{
			if(country.getCountryName().equals(countryname))
			{
				return country;
			}
		}
		
		return null;
	}
}
