package risk.view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * main window controlling the dice view
 */
public class DiceRollView extends JPanel{
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 2080193456450432494L;

	/**
	 * Creating the dice roll view.
	 */
	public DiceRollView(){
		JLabel label = new JLabel("Dice Here.");
		this.setLayout(new FlowLayout());
		this.add(label);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
}
