package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DiceRollView extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2080193456450432494L;

	public DiceRollView(){
		JLabel label = new JLabel("Dice Here.");
		this.setLayout(new FlowLayout());
		this.add(label);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
}
