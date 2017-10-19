package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class PlayerInfoView extends JPanel {
	
	/**
	 * Version number for serializable class
	 */
	private static final long serialVersionUID = -7512274442706727095L;

	public PlayerInfoView() {
		JLabel label = new JLabel("Players data Here.");
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public void setPlayerInfo(String[] playerNames) {
		for(String name : playerNames){
			JLabel comp = new JLabel(name);
			Border border = comp.getBorder();
			Border margin = new EmptyBorder(10,10,10,10);
			comp.setBorder(new CompoundBorder(border, margin));
			this.add(comp);
		}
		this.validate();
	}
}
