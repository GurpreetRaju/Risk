package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
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
import model.Player;

public class ControlsView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2537156060382941763L;
	private JSpinner armiesSpinner;
	private JComboBox<String> countriesList;
	private JButton playMove;
	private JButton doneButton;

	public ControlsView(){
		
		JLabel label = new JLabel("Controls Here.");
		this.setLayout(new FlowLayout());
		this.add(label);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
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
		this.add(playMove);
		this.add(doneButton);
		this.validate();
	}
	
	public void fortificationControls(String[] countryList){
		this.removeAll();
		
		countriesList = new JComboBox<String>(countryList);
		countriesList.setSelectedIndex(0);
		
		this.add(new Label("Country"));
		this.add(countriesList);
		this.validate();
		
		String neighbourSelected;
		int selectedArmies;
		String countrySelected = (String) countriesList.getSelectedItem();
		
		Player p = Player.getCurrentPlayer();
		ArrayList<CountryNode> c = p.getCountries();
		
		for(CountryNode i : c){
			if(i.getCountryName() == countrySelected){
				JComboBox<String> neighborList = new JComboBox<String>(i.getNeighbourCountriesString());
				neighborList.setSelectedIndex(0);
				
				this.add(new Label("Country"));
				this.add(neighborList);
				this.validate();
				
				neighbourSelected = (String) neighborList.getSelectedItem();
				
				int armies = i.getArmiesCount();
				SpinnerModel sm = new SpinnerNumberModel(1, 1, armies-1, 1); 
				JSpinner armiesSpinner = new JSpinner(sm);
				armiesSpinner.addChangeListener(new ChangeListener() {
					public void stateChanged(ChangeEvent e){
						selectedArmies = (int) ((JSpinner)e.getSource()).getValue();
					}
				});
				i.setArmies(i.getArmiesCount()-selectedArmies); 
				for(CountryNode j : i.getNeighbourCountries()){
					if(j.getCountryName() == neighbourSelected){
						j.setArmies(j.getArmiesCount() + selectedArmies);
						break;
					}
				}
				break;
			}
		}
		
	}
	
	public void playButtonAction(ActionListener a){
		this.playMove.addActionListener(a);
	}
	
	public void doneButtonAction(ActionListener newAction){
		this.doneButton.addActionListener(newAction);
	}
	
}
