package controller.mapeditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.UnsupportedLookAndFeelException;

import model.MapReader;
import model.MapWriter;
import view.mapeditor.ExistingMap;
import view.mapeditor.MapFileChooser;
import view.mapeditor.NewMap;

/**
 * mapEditorController perform action listeners for 
 * New Map and Existing Map buttons in @see MapFrame view 
 * and choosing map file action
 * 
 * @see MapFileChooser
 * @author jyotsna
 *
 */
public class mapEditorController {
	/**
	 * object of MapFileChooser class used for calling the class methods 
	 */
	private MapFileChooser mapChooser;
	/**
	 * action listener applied on button "Choose Map File" for selecting map file
	 */	
	private ActionListener existingBtnAction;
	
	/**
	 * action listener applied on button "Choose Map File" for selecting map file
	 */
	private ActionListener newButtonAction;
	
	/**
	 * Stores the path of the file chosen
	 */
	public static String path = "";

	/**
	 * Calls the readMap function of MapReader to read the map file
	 * @param filename address of the map file to be loaded
	 */
	public void MapRead(String filename) {
		MapReader mapReader = new MapReader();
		mapReader.readMap(filename);

	}

	/**
	 * Function to browse the map file on the local system
	 * This function implements the ActionListener events for the map file chooser button
	 */
	public void mapFileChooserActions() {
		try {
			this.mapChooser = new MapFileChooser();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}
		existingBtnAction=(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File("user.home"));
				fc.setDialogTitle("Choose your Conquest Map File");
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					path = fc.getSelectedFile().getAbsolutePath();
					MapReader mapReader = new MapReader();
					ExistingMap existingMap = new ExistingMap(mapReader.readMap(fc.getSelectedFile().getAbsolutePath()));
					existingMap.setVisible(true);
					// MapWriter mapWriter = new MapWriter();
					// mapWriter.writeMap(mapReader.readMap(fc.getSelectedFile().getAbsolutePath()));
				}
			}
		});
		this.mapChooser.openFileChooseBtnAction(existingBtnAction);
	}

	/**
	 * NewMap Frame get initialize with this method
	 */
	public void newMapActions() {
		NewMap newMap = new NewMap();
		newMap.setVisible(true);
	}

}

