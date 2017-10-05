package View.mapeditor;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExistingMap frame = new ExistingMap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExistingMap() {
		this.setLocationRelativeTo(null);
		this.setTitle("Existing Map");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(140, 140, 500, 340);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		table = new JTable();
		table.setToolTipText("Map file displayed here");
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridwidth = 16;
		gbc_table.gridheight = 2;
		gbc_table.insets = new Insets(0, 0, 5, 0);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		contentPane.add(table, gbc_table);
		
		JButton btnEdit = new JButton("Edit");
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.insets = new Insets(0, 0, 5, 0);
		gbc_btnEdit.gridx = 15;
		gbc_btnEdit.gridy = 2;
		contentPane.add(btnEdit, gbc_btnEdit);
		
		JButton btnAddContinent = new JButton("Add Continent");
		btnAddContinent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnAddContinent = new GridBagConstraints();
		gbc_btnAddContinent.fill = GridBagConstraints.BOTH;
		gbc_btnAddContinent.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddContinent.gridx = 2;
		gbc_btnAddContinent.gridy = 3;
		contentPane.add(btnAddContinent, gbc_btnAddContinent);
		
		btnAddCountry = new JButton("Add Country");
		GridBagConstraints gbc_btnAddCountry = new GridBagConstraints();
		gbc_btnAddCountry.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddCountry.gridx = 9;
		gbc_btnAddCountry.gridy = 3;
		contentPane.add(btnAddCountry, gbc_btnAddCountry);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 4;
		contentPane.add(lblName, gbc_lblName);
		
		txtContinentName_1 = new JTextField();
		txtContinentName_1.setForeground(Color.GRAY);
		txtContinentName_1.setFont(new Font("Tahoma", Font.ITALIC, 13));
		txtContinentName_1.setText("Continent Name...");
		GridBagConstraints gbc_txtContinentName_1 = new GridBagConstraints();
		gbc_txtContinentName_1.gridwidth = 4;
		gbc_txtContinentName_1.insets = new Insets(0, 0, 5, 5);
		gbc_txtContinentName_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtContinentName_1.gridx = 2;
		gbc_txtContinentName_1.gridy = 4;
		contentPane.add(txtContinentName_1, gbc_txtContinentName_1);
		txtContinentName_1.setColumns(10);
		
		lblPickContinent = new JLabel("Pick Continent");
		GridBagConstraints gbc_lblPickContinent = new GridBagConstraints();
		gbc_lblPickContinent.anchor = GridBagConstraints.EAST;
		gbc_lblPickContinent.insets = new Insets(0, 0, 5, 5);
		gbc_lblPickContinent.gridx = 8;
		gbc_lblPickContinent.gridy = 4;
		contentPane.add(lblPickContinent, gbc_lblPickContinent);
		
		comboBox = new JComboBox();
		comboBox.setToolTipText("Choose an existing continent...");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 6;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 9;
		gbc_comboBox.gridy = 4;
		contentPane.add(comboBox, gbc_comboBox);
		
		JLabel lblValue = new JLabel("Value");
		GridBagConstraints gbc_lblValue = new GridBagConstraints();
		gbc_lblValue.anchor = GridBagConstraints.EAST;
		gbc_lblValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue.gridx = 1;
		gbc_lblValue.gridy = 5;
		contentPane.add(lblValue, gbc_lblValue);
		
		txtControlValue_1 = new JTextField();
		txtControlValue_1.setForeground(Color.GRAY);
		txtControlValue_1.setFont(new Font("Tahoma", Font.ITALIC, 13));
		txtControlValue_1.setText("Control Value...");
		GridBagConstraints gbc_txtControlValue_1 = new GridBagConstraints();
		gbc_txtControlValue_1.gridwidth = 4;
		gbc_txtControlValue_1.insets = new Insets(0, 0, 5, 5);
		gbc_txtControlValue_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtControlValue_1.gridx = 2;
		gbc_txtControlValue_1.gridy = 5;
		contentPane.add(txtControlValue_1, gbc_txtControlValue_1);
		txtControlValue_1.setColumns(10);
		
		lblName_1 = new JLabel("Name");
		GridBagConstraints gbc_lblName_1 = new GridBagConstraints();
		gbc_lblName_1.anchor = GridBagConstraints.EAST;
		gbc_lblName_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblName_1.gridx = 8;
		gbc_lblName_1.gridy = 5;
		contentPane.add(lblName_1, gbc_lblName_1);
		
		txtCountryName = new JTextField();
		txtCountryName.setForeground(Color.GRAY);
		txtCountryName.setFont(new Font("Tahoma", Font.ITALIC, 13));
		txtCountryName.setText("Country name...");
		GridBagConstraints gbc_txtCountryName = new GridBagConstraints();
		gbc_txtCountryName.gridwidth = 6;
		gbc_txtCountryName.insets = new Insets(0, 0, 5, 5);
		gbc_txtCountryName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCountryName.gridx = 9;
		gbc_txtCountryName.gridy = 5;
		contentPane.add(txtCountryName, gbc_txtCountryName);
		txtCountryName.setColumns(10);
		
		lblNeighbours = new JLabel("Neighbours");
		GridBagConstraints gbc_lblNeighbours = new GridBagConstraints();
		gbc_lblNeighbours.anchor = GridBagConstraints.EAST;
		gbc_lblNeighbours.insets = new Insets(0, 0, 0, 5);
		gbc_lblNeighbours.gridx = 8;
		gbc_lblNeighbours.gridy = 6;
		contentPane.add(lblNeighbours, gbc_lblNeighbours);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setToolTipText("Select neighbours");
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox_1.gridwidth = 6;
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 9;
		gbc_comboBox_1.gridy = 6;
		contentPane.add(comboBox_1, gbc_comboBox_1);
		
		
	}

}
