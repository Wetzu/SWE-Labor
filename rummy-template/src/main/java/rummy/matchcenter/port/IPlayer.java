package rummy.matchcenter.port;

public interface IPlayer {

	IMatch currentMatch();

	String getName();

	int cardCount();

	void discardCard(int id);

	void drawOpen();

	void drawClosed();
}
