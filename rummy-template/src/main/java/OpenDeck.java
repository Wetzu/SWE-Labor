public class OpenDeck extends Deck {

	/**
	 * @clientCardinality 1
	 * @supplierCardinality 1
	 */

	private Game lnkGame;

	public void putCard(Card card) {
		this.cards.add(card);
	}

	public Card getTopCard() {
		int indexOfLastCard = this.cards.size() - 1;
		Card card = this.cards.get(indexOfLastCard);
		this.cards.remove(indexOfLastCard);
		return card;
	}
}
