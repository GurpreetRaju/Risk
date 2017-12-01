package risk.model.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import risk.model.gamemode.GameDriver;
import risk.model.map.CountryNode;
import risk.model.turnmanager.TurnManager;

/**
 * Class for Random player that implements the PlayerStrategy interface.
 * @author Amitt
 * @author Gunpreet
 * @version 1.3
 */
public class RandomStrategy implements PlayerStrategy {
	
	/**
	 * GameDriver instance for Cheater player.
	 */
	private GameDriver driver;
	
	/**
	 * Object of TurnManager class.
	 */
	private TurnManager turnManager;
	
	/**
	 * Count the number of attacks.
	 */
	private int countAttacks = 0;
	
	private int randomAttacknumber = 0;
	
	public RandomStrategy(GameDriver nDriver) {
		driver = nDriver;
		turnManager = driver.getTurnManager();
	}

	/**
	 * Reinforcement phase of random player that reinforces random a random country.
	 * @see risk.model.player.PlayerStrategy#reinforcementPhase(int, java.lang.String[])
	 */
	@Override
	public void reinforcementPhase(int armies, String[] countryList) {
		countAttacks = 0;
		reinforcement(armies, countryList);
		driver.nottifyObservers("Random Player has reinforced a random country");
		driver.changePhase();
	}

	/**
	 * Attack phase: random player  attacks a random number of times a random country.
	 * @see risk.model.player.PlayerStrategy#attackPhase(java.util.ArrayList)
	 */
	@Override
	public void attackPhase(ArrayList<String> countryList) {
		CountryNode randomCountry = driver.getCountry(countryList.get(new Random().nextInt(countryList.size())));
		if(randomCountry.getArmiesCount() > 1 && randomAttacknumber > countAttacks){
			countAttacks++;
			CountryNode aCountry = randomCountry;
			
			
			/*randomly select a country to be attacked.*/
			CountryNode dCountry = null;
			Collections.shuffle(aCountry.getNeighbours());
			for (CountryNode neighbour : aCountry.getNeighbours()) {
				if (!neighbour.getOwner().getName().equals(driver.getCurrentPlayer().getName())) {
					dCountry = neighbour;
					break;
				}
			}
			driver.announceAttack(aCountry.getCountryName(), dCountry.getCountryName());
		}		
		else{
			driver.nottifyObservers(driver.getTurnManager().getPhase());
			driver.changePhase();
		}

	}

	/**
	 * Fortification phase of random player: fortifies a random country  
	 * @see risk.model.player.PlayerStrategy#fortificationPhase(java.util.ArrayList)
	 */
	@Override
	public void fortificationPhase(ArrayList<String> countryList) {
		fortify(countryList);
		driver.nottifyObservers("Random player has fortified a random country");
		driver.changePhase();
	}

	/**
	 * Distribute armies in startup phase.
	 */
	public String placeArmy(String[] strings, String string) {
		return strings[new Random().nextInt(strings.length)];
	}

	/**
	 * {@inheritDoc}
	 */
	public int selectDiceNumber(int diceToRoll, String pName) {
		return diceToRoll;
	}

	/**
	 * {@inheritDoc}
	 */
	public int moveArmies(int aArmies, int maxArmies, String message) {
		return new Random().nextInt(maxArmies+1-aArmies) + aArmies;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getStrategyName() {
		return "random";
	}
	
	/**
	 * Reinforcement phase impleemntation
	 * @param armies number of armies to be placed
	 * @param countryList list of countries player owns
	 */
	public void reinforcement(int armies, String[] countryList) {
		randomAttacknumber = new Random().nextInt(6);
		String country = countryList[new Random().nextInt(countryList.length)];
		driver.getCurrentPlayer().shiftArmiesOnReinforcement(country, armies);
	}
	
	/**
	 * Fortification implementation
	 * @param countryList list of countries that can be fortfied
	 */
	public void fortify(ArrayList<String> countryList) {
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
		CountryNode neighbour = country.getNeighbours().get(new Random().nextInt(country.getNeighbours().size()));
		driver.getCurrentPlayer().getArmiesShiftedAfterFortification(country.getCountryName(), neighbour.getCountryName(), armies);
	}

}
