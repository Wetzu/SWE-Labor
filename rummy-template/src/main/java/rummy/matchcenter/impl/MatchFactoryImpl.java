
package rummy.matchcenter.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;
import rummy.matchcenter.port.MatchFactory;
import rummy.statemachine.port.State;
import rummy.statemachine.port.StateMachinePort;

public class MatchFactoryImpl implements MatchFactory {

	/**
	* @stereotype create
	*/

	/* #rummy.matchcenter.impl.Player lnkPlayer */

	/**
	* @stereotype create
	*/
	/* #rummy.matchcenter.impl.Player lnkIPlayer */

	/**
	* @directed true
	* @link composition
	* @supplierCardinality 0..MAX_MATCHES
	*/
	@SuppressWarnings("unused")
	private rummy.matchcenter.impl.Match lnkMatch;

	private Map<Integer, Match> allMatches = new HashMap<>();

	private Random randomGenerator = new Random();
	private StateMachinePort stateMachinePort;

	public MatchFactoryImpl(StateMachinePort smPort) {
		this.stateMachinePort = smPort;
	}

	@Override
	public IPlayer mkPlayer(String name, int id) {
		Match match = this.allMatches.get(id);
		Player player = new Player(match, name);
		int numberOfPlayers = match.addPlayer(player);
		if (numberOfPlayers >= IMatch.MAX) {
			this.stateMachinePort.getMachine(id).setState(State.S.MustStart);
		} else if (numberOfPlayers < IMatch.MIN) {
			this.stateMachinePort.getMachine(id).setState(State.S.MustJoin);
		} else
			this.stateMachinePort.getMachine(id).setState(State.S.JoinOrStart);
		return player;
	}

	public Match getMatch(int id) {
		return this.allMatches.get(id);
	}

	@Override
	public IMatch createMatch(String hostingPlayer) {
		if (this.allMatches.size() >= MatchFactory.MAX_MATCHES)
			return null;
		int idx = -1;
		do {
			idx = this.randomGenerator.nextInt(10000);
		}while (this.allMatches.containsKey(idx));
		
		Match match = new Match(MatchFactory.NUMBER_OF_SERIES, idx);
		Player host = new Player(match, hostingPlayer);
		match.setHost(host);
		this.allMatches.put(idx, match);
		this.stateMachinePort.mkNewMachine(idx).setState(State.S.MustJoin);
		return match;
	}

	@Override
	public boolean closeMatch(IMatch match) {
		int id = match.getId();
		Match theMatch = this.allMatches.remove(id);
		if (theMatch == null)
			return false;
		this.stateMachinePort.closeMachine(id);
		return true;
	}


}
