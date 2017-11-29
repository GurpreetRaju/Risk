package risk.view.mapeditor;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import risk.model.map.MapNode;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Font;

/**
 *Existing Map class opens the JFrame view for
 *choosing map file
 *@author jyotsna
 *@author Harinder
 */
public class ExistingMap extends JFrame {

	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 257217734140252917L;

	/**
	 * Creates panel for existing map file display.
	 */
	private JPanel contentPane;

	/**
	 * ArrayList of MapNode type to receive all the information of existing map file selected by player.
	 */
	ArrayList<MapNode> existingMap = new ArrayList<MapNode>();
	
	/**
	 * JButton to edit the existing map file.
	 */
	private JButton btnEdit;

	/**
	 * Existing Map constructor calls initialize method of the class.
	 * @param map arrayList of map nodes.
	 */
	public ExistingMap(ArrayList<MapNode> map){
		existingMap = map;
		initialize();
	}

	public ExistingMap() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		this.setLocationRelativeTo(null);
		this.setTitle("Existing Map");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(140, 140, 500, 340);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 

		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		String[] column1 = {"Continents", "Control Value"};
		DefaultTableModel model1 = new DefaultTableModel(column1, 0);

		for (int i = 0; i < existingMap.size(); i++) {
			String[] contName = {existingMap.get(i).getContinentName(), Integer.toString(existingMap.get(i).getControlValue())};
			model1.addRow(contName);
		}

		JTable table1 = new JTable(model1);
		table1.setToolTipText("Map file displayed here");
		GridBagConstraints gbc_table1 = new GridBagConstraints();
		gbc_table1.gridwidth = 2;
		gbc_table1.gridheight = 2;
		gbc_table1.insets = new Insets(0, 0, 5, 0);
		gbc_table1.fill = GridBagConstraints.BOTH;
		gbc_table1.gridx = 0;
		gbc_table1.gridy = 0;
		contentPane.add(getContentPane().add(new JScrollPane(table1)), gbc_table1);

		String[] column2 = {"Countries"};
		DefaultTableModel model2 = new DefaultTableModel(column2, 0);
		JTable table2 = new JTable(model2);
		table2.setToolTipText("Map file displayed here");
		GridBagConstraints gbc_table2 = new GridBagConstraints();
		gbc_table2.gridwidth = 2;
		gbc_table2.gridheight = 2;
		gbc_table2.insets = new Insets(0, 0, 5, 0);
		gbc_table2.fill = GridBagConstraints.BOTH;
		gbc_table2.gridx = 2;
		gbc_table2.gridy = 0;
		contentPane.add(getContentPane().add(new JScrollPane(table2)), gbc_table2);

		String[] column3 = {"N Countries"};
		DefaultTableModel model3 = new DefaultTableModel(column3, 0);
		JTable table3 = new JTable(model3);
		table3.setToolTipText("Map file displayed here");
		GridBagConstraints gbc_table3 = new GridBagConstraints();
		gbc_table3.gridwidth = 2;
		gbc_table3.gridheight = 2;
		gbc_table3.insets = new Insets(0, 0, 5, 0);
		gbc_table3.fill = GridBagConstraints.BOTH;
		gbc_table3.gridx = 4;
		gbc_table3.gridy = 0;
		contentPane.add(getContentPane().add(new JScrollPane(table3)), gbc_table3);

		table1.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				model2.setRowCount(0);
				model3.setRowCount(0);
				String selectedCell = table1.getValueAt(table1.getSelectedRow(), table1.getSelectedColumn()).toString();
				for (int i = 0; i < existingMap.size(); i++) {
					if (selectedCell.compareTo(existingMap.get(i).getContinentName())==0) {
						for (int j = 0; j < existingMap.get(i).getCountries().length; j++) {
							String[] countryName = {existingMap.get(i).getCountries()[j].getCountryName()};
							model2.addRow(countryName);
						}
					}
				}
			}
		});

		table2.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				model3.setRowCount(0);
				String selectedCell1 = table1.getValueAt(table1.getSelectedRow(), table1.getSelectedColumn()).toString();
				String selectedCell2 = table2.getValueAt(table2.getSelectedRow(), table2.getSelectedColumn()).toString();
				for (int i = 0; i < existingMap.size(); i++) {
					if (selectedCell1.compareTo(existingMap.get(i).getContinentName())==0) {
						for (int j = 0; j < existingMap.get(i).getCountries().length; j++) {
							if (selectedCell2.compareTo(existingMap.get(i).getCountries()[j].getCountryName())==0) {
								for (int k = 0; k < existingMap.get(i).getCountries()[j].getNeighbourCountries().length; k++) {
									String[] countryInfo = {existingMap.get(i).getCountries()[j].getNeighbourCountries()[k].getCountryName()};
									model3.addRow(countryInfo);
								}
								break;
							}
						}
						break;
					}
				}
			}
		});

		btnEdit = new JButton("Edit and Save Map");
		btnEdit.setBackground(new Color(152, 251, 152));
		btnEdit.setFont(new Font("Script MT Bold", Font.BOLD, 18));
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEdit.gridwidth = 3;
		gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdit.gridx = 13;
		gbc_btnEdit.gridy = 2;
		contentPane.add(btnEdit, gbc_btnEdit);
	}
	
	/**
	 * Function to display error message dialog box.
	 */
	public void cannotLoadMapError() {
		JOptionPane.showMessageDialog(contentPane, "Map file validation error", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Function to display error message dialog box.
	 */
	public void showUnconnectedContinentError() {
		JOptionPane.showMessageDialog(contentPane, "Cannot load Map file with unconnected continent", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * displays dialog box with success message.
	 */
	public void successfullLoad() {
		JOptionPane.showMessageDialog(contentPane, "Map file validated => Click ok to load the map data", "Message", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * @see risk.controller.mapeditor.mapEditorController
	 * @param newAction actionListener for btnEdit
	 */
	public void addActionsToBtnEdit(ActionListener newAction) {
		btnEdit.addActionListener(newAction);
	}

	/**
	 * getting all the information from existing map
	 * @return existing map info
	 */
	public ArrayList<MapNode> getExistingMapInfo() {
		return existingMap;
	}
}
