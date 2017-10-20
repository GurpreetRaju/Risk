package view.mapeditor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.MatteBorder;

import controller.mapeditor.mapEditorController;

/**
 * MapFrame class opens the JFrame view for selecting 
 * New map and Existing map
 * @author Harinder
 * @author jyotsna
 */
public class MapFrame extends JFrame {

	/**
	 * Stores the JPanel of map frame.
	 */
	private JPanel contentPane;
	
	/**
	 * Stores the object of mapEditorController.
	 */
	mapEditorController obj = new mapEditorController();

	/**
	 * Stores the object of this class.
	 */
	static MapFrame frame = new MapFrame();
	
	/**
	 * Stores the existing map button information.
	 */
	JButton btnExistingMap;
	
	/**
	 * Stores the new map button information.
	 */
	JButton btnNewMap;
	
	/**
	 * Stores the selected Action of creating new map or editing existing.
	 */
	static String selectedAction ="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * MapFrame constructor calls initialize method of the class.
	 */
	public MapFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * includes New Map button and Existing Map button
	 * together with three text labels
	 */
	public void initialize() {
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(140, 140, 500, 340);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		JLabel lblThisIsYour = new JLabel("This is your Map Editor");
		lblThisIsYour.setFont(new Font("Script MT Bold", Font.PLAIN, 48));
		GridBagConstraints gbc_lblThisIsYour = new GridBagConstraints();
		gbc_lblThisIsYour.insets = new Insets(0, 0, 5, 0);
		gbc_lblThisIsYour.gridwidth = 10;
		gbc_lblThisIsYour.gridheight = 2;
		gbc_lblThisIsYour.gridx = 0;
		gbc_lblThisIsYour.gridy = 0;
		contentPane.add(lblThisIsYour, gbc_lblThisIsYour);

		JLabel lblYouCanChoose = new JLabel("You can choose to play with an existing map or create your own map.");
		lblYouCanChoose.setFont(new Font("Monotype Corsiva", Font.PLAIN, 29));
		GridBagConstraints gbc_lblYouCanChoose = new GridBagConstraints();
		gbc_lblYouCanChoose.gridheight = 3;
		gbc_lblYouCanChoose.gridwidth = 7;
		gbc_lblYouCanChoose.insets = new Insets(0, 0, 5, 5);
		gbc_lblYouCanChoose.gridx = 2;
		gbc_lblYouCanChoose.gridy = 2;
		contentPane.add(lblYouCanChoose, gbc_lblYouCanChoose);

		btnNewMap = new JButton("New Map");
		btnNewMap.setToolTipText("Click here...");
		btnNewMap.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		btnNewMap.setForeground(Color.BLACK);
		btnNewMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedAction = "new";
				frame.setVisible(true);
				obj.newMapActions();
			}
		});

		JLabel lblSelectOne = new JLabel("Select one...");
		lblSelectOne.setFont(new Font("Tahoma", Font.ITALIC, 18));
		GridBagConstraints gbc_lblSelectOne = new GridBagConstraints();
		gbc_lblSelectOne.fill = GridBagConstraints.VERTICAL;
		gbc_lblSelectOne.gridwidth = 3;
		gbc_lblSelectOne.insets = new Insets(0, 0, 5, 0);
		gbc_lblSelectOne.gridx = 5;
		gbc_lblSelectOne.gridy = 5;
		contentPane.add(lblSelectOne, gbc_lblSelectOne);
		btnNewMap.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_btnNewMap = new GridBagConstraints();
		gbc_btnNewMap.gridwidth = 3;
		gbc_btnNewMap.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewMap.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewMap.gridx = 5;
		gbc_btnNewMap.gridy = 6;
		contentPane.add(btnNewMap, gbc_btnNewMap);

		btnExistingMap = new JButton("Existing Map");
		btnExistingMap.setForeground(Color.BLACK);
		btnExistingMap.setToolTipText("Click here...");
		btnExistingMap.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 18));
		btnExistingMap.setBackground(new Color(240, 255, 255));
		GridBagConstraints gbc_btnExistingMap = new GridBagConstraints();
		gbc_btnExistingMap.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExistingMap.gridwidth = 3;
		gbc_btnExistingMap.insets = new Insets(0, 0, 0, 5);
		gbc_btnExistingMap.gridx = 5;
		gbc_btnExistingMap.gridy = 7;
		contentPane.add(btnExistingMap, gbc_btnExistingMap);
		btnExistingMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selectedAction = "existing";
				obj.mapFileChooserActions();
			}
		});
	}

	public static String selectedAction() {
		return selectedAction;
	}
}
