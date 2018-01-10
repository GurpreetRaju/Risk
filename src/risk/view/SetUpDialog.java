package risk.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * This class display a dialog to ask user for number of players and map file to use.
 * @author Gurpreet
 * @author Gunpreet
 * @version 1.0
 */
public class SetUpDialog {
	
	/**
	 * Array to store the names of players entered by user.
	 */
	private String[] playerNames;
	
	/**
	 * JFrame for dialog boxes.
	 */
	private JFrame frame;
	
	/**
	 * Button to edit map.
	 */
	private JButton mapEdit;
	
	/**
	 * Button to begin the game.
	 */
	private JButton playGame;
	
	/**
	 * Stores the path of the map file uploaded.
	 */
	private String mapRead = null;
	
	/**
	 * Ask user to enter an integer value.
	 * @param min Minimum value for spinner.
	 * @param max Maximum value for Spinner.
	 * @param message The message to be displayed on the spinner.
	 * @return number of players entered by user or by default 2.
	 */
	public int getInput(int min, int max, String message){
		 JPanel box = new JPanel();
		 SpinnerModel sm = new SpinnerNumberModel(min, min, max, 1); 
		 JSpinner inputSpinner = new JSpinner(sm);
         box.add(new JLabel("Input"));
         box.add(inputSpinner);
         
         int result = JOptionPane.showConfirmDialog(null, box, message, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
         if (result == JOptionPane.OK_OPTION) {
             return (int) inputSpinner.getValue();
         }
		return min;
	}
	
	/**
	 * Ask user to enter name of player one by one.
	 * @return string array containing number of players.
	 */
	public String[] getPlayerInfo() {
		int n = getInput(2,6,"Enter number of Players");
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
	
	/**
	 * Places army on the selected countries.
	 * @param countryList List of countries where the player can place armies.
	 * @return country name selected.
	 */
	public String placeArmyDialog(String[] countryList, String message) {
		JComboBox<String> countriesList = new JComboBox<String>(countryList);
		String[] options = {"OK"};		
		JOptionPane.showOptionDialog(null, countriesList, message, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,		
		options, options[0]);
		String country = countryList[countriesList.getSelectedIndex()];
		return country;
	}
	
	/**
	 * Ask user for the map file to be used for the game.
	 * @param newExtension extension of the file to be picked.
	 * @return mapRead Stores the absolute path of the map file read.
	 */
	public String getMapInfo(String newExtension) {
		JFrame frame = new JFrame("Map File Chooser");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.validate();
		frame.setVisible(true);
		/*JFileChooser to ask user to choose a map file.*/
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(new File("./data/map"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Map Files", newExtension);
		jfc.setFileFilter(filter);

		int returnValue = jfc.showOpenDialog(frame);
		/*Get the path of the map file chosen*/
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			mapRead = selectedFile.getAbsolutePath();
			frame.dispose();
			if(mapRead.substring(mapRead.lastIndexOf("."),mapRead.length()).equalsIgnoreCase("."+newExtension)){
				return mapRead;
			}
		}
		if(newExtension.equals("map")) {
			return getMapInfo(newExtension);
		}
		return null;
	}
	
	/**
	 * Displays frame to choose from Map Edit and Play Game options at the start.
	 */
	public void chooseMapEditorOrPlayGame() {
		frame = new JFrame("Choose one:");
		frame.setSize(new Dimension(200,200));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mapEdit = new JButton("Edit Map");
		playGame = new JButton("Play Game");
		frame.setLayout(new FlowLayout());
		frame.add(mapEdit);
		frame.add(playGame);
		frame.validate();
		frame.setVisible(true);
	}
	
	/**
	 * Returns the frame to be used to dispose it after selection of an option.
	 * @return JFrame
	 */
	public JFrame chooseOptionFrame() {
		return this.frame;
	}
	
	/**
	 * Sets action listener to map edit button.
	 * @param newAction ActionListener for map edit button.
	 */
	public void mapEditAction(ActionListener newAction) {
		this.mapEdit.addActionListener(newAction);
	}
	
	/**
	 * Sets action listener to Play Game button.
	 * @param newAction ActionListener for Play Game button
	 */
	public void playGameAction(ActionListener newAction) {
		this.playGame.addActionListener(newAction);
	}

}
