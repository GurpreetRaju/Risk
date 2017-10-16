package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.CountryNode;
import model.GameDriver;
import model.Player;

public class ControlsView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2537156060382941763L;
	/**
	 * Spinner to display armies count available to the player for Reinforcement phase.
	 */
	private JSpinner armiesSpinner;
	/**
	 * ComboBox to display the countries owned by the current player.
	 */
	private JComboBox<String> countriesList;
	/**
	 * Button to start the phase.
	 */
	private JButton playMove;
	/**
	 * Button to end the phase.
	 */
	private JButton doneButton;
	/**
	 * Number of armies selected to move to the neighboring country for Fortification phase.
	 */
	private int selectedArmies;
	JComboBox<String> neighborList;
	CountryNode countrySelect =null;
	private String neighbourSelected;
	/**
	 * Constructor to display the Control section of the game for Reinforcement, Attack and Fortification phases.
	 */
	public ControlsView(){
		
		JLabel label = new JLabel("Controls Here.");
		this.setLayout(new FlowLayout());
		this.add(label);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * Displays the Reinforcement Phase controls.
	 * @param armiesCount Number of armies available to the player for Reinforcement phase.
	 * @param countryList String array containing the countries owned by the current player.
	 */
	public void reinforcementConrols(int armiesCount, String[] countryList){
		this.removeAll();
		SpinnerModel sm = new SpinnerNumberModel(1, 1, armiesCount, 1); 
		armiesSpinner = new JSpinner(sm);
		countriesList = new JComboBox<String>(countryList);
		countriesList.setSelectedIndex(0);
		
		this.add(new Label("Armies"));
		this.add(armiesSpinner);
		this.add(new Label("Country"));
		this.add(countriesList);
		//this.add(playMove);
		//this.add(doneButton);
		this.validate();
	}
	
	/**
	 * This function implements the Fortification Phase.
	 * @param countryList String array that contains the names of the country owned by the current player.
	 */
	public void fortificationControls(String[] countryList){
		this.removeAll();

		countriesList = new JComboBox<String>(countryList);
		countriesList.setSelectedIndex(0);
		
		this.add(new Label("Country "));
		this.add(countriesList);
		//this.add(playMove);
		//this.add(doneButton);
		this.validate();
		
		Player p = GameDriver.getInstance().getCurrentPlayer();
		ArrayList<CountryNode> c = p.getCountries();
		add(new Label("Neighbours"));
		neighborList = new JComboBox<String>();
		
		countriesList.addActionListener(new ActionListener() {
			
			@Override
            public void actionPerformed(ActionEvent e) {
				String countrySelected = (String) countriesList.getSelectedItem();
				for(CountryNode i : c){
					if(i.getCountryName() == countrySelected){
						neighborList.removeAllItems();
						for(String name: i.getNeighbourCountriesString()){
							neighborList.addItem(name);
						}
						neighborList.setSelectedIndex(0);
						countrySelect = i;
						add(neighborList);
						validate();
						break;
					}
				}
				neighbourSelected = (String) neighborList.getSelectedItem();
				
				//int armies = i.getArmiesCount();
				int armies = 10;
				SpinnerModel sm = new SpinnerNumberModel(1, 0, armies-1, 1); 
				JSpinner armiesCountSpinner = new JSpinner(sm);
				add(armiesCountSpinner);
				validate();
				JButton button = new JButton("Done");
				add(button);
				validate();
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e){
						selectedArmies = (int) armiesCountSpinner.getValue();
						countrySelect.setArmies(countrySelect.getArmiesCount()-selectedArmies); 
						for(CountryNode j : countrySelect.getNeighbourCountries()){
							if(j.getCountryName() == neighbourSelected){
								j.setArmies(j.getArmiesCount() + selectedArmies);
								//call end turn
								
								break;
							}
						}
					}
				});
				
            }
		});
		
	}

	/**
	 * 
	 * @param a
	 */
	public void playButtonAction(ActionListener a){
		this.playMove.addActionListener(a);
	}
	
	public void doneButtonAction(ActionListener newAction){
		this.doneButton.addActionListener(newAction);
	}
	
}
