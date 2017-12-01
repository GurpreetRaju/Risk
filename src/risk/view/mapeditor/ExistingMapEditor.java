package risk.view.mapeditor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import risk.controller.mapeditor.mapEditorController;
import risk.model.map.CountryNode;
import risk.model.map.MapNode;
import risk.model.map.MapWriter;

import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


/**
 * New map class opens the JFrame view for add/delete country and continent.
 * @author jyotsna
 * @author Harinder
 */
public class ExistingMapEditor extends JFrame {

	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = -5188102736306067380L;
	
	/**
	 * Creates a new MapNode.
	 */
	ArrayList<MapNode> continents = new ArrayList<MapNode>();

	/**
	 * Stores the JPanel of the frame this class
	 */
	private JPanel contentPane;

	/**
	 * Text field that receives continent name. 
	 */
	private JTextField txtContinentNameHere;

	/**
	 * Text field that receives control value.
	 */
	private JTextField txtContinentControlValue;

	/**
	 * Text Field to hold country name
	 */
	private JTextField txtCountryName;

	/**
	 * ComboBox for continent dropdown.
	 */
	private JComboBox comboBox;

	/**
	 * ComboBox for continent dropdown.
	 */
	private JComboBox comboBox_3;

	/**
	 * Stores reference to the MapNode object.
	 */
	private MapNode mapNode;

	/**
	 * Stores add continent button.
	 */
	private JButton btnDone;

	/**
	 * Stores add neighbours button
	 */
	private JButton btnAddNeighbours;

	/**
	 * Button to enable add continent field.
	 */
	private JButton btnAddContinent;
	
	/**
	 * Creates MapWriter object.
	 */
	private MapWriter mapWriter = new MapWriter();

	/**
	 * Stores the continent name.
	 */
	private String continentName;

	/**
	 * Stores the continent control value.
	 */
	private String cv;

	/**
	 * Stores list of neighbours.
	 */
	private JList list;

	/**
	 * Stores list of countries.
	 */
	private JComboBox comboBox_1;

	/**
	 * Stores list model for neighbours JList.
	 */
	private DefaultListModel<String> model2;

	/**
	 * Stores list model for JList for deleting neighbors.
	 */
	private DefaultListModel<String> model1;
	
	/**
	 * Button for selected neighbours. 
	 */
	private JButton btnSelectedNeighbours;

	/**
	 * Button for continent deletion.
	 */
	private JButton btnDeleteContinent;

	/**
	 * Button for saving map file.
	 */
	private JButton btnSaveMap;

	/**
	 * Button for country deletion.
	 */
	private JButton btnDeleteCountry;

	/**
	 * Stores and displays list of continents.
	 */
	private JComboBox comboBox_2;

	/**
	 * Button to add a new country.
	 */
	private JButton btnAdd;
	
	/**
	 * Button to update country arrayList
	 */
	private JButton btnAddCountry;
	
	/**
	 * JButton to get possible neighbors of a country for deletion.
	 */
	private JButton btnDeleteNeighbours;
	
	/**
	 * ComboBox to display country list for deleting neighbors.
	 */
	private JComboBox comboBox_4;
	
	/**
	 * JLabel for list of neighbors of each country.
	 */
	private JLabel lblNeighboursOfSelected;
	
	/**
	 * JList to display neighbors of selected country.
	 */
	private JList list_1;
	
	/**
	 * JButton to delete selected countries.
	 */
	private JButton btnDeleteSelectedNeighbours;

