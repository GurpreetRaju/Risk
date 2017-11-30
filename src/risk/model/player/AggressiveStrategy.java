package risk.model.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import risk.controller.GameController;
import risk.model.gamemode.GameDriver;
import risk.model.map.CountryNode;
import risk.model.turnmanager.TurnManager;

/**
 * Class for Aggressive player that implements the PlayerStrategy interface.
 * @author Gunpreet
 * @version 1.3
 */
public class AggressiveStrategy implements PlayerStrategy {

	/**
	 * GameDriver instance for benevolent player.
	 */
	private GameDriver driver = new GameDriver();
	
	/**
	 * Object of TurnManager class.
	 */
	private TurnManager turnManager;
	
	
	/**
	 * Stores the list of country nodes of the player.
	 */
	ArrayList<CountryNode> countries = new ArrayList<CountryNode>();
	
	/**
	 * Stores the strongest country of the player.
	 */
	CountryNode strongest;
	
	/**
	 * Reinforcement phase of aggressive player that reinforces its strongest countries.
	 * @see risk.model.player.PlayerStrategy#reinforcementPhase(int, java.lang.String[])
	 */
	@Override
	public void reinforcementPhase(int armies, String[] countryList) {
		countries = driver.getPlayerCountries();
		/*sort countries according to armies count in descending order.*/
		Collections.sort(countries, new Comparator<CountryNode>(){

			@Override
			public int compare(CountryNode o1, CountryNode o2) {
				return o2.getArmiesCount() - o1.getArmiesCount();
			}
		}); 
		
		strongest = countries.get(0);
		
		/*get the list of strong countries.*/
		int countOfStrongCountries = 1;
		ArrayList<CountryNode> strongCountryList = new ArrayList<CountryNode>();
		strongCountryList.add(countries.get(0));
		for(int i= 1; i < countries.size(); i++){
			if(countries.get(i).getArmiesCount() == countries.get(i-1).getArmiesCount()){
				strongCountryList.add(countries.get(i));
				countOfStrongCountries++;
			}
			else{
				break;
			}
		}
		
		/*get the integer round-off of the armies to be alloted to each strong country.*/
		int armiesToBeReinforced = (int)(driver.getCurrentPlayer().getArmiesCount()/countOfStrongCountries);
		for( CountryNode country: strongCountryList){
			country.addArmy(armiesToBeReinforced);
			driver.getCurrentPlayer().removeArmies(armiesToBeReinforced);
		}
		
		/*Move the armies remaining into the first strong country in the list.*/
		int playerArmiesLeft = driver.getCurrentPlayer().getArmiesCount();
				
		if(!(playerArmiesLeft == 0)){
			strongCountryList.get(0).addArmy(playerArmiesLeft);
			driver.getCurrentPlayer().removeArmies(playerArmiesLeft);
		}
		
		driver.changePhase();

	}

	/**
	 * Attack phase: aggressive player always attack with it until it cannot attack anymore.
	 * @see risk.model.player.PlayerStrategy#attackPhase(java.util.ArrayList)
	 */
	@Override
	public void attackPhase(ArrayList<String> countryList) {
		if(strongest.getArmiesCount() > 1){
			CountryNode aCountry = strongest;
			
			/*calculate number of dice for attacker.*/
			int aArmies = aCountry.getArmiesCount();
			if(driver.getCurrentPlayer().getTurn() && aArmies>4) {
				aArmies = 3;
			}
			else if(driver.getCurrentPlayer().getTurn()) {
				aArmies -= 1;
			}
			else if(aArmies>2) {
				aArmies = 2;
			}
			
			/*randomly select a country to be attacked.*/
			CountryNode dCountry = null;
			Collections.shuffle(aCountry.getNeighbours());
			for (CountryNode neighbour : aCountry.getNeighbours()) {
				if (!neighbour.getOwner().getName().equals(driver.getCurrentPlayer().getName())) {
					dCountry = neighbour;
					break;
				}
			}
			
			/*calculate the number of dice for defender.*/
			int dArmies = dCountry.getArmiesCount();
			if(driver.getCurrentPlayer().getTurn() && dArmies>4) {
				dArmies = 3;
			}
			else if(driver.getCurrentPlayer().getTurn()) {
				dArmies -= 1;
			}
			else if(dArmies>2) {
				dArmies = 2;
			}
			
			/*find the attack result.*/
			ArrayList<Integer> aResults = driver.diceRoll(aArmies);
			ArrayList<Integer> dResults = driver.diceRoll(dArmies);
			driver.battle(dCountry, dCountry.getOwner(), aCountry, aArmies, dArmies, aResults, dResults);
			
			/*check if defender country can be occupied.*/
			if(dCountry.getArmiesCount()==0) {
				dCountry.setOwner(driver.getCurrentPlayer());
				turnManager.setWonCard(true);
				
				System.out.println("Country "+ dCountry.getCountryName() +" won by " + dCountry.getOwner().getName() + ", new armies "+dCountry.getArmiesCount());
				
				/*move countries from attacker country to newly acquired country.*/
				int moveArmies = 1;
				dCountry.addArmy(moveArmies);
				aCountry.removeArmies(moveArmies);
				if(driver.getMap().continentWonByPlayer(driver.getCurrentPlayer(), dCountry)) {
					driver.getCurrentPlayer().addContinent(dCountry.getContinent());
				}
			}
			driver.setPlayerOut(dCountry.getOwner());
			if(!driver.checkGameState()) {
				driver.continuePhase();
			}
			else {
				driver.announceGameOver(driver.getPlayers().get(0).getName());
			}
		}
		else{
			driver.changePhase();
		}
		
	}

	/**
	 * Fortification phase of aggressive player: maximizes aggregation of forces in one country.
	 * @see risk.model.player.PlayerStrategy#fortificationPhase(java.util.ArrayList)
	 */
	@Override
	public void fortificationPhase(ArrayList<String> countryList) {
		/*fortify the strongest country.*/
		CountryNode weakest = countries.get(countries.size()-1);
		int average = (int)(weakest.getArmiesCount() + strongest.getArmiesCount()) / 2;
		strongest.addArmy(average);
		weakest.removeArmies(average);
		driver.changePhase();

	}

	/**
	 * Distribute armies in startup phase.
	 */
	@Override
	public String placeArmy(String[] strings, String string) {
		return strings[new Random().nextInt(strings.length)];
	}

}
