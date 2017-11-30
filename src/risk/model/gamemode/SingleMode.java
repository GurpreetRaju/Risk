package risk.model.gamemode;

import risk.controller.GameController;
import risk.view.MapView;

public class SingleMode implements Mode{

	public SingleMode(String map, String bmp, String[][] players, int moveLimit) {
		GameController controller = new GameController(map, bmp, players, moveLimit);
	}

	
	public SingleMode(String map, String[][] players, int i) {
		GameController controller = new GameController(map, players, i);
	}


	public void updateResults(String winnerPlayer) {
		
	}
	
	public void start() {
		
	}
	
}
