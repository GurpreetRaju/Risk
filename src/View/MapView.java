package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MapView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2353535256045293828L;

	public MapView(){
		JLabel label = new JLabel("Map Here.");
		this.setLayout(new FlowLayout());
		this.add(label);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
}
