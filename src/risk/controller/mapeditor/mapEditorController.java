package risk.controller.mapeditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

import risk.model.CountryNode;
import risk.model.map.MapModel;
import risk.model.map.MapNode;
import risk.model.map.MapReader;
import risk.model.map.MapWriter;
import risk.view.mapeditor.ExistingMap;
import risk.view.mapeditor.ExistingMapEditor;
import risk.view.mapeditor.MapFileChooser;
import risk.view.mapeditor.NewMap;

/**
 * mapEditorController perform action listeners for 
 * New Map and Existing Map buttons in @see MapFrame view 
 * and choosing map file action
 * 
 * @see MapFileChooser
 * @author jyotsna
 * @author Harinder
 *
 */
public class mapEditorController {

	/**
	 * object of MapFileChooser class used for calling the class methods 
	 */
	private MapFileChooser mapChooser;

	/**
	 * object of ExistingMapEditor class used for calling the class methods 
	 */
	ExistingMapEditor existingMapEditor;

	/**
	 * reference to NewMap object.
	 */
	NewMap newMap;

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
	public String path = "";

	/**
	 * Creates object of MapModel class.
	 */
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

		existingBtnAction=(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new java.io.File("user.home"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Map Files", "map");
				fc.setFileFilter(filter);
				fc.setDialogTitle("Choose your Conquest Map File");
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					path = fc.getSelectedFile().getAbsolutePath();
					MapReader mapReader = new MapReader();
					ExistingMap existingMap = new ExistingMap(mapReader.readMap(fc.getSelectedFile().getAbsolutePath()));
					existingMap.addActionsToBtnEdit(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							existingMap.setVisible(false);
							ArrayList<MapNode> existing_map_Info = existingMap.getExistingMapInfo();
							mapModel.writeExistingMap(existing_map_Info);
							existingMapEditor = new ExistingMapEditor(existing_map_Info);
							existingMapEditor.setVisible(true);
							existingMapActions();
						}
					});
					existingMap.setVisible(true);
				}
			}
		});
		this.mapChooser.openFileChooseBtnAction(existingBtnAction);
	}	

	/**
	 * Function to get the path of map file 
	 * @return path of map file.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Contains actionListeners for all NewMap buttons.
	 * @see NewMap
	 */
	public void newMapActions() {
		newMap = new NewMap();
		newMap.setVisible(true);

		newMap.addActionsToBtnAddContinent(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMap.enableContinentFields();
			}
		});

		newMap.addActionsToBtnAddCountry(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMap.enableCountryfield();
			}
		});

		newMap.addActionsToBtnDone(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cn = newMap.getContinentName();
				String cv = newMap.getControlValue();
				if(cn.compareTo("")==0 || cv.compareTo("")==0){
					newMap.enterValuesError();
				}else{
					int control_value= Integer.parseInt(cv);
					Boolean continentExist1 = mapModel.checkContinentExist(cn);
					if(!continentExist1){
						ArrayList<CountryNode> countryArr = new ArrayList<CountryNode>();
						mapModel.addContinents(cn, countryArr, control_value);
						newMap.clearComboBoxContents();
						for(MapNode i: mapModel.getContinents()){
							String continent = i.getContinentName();
							newMap.setContinentsComboBox(continent);
						}
					}
				}
				newMap.disableContinentField();
			}
		});

		newMap.addActionsToBtnAddNeighbours(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMap.enableJList();
				String sCountrytToAddNeighbour = newMap.getSelectedCountryForNeighbours();
				newMap.clearNeighboursJList();

				for (MapNode node : mapModel.getContinents()){
					for (CountryNode countryNode : node.getCountries()){
						if(sCountrytToAddNeighbour.compareTo(countryNode.getCountryName())==0)
							continue;
						newMap.addPossibleNeighboursToJList(countryNode.getCountryName());
					}
				}
			}
		});

		newMap.addActionsToBtnDeleteNeighbours(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newMap.enableJList_1();
				String sCountryToDeleteNeighbour = newMap.getSelectedCountryForNeighbourDeletion();
				newMap.clearNeighboursJList_1();

				for (MapNode node : mapModel.getContinents()){
					for (CountryNode countryNode : node.getCountries()){
						if(sCountryToDeleteNeighbour.compareTo(countryNode.getCountryName())==0) {
							for (CountryNode neighbour : countryNode.getNeighbourCountries() ) {
								newMap.addPossibleNeighboursToJList_1(neighbour.getCountryName());
							}
						}
					}
				}
			}
		});

		newMap.addActionsToBtnSelectedNeighbours(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(newMap.getNeighboursList().isEmpty()) {
					newMap.noSelectedNeighboursError();
				}
				else {
					ArrayList<CountryNode> neighbours= new ArrayList<CountryNode>();
					for (Object ncountry : newMap.getNeighboursList()){
						CountryNode cn =  new CountryNode(ncountry.toString(), null, null,null);
						neighbours.add(cn);
					}
					String sCountrytToAddNeighbour = newMap.getSelectedCountryForNeighbours();
					for (MapNode node : mapModel.getContinents()){
						for (CountryNode cNode : node.getCountries()){
							if(sCountrytToAddNeighbour.compareTo(cNode.getCountryName())==0)
								for (CountryNode neighbourNode : neighbours){
									cNode.addNeighbour(neighbourNode);
								}

						}
					}
					for (CountryNode neighbour : neighbours) {
						for (MapNode node : mapModel.getContinents()) {
							for (CountryNode countryNode : node.getCountries()) {
								if(countryNode.getCountryName().compareTo(neighbour.getCountryName())==0) {
									countryNode.addNeighbour(new CountryNode(sCountrytToAddNeighbour, null, null,null));
								}
							}
						}
					}
				}	
			}
		});

		
		newMap.addActionsToBtnDeleteSelectedNeighbours(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(newMap.getNeighboursList_1().isEmpty()) {
					newMap.noSelectedNeighboursError();
				}
				else {
					ArrayList<CountryNode> neighbours_1= new ArrayList<CountryNode>();
					for (Object ncountry : newMap.getNeighboursList_1()){
						CountryNode cn =  new CountryNode(ncountry.toString(), null, null,null);
						neighbours_1.add(cn);
					}
					String sCountrytToDeleteNeighbour = newMap.getSelectedCountryForNeighbourDeletion();
					for (MapNode node : mapModel.getContinents()){
						for (CountryNode cNode : node.getCountries()){
							if(sCountrytToDeleteNeighbour.compareTo(cNode.getCountryName())==0)
								for (CountryNode neighbourNode : neighbours_1){
									cNode.removeNeighbour(neighbourNode);	
								}
						}
					}
					for (CountryNode neighbour : neighbours_1) {
						for (MapNode node : mapModel.getContinents()) {
							for (CountryNode countryNode : node.getCountries()) {
								if(countryNode.getCountryName().compareTo(neighbour.getCountryName())==0) {
									countryNode.removeNeighbour(new CountryNode(sCountrytToDeleteNeighbour, null, null,null));
								}
							}
						}
					}
				}	
			}
		});


		newMap.addActionsToBtnDeleteContinent(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<MapNode> continents = mapModel.getContinents();
				String delete_continent = newMap.getContinentToDelete();
				for (MapNode i :continents) {
					if(i.getContinentName().compareTo(delete_continent)==0) {
						continents.remove(i);
						break;
					}
				}
				newMap.clearNeighboursJList();
				newMap.clearComboBoxContents();
				newMap.clearCountryComBoxContents();
				for(MapNode i: continents) {
					newMap.setContinentsComboBox(i.getContinentName());
					for (CountryNode countryNode : i.getCountries()){
						newMap.setCountriesComboBox(countryNode.getCountryName());
						newMap.addPossibleNeighboursToJList(countryNode.getCountryName());
					}
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
							node.removeCountry(temp);
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

		newMap.addActionsToBtnAdd(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean continentExist = newMap.checkContinentExist();
				if(continentExist) {
					String cn1 = newMap.getCountryName();
					if(cn1.compareTo("")==0) {
						newMap.enterValuesError();
					}else {
						String selectedContinent = newMap.getSelectedContinent();
						Boolean countryExist = mapModel.checkCountryExist(cn1);
						if(!countryExist) {
							ArrayList<CountryNode> neighbours= new ArrayList<CountryNode>();
							for (Object ncountry : newMap.getNeighboursList()) {
								CountryNode cn =  new CountryNode(ncountry.toString(), null, null,null);
								neighbours.add(cn);
							}
							newMap.clearNeighboursJList();
							newMap.clearCountryComBoxContents();
							for (MapNode node: mapModel.getContinents()) {
								if(selectedContinent.compareTo(node.getContinentName())==0) {
									int a[]= {250,250};
									CountryNode newCountry = new CountryNode(cn1,  neighbours , a,null);
									node.addCountry(newCountry);
								}
								for (CountryNode temp : node.getCountries()) {
									newMap.addPossibleNeighboursToJList(temp.getCountryName());
									newMap.setCountriesComboBox(temp.getCountryName());

								}
							}
						}else {
							newMap.countryAlreadyExistError();
						}
					}
				}else {
					newMap.nullContinentError();
				}
				newMap.disableCountryfield();
			}
		});

	}

	/**
	 * Contains actionListeners for all ExistingMapEditor buttons.
	 * @see ExistingMapEditor
	 */
	public void existingMapActions() {

		existingMapEditor.addActionsToBtnAddContinent(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existingMapEditor.enableContinentFields();
			}
		});

		existingMapEditor.addActionsToBtnAddCountry(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existingMapEditor.enableCountryfield();
			}
		});

		existingMapEditor.addActionsToBtnDeleteNeighbours(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existingMapEditor.enableJList_1();
				String sCountrytToDeleteNeighbour = existingMapEditor.getSelectedCountryForNeighbourDeletion();
				existingMapEditor.clearNeighboursJList_1();

				for (MapNode node : mapModel.getContinents()){
					for (CountryNode countryNode : node.getCountries()){
						if(sCountrytToDeleteNeighbour.compareTo(countryNode.getCountryName())==0) {
							for (CountryNode neighbour : countryNode.getNeighbourCountries() ) {
								existingMapEditor.addPossibleNeighboursToJList_1(neighbour.getCountryName());
							}
						}
					}
				}
			}
		});

		existingMapEditor.addActionsToBtnDeleteSelectedNeighbours(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(existingMapEditor.getNeighboursList_1().isEmpty()) {
					existingMapEditor.noSelectedNeighboursError();
				}else {
					ArrayList<CountryNode> neighbours_1= new ArrayList<CountryNode>();
					String sCountrytToDeleteNeighbour = existingMapEditor.getSelectedCountryForNeighbourDeletion();
					for (Object ncountry : existingMapEditor.getNeighboursList_1()){
						CountryNode cn =  new CountryNode(ncountry.toString(), null, null, null);
						neighbours_1.add(cn);
					}
					for (MapNode node : mapModel.getContinents()){
						for (CountryNode cNode : node.getCountries()){
							if(sCountrytToDeleteNeighbour.compareTo(cNode.getCountryName())==0)
								for (CountryNode neighbourNode : neighbours_1){
									cNode.removeNeighbour(neighbourNode);
								}
						}
					}
					for (CountryNode neighbour : neighbours_1) {
						for (MapNode node : mapModel.getContinents()) {
							for (CountryNode countryNode : node.getCountries()) {
								if(countryNode.getCountryName().compareTo(neighbour.getCountryName())==0) {
									countryNode.removeNeighbour(new CountryNode(sCountrytToDeleteNeighbour, null, null, null));
								}
							}
						}
					}
				}	
			}
		});


		existingMapEditor.addActionsToBtnDone(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cn = existingMapEditor.getContinentName();
				String cv = existingMapEditor.getControlValue();
				if(cn.compareTo("")==0 || cv.compareTo("")==0){
					existingMapEditor.enterValuesError();
				}else{
					int control_value= Integer.parseInt(cv);
					Boolean continentExist1 = mapModel.checkContinentExist(cn);
					if(!continentExist1){
						ArrayList<CountryNode> countryArr = new ArrayList<CountryNode>();
						mapModel.addContinents(cn, countryArr, control_value);
						existingMapEditor.clearComboBoxContents();
						for(MapNode i: mapModel.getContinents()){
							String continent = i.getContinentName();
							existingMapEditor.setContinentsComboBox(continent);
						}
					}
				}
				existingMapEditor.disableContinentField();
			}
		});

		existingMapEditor.addActionsToBtnAddNeighbours(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existingMapEditor.enableJList();
				String sCountrytToAddNeighbour = existingMapEditor.getSelectedCountryForNeighbours();
				existingMapEditor.clearNeighboursJList();
				for (MapNode node : mapModel.getContinents()){
					for (CountryNode countryNode : node.getCountries()){
						if(sCountrytToAddNeighbour.compareTo(countryNode.getCountryName())==0)
							continue;
						existingMapEditor.addPossibleNeighboursToJList(countryNode.getCountryName());
					}
				}
			}
		});

		existingMapEditor.addActionsToBtnSelectedNeighbours(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(existingMapEditor.getNeighboursList().isEmpty()) {
					existingMapEditor.noSelectedNeighboursError();
				}else {
					ArrayList<CountryNode> neighbours= new ArrayList<CountryNode>();
					String sCountrytToAddNeighbour = existingMapEditor.getSelectedCountryForNeighbours();
					for (Object ncountry : existingMapEditor.getNeighboursList()){
						CountryNode cn =  new CountryNode(ncountry.toString(), null, null, null);
						neighbours.add(cn);
					}
					for (MapNode node : mapModel.getContinents()){
						for (CountryNode cNode : node.getCountries()){
							if(sCountrytToAddNeighbour.compareTo(cNode.getCountryName())==0)
								for (CountryNode neighbourNode : neighbours){
									cNode.addNeighbour(neighbourNode);	
								}
						}
					}
					for (CountryNode neighbour : neighbours) {
						for (MapNode node : mapModel.getContinents()) {
							for (CountryNode countryNode : node.getCountries()) {
								if(countryNode.getCountryName().compareTo(neighbour.getCountryName())==0) {
									countryNode.addNeighbour(new CountryNode(sCountrytToAddNeighbour, null, null, null));
								}
							}
						}
					}
				}
			}
		});

		existingMapEditor.addActionsToButtonDeleteContinent(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<MapNode> continents = mapModel.getContinents();
				String delete_continent = existingMapEditor.getContinentToDelete();
				for (MapNode i :continents) {
					if(i.getContinentName().compareTo(delete_continent)==0) {
						continents.remove(i);
						break;
					}
				}
				existingMapEditor.clearComboBoxContents();
				for(MapNode i: continents) {
					existingMapEditor.setContinentsComboBox(i.getContinentName());
				}
				existingMapEditor.clearNeighboursJList();
				existingMapEditor.clearComboBoxContents();
				existingMapEditor.clearCountryComBoxContents();
				for(MapNode i: continents) {
					existingMapEditor.setContinentsComboBox(i.getContinentName());
					for (CountryNode countryNode : i.getCountries()){
						existingMapEditor.setCountriesComboBox(countryNode.getCountryName());
						existingMapEditor.addPossibleNeighboursToJList(countryNode.getCountryName());
					}
				}
			}
		});

		existingMapEditor.addActionsToBtnSave(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mapModel.checkOnSaveMap()) {
					mapModel.saveToExistingMapFile(getPath());
				}else {
					existingMapEditor.nullCountryError();
				}
			}
		});

		existingMapEditor.addActionsToBtnDeleteCountry(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selectedcountry = existingMapEditor.getCountryForDeletion();
				existingMapEditor.clearNeighboursJList();
				existingMapEditor.clearCountryComBoxContents();
				ArrayList<MapNode> continents = mapModel.getContinents();
				for (MapNode node: continents) {
					for (CountryNode temp : node.getCountries()) {
						if(temp.getCountryName().compareTo(selectedcountry)==0) {
							node.removeCountry(temp);
						}
					}
				}
				for (MapNode node: continents) {
					for (CountryNode temp : node.getCountries()) {
						existingMapEditor.setCountriesComboBox(temp.getCountryName());
						existingMapEditor.addPossibleNeighboursToJList(temp.getCountryName());
					}
				}
			}
		});

		existingMapEditor.addActionsToBtnAdd(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean continentExist = existingMapEditor.checkContinentExist();
				if(continentExist) {
					String cn1 = existingMapEditor.getCountryName();
					if(cn1.compareTo("")==0) {
						existingMapEditor.enterValuesError();
					}else {
						String selectedContinent = existingMapEditor.getSelectedContinent();
						Boolean countryExist = mapModel.checkCountryExist(cn1);
						if(!countryExist) {
							ArrayList<CountryNode> neighbours= new ArrayList<CountryNode>();
							for (Object ncountry : existingMapEditor.getNeighboursList()) {//check
								CountryNode cn =  new CountryNode(ncountry.toString(), null, null, null);
								neighbours.add(cn);
							}
							existingMapEditor.clearNeighboursJList();
							existingMapEditor.clearCountryComBoxContents();
							for (MapNode node: mapModel.getContinents()) {
								if(selectedContinent.compareTo(node.getContinentName())==0) {
									int a[]= {250,250};
									CountryNode newCountry = new CountryNode(cn1,  neighbours , a, null);
									node.addCountry(newCountry);
								}
								for (CountryNode temp : node.getCountries()) {
									existingMapEditor.addPossibleNeighboursToJList(temp.getCountryName());
									existingMapEditor.setCountriesComboBox(temp.getCountryName());
								}
							}
						}else {
							existingMapEditor.countryAlreadyExistError();
						}
					}
				}else {
					existingMapEditor.nullContinentError();
				}
				existingMapEditor.disableCountryfield();
			}
		});
	}
}

