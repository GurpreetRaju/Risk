package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import model.CountryNode;
import model.MapNode;

public class MapView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2353535256045293828L;

	public MapView(){
		JLabel label = new JLabel("Map Here.");
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public void setMap(Object[][] newMapData) {
		Object[] headings = {"Continent","Country","Armies","Owner","Neighbour"};
		JTable table = new JTable(newMapData,headings);
		this.add(table);
	}
	
}
