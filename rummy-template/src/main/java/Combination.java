public class Combination {



	
	/**
	 * @clientCardinality 0..6
	 * @supplierCardinality 0..*
	 */
	
	private Joker lnkJoker;
	
	/**
	 * @clientCardinality 0..*
	 * @supplierCardinality 1
	 */
	
	private Table lnkTable;
}
