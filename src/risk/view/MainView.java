package risk.view;

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
	
	private PhaseView phaseArea;
	
	private WorldDominationView dominationView;
	
	/**
	 * Initialize each view with the object of corresponding type.
	 * @param newPlayerInfo PlayerInfoView object.
	 * @param newMap MapView object.
	 * @param newDice DiceRollView object.
	 * @param newCards Cards object.
	 * @param newControls ControlsView object.
	 * @param newPhase PhaseView Object.
	 */
	private MainView(PlayerInfoView newPlayerInfo, MapView newMap, DiceRollView newDice, CardsView newCards, ControlsView newControls, PhaseView newPhase, WorldDominationView newDominationView) 
	{	
		playerData = newPlayerInfo;
        map = newMap;
        diceArea = newDice;
        cardsArea = newCards;
        controlsArea = newControls;
        phaseArea = newPhase;
        dominationView = newDominationView;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        contentPane.add(phaseArea);
        contentPane.add(dominationView);
        
        layout.putConstraint(SpringLayout.WEST, phaseArea, 5,  SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, phaseArea, 0,  SpringLayout.EAST, cardsArea);
        layout.putConstraint(SpringLayout.NORTH, phaseArea, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, phaseArea, -5,  SpringLayout.NORTH, playerData);
        
		/*playerData constraints.*/
        layout.putConstraint(SpringLayout.WEST, playerData, 5,  SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.EAST, playerData, 0,  SpringLayout.EAST, cardsArea);
        //layout.putConstraint(SpringLayout.NORTH, playerData, -5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, playerData, -5,  SpringLayout.NORTH, cardsArea);
        
        /*map constraints.*/
        layout.putConstraint(SpringLayout.WEST, map, 5,  SpringLayout.EAST, playerData);
        layout.putConstraint(SpringLayout.NORTH, map, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, map, 0, SpringLayout.SOUTH, playerData);
        layout.putConstraint(SpringLayout.EAST, map, -5, SpringLayout.WEST, diceArea);
        
        /*diceArea constraints.*/
        layout.putConstraint(SpringLayout.EAST, diceArea, -5,  SpringLayout.EAST, contentPane);
        layout.putConstraint(SpringLayout.NORTH, diceArea, 5, SpringLayout.NORTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, diceArea, -100, SpringLayout.NORTH, controlsArea);
        
        layout.putConstraint(SpringLayout.NORTH, dominationView, 5, SpringLayout.SOUTH, diceArea);
        layout.putConstraint(SpringLayout.SOUTH, dominationView, -5, SpringLayout.NORTH, controlsArea);
        layout.putConstraint(SpringLayout.WEST, dominationView, 5, SpringLayout.EAST, map);
        layout.putConstraint(SpringLayout.EAST, dominationView, -5, SpringLayout.EAST, contentPane);
        
        /*cardsArea constraints.*/
        layout.putConstraint(SpringLayout.WEST, cardsArea, 5,  SpringLayout.WEST, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, cardsArea, -5, SpringLayout.SOUTH, contentPane);
        
        /*controlsArea constraints.*/
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
	 * @param newPhase 
	 */
	public static void createInstance(PlayerInfoView newPlayerInfo, MapView newMap, DiceRollView newDice, CardsView newCards, ControlsView newControls, PhaseView newPhase, WorldDominationView newDominationView)
	{
		if(mainView == null){
			mainView = new MainView(newPlayerInfo, newMap, newDice, newCards, newControls, newPhase, newDominationView);
		}
	}
}
