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

public class Controller {
	
	private GameDriver driver;
	private CardsView cardsGUI;
	private ControlsView controlsGUI;
	private DiceRollView diceRollGUI;
	private MapView mapGUI;
	private PlayerInfoView playerInfoGUI;
	private ArrayList<Player> players;
	
	public Controller(GameDriver newDriver){
		playerInfoGUI = new PlayerInfoView();
        mapGUI = new MapView();
        diceRollGUI = new DiceRollView();
        cardsGUI = new CardsView();
        controlsGUI = new ControlsView();
        MainView.createInstance(playerInfoGUI, mapGUI, diceRollGUI, cardsGUI, controlsGUI);
        this.driver = newDriver;
        init();
	}
	
	private void init(){
		driver.setPlayerView(playerInfoGUI);
		driver.setMapView(mapGUI);
	}
}
