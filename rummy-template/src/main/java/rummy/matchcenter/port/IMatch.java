package rummy.matchcenter.port;

import rummy.logic.Karte;
import rummy.statemachine.port.StateMachine;

import java.util.Collection;

public interface IMatch {

	int MAX = 4;
	int MIN = 2;

	IPlayer getHost();

	boolean enoughPlayers();

	int getId();

	Collection<? extends IPlayer> allPlayers();

	void StartGame();

	Karte GetTopCard();

	StateMachine getStatemachine();

	public void discardCard(IPlayer player, int index);

	public void drawClosed(IPlayer player);
	public void drawOpen(IPlayer player);

	int getCurrentTurn();
	void setCurrentTurn(int currentTurn);

	void setNextCurrentTurn();
}
