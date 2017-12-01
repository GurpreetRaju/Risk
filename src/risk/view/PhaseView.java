package risk.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import risk.model.gamemode.GameDriver;
import risk.model.player.Player;

/**
 * Implements the Phase View panel of the main window using Observer Pattern.
 * @author Gunpreet
 * @version 1.2
 */
public class PhaseView extends JPanel implements Observer{
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 5240018585440964453L;
	
	/**
	 * Store the current phase info
	 */
	private String phase = "";
	
	/**
	 * JPanel to display nfo
	 */
	private JPanel panel;
	
	private JScrollPane scroll;

	/**
	 * Constructor to initialize PhaseView.
	 */
	public PhaseView() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		scroll = new JScrollPane(panel);
		scroll.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
		this.add(scroll);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.validate();
	}

	/**
	 * Observer method called on notifyObservers for PhaseView.
	 */
	@Override
	public void update(Observable o, Object arg) {
		String current = ((GameDriver) o).getTurnManager().getPhase();
		if(!this.phase.equals(current)) {
			this.phase = current;
			panel.removeAll();
		}
		scroll.setPreferredSize(new Dimension(this.getWidth(),this.getHeight()));
		panel.add(new JLabel((String) arg));
		this.validate();
		this.repaint();
	}
}
