package risk.model.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risk.model.GameDriver;
import risk.model.Player;

public class GameLogger extends JPanel implements Observer {

	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = -8766321280014020596L;
	
	/**
	 * 
	 */
	private JFrame frame;
	/**
	 * Constructor to intialize GameLogger
	 */
	public GameLogger(){
		frame = new JFrame("GameLogger");
		frame.setSize(new Dimension(400,1000));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.validate();
		frame.setVisible(true);
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
			
			frame.add(new JLabel("<html><div><b>Cards Exchange</b></div><br/><br/></html>"));
			frame.add(new JLabel("Player: "));
			frame.add(new JLabel(current.getName()));
			frame.add(new JLabel(" can exchange cards to get more armies  "));
		}
		/*Reinforcement Game Logger Display.*/
		else if(arg.equals("Reinforcement")){
			
			frame.add(new JLabel("<html><div><b>Reinforcement Phase</b></div><br/><br/></html>"));
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
		/*Attack Game Logger Display.*/
		else if(arg.toString().contains("Attack")){
			
			frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
			frame.add(new JLabel("<html><div><b>Attack Phase</b></div><br/><br/></html>"));
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
			
			frame.setLayout(new FlowLayout());
			frame.add(new JLabel("<html><div><b>Fortification Phase</b></div><br/><br/></html>"));
			frame.add(new JLabel("Player: "));
			frame.add(new JLabel(current.getName()));
		}
		/*Players Display for Startup Phase.*/
		else{
			frame.add(new JLabel("<html><div>Player:</div></html>"));
			frame.add(new JLabel((String) arg));
		}
		System.out.println("Observer");
		frame.validate();
	}

}
