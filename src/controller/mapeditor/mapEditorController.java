package controller.mapeditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.CountryNode;
import model.MapModel;
import model.MapNode;
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

	MapModel mapModel = new MapModel();

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
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Map Files", "map");
				fc.setFileFilter(filter);
				fc.setDialogTitle("Choose your Conquest Map File");
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					path = fc.getSelectedFile().getAbsolutePath();
					MapReader mapReader = new MapReader();
					ExistingMap existingMap = new ExistingMap(mapReader.readMap(fc.getSelectedFile().getAbsolutePath()));
					existingMap.setVisible(true);
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

		newMap.addActionsToBtnDone(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cn = newMap.getContinentName();
				String cv = newMap.getControlValue();
				if(cn.compareTo("")==0 || cv.compareTo("")==0) {
					newMap.enterValuesError();
				}else {
					int control_value= Integer.parseInt(cv);
					Boolean continentExist1 = mapModel.checkContinentExist(cn);
					if(!continentExist1) {
						ArrayList<CountryNode> countryArr = new ArrayList<CountryNode>();
						mapModel.addContinents(cn, countryArr, control_value);
						newMap.clearComboBoxContents();
						for(MapNode i: mapModel.getContinents()) {
							String continent = i.getContinentName();
							newMap.setContinentsComboBox(continent);

						}
					}
				}
			}
		});

		newMap.addActionsToBtnAddNeighbours(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMap.enableJList();
				String sCountrytToAddNeighbour = newMap.getSelectedCountryForNeighbours();
				newMap.clearNeighboursJList();
				for (MapNode node : mapModel.getContinents()) {
					for (CountryNode countryNode : node.getCountries()) {
						if(sCountrytToAddNeighbour.compareTo(countryNode.getCountryName())==0)
							continue;
						newMap.addPossibleNeighboursToJList(countryNode.getCountryName());
					}
				}
			}
		});
		
		newMap.addActionsToBtnSelectedNeighbours(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<CountryNode> neighbours= new ArrayList<CountryNode>();
				for (Object ncountry : newMap.getNeighboursList() ) {
					CountryNode cn =  new CountryNode(ncountry.toString(), null, null);
					neighbours.add(cn);
				}
				for (MapNode node : mapModel.getContinents()) {
					for (CountryNode cNode : node.getCountries()) {
						String sCountrytToAddNeighbour = newMap.getSelectedCountryForNeighbours();
						if(sCountrytToAddNeighbour.compareTo(cNode.getCountryName())==0)
							for (CountryNode neighbourNode : neighbours) {
								cNode.addNeighbour(neighbourNode);	
							}
					}
				}
			}
		});

		newMap.addActionsToButtonDeleteContinent(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<MapNode> continents = mapModel.getContinents();
				String delete_continent = newMap.getContinentToDelete();
				for (MapNode i :continents) {
					if(i.getContinentName().compareTo(delete_continent)==0) {
						continents.remove(i);//check
						break;
					}
				}
				newMap.clearComboBoxContents();
				for(MapNode i: continents) {
					newMap.setContinentsComboBox(i.getContinentName());
				}
			}
		});
		
		newMap.addActionsToBtnSave(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mapModel.checkOnSaveMap()) {
					mapModel.saveMapFile();
				}else {
					newMap.nullCountryError();
				}
			}
		});
		
		newMap.addActionsToBtnDeleteCountry(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedcountry = newMap.getCountryForDeletion();
				newMap.clearNeighboursJList();
				newMap.clearCountryComBoxContents();
				ArrayList<MapNode> continents = mapModel.getContinents();
				for (MapNode node: continents) {
					for (CountryNode temp : node.getCountries()) {
						if(temp.getCountryName().compareTo(selectedcountry)==0) {
							node.removeCountry(temp);//check
						}
					}
				}
				for (MapNode node: continents) {
					for (CountryNode temp : node.getCountries()) {
						newMap.setCountriesComboBox(temp.getCountryName());
						newMap.addPossibleNeighboursToJList(temp.getCountryName());
					}
				}
			}
		});
		
		newMap.setVisible(true);
	}

}

