package risk.model.util;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import risk.model.gamemode.GameDriver;
import risk.model.player.Player;

/**
 * Displays the logging window for the game, implements Observer pattern.
 * @author Gunpreet
 * @author Amitt
 */
public class GameLogger extends JFrame implements Observer {

	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = -8766321280014020596L;
	
	/**
	 * Panel for the logging window.
	 */
	private JPanel panel;
	
	private JScrollPane scroll;
	
	/**
	 * Constructor to initialize GameLogger
	 */
	public GameLogger(){
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		scroll = new JScrollPane(panel);
		scroll.setPreferredSize(new Dimension(400,800));
		this.add(scroll);
		this.pack();
		this.validate();
		this.setVisible(true);
	}

	/**
	 * Observer method called on notifyObservers for GameLogger.
	 */
	@Override
	public void update(Observable o, Object arg) {
		panel.add(new JLabel((String) arg));
		this.validate();
	}
	
	public static void main(String[] s) {
		GameLogger g = new GameLogger();
		for(int i=0;i<60;i++) {
			g.update(null,"Line "+i);
		}
	}
	
}
