package risk.model.gamemode;

import risk.controller.GameController;
import risk.view.MapView;

public class SingleMode implements Mode{

	public SingleMode(String map, String bmp, String[] players, String[] behaviors, int moveLimit) {
		GameController controller = new GameController(map, bmp, players, behaviors, moveLimit);
	}

	
	public SingleMode(String map, String[] players, String[] playerBehavior, int i) {
		GameController controller = new GameController(map, players, playerBehavior, i);
	}


	public void updateResults(String winnerPlayer) {
		
	}
	
	public void start() {
		
	}
	
}
