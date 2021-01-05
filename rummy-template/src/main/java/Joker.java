import rummy.CardColor;
import rummy.CardFace;

public class Joker extends Card {


	
	public Joker(CardFace cardFace, CardColor cardColor) {
		super(cardFace, cardColor);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @clientCardinality 0..6
	 * @supplierCardinality 0..4
	 */
	
	private Player lnkPlayer;
}
