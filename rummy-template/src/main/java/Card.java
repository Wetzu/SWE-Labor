import java.util.Comparator;

import rummy.CardColor;
import rummy.CardFace;

public class Card implements Comparable<Card>, Comparator<Card> {
	private CardFace cardFace;
	private CardColor cardColor;

	public Card(CardFace cardFace, CardColor cardColor) {
		this.cardFace = cardFace;
		this.cardColor = cardColor;
	}

	public CardFace getCardFace() {
		return this.cardFace;
	}

	public CardColor getCardColor() {
		return this.cardColor;
	}

	@Override
	public int compareTo(Card o) {
		if (o instanceof Card) {
			Card otherCard = (Card) o;
			if (this.getCardFace().ordinal() < otherCard.getCardFace().ordinal()) {
				return -1;
			} else if (this.getCardFace().ordinal() == otherCard.getCardFace().ordinal()) {
				return 0;
			} else
				return 1;
		}
		return 0;
	}

	@Override
	public int compare(Card o1, Card o2) {
		return o1.compareTo(o2);
	}
}
