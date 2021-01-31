package rummy.matchcenter.port;

import rummy.logic.Karte;

public interface IPlayer {

	IMatch currentMatch();

	String getName();

	int getId();

	int cardCount();

	Karte discardCard(int id);

	void addCard(Karte karte);

	public boolean isHasDrawn();

	public void setHasDrawn(boolean hasDrawn);

	public boolean isTurn();
	public void setTurn(boolean turn);
}
