package risk.model.util;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import risk.model.GameDriver;
import risk.model.player.Player;

public class GameLogger extends JFrame implements Observer {

	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = -8766321280014020596L;
	
	/**
	 * Panel for the logging window.
	 */
	private JPanel frame;
	/**
	 * Constructor to intialize GameLogger
	 */
	public GameLogger(){
		frame = new JPanel();
		frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
	}

	/**
	 * Observer method called on notifyObservers for GameLogger.
	 */
	@Override
	public void update(Observable o, Object arg) {
		Player current = GameDriver.getInstance().getCurrentPlayer();
		/*Startup GameLogger Display.*/
		if(arg.equals("Startup")){
			frame.add(new JLabel("<html><div><b>Startup Phase</b></div><br/><br/></html>"));
		}
		/*Cards Exchange View Display.*/
		else if(arg.equals("Cards")){
			frame.add(new JLabel("<html><p></p><br/><br/></html>"));
			frame.add(new JLabel("<html><div><b>Cards Exchange</b></div><br/><br/></html>"));
			frame.add(new JLabel("Player: "));
			frame.add(new JLabel(current.getName()));
			frame.add(new JLabel(" can exchange cards to get more armies.  "));
		}
		/*Reinforcement Game Logger Display.*/
		else if(arg.equals("Reinforcement")){
			frame.add(new JLabel("<html><p></p><br/><br/></html>"));
			if(!(current.armiesMoved > 0)){
				frame.add(new JLabel("<html><p><b>Reinforcement Phase</b></p><br/><br/></html>"));
				frame.add(new JLabel("Player:"));
				frame.add(new JLabel(current.getName()));
				frame.add(new JLabel("Armies:"));
				frame.add(new JLabel(String.valueOf(current.getArmiesCount())));
				frame.add(new JLabel("Countries:"));
				String[] countries = current.getCountriesNames();
				for(String s: countries){
					frame.add(new JLabel(s));
				}
			}
			if(current.armiesMoved>0){
				frame.add(new JLabel(String.valueOf(current.armiesMoved)+" armies Reinforced to: "+current.countrySelected));
			}
		}
		/*Attack Game Logger Display.*/
		else if(arg.toString().contains("Attack")){
			frame.add(new JLabel("<html><p></p><br/><br/></html>"));
			frame.add(new JLabel("<html><p><b>Attack Phase</b></p><br/><br/></html>"));
			frame.add(new JLabel("Player: "+ current.getName()));
			if(arg.toString().length() > 7){
				String words[] = arg.toString().substring(6).split("<br>");
				for( String word : words){
					frame.add(new JLabel(word));
				}
			}
		}
		/*Fortification Game Logger Display.*/
		else if(arg.equals("Fortification")){
			frame.add(new JLabel("<html><p></p><br/><br/></html>"));
			frame.add(new JLabel("<html><p><b>Fortification Phase</b></p><br/><br/></html>"));
			frame.add(new JLabel("Player: "));
			frame.add(new JLabel(current.getName()));
			frame.add(new JLabel(" Armies moved from: " + current.getCountrySelected().getCountryName()+ " to "+ current.getNeighbourSelected().getCountryName()));
		}
		/*Players Display for Startup Phase.*/
		else{
			frame.add(new JLabel("<html><p>Player:</p></html>"));
			frame.add(new JLabel((String) arg));
		}
		frame.add(new JLabel("<html><p></p><br/><br/></html>"));
		System.out.println("Observer");
		this.setSize(400, 800);
		JScrollPane scroll = new JScrollPane(frame);
		scroll.setPreferredSize(getSize());
		this.add(scroll);
		this.validate();
		this.setVisible(true);
	}

}
