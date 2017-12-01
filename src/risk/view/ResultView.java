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
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 1257813257366300458L;

	/**
	 * Play Again button
	 */
	private JButton playAgain;
	
	/**
	 * Result Close Button.
	 */
	private JButton close;
	
	/**
	 * Result view frame display.
	 * @param data Result view initialization.
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
	 * Result view initialization.
	 * @param data Result view initialization.
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
	 * Add listener to Play Again button.
	 * @param e ActionListener.
	 */
	public void addListenerToPlayAgainButton(ActionListener e) {
		this.playAgain.addActionListener(e);
	}
	
}
