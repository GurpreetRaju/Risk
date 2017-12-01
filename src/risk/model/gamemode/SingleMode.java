package risk.model.gamemode;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import risk.controller.GameController;
import risk.controller.MainController;

public class SingleMode implements Mode{

	private MainController mController;
	private static String myMap;
	private String myBmp;
	private String[][] myPlayers;
	private int myMoveLimit;
	private GameController controller;
	
	/**
	 * Constructor for initializing the instance variables.
	 * @param map stores .map file path
	 * @param bmp stores .bmp file path
	 * @param players player data.
	 * @param moveLimit number of moves allowed.
	 * @param nController MainController object.
	 */
	public SingleMode(String map, String bmp, String[][] players, int moveLimit, MainController nController) {
		mController = nController;
		myMap = map;
		myBmp = bmp;
		myPlayers = players;
		myMoveLimit = moveLimit;
	}

	/**
	 * Constructor for initializing the instance variables.
	 * @param map map file path
	 * @param players player data
	 * @param moveLimit number of turns
	 * @param nController MainController instance.
	 */
	public SingleMode(String map, String[][] players, int moveLimit, MainController nController) {
		mController = nController;
		myMap = map;
		myPlayers = players;
		myMoveLimit = moveLimit;
	}

	public SingleMode() {}

	/**
	* udating results using the winner player
	*/
	public void updateResults(String winnerPlayer) {
		String[][] data = {{myMap , winnerPlayer}};
		mController.setResults(data);
	}
	
	/**
	* initializing the instance variables depending upon condition 
	*/
	public void start() {
		if(myBmp!=null) {
			controller = new GameController(myMap, myBmp, myPlayers, myMoveLimit);
		}
		else {
			controller = new GameController(myMap, myPlayers, myMoveLimit);
		}
	}
	
	public static void main(String[] arg) {
		String[][] myPs = {{"Gur","human"},{"Raj","human"}};
//		SingleMode s = new SingleMode("D:\\Gurpreet\\Study\\Meng\\Projects\\Risk\\data\\map\\World2005.map", "D:\\Gurpreet\\Study\\Meng\\Projects\\Risk\\data\\map\\World2005.bmp", myPs, 0, MainController.getInstance());
//		MainController.getInstance().setMode(s);
//		s.start();
		SingleMode s = new SingleMode();
		s.loadGameDataFromFile(new File("D:\\Gurpreet\\Study\\Meng\\Projects\\Risk\\SaveGame2017.11.30.23.36.53.sav"));
	}
	
	/**
	* @return map
	*/
	public static String getMapName(){
		return myMap;
	}
	
	public void loadGameDataFromFile(File file){ 

		try{
			FileInputStream saveFile = new FileInputStream(file);
			ObjectInputStream save = new ObjectInputStream(saveFile);
			
			/*Map file path.*/
			String filePath = (String) save.readObject();
			
			/*Number of players.*/
			int playerCount = (int) save.readObject();
			
			/*Player data.*/
			String[][] players= new String[playerCount][2];
			ArrayList<ArrayList<String>> countryList = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<Integer>> armyCountList = new ArrayList<ArrayList<Integer>>();
			for(int i= 0; i< playerCount; i++){
				String[] player = new String[2];
				player[0] = (String) save.readObject();
				player[1] = (String) save.readObject();
				players[i] = player;
				ArrayList<String> countries = new ArrayList<String>();
				ArrayList<Integer> armies = new ArrayList<Integer>();
				int countryCount = (int) save.readObject();
				for(int j = 0; j < countryCount; j++){
					countries.add((String) save.readObject());
					armies.add((Integer) save.readObject());
				}
				countryList.add(countries);
				armyCountList.add(armies);
			}
			
			/*Current player.*/
			String currentPlayer = (String) save.readObject();
			
			/*Current phase.*/
			String phaseName = (String) save.readObject();

			controller = new GameController(filePath, players, countryList, armyCountList, currentPlayer, phaseName);

			save.close();
			
		}catch(Exception exc){
			System.out.println("Failed to load file\n"+exc);
		}
	}
	
}
