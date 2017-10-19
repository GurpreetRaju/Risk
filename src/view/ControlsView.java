package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class ControlsView extends JPanel {
	
	/**
	 * Version number for serializable class
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
	
	JComboBox<String> neighborList;
	private String armiesAvailable;
	private JButton addArmies;
	private JButton endPhase;
	
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
	public void reinforcementConrols(int armiesCount, String[] countryList) {
		this.removeAll();
		armiesAvailable = "Armies Available:" + String.valueOf(armiesCount);
		System.out.println(armiesCount);
		SpinnerModel sm = new SpinnerNumberModel(1, 1, armiesCount, 1); 
		armiesSpinner = new JSpinner(sm);
		countriesList = new JComboBox<String>(countryList);
		countriesList.setSelectedIndex(0);
		addArmies = new JButton("Add Armies");
		endPhase = new JButton("End Reinforcement Phase");
		
		this.add(new Label(armiesAvailable));
		this.add(new Label("Country"));
		this.add(countriesList);
		this.add(armiesSpinner);
		this.add(addArmies);
		this.add(endPhase);
		//this.add(playMove);
		//this.add(doneButton);
		this.revalidate();
		this.repaint();
	}
	
	/**
	 * This function implements the Fortification Phase.
	 * @param countryList String array that contains the names of the country owned by the current player.
	 */
	public void fortificationControls(String[] countryList) {
		this.removeAll();

		countriesList = new JComboBox<String>(countryList);
		countriesList.setSelectedIndex(0);
		
		neighborList = new JComboBox<String>();
		neighborList.setEnabled(false);;
		armiesSpinner = new JSpinner();
		armiesSpinner.setEnabled(false);;
		playMove = new JButton("Move Armies");
		
		this.add(new Label("Country "));
		this.add(countriesList);
		this.add(new Label("Neighbours"));
		this.add(neighborList);
		this.add(armiesSpinner);
		this.add(playMove);
		
		this.revalidate();
		this.repaint();
	}

	public void playButtonAction(ActionListener a) {
		this.playMove.addActionListener(a);
	}
	
	public void doneButtonAction(ActionListener newAction){
		this.doneButton.addActionListener(newAction);
	}
	
	public void addArmiesButtonAction(ActionListener newAction) {
		this.addArmies.addActionListener(newAction);
	}
	
	public void endPhaseAction(ActionListener newAction) {
		this.endPhase.addActionListener(newAction);
	}
	
	public void countrieslistAction(ActionListener newAction) {
		this.countriesList.addActionListener(newAction);
	}
	
	public void updateFortification(int armies, String[] neighbourNames) {
		this.armiesSpinner.setModel(new SpinnerNumberModel(1, 0, armies-1, 1));
		this.armiesSpinner.setEnabled(true);;
		this.neighborList.setModel(new DefaultComboBoxModel<String>(neighbourNames));
		this.neighborList.setSelectedIndex(0);
		this.neighborList.setEnabled(true);;
		this.playMove.setEnabled(true);;
	}
	
	public int getArmiesValue() {
		return (int) this.armiesSpinner.getValue();
	}
	
	public String getCountrySelected() {
		return (String) this.countriesList.getSelectedItem();
	}

	public String getNeighborSelected() {
		return (String) this.neighborList.getSelectedItem();
	}
	
	public boolean isNeighbourSelected(){
		return this.neighborList.isEnabled();
	}
}
