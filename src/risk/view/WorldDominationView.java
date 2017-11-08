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

/**
 * This class implements the World Domination View which shows 
 * the portion of countries each player occupy.
 * @author amitt
 *@version 1.0
 */
public class WorldDominationView extends JPanel implements Observer {
	
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = -1084389704252002294L;

	/**
	 * Creates World Domination View on the Main frame
	 */
	public WorldDominationView() {
		JLabel label = new JLabel("<html><p><b>World Domination View</b></p><br/><br/></html>");
		this.setLayout(new FlowLayout());
		this.add(label);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * Observer pattern function for Observers to update when there is a notification from the observable.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(arg.equals("Reinforcement") || arg.equals("Fortification")){
			this.removeAll();
			JLabel label = new JLabel("<html><p><b>World Domination View</b></p><br/><br/></html>");
			this.add(label);
			
			int totalCountries = GameDriver.getInstance().getMap().getCountryCount();
			System.out.println(totalCountries);
			for (Player player : GameDriver.getInstance().getPlayers()){
				System.out.println(player.getPlayerCountryCount());
				this.add(new JLabel(" "+player.getName() + ": " + ((float)(player.getPlayerCountryCount()/(float)totalCountries))*100 + "% "));
			}
		}
	}
}
