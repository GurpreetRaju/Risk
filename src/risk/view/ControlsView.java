package risk.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class ControlsView extends JPanel {
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = -2537156060382941763L;
	
	/**
	 * Spinner to display armies count available to the player for Reinforcement phase, Fortification phase and attack phase.
	 */
	private JSpinner armiesSpinner;
	
	/**
	 * Spinner to display armies count available to the player for attack phase.
	 */
	private JSpinner armiesSpinner2;
	
	/**
	 * ComboBox to display the countries owned by the current player.
	 */
	private JComboBox<String> countriesList;
	
	/**
	 * Button for the fortification phase move.
	 */
	private JButton playMove;
	
	/**
	 * Dropdown for list of neighbors.
	 */
	private JComboBox<String> neighborList;
	
	/**
	 * Armies available to the player for reinforcement phase.
	 */
	private String armiesAvailable;
	
	/**
	 * Number of armies selected to move to the neighboring country for Fortification phase.
	 */
	private JButton addArmies;
	
	/**
	 * Button to end a phase.
	 */
	private JButton endPhase;
	
	/**
	 * Constructor to display the Control section of the game for Reinforcement, Attack and Fortification phases.
	 */
	public ControlsView() {
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
	public void reinforcementControls(int armiesCount, String[] countryList) {
		this.removeAll();
		armiesAvailable = "Armies Available:" + String.valueOf(armiesCount);
		System.out.println(armiesCount);
		SpinnerModel sm = new SpinnerNumberModel(1, 1, armiesCount, 1); 
		armiesSpinner = new JSpinner(sm);
		countriesList = new JComboBox<String>(countryList);
		countriesList.setSelectedIndex(0);
		addArmies = new JButton("Add Armies");
		
		this.add(new Label(armiesAvailable));
		this.add(new Label("Country"));
		this.add(countriesList);
		this.add(armiesSpinner);
		this.add(addArmies);
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
		neighborList.setEnabled(false);
		armiesSpinner = new JSpinner();
		armiesSpinner.setEnabled(false);
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

	/**
	 * Play a move for fortification phase.
	 * @param a ActionListener for the playMove button.
	 */
	public void playButtonAction(ActionListener a) {
		this.playMove.addActionListener(a);
	}
	
	/**
	 * Sets ActionListener on add armies button.
	 * @param newAction ActionListener to be attached to the button.
	 */
	public void addArmiesButtonAction(ActionListener newAction) {
		this.addArmies.addActionListener(newAction);
	}
	
	/**
	 * Sets ActionListener on end phase button.
	 * @param newAction ActionListener to be attached to the button.
	 */
	public void endPhaseAction(ActionListener newAction) {
		this.endPhase.addActionListener(newAction);
	}
	
	/**
	 * Sets ActionListener on countries list combobox.
	 * @param newAction ActionListener to be attached to the combobox.
	 */
	public void countrieslistAction(ActionListener newAction) {
		this.countriesList.addActionListener(newAction);
	}
	
	/**
	 * Update fortification control view whenever required.
	 * @param armies Count of the armies the player has.
	 * @param neighbourNames List of neighbors of the country selected.
	 */
	public void updateFortification(int armies, String[] neighbourNames) {
		this.armiesSpinner.setModel(new SpinnerNumberModel(1, 0, armies-1, 1));
		this.armiesSpinner.setEnabled(true);
		setNeighborList(neighbourNames);
	}
	
	/**
	 * This method set list of neighbors to neighborList combobox.
	 * @param newNeighbourNames list of neighbors
	 */
	public void setNeighborList(String[] newNeighbourNames) {
		this.neighborList.setModel(new DefaultComboBoxModel<String>(newNeighbourNames));
		this.neighborList.setSelectedIndex(0);
		this.neighborList.setEnabled(true);
		this.playMove.setEnabled(true);
	}
	
	/**
	 * Gets the value of armies from the spinner.
	 * @return integer value from the spinner.
	 */
	public int getArmiesValue() {
		return (int) this.armiesSpinner.getValue();
	}
	
	/**
	 * Gets the country selected in the combobox.
	 * @return country selected in the combobox.
	 */
	public String getCountrySelected() {
		return (String) this.countriesList.getSelectedItem();
	}

	/**
	 * Gets the neighbor selected in the combobox.
	 * @return neighbor selected in the combobox.
	 */
	public String getNeighborSelected() {
		return (String) this.neighborList.getSelectedItem();
	}
	
	/**
	 * Checks if neighbor list combobox is enabled or not.
	 * @return boolean value depending on the combobox enabled or not.
	 */
	public boolean isNeighbourSelected() {
		return this.neighborList.isEnabled();
	}
	
	/**
	 * This method reset the controls for attack phase
	 * @param countriesNames list of countries
	 */
	public void attackControls(String[] countriesNames) {
		this.removeAll();

		countriesList = new JComboBox<String>(countriesNames);
		countriesList.setSelectedIndex(0);
		
		neighborList = new JComboBox<String>();
		neighborList.setEnabled(false);
		
		playMove = new JButton("Announce attack");
		playMove.setEnabled(false);
		endPhase = new JButton("Skip attack");
		
		this.add(new Label("Country "));
		this.add(countriesList);
		this.add(new Label("Neighbours"));
		this.add(neighborList);
		this.add(playMove);
		this.add(endPhase);
		
		this.revalidate();
		this.repaint();
	}
	
}
