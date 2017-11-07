package risk.view.mapeditor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import risk.model.map.CountryNode;
import risk.model.map.MapModel;
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
import java.awt.event.ActionEvent;

/**
 * New map class opens the JFrame view for  
 *  add/delete country and continent
 */
public class NewMap extends JFrame {

	/**
	 * Stores the JPanel of the frame of this class.
	 */
	private JPanel contentPane;
	
	/**
	 * TextField that receives continent name.
	 */
	private JTextField txtContinentNameHere;
	
	/**
	 * TextField that receives control value.
	 */
	private JTextField txtContinentControlValue;
	
	/**
	 * TextField that receives country name.
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
	 * Stores the object of MapNode.
	 */
	private MapNode mapNode;
	
	/**
	 * Button to add continent.
	 */
	private JButton btnDone;
	
	/**
	 * Button to add neighbours.
	 */
	private JButton btnAddNeighbours;
	
	/**
	 * Creates the object of mapWrited class.
	 */
	private MapWriter mapWriter = new MapWriter();
	
	/**
	 * String to hold the continent name.
	 */
	private String continentName;
	
	/**
	 * String variable to hold the control value.
	 */
	private String cv;
	
	/**
	 * Stores list of neighbours.
	 */
	private JList list;
	
	/**
	 * ComboBox for storing list of countries.
	 */
	private JComboBox comboBox_1;
	
	/**
	 * Defines list model for JList of adding neighbours.
	 */
	private DefaultListModel<String> model2;
	
	/**
	 * Defines list model for JList of deleting models.
	 */
	private DefaultListModel<String> model1;
	
	/**
	 * Button to get country for adding neighbours.
	 */
	private JButton btnSelectedNeighbours;
	
	/**
	 * Button to delete a continent.
	 */
	private JButton btnDeleteContinent;
	
	/**
	 * Button to save a map.
	 */
	private JButton btnSaveMap;
	
	/**
	 * Button to delete a country.
	 */
	private JButton btnDeleteCountry;
	
	/**
	 * ComboBox to hold list of all countries.
	 */
	private JComboBox comboBox_2;
	
	/**
	 * Button to add new country.
	 */
	private JButton btnAdd;
	
	/**
	 * Button to add new country.
	 */
	private JButton btnAddCountry;
	
	/**
	 * Button to add new continent.
	 */
	private JButton btnAddContinent;
	
	/**
	 * ComboBox for displaying list of countries.
	 */
	private JComboBox comboBox_4;
	
	/**
	 * Label for JList of neighbors.
	 */
	private JLabel lblNeighboursOfSelected;
	
	/**
	 * JList for displaying neighbors of selected country.
	 */
	private JList list_1;
	
	/**
	 * JButton to display possible neighbors of a country for deletion. 
	 */
	private JButton btnDeleteSelectedNeighbours;
	
	/**
	 * JButton to delete selected neighbors for the selected country.
	 */
	private JButton btnDeleteNeighbours;

