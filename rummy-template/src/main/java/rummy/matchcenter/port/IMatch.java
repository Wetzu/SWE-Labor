package rummy.matchcenter.port;

import rummy.logic.Karte;

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

}
