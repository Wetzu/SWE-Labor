import java.util.ArrayList;

public abstract class Combination {

	/**
	 * @clientCardinality 0..6
	 * @supplierCardinality 0..*
	 */

	public Joker lnkJoker = null;
	/**
	 * @clientCardinality 0..*
	 * @supplierCardinality 1
	 */

	public Table lnkTable = null;

	protected ArrayList<Card> cards;

	public abstract boolean addCard(Card card);

	public abstract boolean addCards(ArrayList<Card> cards);

	public abstract Joker switchJoker(Card card);

}
