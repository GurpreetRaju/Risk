package risk.model.player;

import java.util.ArrayList;

import risk.model.gamemode.GameDriver;
import risk.model.map.CountryNode;

public class CheaterStrategy implements PlayerStrategy {

	private GameDriver driver;
	
	private Player player = driver.getCurrentPlayer();
	
	@Override
	public void reinforcementPhase(int armies, String[] countryList) {
		for (CountryNode country : player.getCountries()) {
			country.addArmy(country.getArmiesCount());
		}
		player.setArmies(0);
		driver.changePhase();

	}

	@Override
	public void attackPhase(ArrayList<String> countryList) {
		for (CountryNode country : player.getCountries()) {
			for (CountryNode neighbour : country.getNeighbours()) {
				neighbour.setOwner(player);
			}
		}
	}

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

	@Override
	public String placeArmy(String[] strings, String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
