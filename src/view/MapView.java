package View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MapView extends JPanel {
	
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
	
}
