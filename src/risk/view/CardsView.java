package risk.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import risk.model.Card;
import risk.model.GameDriver;
import risk.model.Player;

/**
 * Creates cards view on main window.
 */
public class CardsView extends JPanel implements Observer {
	
	/**
	 * Serial Version id for JFrame.
	 * {@inheritDoc}
	 */
	private static final long serialVersionUID = 9127819244400811786L;

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
	
	public void showCards(Player player){
		this.removeAll();
		for (risk.model.Card card : player.getCards()){
			JLabel cardName = new JLabel(card.getName()); 
			this.add(cardName);
		}
		
		exchangeCards = new JButton("Exchange Cards for Armies");
		exchangeCards.addActionListener(new ActionListener (){
			@Override
            public void actionPerformed(ActionEvent e) {
				exchangeCards(player);
			}
		});
		this.add(exchangeCards);
		
	}
	
	
	public void exchangeCards(Player player){
		
	}

	@Override
	public void update(Observable o, Object arg) {
		Player player = GameDriver.getInstance().getCurrentPlayer();
		if (arg.equals("Reinforcement")){
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
