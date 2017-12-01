package risk.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
/**
 * This class display resukt of Tournament and Single Game mode
 * @author Gurpreet
 *
 */
public class ResultView extends JFrame{
	/**
	 * JButton it start a new game again
	 */
	private JButton playAgain;
	
	/**
	 * This button closes the game
	 */
	private JButton close;
	
	/**
	 * Constructor for Result View
	 * @param data data to be displayed on view
	 */
	public ResultView(Object[][] data) {
		playAgain = new JButton("Play again");
		close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}			
		});
		init(data);
	}
	/**
	 * Display data using table form
	 * @param data data to be dislayed on view
	 */
	private void init(Object[][] data) {
		int columns = data[0].length;
		String[] headings = new String[columns];
		headings[0] = "Maps";
		for(int i=1; i<columns; i++) {
			headings[i] = "Game "+i;
		}
		JTable results = new JTable(data, headings);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.getContentPane().add(panel);
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(results.getTableHeader());
		panel.add(results);
		panel.add(Box.createRigidArea(new Dimension(10, 10)));
		panel.add(playAgain);
		panel.add(Box.createRigidArea(new Dimension(10, 10)));
		panel.add(close);
		this.pack();
		this.validate();
		this.setVisible(true);
	}
	
	/**
	 * Adda listener to playAgain button
	 * @param e action listener to be added to play again button
	 */
	public void addListenerToPlayAgainButton(ActionListener e) {
		this.playAgain.addActionListener(e);
	}
	
}
