package view.mapeditor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.CountryNode;
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
import java.awt.event.ActionEvent;

/**
 * New map class opens the JFrame view for  
 *  add/delete country and continent
 */
public class NewMap extends JFrame {

	private JPanel contentPane;
	private JTextField txtContinentNameHere;
	private JTextField txtContinentControlValue;
	private JTextField txtCountryName;
	private JComboBox comboBox;
	MapNode mapNode;
	ArrayList<MapNode> continents = new ArrayList<MapNode>();
	MapWriter mapWriter = new MapWriter();

	/**
	 * NewMap constructor calls initialize method of the class
	 */
	public NewMap()
	{
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
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblCreateYourOwn = new JLabel("Create your own Game Map!");
		lblCreateYourOwn.setFont(new Font("Script MT Bold", Font.PLAIN, 48));
		GridBagConstraints gbc_lblCreateYourOwn = new GridBagConstraints();
		gbc_lblCreateYourOwn.insets = new Insets(0, 0, 5, 0);
		gbc_lblCreateYourOwn.gridwidth = 7;
		gbc_lblCreateYourOwn.gridx = 0;
		gbc_lblCreateYourOwn.gridy = 0;
		contentPane.add(lblCreateYourOwn, gbc_lblCreateYourOwn);

		JButton btnAddContinent = new JButton("Add Continent");
		btnAddContinent.setForeground(Color.BLACK);
		btnAddContinent.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		btnAddContinent.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_btnAddContinent = new GridBagConstraints();
		gbc_btnAddContinent.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddContinent.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddContinent.gridx = 1;
		gbc_btnAddContinent.gridy = 2;
		contentPane.add(btnAddContinent, gbc_btnAddContinent);

		JLabel lblName = new JLabel("Name");
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
		txtContinentNameHere.setText("Continent Name here...");
		contentPane.add(txtContinentNameHere, gbc_txtContinentNameHere);
		txtContinentNameHere.setColumns(10);

		txtContinentControlValue = new JTextField();
		txtContinentControlValue.setFont(new Font("Tahoma", Font.ITALIC, 13));
		txtContinentControlValue.setForeground(Color.BLACK);
		txtContinentControlValue.setText("Continent Control value..");
		txtContinentControlValue.setEnabled(false);
		GridBagConstraints gbc_txtContinentControlValue = new GridBagConstraints();
		gbc_txtContinentControlValue.insets = new Insets(0, 0, 5, 0);
		gbc_txtContinentControlValue.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtContinentControlValue.gridx = 6;
		gbc_txtContinentControlValue.gridy = 3;
		contentPane.add(txtContinentControlValue, gbc_txtContinentControlValue);
		txtContinentControlValue.setColumns(10);


		btnAddContinent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if (e.getSource() == btnAddContinent) {
					txtContinentNameHere.setText("");
					txtContinentControlValue.setText("");
					txtContinentNameHere.setEnabled(true);
					txtContinentControlValue.setEnabled(true);
				}
			}});


		JLabel lblValue = new JLabel("Value");
		GridBagConstraints gbc_lblValue = new GridBagConstraints();
		gbc_lblValue.anchor = GridBagConstraints.EAST;
		gbc_lblValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue.gridx = 5;
		gbc_lblValue.gridy = 3;
		contentPane.add(lblValue, gbc_lblValue);

		JButton btnDone = new JButton("Add");
		btnDone.setBackground(new Color(240, 255, 255));
		btnDone.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnDone.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnDone = new GridBagConstraints();
		gbc_btnDone.anchor = GridBagConstraints.EAST;
		gbc_btnDone.insets = new Insets(0, 0, 5, 0);
		gbc_btnDone.gridx = 6;
		gbc_btnDone.gridy = 4;
		contentPane.add(btnDone, gbc_btnDone);

		JButton btnAddCountry = new JButton("Add Country");
		btnAddCountry.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		btnAddCountry.setForeground(Color.BLACK);
		btnAddCountry.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_btnAddCountry = new GridBagConstraints();
		gbc_btnAddCountry.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAddCountry.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddCountry.gridx = 1;
		gbc_btnAddCountry.gridy = 5;
		contentPane.add(btnAddCountry, gbc_btnAddCountry);

		comboBox = new JComboBox();
		comboBox.setToolTipText("Choose existing continent to add new country");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 5;

		contentPane.add(comboBox, gbc_comboBox);

		JLabel lblName_1 = new JLabel("Name");
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

		JLabel lblNeighbours = new JLabel("Neighbours");
		GridBagConstraints gbc_lblNeighbours = new GridBagConstraints();
		gbc_lblNeighbours.anchor = GridBagConstraints.EAST;
		gbc_lblNeighbours.insets = new Insets(0, 0, 5, 5);
		gbc_lblNeighbours.gridx = 5;
		gbc_lblNeighbours.gridy = 7;
		contentPane.add(lblNeighbours, gbc_lblNeighbours);

		DefaultListModel<String> model1 = new DefaultListModel<String>();
		//		model1.addElement("ele1");
		JList items = new JList();
		//		items.setToolTipText("Choose neighbours...");
		items.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		items.setModel(model1);
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 6;
		gbc_comboBox_1.gridy = 7;
		contentPane.add(items, gbc_comboBox_1);

		JButton btnDeleteCountry = new JButton("Delete Country");
		btnDeleteCountry.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		btnDeleteCountry.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnDeleteCountry = new GridBagConstraints();
		gbc_btnDeleteCountry.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeleteCountry.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteCountry.gridx = 1;
		gbc_btnDeleteCountry.gridy = 8;
		contentPane.add(btnDeleteCountry, gbc_btnDeleteCountry);

		JComboBox comboBox_2 = new JComboBox();
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		ArrayList<String> choice = new ArrayList<>();
		gbc_comboBox_2.gridwidth = 3;
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 2;
		gbc_comboBox_2.gridy = 8;
		contentPane.add(comboBox_2, gbc_comboBox_2);
		//comboBox_2.addItem("hello");

		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(new Color(240, 255, 255));
		btnAdd.setFont(new Font("Bookman Old Style", Font.BOLD, 13));
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.anchor = GridBagConstraints.EAST;
		gbc_btnAdd.gridx = 6;
		gbc_btnAdd.gridy = 8;
		contentPane.add(btnAdd, gbc_btnAdd);

		JButton btnDeleteContinent = new JButton("Delete Continent");
		btnDeleteContinent.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		btnDeleteContinent.setForeground(Color.BLACK);
		GridBagConstraints gbc_btnDeleteContinent = new GridBagConstraints();
		gbc_btnDeleteContinent.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeleteContinent.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeleteContinent.gridx = 1;
		gbc_btnDeleteContinent.gridy = 11;
		contentPane.add(btnDeleteContinent, gbc_btnDeleteContinent);

		JComboBox comboBox_3 = new JComboBox();
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.gridwidth = 3;
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_3.gridx = 2;
		gbc_comboBox_3.gridy = 11;
		//		for(String temp: continent) {
		//			comboBox_3.addItem(temp);
		//		}

		contentPane.add(comboBox_3, gbc_comboBox_3);

		JButton btnSaveMap = new JButton("Save Map");
		btnSaveMap.setBackground(new Color(152, 251, 152));
		btnSaveMap.setFont(new Font("Lucida Calligraphy", Font.BOLD, 16));
		GridBagConstraints gbc_btnSaveMap = new GridBagConstraints();
		gbc_btnSaveMap.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSaveMap.gridx = 6;
		gbc_btnSaveMap.gridy = 29;
		contentPane.add(btnSaveMap, gbc_btnSaveMap);

		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnDone) {
					String cn = txtContinentNameHere.getText(); //perform your operation	     

					if(cn.compareTo("")==0 || txtContinentControlValue.getText().compareTo("")==0) {
						JOptionPane.showMessageDialog(contentPane, "Enter values first", "Error", JOptionPane.ERROR_MESSAGE);


					}
					//System.out.println("hi");
					else {
						int cv = Integer.parseInt(txtContinentControlValue.getText()); //perform your operation
						ArrayList<CountryNode> countryArr = new ArrayList<CountryNode>();
						Boolean continentExist = false;
						for (MapNode con: continents) {
							if(con.getContinentName().compareTo(cn)==0) {
								continentExist = true;
							}
						}
						if(!continentExist) {
							continents.add(new MapNode(cn, countryArr, cv));
							comboBox.removeAllItems();
							comboBox_3.removeAllItems();
							for(MapNode i: continents) {
								comboBox.addItem(i.getContinentName());
								comboBox_3.addItem(i.getContinentName());

							}
							txtContinentNameHere.setText("");
							txtContinentControlValue.setText("");
							txtContinentNameHere.setEnabled(false);
							txtContinentControlValue.setEnabled(false);
						}
						else {
							JOptionPane.showMessageDialog(contentPane, "Continent already exist", "Error", JOptionPane.ERROR_MESSAGE);
						}
						//			            for(MapNode node: continents)		    
						//			            mapNode = new MapNode(cn, null, cv);
						//System.out.println( continent_name.get(0));
						//			            System.out.println(control_value.get(0));


					}
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
							ArrayList<CountryNode> neighbours= new ArrayList<CountryNode>();

							for (Object ncountry : items.getSelectedValuesList()) {
								CountryNode cn =  new CountryNode(ncountry.toString(), null, null);
								neighbours.add(cn);
							}
							model1.removeAllElements();
							comboBox_2.removeAllItems();

							for (MapNode node: continents) {
								if(selectedContinent.compareTo(node.getContinentName())==0) {

									int a[]= {250,250};

									CountryNode newCountry = new CountryNode(cn1,  neighbours , a);
									node.addCountry(newCountry);
								}
								for (CountryNode temp : node.getCountries()) {
									model1.addElement(temp.getCountryName());
									comboBox_2.addItem(temp.getCountryName());
								}

							}	
						}else {
							JOptionPane.showMessageDialog(contentPane, "Country already exist", "Error", JOptionPane.ERROR_MESSAGE);
						}



					}
				}
			}
		});


		btnDeleteCountry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnDeleteCountry) {
					String selectedcountry = comboBox_2.getSelectedItem().toString();
					comboBox_2.removeAllItems();
					model1.removeAllElements();
					for (MapNode node: continents) {
						for (CountryNode temp : node.getCountries()) {
							if(temp.getCountryName().compareTo(selectedcountry)==0) {
								node.removeCountry(temp);
							}
						}
					}
					for (MapNode node: continents) {
						for (CountryNode temp : node.getCountries()) {
							model1.addElement(temp.getCountryName());
							comboBox_2.addItem(temp.getCountryName());
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
}
