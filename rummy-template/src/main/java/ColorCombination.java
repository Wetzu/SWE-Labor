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
	private Card firstCard;
	private Card lastCard;
	private HashSet<Card> jokers;
	ArrayList<Card> cards;

	public ColorCombination(ArrayList<Card> cards) {
		if (cards.size() < 3 || cards.size() > 13) {
			// throw Exception: Wrong size of combination
		} // J,5,6
		checkIfColorsAreSame(cards);
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
		this.cards = cards;// clone??
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

	@Override
	public boolean addCard(Card card) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCards(ArrayList<Card> cards) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Joker switchJoker(Card card) {
		// TODO Auto-generated method stub
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
