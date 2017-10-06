package controller.mapeditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.UnsupportedLookAndFeelException;

import model.MapReader;
import view.mapeditor.MapFileChooser;
import view.mapeditor.NewMap;

public class mapEditorController {
	
	private MapFileChooser mapChooser;
	private ActionListener existingBtnAction, newButtonAction;
	
	public void MapRead(String filename) {
		MapReader mapReader = new MapReader();
		mapReader.readMap(filename);

	}
	
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
	                fc.getSelectedFile().getAbsolutePath();
	            }
	        }
	    });
		this.mapChooser.openFileChooseBtnAction(existingBtnAction);
	}
	
	public void newMapActions() {
				NewMap newMap = new NewMap();
				newMap.setVisible(true);
			}
		
	}
	
