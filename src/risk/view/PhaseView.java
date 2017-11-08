package risk.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risk.model.GameDriver;
import risk.model.Player;

/**
 * Implements the Phase View panel of the main window using Observer Pattern.
 * @author Gunpreet
 */
public class PhaseView extends JPanel implements Observer{
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 5240018585440964453L;

	/**
	 * Constructor to initialize PhaseView.
	 */
	public PhaseView() {
		JLabel label = new JLabel("PHASE VIEW");
		this.setLayout(new FlowLayout());
		this.add(label);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	/**
	 * Observer method called on notifyObservers for PhaseView.
	 */
	@Override
	public void update(Observable o, Object arg) {
		Player current = GameDriver.getInstance().getCurrentPlayer();
		if(arg.equals("Startup")){
			this.removeAll();
			this.add(new JLabel("<html><div><b>Startup Phase</b></div><br/><br/></html>"));
		}
		else if(arg.equals("Cards")){
			this.removeAll();
			this.add(new JLabel("<html><div><b>Cards Exchange</b></div><br/><br/></html>"));
			this.add(new JLabel("Player: "));
			this.add(new JLabel(current.getName()));
			this.add(new JLabel(" can exchange cards to get more armies  "));
		}
		else if(arg.equals("Reinforcement")){
			this.removeAll();
			this.add(new JLabel("<html><div><b>Reinforcement Phase</b></div><br/><br/></html>"));
			this.add(new JLabel("Player:"));
			this.add(new JLabel(current.getName()));
			this.add(new JLabel("Armies:"));
			this.add(new JLabel(String.valueOf(current.getArmiesCount())));
			this.add(new JLabel("Countries:"));
			String[] countries = current.getCountriesNames();
			for(String s: countries){
				this.add(new JLabel(s));
			}
		}
		else if(arg.toString().contains("Attack")){
			this.removeAll();
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.add(new JLabel("<html><div><b>Attack Phase</b></div><br/><br/></html>"));
			this.add(new JLabel("Player: "+ current.getName()));
			if(arg.toString().length() > 7){
				String words[] = arg.toString().substring(6).split("<br>");
				for( String word : words){
					this.add(new JLabel(word));
				}
			}
		}
		else if(arg.equals("Fortification")){
			this.removeAll();
			this.setLayout(new FlowLayout());
			this.add(new JLabel("<html><div><b>Fortification Phase</b></div><br/><br/></html>"));
			this.add(new JLabel("Player: "));
			this.add(new JLabel(current.getName()));
		}
		else{
			this.add(new JLabel("<html><div>Player:</div></html>"));
			this.add(new JLabel((String) arg));
		}
		System.out.println("Observer");
		this.validate();
	}
}
