import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import rummy.CardColor;
import rummy.CardFace;

public class FaceCombination extends Combination {

	public FaceCombination(ArrayList<Card> cards) {
		if (cards.size() < 3 || cards.size() > 4) {
			// throw. FEHLER
		}
		checkIfLessThenTwoJoker(cards);
		checkIfFacesAreSame(cards);
		checkIfColorsAreDifferent(cards);
		this.cards.addAll(cards);

	}

	private void checkIfLessThenTwoJoker(ArrayList<Card> cards) {
		int numberOfJokers = getNumberOfJokers(cards);
		if (numberOfJokers >= 2) {
			// throw FEHLER
		}

	}

	private int getNumberOfJokers(ArrayList<Card> cards) {
		int numberOfJokers = 0;
		for (Card card : cards) {
			if (card.getCardFace() == CardFace.JOKER) {
				numberOfJokers++;
			}
		}
		return numberOfJokers;
	}

	private void checkIfColorsAreDifferent(ArrayList<Card> cards) {
		Set<CardColor> colors = new HashSet();
		for (Card card : cards) {
			if (card.getCardFace() != CardFace.JOKER) {
				if (colors.contains(card.getCardColor())) {
					// throw FEHLER
				} else {
					colors.add(card.getCardColor());
				}
			}
		}
	}

	private Card getFirstNonjokerCard(ArrayList<Card> cards) {
		Card controlCard = null;
		for (Card cardIter : cards) {
			if (cardIter.getCardFace() != CardFace.JOKER) {
				controlCard = cardIter;
				break;
			}
		}
		return controlCard;
	}

	private void checkIfFacesAreSame(ArrayList<Card> cards) {
		Set<CardFace> faces = new HashSet();
		for (Card card : cards) {
			if (card.getCardFace() != CardFace.JOKER) {
				faces.add(card.getCardFace());
			}
		}

		for (Card card : cards) {
			if (card.getCardFace() != CardFace.JOKER) {
				if (!faces.contains(card.getCardFace())) {
					// throw FEHLER
				} else {

				}
			}
		}
	}

	private int getIndexOfJoker(ArrayList<Card> cards) {
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).getCardFace() == CardFace.JOKER) {
				return i;
			}

		}
		return -1;
	}

	@Override
	public boolean addCard(Card card,boolean end) {
		if (this.cards.size() != 3) {
			return false;
		}
		if (card.getCardFace() == CardFace.JOKER) {
			if (this.getNumberOfJokers(cards) > 0) {
				return false;
			}
		}
		return tryAddNonjokerCard(card);
	}

	private boolean tryAddNonjokerCard(Card card) {
		Card firstNonJokerCard = this.getFirstNonjokerCard(this.cards);
		if (firstNonJokerCard.getCardFace() != card.getCardFace()) {
			return false;
		}

		for (Card cardIter : this.cards) {
			// boolean cardFaceSame = card.getCardFace() == cardIter.getCardFace();
			// boolean cardIterIsJoker = cardIter.getCardFace() == CardFace.JOKER;
			boolean cardColorSame = cardIter.getCardColor() == cardIter.getCardColor();
			if ((cardColorSame)) {
				return false;
			}
		}
		this.cards.add(card);
		return true;
	}

	/*@Override
	public boolean addCards(ArrayList<Card> cards, boolean end) {
		if (cards.size() != 1) {
			return false; // trow ... Size
		} else {
			return this.addCard(cards.get(0),end);
		}

	}*/

	@Override
	public Joker switchJoker(Card card) {
		if (getNumberOfJokers(cards) == 0) {
			return null; // Throw?
		}

		if (tryAddNonjokerCard(card)) {
			int indexOfJoker = this.getIndexOfJoker(this.cards);
			if (indexOfJoker == -1) {
				// katastrofe
			}
			return (Joker) this.cards.remove(indexOfJoker);

		} else {
			// throw...
			return null;
		}

	}
}
