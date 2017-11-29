package risk.model.player;

import java.util.ArrayList;
import java.util.Random;

import risk.model.gamemode.GameDriver;
import risk.model.map.CountryNode;

public class RandomStrategy implements PlayerStrategy {
	private GameDriver driver;

	@Override
	public void reinforcementPhase(int armies, String[] countryList) {
		CountryNode country = driver.getCountry(countryList[new Random ().nextInt(countryList.length)]);
		country.addArmy(armies);
		driver.getCurrentPlayer().setArmies(0);
		driver.changePhase();
	}

	@Override
	public void attackPhase(ArrayList<String> countryList) {
		// TODO Auto-generated method stub

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
