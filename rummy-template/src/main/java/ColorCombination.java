import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import rummy.CardColor;
import rummy.CardFace;

//QJA
public class ColorCombination extends Combination {
	private CardColor cardColor;
	private CardFace firstCard;
	private CardFace lastCard;
	private int numberOfJokers;
	private HashSet<Card> jokers;

	public ColorCombination(ArrayList<Card> cards) {
		if (cards.size() < 3 || cards.size() > 13) {
			// throw Exception: Wrong size of combination
		} // J,5,6
		checkIfColorsAreSame(cards);
		checkIfJokersAreToMany(cards);
		checkIfJokersAreCorrectlyPlaced(cards);
		this.cards = cards;// clone??

		setFirstAndLastCard(cards);

	}

	private void setFirstAndLastCard(ArrayList<Card> cards) {
		if (cards.get(0).getCardFace().isEqual(CardFace.JOKER)) {
			firstCard = cards.get(1).getCardFace().getPrevious();
		} else {
			firstCard = cards.get(0).getCardFace();
		}

		if (cards.get(cards.size() - 1).getCardFace().isEqual(CardFace.JOKER)) {
			lastCard = cards.get(cards.size() - 2).getCardFace().getNext();
		} else {
			lastCard = cards.get(cards.size() - 1).getCardFace();
		}
	}

	private void checkIfJokersAreCorrectlyPlaced(ArrayList<Card> cards) {
		for (int i = 0; i < cards.size() - 1; i++) {
			if (cards.get(i).getCardFace() == CardFace.JOKER) {
				if (cards.get(i + 1).getCardFace() == CardFace.JOKER) {
					// throw Exception: two Jokers next to eachother
				}
			} else {
				if (cards.get(i).getCardFace().getNext() != cards.get(i + 1).getCardFace()) {
					if (cards.get(i + 1).getCardFace() == CardFace.JOKER) {
						if (cards.size() == i + 2) {
							break;
						} else if (cards.get(i).getCardFace().getNext().getNext() != cards.get(i + 2).getCardFace()) {
							// throw Exception: cards in wrong order
						}
					} else {
						// throw Exception: cards in wrong order
					}

				}
			}
		}
	}

	private void checkIfJokersAreToMany(ArrayList<Card> cards) {
		for (Card card : cards) {
			if (card.getCardFace() == CardFace.JOKER) {
				this.numberOfJokers++;
			}
		}
		if (this.numberOfJokers * 2 >= cards.size()) {
			// throw exception: Jokers need to be in Unterzahl
		}
	}

	@Override
	public boolean addCard(Card card, boolean end) {
		if (this.cardColor != card.getCardColor()) {
			return false;
		}
		if (card.getCardFace() == CardFace.JOKER) {
			if ((this.numberOfJokers + 1) * 2 >= cards.size() + 1) {
				return false;
			}
			if (end && this.cards.get(this.cards.size() - 1).getCardFace() == CardFace.JOKER) {
				return false;
			}
			if (!end && this.cards.get(0).getCardFace() == CardFace.JOKER) {
				return false;
			}
			if (!end) {
				this.firstCard = this.firstCard.getPrevious();
				this.numberOfJokers++;
				this.cards.add(0, card);
				return true;
			}
			if (end) {
				this.lastCard = this.lastCard.getNext();
				this.numberOfJokers++;
				this.cards.add(0, card);
				return true;
			}

		} else {
			if (end) {
				if (lastCard.getNext() == card.getCardFace()) {
					this.lastCard = card.getCardFace();
					this.cards.add(card);
					return true;
				} else {
					return false;
				}
			} else if (!end) {
				if (firstCard.getPrevious() == card.getCardFace()) {
					this.firstCard = card.getCardFace();
					this.cards.add(card);
					return true;
				} else {
					return false;
				}
			}
		}
		return false;

	}

	/*
	 * @Override public boolean addCards(ArrayList<Card> cards,boolean end) { int
	 * numberOfCards=this.cards.size()+cards.size();
	 * 
	 * return false; }
	 */

	@Override
	public Joker switchJoker(Card card) {
		if (card.getCardColor() != this.cardColor) {
			// throw exception Not the same color
		}
		for (int i = 0; i < this.cards.size() - 1; i++) {
			if (this.cards.get(i).getCardFace() == CardFace.JOKER) {
				CardFace jokerFace = this.cards.get(i + 1).getCardFace().getPrevious();
				if (card.getCardFace() == jokerFace) {
					Joker joker = (Joker) this.cards.get(i);
					this.cards.set(i, card);
					this.numberOfJokers--;
					return joker;
				}
			}

		}
		int lastIndex = this.cards.size() - 1;
		if (this.cards.get(lastIndex).getCardFace() == CardFace.JOKER) {
			CardFace jokerFace = this.cards.get(lastIndex - 1).getCardFace().getNext();
			if (card.getCardFace() == jokerFace) {
				Joker joker = (Joker) this.cards.get(lastIndex);
				this.cards.set(lastIndex, card);
				this.numberOfJokers--;
				return joker;
			}
		}
		return null;
	}

	private void checkIfColorsAreSame(ArrayList<Card> cards) {
		Set<CardColor> colors = new HashSet<CardColor>();
		for (Card card : cards) {
			if (card.getCardFace() != CardFace.JOKER) {
				colors.add(card.getCardColor());
			}
		}
		if (colors.size() != 1) {
			// throw ...
		}
	}
}
