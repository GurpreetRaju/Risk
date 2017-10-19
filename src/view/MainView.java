package view;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

/**
 * Creates the main game window.
 */
public class MainView extends JFrame{	
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * MainView object.
	 */
	private static MainView mainView;
	/**
	 * PlayerInfoView class object.
	 */
	private PlayerInfoView playerData;
	/**
	 * MapView class object.
	 */
	private MapView map;
	/**
	 * DiceRollView class object.
	 */
	private DiceRollView diceArea;
	/**
	 * CardsView class object.
	 */
	private CardsView cardsArea;
	/**
	 * ControlsView class object.
	 */
	private ControlsView controlsArea;
	
	/**
	 * Initialize each view with the object of corresponding type.
	 */
	private MainView(PlayerInfoView newPlayerInfo, MapView newMap, DiceRollView newDice, CardsView newCards, ControlsView newControls) 
	{	
		playerData = newPlayerInfo;
        map = newMap;
        diceArea = newDice;
        cardsArea = newCards;
        controlsArea = newControls;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        this.setVisible(true);
	}

	/**
	 * Makes the container for the main window.
	 */
	private void init(){
			
		Container contentPane = this.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        
        diceArea.setPreferredSize(new Dimension(400,playerData.getHeight()));
        
        contentPane.add(playerData);
        contentPane.add(map);
        contentPane.add(diceArea);
        contentPane.add(cardsArea);
        contentPane.add(controlsArea);
        
		//playerData constraints
        layout.putConstraint(SpringLayout.WEST, playerData, 5,  SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, playerData, 0,  SpringLayout.EAST, cardsArea);
        layout.putConstraint(SpringLayout.NORTH, playerData, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, playerData, -5,  SpringLayout.NORTH, cardsArea);
        //map constraints
        layout.putConstraint(SpringLayout.WEST, map, 5,  SpringLayout.EAST, playerData);
        layout.putConstraint(SpringLayout.NORTH, map, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, map, 0, SpringLayout.SOUTH, playerData);
        layout.putConstraint(SpringLayout.EAST, map, -5, SpringLayout.WEST, diceArea);
        //diceArea constraints
        layout.putConstraint(SpringLayout.EAST, diceArea, -5,  SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, diceArea, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, diceArea, 0, SpringLayout.SOUTH, playerData);
        //cardsArea constraints
        layout.putConstraint(SpringLayout.WEST, cardsArea, 5,  SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, cardsArea, -5, SpringLayout.SOUTH, contentPane);
        //controlsArea constraints
        layout.putConstraint(SpringLayout.WEST, controlsArea, 5,  SpringLayout.EAST, cardsArea);
        layout.putConstraint(SpringLayout.EAST, controlsArea, -5,  SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, controlsArea, 0, SpringLayout.NORTH, cardsArea);
        layout.putConstraint(SpringLayout.SOUTH, controlsArea, 0, SpringLayout.SOUTH, cardsArea);
        this.pack();
	}

	/**
	 * Gives the instance of the singleton MainView class.
	 * @return MainView object.
	 */
	public static MainView getInstance() {
		return mainView;
    }
	
	/**
	 * Creates the instance of the MainView class.
	 * @param newPlayerInfo PlayerInfoView object
	 * @param newMap MapView object
	 * @param newDice DiceRollView object
	 * @param newCards CardsView object
	 * @param newControls ControlsView object
	 */
	public static void createInstance(PlayerInfoView newPlayerInfo, MapView newMap, DiceRollView newDice, CardsView newCards, ControlsView newControls)
	{
		if(mainView == null){
			mainView = new MainView(newPlayerInfo, newMap, newDice, newCards, newControls);
		}
	}
	
}
