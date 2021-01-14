package rummy.matchcenter.port;

import java.util.Collection;

public interface IMatch {

	int MAX = 4;
	int MIN = 2;

	IPlayer getHost();

	boolean enoughPlayers();

	int getId();

	Collection<? extends IPlayer> allPlayers();

}
