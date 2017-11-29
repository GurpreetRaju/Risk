package risk.model.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import risk.model.gamemode.GameDriver;
import risk.model.map.CountryNode;

public class BenevolentStrategy implements PlayerStrategy {
	
	/**
	 * GameDriver
	 */
	GameDriver driver = new GameDriver();
	Player player = driver.getCurrentPlayer();
	ArrayList<CountryNode> countries = player.getCountries();
	
	@Override
	public void reinforcementPhase(int armies, String[] countryList) {
		
		//sort countries according to armies count.
		Collections.sort(countries, new Comparator<CountryNode>(){

			@Override
			public int compare(CountryNode o1, CountryNode o2) {
				return o1.getArmiesCount() - o2.getArmiesCount();
			}
		}); 
		
		//get the list of weak countries.
		int countOfWeakCountries = 1;
		ArrayList<CountryNode> weakCountryList = new ArrayList<CountryNode>();
		weakCountryList.add(countries.get(0));
		for(int i= 1; i < countries.size(); i++){
			if(countries.get(i).getArmiesCount() == countries.get(i-1).getArmiesCount()){
				weakCountryList.add(countries.get(i));
				countOfWeakCountries++;
			}
			else{
				break;
			}
		}
		
		//get the integer round-off of the armies to be alloted to each weak country.
		int armiesToBeReinforced = (int)(player.getArmiesCount()/countOfWeakCountries);
		for( CountryNode country: weakCountryList){
			country.addArmy(armiesToBeReinforced);
			player.removeArmies(armiesToBeReinforced);
		}
		
		//Move the armies remaining into the first weakest country in the list.
		int playerArmiesLeft = player.getArmiesCount();
				
		if(!(playerArmiesLeft == 0)){
			weakCountryList.get(0).addArmy(playerArmiesLeft);
			player.removeArmies(playerArmiesLeft);
		}
		
		driver.changePhase();
	}

	@Override
	public void attackPhase(ArrayList<String> countryList) {
		//skip attack phase.
		driver.changePhase();
	}

	@Override
	public void fortificationPhase(ArrayList<String> countryList) {
		//fortify the weakest country.
		CountryNode weakest = countries.get(0);
		CountryNode strongest = countries.get(countries.size()-1);
		int average = (int)(weakest.getArmiesCount() + strongest.getArmiesCount()) / 2;
		weakest.addArmy(average);
		strongest.removeArmies(average);
		driver.changePhase();
	}

	@Override
	public String placeArmy(String[] strings, String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