	/**
	 * NewMap constructor calls initialize method of the class
	 * @see initialize()
	 * @param editMap arraylist of continents.
	 */
	public ExistingMapEditor(ArrayList<MapNode> editMap) {
		continents = editMap;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(140, 140, 500, 340);
		this.setTitle("Existing map editor");
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 

		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblCreateYourOwn = new JLabel("Edit the existing map file!");
		lblCreateYourOwn.setFont(new Font("Script MT Bold", Font.PLAIN, 48));
		GridBagConstraints gbc_lblCreateYourOwn = new GridBagConstraints();
		gbc_lblCreateYourOwn.insets = new Insets(0, 0, 5, 0);
		gbc_lblCreateYourOwn.gridwidth = 7;
		gbc_lblCreateYourOwn.gridx = 0;
		gbc_lblCreateYourOwn.gridy = 0;
		contentPane.add(lblCreateYourOwn, gbc_lblCreateYourOwn);

		btnAddContinent = new JButton("New Continent");
		btnAddContinent.setForeground(Color.BLACK);
		btnAddContinent.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		btnAddContinent.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_btnAddContinent = new GridBagConstraints();
		gbc_btnAddContinent.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddContinent.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddContinent.gridx = 1;
		gbc_btnAddContinent.gridy = 2;
		contentPane.add(btnAddContinent, gbc_btnAddContinent);

		JLabel lblName = new JLabel("Continent Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 5;
		gbc_lblName.gridy = 2;
		contentPane.add(lblName, gbc_lblName);

		txtContinentNameHere = new JTextField();
		txtContinentNameHere.setFont(new Font("Tahoma", Font.ITALIC, 13));
		txtContinentNameHere.setForeground(Color.BLACK);
		GridBagConstraints gbc_txtContinentNameHere = new GridBagConstraints();
		gbc_txtContinentNameHere.insets = new Insets(0, 0, 5, 0);
		gbc_txtContinentNameHere.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtContinentNameHere.gridx = 6;
		gbc_txtContinentNameHere.gridy = 2;
		txtContinentNameHere.setEnabled(false);
		contentPane.add(txtContinentNameHere, gbc_txtContinentNameHere);
		txtContinentNameHere.setColumns(10);

		txtContinentControlValue = new JTextField();
		txtContinentControlValue.setFont(new Font("Tahoma", Font.ITALIC, 13));
		txtContinentControlValue.setForeground(Color.BLACK);
		txtContinentControlValue.setEnabled(false);
		GridBagConstraints gbc_txtContinentControlValue = new GridBagConstraints();
		gbc_txtContinentControlValue.insets = new Insets(0, 0, 5, 0);
		gbc_txtContinentControlValue.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtContinentControlValue.gridx = 6;
		gbc_txtContinentControlValue.gridy = 3;
		contentPane.add(txtContinentControlValue, gbc_txtContinentControlValue);
		txtContinentControlValue.setColumns(10);

		JLabel lblValue = new JLabel("Control Value");
		GridBagConstraints gbc_lblValue = new GridBagConstraints();
		gbc_lblValue.anchor = GridBagConstraints.EAST;
		gbc_lblValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue.gridx = 5;
		gbc_lblValue.gridy = 3;
		contentPane.add(lblValue, gbc_lblValue);

		btnDone = new JButton("Add Continent");
		btnDone.setBackground(new Color(240, 255, 255));
		btnDone.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDone.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnDone = new GridBagConstraints();
		gbc_btnDone.anchor = GridBagConstraints.EAST;
		gbc_btnDone.insets = new Insets(0, 0, 5, 0);
		gbc_btnDone.gridx = 6;
		gbc_btnDone.gridy = 4;
		contentPane.add(btnDone, gbc_btnDone);

		btnAddCountry = new JButton("New Country");
		btnAddCountry.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		btnAddCountry.setForeground(Color.BLACK);
		btnAddCountry.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_btnAddCountry = new GridBagConstraints();
		gbc_btnAddCountry.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddCountry.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddCountry.gridx = 1;
		gbc_btnAddCountry.gridy = 5;
		contentPane.add(btnAddCountry, gbc_btnAddCountry);

		comboBox = new JComboBox();			/*continent list*/
		comboBox.setToolTipText("Choose existing continent to add new country");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 5;
		contentPane.add(comboBox, gbc_comboBox);
		for (MapNode mapNode : continents) {
			comboBox.addItem(mapNode.getContinentName());
		}

		JLabel lblName_1 = new JLabel("Country Name");
		GridBagConstraints gbc_lblName_1 = new GridBagConstraints();
		gbc_lblName_1.anchor = GridBagConstraints.EAST;
		gbc_lblName_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblName_1.gridx = 5;
		gbc_lblName_1.gridy = 6;
		contentPane.add(lblName_1, gbc_lblName_1);

		txtCountryName = new JTextField();
		txtCountryName.setFont(new Font("Tahoma", Font.ITALIC, 13));
		txtCountryName.setForeground(Color.BLACK);
		txtCountryName.setEnabled(false);
		txtCountryName.setText("");
		GridBagConstraints gbc_txtCountryName = new GridBagConstraints();
		gbc_txtCountryName.insets = new Insets(0, 0, 5, 0);
		gbc_txtCountryName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCountryName.gridx = 6;
		gbc_txtCountryName.gridy = 6;
		contentPane.add(txtCountryName, gbc_txtCountryName);
		txtCountryName.setColumns(10);

		btnDeleteCountry = new JButton("Delete Country");
		btnDeleteCountry.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		btnDeleteCountry.setForeground(Color.BLACK);
		btnDeleteCountry.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_btnDeleteCountry = new GridBagConstraints();
		gbc_btnDeleteCountry.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeleteCountry.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteCountry.gridx = 1;
		gbc_btnDeleteCountry.gridy = 8;
		contentPane.add(btnDeleteCountry, gbc_btnDeleteCountry);

		comboBox_2 = new JComboBox();			/*list of countries.*/
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		ArrayList<String> choice = new ArrayList<>();
		gbc_comboBox_2.gridwidth = 3;
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 2;
		gbc_comboBox_2.gridy = 8;
		contentPane.add(comboBox_2, gbc_comboBox_2);
		for (MapNode mapNode : continents) {
			for (CountryNode countryNode : mapNode.getCountries()) {
				comboBox_2.addItem(countryNode.getCountryName());
			}
		}

		btnAdd = new JButton("Add Country");
		btnAdd.setBackground(new Color(240, 255, 255));
		btnAdd.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.anchor = GridBagConstraints.EAST;
		gbc_btnAdd.gridx = 6;
		gbc_btnAdd.gridy = 8;
		contentPane.add(btnAdd, gbc_btnAdd);

		btnDeleteContinent = new JButton("Delete Continent");
		btnDeleteContinent.setBackground(new Color(240, 255, 255));
		btnDeleteContinent.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		btnDeleteContinent.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnDeleteContinent = new GridBagConstraints();
		gbc_btnDeleteContinent.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeleteContinent.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteContinent.gridx = 1;
		gbc_btnDeleteContinent.gridy = 11;
		contentPane.add(btnDeleteContinent, gbc_btnDeleteContinent);

		comboBox_3 = new JComboBox();
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.gridwidth = 3;
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.gridx = 2;
		gbc_comboBox_3.gridy = 11;
		contentPane.add(comboBox_3, gbc_comboBox_3);
		for (MapNode mapNode : continents) {
			comboBox_3.addItem(mapNode.getContinentName());
		}

		btnAddNeighbours = new JButton("Choose Neighbours");
		btnAddNeighbours.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		GridBagConstraints gbc_btnAddNeighbours = new GridBagConstraints();
		gbc_btnAddNeighbours.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddNeighbours.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddNeighbours.gridx = 1;
		gbc_btnAddNeighbours.gridy = 14;
		contentPane.add(btnAddNeighbours, gbc_btnAddNeighbours);

		comboBox_1 = new JComboBox();
		GridBagConstraints gbc_comboBox_11 = new GridBagConstraints();
		comboBox_1.setToolTipText("Select country for adding neighbours");
		gbc_comboBox_11.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_11.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_11.gridx = 2;
		gbc_comboBox_11.gridy = 14;
		comboBox_1.setEnabled(true);
		contentPane.add(comboBox_1, gbc_comboBox_11);
		for (MapNode mapNode : continents) {
			for (CountryNode countryNode : mapNode.getCountries()) {
				comboBox_1.addItem(countryNode.getCountryName());
			}
		}

		JLabel lblChooseNeighbours = new JLabel("Possible neighbours for selected country");
		lblChooseNeighbours.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		GridBagConstraints gbc_lblChooseNeighbours = new GridBagConstraints();
		gbc_lblChooseNeighbours.insets = new Insets(0, 0, 5, 5);
		gbc_lblChooseNeighbours.gridx = 1;
		gbc_lblChooseNeighbours.gridy = 16;
		contentPane.add(lblChooseNeighbours, gbc_lblChooseNeighbours);

		model2 = new DefaultListModel<String>();
		list = new JList();
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setModel(model2);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 2;
		gbc_list.gridy = 16;
		contentPane.add(scrollPane, gbc_list);
		list.setEnabled(false);

		btnSelectedNeighbours = new JButton("Add selected countries as neighbours");
		GridBagConstraints gbc_btnDeleteNeighbours = new GridBagConstraints();
		gbc_btnDeleteNeighbours.anchor = GridBagConstraints.EAST;
		gbc_btnDeleteNeighbours.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteNeighbours.gridx = 2;
		gbc_btnDeleteNeighbours.gridy = 17;
		contentPane.add(btnSelectedNeighbours, gbc_btnDeleteNeighbours);
		
		btnDeleteNeighbours = new JButton("Delete Neighbours");
		btnDeleteNeighbours.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		gbc_btnDeleteNeighbours = new GridBagConstraints();
		gbc_btnDeleteNeighbours.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeleteNeighbours.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteNeighbours.gridx = 1;
		gbc_btnDeleteNeighbours.gridy = 20;
		contentPane.add(btnDeleteNeighbours, gbc_btnDeleteNeighbours);
		
		comboBox_4 = new JComboBox();
		GridBagConstraints gbc_comboBox_4 = new GridBagConstraints();
		gbc_comboBox_4.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_4.gridx = 2;
		gbc_comboBox_4.gridy = 20;
		contentPane.add(comboBox_4, gbc_comboBox_4);
		for (MapNode mapNode : continents) {
			for (CountryNode countryNode : mapNode.getCountries()) {
				comboBox_4.addItem(countryNode.getCountryName());
			}
		}
		
		lblNeighboursOfSelected = new JLabel("Neighbours of selected country");
		lblNeighboursOfSelected.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		GridBagConstraints gbc_lblNeighboursOfSelected = new GridBagConstraints();
		gbc_lblNeighboursOfSelected.insets = new Insets(0, 0, 5, 5);
		gbc_lblNeighboursOfSelected.gridx = 1;
		gbc_lblNeighboursOfSelected.gridy = 22;
		contentPane.add(lblNeighboursOfSelected, gbc_lblNeighboursOfSelected);
		
		model1 = new DefaultListModel<String>();
		list_1 = new JList();
		list_1.setModel(model1);
		JScrollPane scrollPane1 = new JScrollPane(list_1);
		list_1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		GridBagConstraints gbc_list_1 = new GridBagConstraints();
		gbc_list_1.insets = new Insets(0, 0, 5, 5);
		gbc_list_1.fill = GridBagConstraints.BOTH;
		gbc_list_1.gridx = 2;
		gbc_list_1.gridy = 22;
		contentPane.add(list_1, gbc_list_1);
		list_1.setEnabled(false);
		
		btnDeleteSelectedNeighbours = new JButton("Delete selected Neighbours");
		GridBagConstraints gbc_btnDeleteSelectedNeighbours = new GridBagConstraints();
		gbc_btnDeleteSelectedNeighbours.anchor = GridBagConstraints.EAST;
		gbc_btnDeleteSelectedNeighbours.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteSelectedNeighbours.gridx = 2;
		gbc_btnDeleteSelectedNeighbours.gridy = 23;
		contentPane.add(btnDeleteSelectedNeighbours, gbc_btnDeleteSelectedNeighbours);

		btnSaveMap = new JButton("Save Map");
		btnSaveMap.setBackground(new Color(152, 251, 152));
		btnSaveMap.setFont(new Font("Lucida Calligraphy", Font.BOLD, 16));
		GridBagConstraints gbc_btnSaveMap = new GridBagConstraints();
		gbc_btnSaveMap.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSaveMap.gridx = 6;
		gbc_btnSaveMap.gridy = 29;
		contentPane.add(btnSaveMap, gbc_btnSaveMap);
	
	}
	
	/**
	 * Function to add actionListener to add country button.
	 * @param newAction receives the actionListener for this function.
	 */
	public void addActionsToBtnAddCountry(ActionListener newAction) {
		btnAddCountry.addActionListener(newAction);
	}
	
	/**
	 * Function to add actionListener to add continent button.
	 * @param newAction receives the actionListener for this function.
	 */
	public void addActionsToBtnAddContinent(ActionListener newAction) {
		btnAddContinent.addActionListener(newAction);
	}
    
	/**
	 * Function to add actionListener to button for updating continent list.
	 * @param newAction receives the actionListener for this function.
	 */
	public void addActionsToBtnDone(ActionListener newAction) {
		btnDone.addActionListener(newAction);
	}
	
	/**
	 * Function to add actionListener to button for updating country list.
	 * @param newAction receives the actionListener for this function.
	 */
	public void addActionsToBtnAdd(ActionListener newAction) {
		btnAdd.addActionListener(newAction);
	}
	
	/**
	 * Function to add actionListener to add neighbours button.
	 * @param newAction receives the actionListener for this function.
	 */
	public void addActionsToBtnAddNeighbours(ActionListener newAction) {
		btnAddNeighbours.addActionListener(newAction);
	}
	
	/**
	 * @see mapEditorController
	 * @param newAction actionListener for btnDeleteNeighbors.
	 */
	public void addActionsToBtnDeleteNeighbours(ActionListener newAction) {
		 		btnDeleteNeighbours.addActionListener(newAction);
	}
	
	/**
	 * @see mapEditorController 
	 * @param newAction actionListener for btnDeleteSelectedNeighbours.
	 */
	public void addActionsToBtnDeleteSelectedNeighbours(ActionListener newAction) {
		 		btnDeleteSelectedNeighbours.addActionListener(newAction);
	}
	
	/**
	 * Function to add actionListener to select neighbours button.
	 * @param newAction receives the actionListener for this function.
	 */
	public void addActionsToBtnSelectedNeighbours(ActionListener newAction) {
		btnSelectedNeighbours.addActionListener(newAction);
	}
	
	/**
	 * Function to add actionListener to delete continent button.
	 * @param newAction receives the actionListener for this function.
	 */
	public void addActionsToButtonDeleteContinent(ActionListener newAction) {
		btnDeleteContinent.addActionListener(newAction);
	}
	
	/**
	 * Function to add actionListener to save button.
	 * @param newAction receives the actionListener for this function.
	 */
	public void addActionsToBtnSave(ActionListener newAction) {
		btnSaveMap.addActionListener(newAction);
	}
	
	/**
	 * Function to add actionListener to delete country button.
	 * @param newAction receives the actionListener for this function.
	 */
	public void addActionsToBtnDeleteCountry(ActionListener newAction) {
		btnDeleteCountry.addActionListener(newAction);
	}
	
	/**
	 * Function to display error dialog box.
	 */
	public void noSelectedNeighboursError() {
		JOptionPane.showMessageDialog(contentPane, "Select neighbours first", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Function to enable the fields associated with continent.
	 */
	public void enableContinentFields() {
		txtContinentNameHere.setEnabled(true);
		txtContinentControlValue.setEnabled(true);
	}
	
	/**
	 * Function to check for unique continents.
	 * @return true if continent already exist.
	 */
	public boolean checkContinentExist() {
		Boolean continentExist = false;
		if(comboBox.getItemCount()!=0) {
			continentExist = true;
		}
		return continentExist;
	}
	
	/**
	 * Function to get the country name entered.
	 * @return the country name entered.
	 */
	public String getCountryName() {
		return (txtCountryName.getText());
	}

	/**
	 * Funtion to return list of selected neighbours.
	 * @return list of selected neighbours.
	 */
	public List getNeighboursList() {
		return (list.getSelectedValuesList());
	}
	
	/**
	 * gives list of neighbors for deletion
	 * @return list of neighbor countries for deletion.
	 */
	public List getNeighboursList_1() {
		 return (list_1.getSelectedValuesList());
	}
	
	/**
	 * Function to enable JList.
	 */
	public void enableJList() {
		list.setEnabled(true);
	}
	
	/**
	 * enables the JList of neighbors.
	 */
	public void enableJList_1() {
		list_1.setEnabled(true);
	}
	
	/**
	 * Function to disable and clear country name field.
	 */
	public void disableCountryfield() {
		txtCountryName.setText("");
		txtCountryName.setEnabled(false);
	}
	
	/**
	 * Function to enable the country text field.
	 */
	public void enableCountryfield() {
		txtCountryName.setEnabled(true);
	}
	
	/**
	 * Function to disable continent fields
	 */
	public void disableContinentField() {
		txtContinentNameHere.setText("");
		txtContinentControlValue.setText("");
		txtContinentNameHere.setEnabled(false);
		txtContinentControlValue.setEnabled(false);
	}
	
	/**
	 * Function to get selected country for adding neighbours.
	 * @return selected country for adding neighbours.
	 */
	public String getSelectedCountryForNeighbours() {
		return (comboBox_1.getSelectedItem().toString());
	}
	
	/**
	 * Function to get the selected country for deleting neighbors.
	 * @return selected country for deleting its neighbors.
	 */
	public String getSelectedCountryForNeighbourDeletion() {
		return (comboBox_4.getSelectedItem().toString());
	}
	
	/**
	 *Function to get selected country for deletion.
	 * @return selected country for deletion.
	 */
	public String getCountryForDeletion() {
		return (comboBox_2.getSelectedItem().toString());
	}
	
	/**
	 * Function to get the continent to be deleted.
	 * @return the contintent selected for deletion.
	 */
	public String getContinentToDelete() {
		return (comboBox_3.getSelectedItem().toString());
	}
	
	/**
	 * Function to get the selected continent name.
	 * @return the selected continent.
	 */
	public String getSelectedContinent() {
		return (comboBox.getSelectedItem().toString());
	}
	
	/**
	 * Function to clear the JList of neighbours.
	 */
	public void clearNeighboursJList() {
		model2.removeAllElements();
	}
	
	/**
	 * Clears the JList of neighbors.
	 */
	public void clearNeighboursJList_1() {
		model1.removeAllElements();
	}

	/**
	 * Function to update the neighbour JList view.
	 * @param neighbour receives the neighbour to be added.
	 */
	public void addPossibleNeighboursToJList(String neighbour) {
		model2.addElement(neighbour);
	}
	
	/**
	 * This function adds neighbors to JList.
	 * @param neighbour receives the neighbor to be added to the list.
	 */
	public void addPossibleNeighboursToJList_1(String neighbour) {
		model1.addElement(neighbour);
	}

	/**
	 * Function to get the continent name.
	 * @return the continent name.
	 */
	public String getContinentName() {
		continentName = txtContinentNameHere.getText();
		return continentName;
	}

	/**
	 * Function to display error message dialog box.
	 */
	public void enterValuesError() {
		JOptionPane.showMessageDialog(contentPane, "Enter values first", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Function to display error message dialog box.
	 */
	public void continentAlreadyExistError() {
		JOptionPane.showMessageDialog(contentPane, "Continent already exist", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Function to display error message dialog box.
	 */
	public void countryAlreadyExistError() {
		JOptionPane.showMessageDialog(contentPane, "Country already exist", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Function to display error message dialog box.
	 */
	public void nullContinentError() {
		JOptionPane.showMessageDialog(contentPane, "Add continent first", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Function to display error message dialog box.
	 */
	public void nullCountryError() {
		JOptionPane.showMessageDialog(contentPane, "Map validation error", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * displays dialog box with success message.
	 */
	public void successMessage() {
		JOptionPane.showMessageDialog(contentPane, "Successfully saved", "Message", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * displays dialog box with success message.
	 */
	public void successAddedNeighbours() {
		JOptionPane.showMessageDialog(contentPane, "Successfully added neighbours", "Message", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * displays dialog box with success message.
	 */
	public void successDeletedNeighbours() {
		JOptionPane.showMessageDialog(contentPane, "Successfully deleted neighbours", "Message", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Function to get the continent control value.
	 * @return the continent control value.
	 */
	public String getControlValue() {
		cv = (txtContinentControlValue.getText());
		return cv;
	}

	/**
	 * Function to clear and enable the continent associated text fields. 
	 */
	public void eraseContinentFields() {
		txtContinentNameHere.setText("");
		txtContinentControlValue.setText("");
		txtContinentNameHere.setEnabled(false);
		txtContinentControlValue.setEnabled(false);
	}

	/**
	 * Function to clear the continent comboBox contents.
	 */
	public void clearComboBoxContents() {
		comboBox.removeAllItems();
		comboBox_3.removeAllItems();
	}

	/**
	 * Function to clear the countries from comboBox.
	 */
	public void clearCountryComBoxContents() {
		comboBox_2.removeAllItems();
		comboBox_1.removeAllItems();
		comboBox_4.removeAllItems();
	}

	/**
	 * Function to update the continent list view.
	 * @param continent receives the newly added continent.
	 */
	public void setContinentsComboBox(String continent) {
		comboBox.addItem(continent);
		comboBox_3.addItem(continent);
	}

	/**
	 * Function to update the country list view.
	 * @param country receives the newly added country.
	 */
	public void setCountriesComboBox(String country) {
		comboBox_1.addItem(country);
		comboBox_2.addItem(country);
		comboBox_4.addItem(country);
	}
	
	/**
	 * Function to return the existing map editor frame.
	 * @return returns the existing map editor frame.
	 */
	public ExistingMapEditor getFrame() {
		return this;
	}
}

