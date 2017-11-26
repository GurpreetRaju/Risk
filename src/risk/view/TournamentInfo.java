package risk.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class TournamentInfo extends JFrame{
	
	private JButton submitButton;
	private JSpinner mapCount;
	private JSpinner playerCount;
	private JSpinner gameCount;
	private JSpinner moveCount;
	
	public TournamentInfo(){
		mapCount = new JSpinner(new SpinnerNumberModel(1,1,5,1));
		playerCount = new JSpinner(new SpinnerNumberModel(2,2,4,1));
		gameCount = new JSpinner(new SpinnerNumberModel(1,1,5,1));
		moveCount = new JSpinner(new SpinnerNumberModel(10,10,50,1));
		submitButton = new JButton("OK");
		init();
	}
	
	private void init() {
		this.add(new JLabel("Enter number of maps: "))
		this.add(mapCount);
		this.add(new JLabel("Enter number of players: "));
		this.add(playerCount);
		this.add(new JLabel("Enter number of games toplayer on each map: "));
		this.add(gameCount);
		this.add(submitButton);
	}
	
	public void setListeners(ActionListener actionListener) {
		submitButton.addActionListener(actionListener);
	}

}
