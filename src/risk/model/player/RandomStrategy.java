package risk.model.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import risk.model.gamemode.GameDriver;
import risk.model.map.CountryNode;
import risk.model.turnmanager.TurnManager;

public class RandomStrategy implements PlayerStrategy {
	private GameDriver driver;
	
	/**
	 * Object of TurnManager class.
	 */
	private TurnManager turnManager;
	
	/**
	 * Stores the current player.
	 */
	Player player = driver.getCurrentPlayer();
	
	/**
	 * Count the number of attacks.
	 */
	private int countAttacks = 0;

	@Override
	public void reinforcementPhase(int armies, String[] countryList) {
		countAttacks = 0;
		CountryNode country = driver.getCountry(countryList[new Random().nextInt(countryList.length)]);
		country.addArmy(armies);
		driver.getCurrentPlayer().setArmies(0);
		driver.changePhase();
	}

	@Override
	public void attackPhase(ArrayList<String> countryList) {
		CountryNode randomCountry = driver.getCountry(countryList.get(new Random().nextInt(countryList.size())));
		int numberOfAttacks = new Random().nextInt(30);
		if(randomCountry.getArmiesCount() > 1 && numberOfAttacks > countAttacks){
			countAttacks++;
			CountryNode aCountry = randomCountry;
			
			/*calculate number of dice for attacker.*/
			int aArmies = aCountry.getArmiesCount();
			if(player.getTurn() && aArmies>4) {
				aArmies = 3;
			}
			else if(player.getTurn()) {
				aArmies -= 1;
			}
			else if(aArmies>2) {
				aArmies = 2;
			}
			
			/*randomly select a country to be attacked.*/
			CountryNode dCountry = null;
			Collections.shuffle(aCountry.getNeighbours());
			for (CountryNode neighbour : aCountry.getNeighbours()) {
				if (!neighbour.getOwner().getName().equals(player.getName())) {
					dCountry = neighbour;
					break;
				}
			}
			
			/*calculate the number of dice for defender.*/
			int dArmies = dCountry.getArmiesCount();
			if(player.getTurn() && dArmies>4) {
				dArmies = 3;
			}
			else if(player.getTurn()) {
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
				dCountry.setOwner(player);
				turnManager.setWonCard(true);
				
				System.out.println("Country "+ dCountry.getCountryName() +" won by " + dCountry.getOwner().getName() + ", new armies "+dCountry.getArmiesCount());
				
				/*move countries from attacker country to newly acquired country.*/
				int moveArmies = 1;
				dCountry.addArmy(moveArmies);
				aCountry.removeArmies(moveArmies);
				if(driver.getMap().continentWonByPlayer(player, dCountry)) {
					player.addContinent(dCountry.getContinent());
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

	@Override
	public void fortificationPhase(ArrayList<String> countryList) {
		ArrayList<CountryNode> countries = new ArrayList<CountryNode>() ;
		for (String countryName : countryList) {
			if (driver.getCountry(countryName).getArmiesCount() > 1) {
				countries.add(driver.getCountry(countryName));
			}
		}
		CountryNode country = countries.get(new Random ().nextInt(countries.size()));
		int armies = new Random().nextInt(country.getArmiesCount()+1)-1;
		if (armies == 0) {
			armies = 1;
		}
		country.getNeighbours().get(new Random().nextInt(country.getNeighbours().size())).addArmy(armies);
		country.removeArmies(armies);
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
