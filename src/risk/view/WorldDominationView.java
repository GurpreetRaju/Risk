package risk.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risk.model.GameDriver;
import risk.model.Player;

public class WorldDominationView extends JPanel implements Observer {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1084389704252002294L;


	public WorldDominationView() {
		JLabel label = new JLabel("<html><p><b>World Domination View</b></p><br/><br/></html>");
		this.setLayout(new FlowLayout());
		this.add(label);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	

	@Override
	public void update(Observable o, Object arg) {
		if(arg.equals("Reinforcement") || arg.equals("Fortification")){
			int totalCountries = GameDriver.getInstance().getMap().getCountryCount();
			for (Player player : GameDriver.getInstance().getPlayers()){
				this.add(new JLabel(" "+player.getName() + ": " + ((float)(player.getPlayerCountryCount()/(float)totalCountries))*100 + "% "));
			}
		}
	}
}
