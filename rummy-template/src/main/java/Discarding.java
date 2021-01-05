public class Discarding {




	Card card;
	
	/**
	 * @clientCardinality 1..*
	 * @supplierCardinality 1
	 */
	
	private OpenDeck lnkOpenDeck;
	
	/**
	 * @clientCardinality 0..*
	 * @supplierCardinality 1..*
	 */
	
	private CombinationInterface lnkCombination;
}
