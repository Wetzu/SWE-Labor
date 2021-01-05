import java.util.ArrayList;

public class Deck {

	/**
	 * @clientCardinality 1
	 * @link composition
	 * @supplierCardinality 1..*
	 */
	protected ArrayList<Card> cards;

	public Deck() {
		cards = new ArrayList<Card>();
	}

	public boolean isEmpty() {
		return this.cards.isEmpty();
	}
}
