package risk.view;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TournamentInfo extends JFrame{
	
	private JButton submitButton;
	private JSpinner mapCount;
	private JPanel mapPanel;
	private ArrayList<JTextField> maps;
	private JSpinner playerCount;
	private JPanel playerPanel;
	private ArrayList<JComboBox<String>> behaviors;
	private JSpinner gameCount;
	private JSpinner moveCount;
	private Container frame; 
	
	public TournamentInfo(){
		super("Enter tournament details");
		mapCount = new JSpinner(new SpinnerNumberModel(1,1,5,1));
		mapPanel = new JPanel();
		playerCount = new JSpinner(new SpinnerNumberModel(2,2,4,1));
		playerPanel = new JPanel();
		gameCount = new JSpinner(new SpinnerNumberModel(1,1,5,1));
		moveCount = new JSpinner(new SpinnerNumberModel(10,10,50,1));
		submitButton = new JButton("OK");
		frame = this.getContentPane();
		init();
		mapCountListener();
		playerCountListener();
		addPlayerPanelContent();
		addMapPanelContent();
		this.pack();
		this.validate();
		this.setVisible(true);
	}
	
	private void playerCountListener() {
		playerCount.addChangeListener( new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				addPlayerPanelContent();
			}
		});
	}

	private void mapCountListener() {
		mapCount.addChangeListener( new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				addMapPanelContent();
			}
		});
	}

	private void init() {
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.setLayout(new GridBagLayout());
		frame.add(panel);
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(3,3,3,3);
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel("Enter number of maps: "), c);
		c.gridx = 1;
		panel.add(mapCount, c);
		c.gridwidth = 2;
		c.gridy = 2;
		c.gridx = 0;
		panel.add(mapPanel, c);
		c.gridwidth = 1;
		c.gridy = 3;
		c.gridx = 0;
		panel.add(new JLabel("Enter number of players: "), c);
		c.gridx = 1;
		panel.add(playerCount, c);
		c.gridwidth = 2;
		c.gridy = 4;
		c.gridx = 0;
		panel.add(playerPanel, c);
		c.gridwidth = 1;
		c.gridy = 5;
		c.gridx = 0;
		panel.add(new JLabel("Enter number of games to play on each map: "), c);
		c.gridx = 1;
		panel.add(gameCount, c);
		c.gridy = 6;
		c.gridx = 0;
		panel.add(new JLabel("Enter number of moves for each game: "), c);
		c.gridx = 1;
		panel.add(moveCount, c);
		c.gridy = 7;
		c.gridx = 0;
		panel.add(submitButton, c);
	}
	
	public void setListeners(ActionListener actionListener) {
		submitButton.addActionListener(actionListener);
	}
	
	private void addMapPanelContent() {
		mapPanel.removeAll();
		mapPanel.setLayout(new GridBagLayout());
		maps = new ArrayList<JTextField>();
		SetUpDialog sBox = new SetUpDialog();
		for(int i=0; i< (int) mapCount.getValue(); i++) {
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(2,2,2,2);
			c.gridx = 0;
			c.gridy = i;
			mapPanel.add(new JLabel("Select map "+(i+1)+": "), c);
			c.gridx = 1;
			JTextField map = new JTextField(15);
			JButton browse = new JButton("Browse");
			browse.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					map.setText(sBox.getMapInfo("map"));
				}
			});
			map.setEnabled(false);
			maps.add(map);
			mapPanel.add(map, c);
			c.gridx = 2;
			mapPanel.add(browse,c);
		}
		revalidate();
		pack();
		repaint();
	}
	
	private void addPlayerPanelContent() {
		playerPanel.removeAll();
		playerPanel.setLayout(new GridBagLayout());
		behaviors = new ArrayList<JComboBox<String>>();
		String[] tempBeh = {"Aggressive", "Benevolent", "Cheater", "Human","Random"};
		for(int i=0; i< (int) playerCount.getValue(); i++) {
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = i;
			playerPanel.add(new JLabel("Player "+(i+1)+" behavior: "), c);
			c.gridx = 1;
			JComboBox<String> behavior = new JComboBox<String>(tempBeh);
			behaviors.add(behavior);
			playerPanel.add(behavior, c);
		}
		revalidate();
		pack();
		repaint();
	}
	
	public String[] getPlayerBehaviorDetails() {
		ArrayList<String> b = new ArrayList<String>();
		for(JComboBox<String> cb: behaviors) {
			b.add((String) cb.getSelectedItem());
		}
		return b.toArray(new String[b.size()]);
	}
	
	public String[] getMapDetails() {
		ArrayList<String> b = new ArrayList<String>();
		for(JTextField cb: maps) {
			b.add(cb.getText());
		}
		return b.toArray(new String[b.size()]);
	}
	
	public int getGamesCount() {
		return (int) gameCount.getValue();
	}
	
	public int getMovesCount() {
		return (int) moveCount.getValue();
	}

}
