package view.mapeditor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.CountryNode;
import model.MapModel;
import model.MapNode;
import model.MapWriter;

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
 *  @author Jyotsna
 */
public class NewMap extends JFrame {

	private JPanel contentPane;
	private JTextField txtContinentNameHere;
	private JTextField txtContinentControlValue;
	private JTextField txtCountryName;
	private JComboBox comboBox;
	JComboBox comboBox_3;
	MapNode mapNode;
	JButton btnDone;
	JButton btnAddNeighbours;
	ArrayList<MapNode> continents = new ArrayList<MapNode>();
	MapWriter mapWriter = new MapWriter();
	MapModel mapModel = new MapModel();
	private String continentName;
	private String cv;
	JList list;
	JComboBox comboBox_1;
	DefaultListModel<String> model2;
	JButton btnSelectedNeighbours;

	/**
	 * NewMap constructor calls initialize method of the class
	 */
	public NewMap() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
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
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblCreateYourOwn = new JLabel("Create your own Game Map!");
		lblCreateYourOwn.setFont(new Font("Script MT Bold", Font.PLAIN, 48));
		GridBagConstraints gbc_lblCreateYourOwn = new GridBagConstraints();
		gbc_lblCreateYourOwn.insets = new Insets(0, 0, 5, 0);
		gbc_lblCreateYourOwn.gridwidth = 7;
		gbc_lblCreateYourOwn.gridx = 0;
		gbc_lblCreateYourOwn.gridy = 0;
		contentPane.add(lblCreateYourOwn, gbc_lblCreateYourOwn);

		JButton btnAddContinent = new JButton("New Continent");
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

		JButton btnAddCountry = new JButton("New Country");
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

		JButton btnDeleteCountry = new JButton("Delete Country");
		btnDeleteCountry.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		btnDeleteCountry.setForeground(Color.BLACK);
		btnDeleteCountry.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_btnDeleteCountry = new GridBagConstraints();
		gbc_btnDeleteCountry.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeleteCountry.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteCountry.gridx = 1;
		gbc_btnDeleteCountry.gridy = 8;
		contentPane.add(btnDeleteCountry, gbc_btnDeleteCountry);

		JComboBox comboBox_2 = new JComboBox();			/*list of countries.*/
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		ArrayList<String> choice = new ArrayList<>();
		gbc_comboBox_2.gridwidth = 3;
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 2;
		gbc_comboBox_2.gridy = 8;
		contentPane.add(comboBox_2, gbc_comboBox_2);

		JButton btnAdd = new JButton("Add Country");
		btnAdd.setBackground(new Color(240, 255, 255));
		btnAdd.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.anchor = GridBagConstraints.EAST;
		gbc_btnAdd.gridx = 6;
		gbc_btnAdd.gridy = 8;
		contentPane.add(btnAdd, gbc_btnAdd);

		JButton btnDeleteContinent = new JButton("Delete Continent");
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
		comboBox_1.setEnabled(false);
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
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.EAST;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 17;
		contentPane.add(btnSelectedNeighbours, gbc_button);

		JButton btnSaveMap = new JButton("Save Map");
		btnSaveMap.setBackground(new Color(152, 251, 152));
		btnSaveMap.setFont(new Font("Lucida Calligraphy", Font.BOLD, 16));
		GridBagConstraints gbc_btnSaveMap = new GridBagConstraints();
		gbc_btnSaveMap.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSaveMap.gridx = 6;
		gbc_btnSaveMap.gridy = 29;
		contentPane.add(btnSaveMap, gbc_btnSaveMap);

