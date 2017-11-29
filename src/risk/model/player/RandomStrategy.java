package risk.model.player;

import java.util.ArrayList;
import java.util.Random;

import risk.model.gamemode.GameDriver;
import risk.model.map.CountryNode;

public class RandomStrategy implements PlayerStrategy {
	private GameDriver driver;

	@Override
	public void reinforcementPhase(int armies, String[] countryList) {
		CountryNode country = driver.getCurrentPlayer().getCountries().get(new Random().nextInt(driver.getCurrentPlayer().getCountries().size()));
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
		// TODO Auto-generated method stub

	}

	@Override
	public String placeArmy(String[] strings, String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
