package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

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
	
	public void playButtonAction(ActionListener a){
		this.playMove.addActionListener(a);
	}
	
	public void doneButtonAction(ActionListener newAction){
		this.doneButton.addActionListener(newAction);
	}
	
}
