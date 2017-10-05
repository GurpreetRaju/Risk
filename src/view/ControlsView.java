package View;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlsView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2537156060382941763L;

	public ControlsView(){
		
		JLabel label = new JLabel("Controls Here.");
		this.setLayout(new FlowLayout());
		this.add(label);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
}
