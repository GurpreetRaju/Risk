package risk.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risk.model.GameDriver;
import risk.model.Player;

/**
 * Creates cards view on main window.
 */
public class CardsView extends JPanel implements Observer {
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 9127819244400811786L;

	/**
	 * Creates cards view.
	 */
	public CardsView(){
		JLabel label = new JLabel("Cards Here.");
		this.setLayout(new FlowLayout());
		this.add(label);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(400,150));
	}

	@Override
	public void update(Observable o, Object arg) {
		Player player = GameDriver.getInstance().getCurrentPlayer();
		if (arg.equals("Reinforcement")){
			if( player.getCards().size()>2){
				//Do here if player have distinct cards or 3 cards of 3 same kind or have 5 cards
		}
			
		}
		
	}
}
