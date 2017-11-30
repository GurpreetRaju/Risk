package risk.model.player;

import java.util.ArrayList;
import java.util.Random;

import risk.model.gamemode.GameDriver;
import risk.model.map.CountryNode;

/**
 * Class for Cheater player that implements the PlayerStrategy interface.
 */
public class CheaterStrategy implements PlayerStrategy {

	/**
	 * GameDriver instance for Cheater player.
	 */
	private GameDriver driver;
	
	/**
	 * Reinforcement phase of cheater player that doubles the number of armies on all its countries
	 * @see risk.model.player.PlayerStrategy#reinforcementPhase(int, java.lang.String[])
	 */
	@Override
	public void reinforcementPhase(int armies, String[] countryList) {
		for (String country : countryList) {
			driver.getCountry(country).addArmy(driver.getCountry(country).getArmiesCount());
		}
		driver.getCurrentPlayer().setArmies(0);
		driver.changePhase();

	}

	/**
	 * Attack phase: cheater player automatically conquers all the neighbors of all its countries.
	 * @see risk.model.player.PlayerStrategy#attackPhase(java.util.ArrayList)
	 */
	@Override
	public void attackPhase(ArrayList<String> countryList) {
		for (String country : countryList) {
			for (CountryNode neighbour : driver.getCountry(country).getNeighbours()) {
				neighbour.setOwner(driver.getCurrentPlayer());
			}
		}
	}

	/**
	 * Fortification phase of cheater player: doubles the number of armies on 
	 * its countries that have neighbors that belong to other players.  
	 * @see risk.model.player.PlayerStrategy#fortificationPhase(java.util.ArrayList)
	 */
	@Override
	public void fortificationPhase(ArrayList<String> countryList) {
		for (String country : countryList) {
			for (CountryNode neighbour : driver.getCountry(country).getNeighbours()) {
				if (!neighbour.getOwner().equals(driver.getCurrentPlayer())) {
					driver.getCountry(country).addArmy(driver.getCountry(country).getArmiesCount());
				}
			}
		}
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
