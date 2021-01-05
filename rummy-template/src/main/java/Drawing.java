
public class Drawing {




	Card card;
	
	/**
	 * @clientCardinality 0..*
	 * @supplierCardinality 1
	 */
	
	private OpenDeck lnkOpenDeck;
	
	/**
	 * @clientCardinality 0..*
	 * @supplierCardinality 1
	 */
	
	private ClosedDeck lnkClosedDeck;

}
