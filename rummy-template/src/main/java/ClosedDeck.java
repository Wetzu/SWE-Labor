import rummy.CardColor;
import rummy.CardFace;
import java.util.ArrayList;

public class ClosedDeck extends Deck {

	/**
	 * @clientCardinality 1
	 * @supplierCardinality 1
	 */

	private Game lnkGame;

	public ClosedDeck() {
		super();
		ArrayList<Card> orderedCards = new ArrayList<Card>();
		for (int i = 0; i < 2; i++) {
			for (CardColor cardColor : CardColor.values()) {
				for (CardFace cardFace : CardFace.values()) {
					if(cardFace==CardFace.JOKER && cardColor==CardColor.DIAMOND) {
						continue;
					}
					orderedCards.add(new Card(cardFace, cardColor));
				}
			}
		}
		
		for(int i=0;i<orderedCards.size();i++) {
		int rand=(int)Math.random()*orderedCards.size();
		cards.add(orderedCards.get(rand));
		orderedCards.remove(rand);
		}
	}

	public Card getCard() {
		Card card=this.cards.get(0);
		this.cards.remove(0);
		return card;
	}
}
