package rummy.matchcenter.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rummy.logic.Stapel;
import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;

public class Match implements IMatch {

	/**
	 * @directed true
	 * @link aggregation
	 * @supplierRole host
	 */

	private Player host;

	/**
	 * @clientCardinality
	 * @clientRole match
	 * @directed true
	 * @link composition
	 * @supplierCardinality 1..MAX
	 * @supplierRole players
	 */
	@SuppressWarnings("unused")
	private rummy.matchcenter.impl.Player lnkPlayer;
	private List<Player> players = new ArrayList<>();

	@SuppressWarnings("unused")
	private int numberOfSeries;
	private int id;

	Match(int num, int idx) {
		this.numberOfSeries = num;
		this.id = idx;

	}

	@Override
	public IPlayer getHost() {
		return this.host;
	}

	@Override
	public boolean enoughPlayers() {
		return this.players.size() >= IMatch.MIN;
	}

	int addPlayer(Player player) {
		this.players.add(player);
		return this.players.size();
	}

	void setHost(Player host) {
		this.host = host;
		this.players.add(host);
	}

	

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public Collection<? extends IPlayer> allPlayers() {
		return new ArrayList<>(this.players);
	}
	
	Stapel stapel = new Stapel();

}