		btnAddContinent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == btnAddContinent){
					txtContinentNameHere.setText("");
					txtContinentControlValue.setText("");
					txtContinentNameHere.setEnabled(true);
					txtContinentControlValue.setEnabled(true);
				}
			}
		});

		btnAddCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnAddCountry) {
					txtCountryName.setEnabled(true);
					txtCountryName.setText("");
				}
			}
		});

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnAdd) {
					Boolean continentExist = false;
					if(comboBox.getItemCount()!=0) {
						continentExist = true;
					}
					if(continentExist) {
						String cn1 = txtCountryName.getText();
						if(cn1.compareTo("")==0) {
							JOptionPane.showMessageDialog(contentPane, "Enter values first", "Error", JOptionPane.ERROR_MESSAGE);
						}else {
							String selectedContinent = comboBox.getSelectedItem().toString();
							Boolean countryExist = false;
							for (MapNode node: continents) {
								for (CountryNode country : node.getCountries()) {
									if(country.getCountryName().compareTo(cn1)==0) {
										countryExist=true;
									}
								}
							}
							if(!countryExist) {
								//System.out.println("hello");
								ArrayList<CountryNode> neighbours= new ArrayList<CountryNode>();
								for (Object ncountry : list.getSelectedValuesList()) {
									CountryNode cn =  new CountryNode(ncountry.toString(), null, null);
									neighbours.add(cn);
								}
								model2.removeAllElements();
								comboBox_2.removeAllItems();
								comboBox_1.removeAllItems();
								for (MapNode node: continents) {
									if(selectedContinent.compareTo(node.getContinentName())==0) {
										int a[]= {250,250};
										CountryNode newCountry = new CountryNode(cn1,  neighbours , a);
										node.addCountry(newCountry);
									}
									for (CountryNode temp : node.getCountries()) {
										model2.addElement(temp.getCountryName());
										comboBox_2.addItem(temp.getCountryName());
										comboBox_1.addItem(temp.getCountryName());
									}
								}
							}else {
								JOptionPane.showMessageDialog(contentPane, "Country already exist", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
					}else {
						JOptionPane.showMessageDialog(contentPane, "Add Continent first", "Error", JOptionPane.ERROR_MESSAGE);
					}
					txtCountryName.setText("");
					txtCountryName.setEnabled(false);
				}
			}
		});

		comboBox_1.setEnabled(true);

		btnSelectedNeighbours.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnSelectedNeighbours) {
					
				}
			}
		});

		btnDeleteCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnDeleteCountry) {
					String selectedcountry = comboBox_2.getSelectedItem().toString();
					comboBox_2.removeAllItems();
					model2.removeAllElements();
					comboBox_1.removeAllItems();
					for (MapNode node: continents) {
						for (CountryNode temp : node.getCountries()) {
							if(temp.getCountryName().compareTo(selectedcountry)==0) {
								node.removeCountry(temp);
							}
						}
					}
					for (MapNode node: continents) {
						for (CountryNode temp : node.getCountries()) {
							model2.addElement(temp.getCountryName());
							comboBox_2.addItem(temp.getCountryName());
							comboBox_1.addItem(temp.getCountryName());
						}
					}
				}
			}
		});

		btnDeleteContinent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnDeleteContinent) {
					for (MapNode i :continents) {
						if(i.getContinentName().compareTo(comboBox_3.getSelectedItem().toString())==0) {
							continents.remove(i);
							break;
						}
					}
					comboBox.removeAllItems();
					comboBox_3.removeAllItems();
					for(MapNode i: continents) {
						comboBox.addItem(i.getContinentName());
						comboBox_3.addItem(i.getContinentName());
					}
				}
			}
		});

		btnSaveMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnSaveMap) {
					Boolean saveMap = true;
					for (MapNode i :continents) {	
						if(i.getCountries().length == 0) {
							saveMap = false;
						}
					}
					if(saveMap) {
						mapWriter.writeMap(continents);
					}else {
						JOptionPane.showMessageDialog(contentPane, "Enter atleast one country", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

	}
	public void addActionsToBtnDone(ActionListener newAction) {
	    btnDone.addActionListener(newAction);
	}
	
	public void addActionsToBtnAddNeighbours(ActionListener newAction) {
		btnAddNeighbours.addActionListener(newAction);
	}
	
	public void addActionsToBtnSelectedNeighbours(ActionListener newAction) {
		btnSelectedNeighbours.addActionListener(newAction);
	}
	
	public List getNeighboursList() {
		return (list.getSelectedValuesList());
	}
	
	public void enableJList() {
		list.setEnabled(true);
	}
	
	public String getSelectedCountryForNeighbours() {
		return (comboBox_1.getSelectedItem().toString());
	}
	
	public void clearNeighboursJList() {
		model2.removeAllElements();
	}
	
	public void addPossibleNeighboursToJList(String neighbour) {
		model2.addElement(neighbour);
	}
	
	public String getContinentName() {
		continentName = txtContinentNameHere.getText();
		return continentName;
	}
	
	public void enterValuesError() {
		JOptionPane.showMessageDialog(contentPane, "Enter values first", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void continentAlreadyExistError() {
		JOptionPane.showMessageDialog(contentPane, "Continent already exist", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public String getControlValue() {
		cv = (txtContinentControlValue.getText());
		return cv;
	}
	
	public void eraseContinentFields() {
		txtContinentNameHere.setText("");
		txtContinentControlValue.setText("");
		txtContinentNameHere.setEnabled(false);
		txtContinentControlValue.setEnabled(false);
	}
	
	public void clearComboBoxContents() {
		comboBox.removeAllItems();
		comboBox_3.removeAllItems();
	}
	
	public void setContinentsComboBox(String continent) {
		comboBox.addItem(continent);
		comboBox_3.addItem(continent);
	}
}

