package risk.model.player;

import java.util.ArrayList;
import java.util.Random;

import risk.model.gamemode.GameDriver;
import risk.model.map.CountryNode;

/**
 * Class for Cheater player that implements the PlayerStrategy interface.
 * @author Amitt
 * @version 1.3
 */
public class CheaterStrategy implements PlayerStrategy {

	/**
	 * GameDriver instance for Cheater player.
	 */
	private GameDriver driver;
	

	/**
	 * Stores the current player.
	 */
	private Player player = driver.getCurrentPlayer();
	
	/**
	 * Reinforcement phase of cheater player that doubles the number of armies on all its countries
	 * @see risk.model.player.PlayerStrategy#reinforcementPhase(int, java.lang.String[])
	 */
	@Override
	public void reinforcementPhase(int armies, String[] countryList) {
		for (CountryNode country : player.getCountries()) {
			country.addArmy(country.getArmiesCount());
		}
		player.setArmies(0);
		driver.changePhase();

	}

	/**
	 * Attack phase: cheater player automatically conquers all the neighbors of all its countries.
	 * @see risk.model.player.PlayerStrategy#attackPhase(java.util.ArrayList)
	 */
	@Override
	public void attackPhase(ArrayList<String> countryList) {
		for (CountryNode country : player.getCountries()) {
			for (CountryNode neighbour : country.getNeighbours()) {
				neighbour.setOwner(player);
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
		for (CountryNode country : player.getCountries()) {
			for (CountryNode neighbour : country.getNeighbours()) {
				if (!neighbour.getOwner().getName().equals(player.getName())) {
					country.addArmy(country.getArmiesCount());
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
