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
/**
 * This class gather input from user regarding the tournament eg. Player names and behaviors, maps, moves etc.
 * @author Gurpreet
 *
 */
public class TournamentInfo extends JFrame{
	/**
	 * Submit button
	 */
	private JButton submitButton;
	
	/**
	 * Get input on number of maps
	 */
	private JSpinner mapCount;
	
	/**
	 * Display map browse button
	 */
	private JPanel mapPanel;
	
	/**
	 * reference to all textfields
	 */
	private ArrayList<JTextField> maps;
	
	/**
	 * Get input on number of players
	 */
	private JSpinner playerCount;
	
	/**
	 * display textfields and combobox for player name and behaviours
	 */
	private JPanel playerPanel;
	
	/**
	 * Reference to comboboxes
	 */
	private ArrayList<JComboBox<String>> behaviors;
	
	/**
	 * reference to textfields containing names of players
	 */
	private ArrayList<JTextField> names;
	
	/**
	 * Get input on number of games to be played on each map
	 */
	private JSpinner gameCount;
	
	/**
	 * input number of moves limited to each game
	 */
	private JSpinner moveCount;
	
	/**
	 * refernce to the internal container of this frame
	 */
	private Container frame; 
	
	/**
	 * Constructor: initialize all attributes
	 */
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
	
	/**
	 * Add listener to playerCount
	 */
	private void playerCountListener() {
		playerCount.addChangeListener( new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				addPlayerPanelContent();
			}
		});
	}
	
	/**
	 * Add listener to mapCount
	 */
	private void mapCountListener() {
		mapCount.addChangeListener( new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				addMapPanelContent();
			}
		});
	}
	
	/**
	 * Create layout in view, add constraints
	 */
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
	
	/**
	 * Set listener on submit Button
	 * @param actionListener action listener to be attached to submit sutton
	 */
	public void setListeners(ActionListener actionListener) {
		submitButton.addActionListener(actionListener);
	}
	
	/**
	 * Update content to mapPanel
	 */
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
	
	/**
	 * Add components to Player panel
	 */
	private void addPlayerPanelContent() {
		playerPanel.removeAll();
		playerPanel.setLayout(new GridBagLayout());
		behaviors = new ArrayList<JComboBox<String>>();
		names = new ArrayList<JTextField>();
		String[] tempBeh = {"aggressive", "benevolent", "cheater", "human","random"};
		for(int i=0; i< (int) playerCount.getValue(); i++) {
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = i;
			playerPanel.add(new JLabel("Player "+(i+1)+" behavior: "), c);
			c.gridx = 1;
			JComboBox<String> behavior = new JComboBox<String>(tempBeh);
			behaviors.add(behavior);
			playerPanel.add(behavior, c);
			c.gridx = 2;
			playerPanel.add(new JLabel(" Name: "),c);
			c.gridx = 3;
			JTextField name = new JTextField(8);
			names.add(name);
			playerPanel.add(name,c);
		}
		revalidate();
		pack();
		repaint();
	}
	
	/**
	 * 
	 * @return multidimensional array conatining player names and their behaviors
	 */
	public String[][] getPlayerBehaviorDetails() {
		ArrayList<String[]> b = new ArrayList<String[]>();
		for(int i=0;i<names.size();i++) {
			String[] temp = new String[2];
			temp[1] = (String) behaviors.get(i).getSelectedItem();
			temp[0] = names.get(i).getText();
			b.add(temp);
		}
		return b.toArray(new String[b.size()][]);
	}
	
	/**
	 * 
	 * @return Array of urls of maps
	 */
	public String[] getMapDetails() {
		ArrayList<String> b = new ArrayList<String>();
		for(JTextField cb: maps) {
			b.add(cb.getText());
		}
		return b.toArray(new String[b.size()]);
	}
	
	/**
	 * @return number of games to be played
	 */
	public int getGamesCount() {
		return (int) gameCount.getValue();
	}
	
	/**
	 * 
	 * @return number of moved=s limited to each game
	 */
	public int getMovesCount() {
		return (int) moveCount.getValue();
	}

}
