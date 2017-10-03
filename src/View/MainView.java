package view;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SpringLayout;

public class MainView extends JFrame{	
	
	private static final long serialVersionUID = 1L;
	private static MainView mainView;
	
	private MainView(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        init();
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        //this.setUndecorated(true);
        SetUpDialog setupBox = new SetUpDialog();
        setupBox.getPlayerInfo();
        
        this.setVisible(true);
	}
	
	private void init(){
			
		Container contentPane = this.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        
        PlayerInfoView playerData = new PlayerInfoView();
        MapView map = new MapView();
        DiceRollView diceArea = new DiceRollView();
        CardsView cardsArea = new CardsView();
        ControlsView controlsArea = new ControlsView();
        
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

	public static MainView getInstance() {
		if(mainView == null){
            mainView = new MainView();
        }
        return mainView;
    }
}