	/**
	 * NewMap constructor calls initialize method of the class
	 */
	public NewMap() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	public void initialize() {
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(140, 140, 500, 340);
		this.setTitle("New Map");
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

		JLabel lblCreateYourOwn = new JLabel("Create your own Game Map!");
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

		comboBox = new JComboBox();			/*continent list.*/
		comboBox.setToolTipText("Choose existing continent to add new country");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 5;
		contentPane.add(comboBox, gbc_comboBox);

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

		btnAdd = new JButton("Add Country");			/*adding countries*/
		btnAdd.setBackground(new Color(240, 255, 255));
		btnAdd.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.anchor = GridBagConstraints.EAST;
		gbc_btnAdd.gridx = 6;
		gbc_btnAdd.gridy = 8;
		contentPane.add(btnAdd, gbc_btnAdd);

		btnDeleteContinent = new JButton("Delete Continent");			/*deleting continent*/
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
		gbc_btnDeleteNeighbours.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeleteNeighbours.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteNeighbours.gridx = 1;
		gbc_btnDeleteNeighbours.gridy = 20;
		
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
		
		btnDeleteSelectedNeighbours = new JButton("Delete selected neighbours");
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
	 * @see mapEditorController
	 * @param newAction actionListener for add country button
	 */
	public void addActionsToBtnAddCountry(ActionListener newAction) {
		btnAddCountry.addActionListener(newAction);
	}

	/**
	 * @see mapEditorController
	 * @param newAction actionlistener for add continent button.
	 */
	public void addActionsToBtnAddContinent(ActionListener newAction) {
		btnAddContinent.addActionListener(newAction);
	}

	/**
	 * @see mapEditorController
	 * @param newAction actionListener for btnDone
	 */
	public void addActionsToBtnDone(ActionListener newAction) {
		btnDone.addActionListener(newAction);
	}

	/**
	 * @see mapEditorController
	 * @param newAction actionListener for btnAdd
	 */
	public void addActionsToBtnAdd(ActionListener newAction) {
		btnAdd.addActionListener(newAction);
	}

	/**
	 * @see mapEditorController
	 * @param newAction actionListener for btnAddNeighbours
	 */
	public void addActionsToBtnAddNeighbours(ActionListener newAction) {
		btnAddNeighbours.addActionListener(newAction);
	}

	/**
	 * @see mapEditorController
	 * @param newAction actionListener for btnDeleteNeighbours
	 */
	public void addActionsToBtnDeleteNeighbours(ActionListener newAction) {
		btnDeleteNeighbours.addActionListener(newAction);
	}

	
	/**
	 * @see mapEditorController
	 * @param newAction actionListener for btnSelectedNeighbours
	 */
	public void addActionsToBtnSelectedNeighbours(ActionListener newAction) {
		btnSelectedNeighbours.addActionListener(newAction);
	}
	
	/**
	 * @see mapEditorController
	 * @param newAction actionListener for btnDeleteSelectedNeighbours
	 */
	public void addActionsToBtnDeleteSelectedNeighbours(ActionListener newAction) {
		btnDeleteSelectedNeighbours.addActionListener(newAction);
	}

	/**
	 * @see mapEditorController
	 * @param newAction actionListener for btnDeleteContinent
	 */
	public void addActionsToBtnDeleteContinent(ActionListener newAction) {
		btnDeleteContinent.addActionListener(newAction);
	}

	/**
	 * @see mapEditorController
	 * @param newAction actionListener for btnSaveMap
	 */
	public void addActionsToBtnSave(ActionListener newAction) {
		btnSaveMap.addActionListener(newAction);
	}

	/**
	 * @see mapEditorController
	 * @param newAction actionListener for btnDeleteCountry
	 */
	public void addActionsToBtnDeleteCountry(ActionListener newAction) {
		btnDeleteCountry.addActionListener(newAction);
	}

	/**
	 * checking whether continent exist or nor
	 * @return boolean value whether country exist or not
	 */
	public boolean checkContinentExist() {
		Boolean continentExist = false;
		if(comboBox.getItemCount()!=0) {
			continentExist = true;
		}
		return continentExist;
	}

	/**
	 * getting the country name
	 * @return country name
	 */
	public String getCountryName() {
		return (txtCountryName.getText());
	}

	/**
	 * getting the neighbours countries from list
	 * @return list of neighbours countries selected
	 */
	public List getNeighboursList() {
		return (list.getSelectedValuesList());
	}
	
	/**
	 * gets list of selected neighbors for deletion.
	 * @return list of selected neighbors for deletion.
	 */
	public List getNeighboursList_1() {
		return (list_1.getSelectedValuesList());
	}

	/**
	 * enabling the list
	 */
	public void enableJList() {
		list.setEnabled(true);
	}
	
	/**
	 * enables JList of neighbors.
	 */
	public void enableJList_1() {
		list_1.setEnabled(true);
	}

	/**
	 * disabling the country field
	 */
	public void disableCountryfield() {
		txtCountryName.setText("");
		txtCountryName.setEnabled(false);
	}

	/**
	 * disabling the continent field
	 */
	public void disableContinentField() {
		txtContinentNameHere.setText("");
		txtContinentControlValue.setText("");
		txtContinentNameHere.setEnabled(false);
		txtContinentControlValue.setEnabled(false);
	}

	/**
	 * getting the selected countries
	 * @return list of countries selected through comboBox_2 that includes selecting the countries
	 */
	public String getSelectedCountryForNeighbours() {
		return (comboBox_1.getSelectedItem().toString());
	}
	
	/**
	 * Gets the country for deleting its neighbors.
	 * @return the country for neighbor deletion.
	 */
	public String getSelectedCountryForNeighbourDeletion() {
		return (comboBox_4.getSelectedItem().toString());
	}

	/**
	 * getting countries for deletion
	 * @return list of countries selected for deletion 
	 */
	public String getCountryForDeletion() {
		return (comboBox_2.getSelectedItem().toString());
	}

	/**
	 * getting continents for deletion
	 * @return list of continents to be deleted
	 */
	public String getContinentToDelete() {
		return (comboBox_3.getSelectedItem().toString());
	}

	/**
	 * getting selected continent
	 * @return list of continents selected
	 */
	public String getSelectedContinent() {
		return (comboBox.getSelectedItem().toString());
	}

	/**
	 * clearing the neighbors list
	 */
	public void clearNeighboursJList() {
		model2.removeAllElements();
	}
	
	/**
	 * clears the JList of neighbor countries.
	 */
	public void clearNeighboursJList_1() {
		model1.removeAllElements();
	}

	/**
	 * adding neighbor countries to the list
	 * @param neighbour include countries
	 */
	public void addPossibleNeighboursToJList(String neighbour) {
		model2.addElement(neighbour);
	}
	
	/**
	 * Fills the JList with possible neighbors of each country.
	 * @param neighbour receives neighbor country to be added.
	 */
	public void addPossibleNeighboursToJList_1(String neighbour) {
		model1.addElement(neighbour);
	}

	/**
	 * getting the continent name
	 * @return continent name
	 */
	public String getContinentName() {
		continentName = txtContinentNameHere.getText();
		return continentName;
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
	 * dialogue box for error messages
	 */
	public void enterValuesError() {
		JOptionPane.showMessageDialog(contentPane, "Enter values first", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * dialogue box for error messages
	 */
	public void noSelectedNeighboursError() {
		JOptionPane.showMessageDialog(contentPane, "Select neighbours first", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * dialogue box for error messages
	 */
	public void continentAlreadyExistError() {
		JOptionPane.showMessageDialog(contentPane, "Continent already exist", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * dialogue box for error messages
	 */
	public void countryAlreadyExistError() {
		JOptionPane.showMessageDialog(contentPane, "Country already exist", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * dialogue box for error messages
	 */
	public void nullContinentError() {
		JOptionPane.showMessageDialog(contentPane, "Add continent first", "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * dialogue box for error messages
	 */
	public void nullCountryError() {
		JOptionPane.showMessageDialog(contentPane, "Map validation error", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * shows dialog box for successful save operation.
	 */
	public void successfullySaved() {
		JOptionPane.showMessageDialog(contentPane, "Successfully saved", "Message", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * getting the control value of continent
	 * @return control value of continent
	 */
	public String getControlValue() {
		cv = (txtContinentControlValue.getText());
		return cv;
	}

	/**
	 * easing or enabling continent fields that are continent name and its control value
	 */
	public void eraseContinentFields() {
		txtContinentNameHere.setText("");
		txtContinentControlValue.setText("");
		txtContinentNameHere.setEnabled(false);
		txtContinentControlValue.setEnabled(false);
	}

	/**
	 * function to enable country field.
	 */
	public void enableCountryfield() {
		txtCountryName.setEnabled(true);
	}

	/**
	 * function to enable continent fields.
	 */
	public void enableContinentFields() {
		txtContinentNameHere.setEnabled(true);
		txtContinentControlValue.setEnabled(true);
	}
	/**
	 * clearing the lists from comboBox
	 */
	public void clearComboBoxContents() {
		comboBox.removeAllItems();
		comboBox_3.removeAllItems();
	}

	/**
	 * clearing list of countries
	 */
	public void clearCountryComBoxContents() {
		comboBox_2.removeAllItems();
		comboBox_1.removeAllItems();
		comboBox_4.removeAllItems();
	}

	/**
	 * setting the continents in the list
	 * @param continent name of continent
	 */
	public void setContinentsComboBox(String continent) {
		comboBox.addItem(continent);
		comboBox_3.addItem(continent);
	}

	/**
	 * adding country to the list
	 * @param country name of country
	 */
	public void setCountriesComboBox(String country) {
		comboBox_1.addItem(country);
		comboBox_2.addItem(country);
		comboBox_4.addItem(country);
	}
	
	/**
	 * gets the JFrame of newMap.
	 * @return the current frame.
	 */
	public NewMap getFrame() {
		return this;
	}
}

