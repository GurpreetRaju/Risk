package risk.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import risk.model.GameDriver;
import risk.model.player.Player;

/**
 * This class implements the Card Exchange View.
 * @author Amitt
 * @version 1.2
 */
public class CardsView extends JPanel implements Observer {
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 9127819244400811786L;

	/**
	 * JButton to initiate card exchange.
	 */
	private JButton exchangeCards;
	
	/**
	 * Creates cards view.
	 */
	public CardsView(){
		JLabel label = new JLabel("Cards Here.");
		this.setLayout(new FlowLayout());
		this.add(label);
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(400,150));
	}
	
	/**
	 * Shows a dialog to the player to exchange the cards to get additional armies
	 * @param player current player whose turn is going on
	 */
	public void showCards(Player player){
		this.removeAll();
		/*Cards exchange Dialog Box.*/
		String cards = "";
		for (risk.model.Card card : player.getCards()){ 
			cards += (card.getName()+",");
		}
		int cardExchange = JOptionPane.showConfirmDialog (null, cards,"Warning",JOptionPane.YES_OPTION);
		if(cardExchange == JOptionPane.YES_OPTION){
			exchangeCards(player);
		}
		
	}
	
	/**
	 * Removes cards from the player and assign additional armies
	 * @param player current player whose turn is going on
	 */
	public void exchangeCards(Player player){
		if (player.haveDistinctCards()){
			player.removeDistinctCards();
		}
		else if (player.haveThreeSameTypeCards()){
			player.removeSimilarThreeCards();
		}
	}
	
	/**
	 * Observer pattern function for Observers to update when there is a notification from the observable.
	 * It checks if the player has atleast 3 similar cards or 3 distinct cards or 5 cards.
	 * If user has 3 or more cards it asks player if he wants to exchange cards for armies.
	 * if user has 5 armies it forces player to exchange card for armies.
	 */
	@Override
	public void update(Observable o, Object arg) {
		Player player = GameDriver.getInstance().getCurrentPlayer();
		if (((String) arg).equals("Cards")){
			if( player.getCards().size()>2 && player.getCards().size() <5){
				if(player.haveDistinctCards() || player.haveThreeSameTypeCards()){					
					this.showCards(player);
				}
			}
			if (player.getCards().size()==5){
				this.exchangeCards(player);
			}
		}
	}
}
