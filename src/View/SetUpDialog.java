package View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class SetUpDialog {
	
	private String[] playerNames;
	
	private int getPlayerCount(){
		 JPanel box = new JPanel();
		 JSpinner inputSpinner = new JSpinner();
         box.add(new JLabel("Number of players: "));
         box.add(inputSpinner);
         
         int result = JOptionPane.showConfirmDialog(null, box, "Enter number of Players", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
         if (result == JOptionPane.OK_OPTION) {
             return (int) inputSpinner.getValue();
         }
		return 2;
	}
	
	public String[] getPlayerInfo(){
		
		int n = getPlayerCount();
		System.out.println(n);
		playerNames = new String[n];
		
		JFrame frame = new JFrame("InputDialog");
		for(int i=1;i<=n;i++){
			String s = (String)JOptionPane.showInputDialog(
					frame,
                    "Enter name of player "+ i,
                    "Player Info",
                    JOptionPane.PLAIN_MESSAGE);

			if ((s != null) && (s.length() > 0)) {
				playerNames[i-1] = s;
				System.out.println(s);
			}
		}
		return playerNames;
	}
	
}
