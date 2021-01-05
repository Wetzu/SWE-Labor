import java.util.ArrayList;

public class Player {

	private ArrayList<Card> handCards;

	/**
	 * @clientCardinality 1
	 * @supplierCardinality 0..*
	 */

	private Discarding lnkCard2;

	/**
	 * @clientCardinality 1
	 * @supplierCardinality 0..*
	 */

	private Drawing lnkCard;

}
