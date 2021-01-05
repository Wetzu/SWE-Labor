package rummy;

public enum CardFace {
	ASS, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, JOKER;
	public CardFace getNext() {
		if (this == KING) {
			return ASS;
		}
		return CardFace.values()[this.ordinal() + 1];
	}

	public CardFace getPrevious() {
		if (this == ASS) {
			return KING;
		}
		return CardFace.values()[this.ordinal() - 1];
	}

	public boolean isEqual(CardFace cardFace) {
		if (this == cardFace) {
			return true;
		}
		return false;
	}

}
