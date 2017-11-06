package risk.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Implements the Phase View panel of the main window.
 * @author Gunpreet
 */
public class PhaseView extends JPanel implements Observer{
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 5240018585440964453L;

	public PhaseView() {
		JLabel label = new JLabel("PHASE VIEW");
		this.setLayout(new FlowLayout());
		this.add(label);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	@Override
	public void update(Observable o, Object arg) {
		
		this.removeAll();
		//set current player, phase and details
		System.out.println("Observer");
		this.add(new JLabel("Startup Phase"));
		this.validate();
		this.revalidate();
		this.repaint();
		
	}
	
	
}
