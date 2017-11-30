package risk.view;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

/**
 * Controls the Players data input and view on the main window.
 */
public class PlayerInfoView extends JPanel implements Observer{
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = -7512274442706727095L;

	/**
	 * Creates the Player Info view on the main window.
	 */
	public PlayerInfoView() {
		JLabel label = new JLabel("Players data Here.");
		this.add(label);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * Sets the player Info view content.
	 * @param playerNames Names of the players.
	 */
	public void setPlayerInfo(String[][] playerNames) {
		for(String[] name : playerNames){
			JLabel comp = new JLabel(name[0]);
			Border border = comp.getBorder();
			Border margin = new EmptyBorder(10,10,10,10);
			comp.setBorder(new CompoundBorder(border, margin));
			this.add(comp);
		}
		this.validate();
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Observable o, Object arg) {
		
	}
}
