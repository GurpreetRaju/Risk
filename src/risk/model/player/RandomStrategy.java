package risk.model.player;

import java.util.ArrayList;
import java.util.Random;

import risk.model.gamemode.GameDriver;
import risk.model.map.CountryNode;

/**
 * Class for Random player that implements the PlayerStrategy interface.
 */
public class RandomStrategy implements PlayerStrategy {
	
	/**
	 * GameDriver instance for Cheater player.
	 */
	private GameDriver driver;

	/**
	 * Reinforcement phase of random player that reinforces random a random country.
	 * @see risk.model.player.PlayerStrategy#reinforcementPhase(int, java.lang.String[])
	 */
	@Override
	public void reinforcementPhase(int armies, String[] countryList) {
		CountryNode country = driver.getCountry(countryList[new Random ().nextInt(countryList.length)]);
		country.addArmy(armies);
		driver.getCurrentPlayer().setArmies(0);
		driver.changePhase();
	}

	/**
	 * Attack phase: random player  attacks a random number of times a random country.
	 * @see risk.model.player.PlayerStrategy#attackPhase(java.util.ArrayList)
	 */
	@Override
	public void attackPhase(ArrayList<String> countryList) {
		// TODO Auto-generated method stub

	}

	/**
	 * Fortification phase of random player: fortifies a random country  
	 * @see risk.model.player.PlayerStrategy#fortificationPhase(java.util.ArrayList)
	 */
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

	@Override
	public String placeArmy(String[] strings, String string) {
		return strings[new Random().nextInt(strings.length)];
	}

}
