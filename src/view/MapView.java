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

	@Override
	public void update(Observable obs, Object map) {
		setMap(((Map) obs).getMapDataObject());
	}
	
}
