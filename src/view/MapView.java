package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Map;

public class MapView extends JPanel implements Observer {
	private BufferedImage image;
	private boolean graphicalMap = false;
	
	/**
	 * Version number for serializable class
	 */
	private static final long serialVersionUID = 2353535256045293828L;

	public MapView(String newImage) {
		this();
		System.out.println("Not working");
	    if(newImage!=null){
	    	try {
	    		image = ImageIO.read(new File(newImage));
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    }
	    this.setBackground(Color.WHITE);
	    this.setOpaque(true);
	    this.setLayout(null);
	    this.graphicalMap = true;
	}
	
	public MapView() {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(image!=null) {	
	    	g.drawImage(image, 0, 0, this);
	    }
	}

	public void setMap(String[][] newMapData) {
		this.removeAll();
		JPanel pane = new JPanel();
		GridBagLayout gb = new GridBagLayout();
		pane.setLayout(gb);
		int i = 0;
		for(String[] o : newMapData){
			int j=0;
			for(String k: o) {
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
			panel.setSize(5, 5);
			panel.setBackground(Color.BLACK);
			panel.setLocation(Integer.parseInt(o[0]), Integer.parseInt(o[4]));
			panel.add(new JLabel(" "));
			String text = o[1]+" "+o[3]+" "+o[2];
			panel.setToolTipText(text);
			this.add(panel);
		}
		this.validate();
	}

	@Override
	public void update(Observable obs, Object map) {
		if(graphicalMap){
			setGraphicalMap(((Map) obs).getMapDataObject());
		}else{
			setMap(((Map) obs).getMapObject());
		}
	}
}
