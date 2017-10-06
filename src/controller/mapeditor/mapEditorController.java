package controller.mapeditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import model.MapReader;
import view.mapeditor.MapFileChooser;

public class mapEditorController {
	
	private MapFileChooser mapChooser;
	private ActionListener existingBtnAction;
	
	public void MapRead(String filename) {
		MapReader mapReader = new MapReader();
		mapReader.readMap(filename);

	}
	
	public void MapFileChooserActions(MapFileChooser newMapChooser) {
		this.mapChooser = newMapChooser;
		existingBtnAction = new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	final JFileChooser fc = new JFileChooser();
	            fc.setCurrentDirectory(new java.io.File("user.home"));
	            fc.setDialogTitle("Choose your Conquest Map File");
	            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	                fc.getSelectedFile().getAbsolutePath();
	            }
	        }
	    }
		this.mapChooser.openFileChooseBtnAction(existingBtnAction);
	}
	
}
