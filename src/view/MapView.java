package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Map;

public class MapView extends JPanel implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2353535256045293828L;

	public MapView()
	{
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setOpaque(true);
	    this.setBackground(Color.WHITE);
	    this.setLayout(null);
	}

	public void setMap(String[][] newMapData) 
	{
		this.removeAll();
		JPanel pane = new JPanel();
		GridBagLayout gb = new GridBagLayout();
		pane.setLayout(gb);
		int i = 0;
		for(String[] o : newMapData)
		{
			int j=0;
			for(String k: o)
			{
			    GridBagConstraints gbc = new GridBagConstraints();
			    gbc.ipadx = 20;
			    gbc.gridx = j;
			    gbc.gridy = i;
				pane.add(new JLabel(k), gbc);
				j++;
			}
			i++;
		}
		JScrollPane scroll = new JScrollPane(pane);
		scroll.setPreferredSize(getSize());
		this.add(scroll);
		this.validate();
	}
	
	public void setGraphicalMap(String[][] newMapData){
		this.removeAll();
		for(String[] o: newMapData){
			JPanel panel = new JPanel();
			panel.setSize(100, 100);
			panel.setLocation(Integer.parseInt(o[0]), Integer.parseInt(o[4]));
			for(int i=1;i<4;i++){
				panel.add(new JLabel(o[i]));				
			}
			this.add(panel);
			System.out.print("Debug message");
		}
		this.validate();
	}

	@Override
	public void update(Observable obs, Object map) {
		setGraphicalMap(((Map) obs).getMapDataObject());
	}
	
}
