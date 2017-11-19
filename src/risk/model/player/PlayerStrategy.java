package risk.model.player;

import java.util.ArrayList;

public interface PlayerStrategy {
	
	public void reinforcementPhase(int armies, String[] countryList);
	
	public void attackPhase(ArrayList<String> countryList);
	
	public void fortificationPhase(ArrayList<String> countryList);

}
