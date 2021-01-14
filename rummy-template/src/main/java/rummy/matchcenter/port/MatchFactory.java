package rummy.matchcenter.port;

public interface MatchFactory {

	/**
		* @stereotype create
		*/
	
		/* #rummy.matchcenter.impl.Match lnkMatch */
	
	int MAX_MATCHES = 100;
	int NUMBER_OF_SERIES = 5;

	IPlayer mkPlayer(String name, int id);

	IMatch createMatch(String hostingPlayer);
	
	boolean closeMatch(IMatch match);
	
}
