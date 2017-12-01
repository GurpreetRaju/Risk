package risk.model.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import risk.model.gamemode.GameDriver;
import risk.model.map.CountryNode;
import risk.model.turnmanager.TurnManager;
import sun.security.krb5.internal.ccache.CCacheInputStream;

/**
 * Class for Aggressive player that implements the PlayerStrategy interface.
 * @author Gunpreet
 * @version 1.3
 */
public class AggressiveStrategy implements PlayerStrategy {

	/**
	 * GameDriver instance for benevolent player.
	 */
	private GameDriver driver;
	
	/**
	 * Object of TurnManager class.
	 */
	private TurnManager turnManager;
	
	public AggressiveStrategy(GameDriver nDriver) {
		driver = nDriver;
		turnManager = driver.getTurnManager();
	}
	
	/**
	 * Reinforcement phase of aggressive player that reinforces its strongest countries.
	 * @see risk.model.player.PlayerStrategy#reinforcementPhase(int, java.lang.String[])
	 */
	@Override
	public void reinforcementPhase(int armies, String[] countryList) {
		ArrayList<CountryNode> countries = new ArrayList<CountryNode>();
		/*get country node for corresponding country name.*/
		for(String c: countryList){
			countries.add(driver.getCountry(c));
		}
		/*sort countries according to armies count in descending order.*/
		countries = sortCountries(countries);
		CountryNode strongest = countries.get(0);
		driver.shiftArmiesOnReinforcement(strongest.getCountryName(), armies);
	}

	/**
	 * Attack phase: aggressive player always attack with it until it cannot attack anymore.
	 * @see risk.model.player.PlayerStrategy#attackPhase(java.util.ArrayList)
	 */
	@Override
	public void attackPhase(ArrayList<String> countryList) {
		ArrayList<CountryNode> countries = new ArrayList<CountryNode>();
		/*get country node for corresponding country name.*/
		for(String c: countryList){
			countries.add(driver.getCountry(c));
		}
		
		/*sort countries according to armies count in descending order.*/
		countries = sortCountries(countries);
		CountryNode aCountry = countries.get(0);
		
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

	/**
	 * Fortification phase of aggressive player: maximizes aggregation of forces in one country.
	 * @see risk.model.player.PlayerStrategy#fortificationPhase(java.util.ArrayList)
	 */
	@Override
	public void fortificationPhase(ArrayList<String> countryList) {
		if(countryList.size()>1) {
			ArrayList<CountryNode> countries = new ArrayList<CountryNode>();
			/*get country node for corresponding country name.*/
			for(String c: countryList){
				countries.add(driver.getCountry(c));
			}
			
			/*sort countries according to armies count in descending order.*/
			countries = sortCountries(countries);
			
			CountryNode strongest = countries.get(0);
			CountryNode c = countries.get(1);
			/*fortify the strongest country.*/
			if(c.getArmiesCount()>1) {
				int mArmies = c.getArmiesCount()-1;
				driver.getArmiesShiftedAfterFortification(strongest.getCountryName(), c.getCountryName(), mArmies);
				driver.nottifyObservers(driver.getTurnManager().getPhase());
			}
			driver.changePhase();
		}
	}

	/**
	 * Distribute armies in startup phase.
	 */
	@Override
	public String placeArmy(String[] strings, String string) {
		return strings[new Random().nextInt(strings.length)];
	}

	/**
	 * Sort countries in descending order as per the armies.
	 * @param countryList list of country nodes to be sorted.
	 * @return sorted list of country nodes.
	 */
	private ArrayList<CountryNode> sortCountries(ArrayList<CountryNode> countryList){
		Collections.sort(countryList, new Comparator<CountryNode>(){
			@Override
			public int compare(CountryNode o1, CountryNode o2) {
				return o2.getArmiesCount() - o1.getArmiesCount();
			}
		});
		return countryList;
	}

	
	public int selectDiceNumber(int diceToRoll, String name) {
		return diceToRoll; //Assuming player chooses maximum number of dice to roll
	}

	@Override
	public int moveArmies(int aArmies, int maxArmies, String message) {
		return new Random().nextInt(maxArmies+1-aArmies) + aArmies;
	}
	
	@Override
	public String getStrategyName() {
		return "aggressive";
	}

}
