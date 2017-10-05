package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerInfoView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7512274442706727095L;

	public PlayerInfoView(){
		JLabel label = new JLabel("Players data Here.");
		this.setLayout(new FlowLayout());
		this.add(label);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	
	
}
