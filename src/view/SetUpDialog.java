package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;

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
import javax.swing.filechooser.FileSystemView;


/**
 * This class display a dialog to ask user for number of players and map file to use.
 * 
 * @author Gurpreet
 * @author Gunpreet
 * @version 1.0
 */
public class SetUpDialog {
	
	/**
	 * Array to store the names of players entered by user.
	 */
	private String[] playerNames;
	
	private JFrame frame;
	
	private JButton mapEdit;
	
	private JButton playGame;
	
	private String mapRead = null;
	/**
	 * Ask user to enter the number of players.
	 * @return number of players entered by user or by default 2.
	 */
	private int getPlayerCount(){
		 JPanel box = new JPanel();
		 SpinnerModel sm = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1); 
		 JSpinner inputSpinner = new JSpinner(sm);
         box.add(new JLabel("Number of players: "));
         box.add(inputSpinner);
         
         int result = JOptionPane.showConfirmDialog(null, box, "Enter number of Players", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
         if (result == JOptionPane.OK_OPTION) {
             return (int) inputSpinner.getValue();
         }
		return 2;
	}
	/**
	 * Ask user to enter name of player one by one.
	 * @return string array containing number of players.
	 */
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
	
	public String placeArmyDialog(String[] countryList){
		JComboBox<String> countriesList = new JComboBox<String>(countryList);
		String[] options = {"OK"};
		int selection = JOptionPane.showOptionDialog(null, countriesList, "Place your army",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
        options, options[0]);
		String country = countryList[countriesList.getSelectedIndex()];
		return country;
	}
	
	/**
	 * Ask user for the map file to be used for the game.
	 * @param extension 
	 * @return mapRead Stores the absolute path of the map file read.
	 */
	public String getMapInfo(String newExtension){
		JFrame frame = new JFrame("Map File Chooser");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.validate();
		frame.setVisible(true);
//		JButton btn = new JButton("Choose File");
//		frame.add(btn);
		JFileChooser jfc = new JFileChooser();
		jfc.setCurrentDirectory(new File("./data/map"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Map Files", newExtension);
		jfc.setFileFilter(filter);
//		btn.addActionListener(e -> {
			int returnValue = jfc.showOpenDialog(frame);
			
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = jfc.getSelectedFile();
				mapRead = selectedFile.getAbsolutePath();
				if(mapRead.substring(mapRead.lastIndexOf("."),mapRead.length()).equalsIgnoreCase(".map")
						|| mapRead.substring(mapRead.lastIndexOf("."),mapRead.length()).equalsIgnoreCase(".bmp")){
					return mapRead;
				}
			}
			
		return null;

//		});
//		frame.pack();
//		frame.setVisible(true);
		
	}
	
	public void chooseMapEditorOrPlayGame(){
		frame = new JFrame("Choose one:");
		frame.setSize(new Dimension(200,200));//set size
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mapEdit = new JButton("Edit Map");
		playGame = new JButton("Play Game");
		frame.setLayout(new FlowLayout());
		frame.add(mapEdit);
		frame.add(playGame);
		frame.validate();
		frame.setVisible(true);
	}
	
	public JFrame chooseOptionFrame(){
		return this.frame;
	}
	
	public void mapEditAction(ActionListener newAction)
	{
		this.mapEdit.addActionListener(newAction);
	}
	
	public void playGameAction(ActionListener newAction)
	{
		this.playGame.addActionListener(newAction);
	}
}
