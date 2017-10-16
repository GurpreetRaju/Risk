package view.mapeditor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.MapNode;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;

/**
 *Existing Map class opens the JFrame view for
 *choosing map file
 */
public class ExistingMap extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtContinentName;
	private JTextField txtControlValue;
	private JTextField textField;
	private JTextField txtContinentName_1;
	private JTextField txtControlValue_1;
	private JComboBox comboBox;
	private JLabel lblName_1;
	private JTextField textField_1;
	private JTextField txtCountryName;
	private JLabel lblNeighbours;
	private JComboBox comboBox_1;
	private JButton btnAddCountry;
	private JLabel lblPickContinent;
	private JButton btnDeleteContinent;
	private JComboBox comboBox_2;
	private JButton btnDeleteCountry;
	private JComboBox comboBox_3;
	ArrayList<MapNode> existingMap = new ArrayList<MapNode>();

	/**
	 * Existing Map constructor calls initialize method of the class
	 */

	public ExistingMap(ArrayList<MapNode> map)
	{
		existingMap = map;
		initialize();
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

		JButton btnEdit = new JButton("Edit Map");
		btnEdit.setBackground(new Color(152, 251, 152));
		btnEdit.setFont(new Font("Script MT Bold", Font.BOLD, 18));
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEdit.gridwidth = 3;
		gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdit.gridx = 13;
		gbc_btnEdit.gridy = 2;
		contentPane.add(btnEdit, gbc_btnEdit);
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnEdit) {
					setVisible(false);
					ExistingMapEditor existingMapEditor = new ExistingMapEditor(existingMap);
					existingMapEditor.setVisible(true);
				}
			}
			});
	}

}
