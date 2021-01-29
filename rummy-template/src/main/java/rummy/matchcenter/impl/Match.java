package rummy.matchcenter.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rummy.logic.Karte;
import rummy.logic.OffenerStapel;
import rummy.logic.VerdeckterStapel;
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

	private VerdeckterStapel verdeckterStapel;
	private OffenerStapel offenerStapel;

	Match(int num, int idx) {
		this.numberOfSeries = num;
		this.id = idx;
		currentTurn = 0;
		verdeckterStapel = new VerdeckterStapel();
		offenerStapel = new OffenerStapel(verdeckterStapel.GetCard());
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
	public void StartGame(){
		for (Player player:players) {
			for(int i = 0; i < 13; i++){
				player.handkarten.add(verdeckterStapel.GetCard());
			}
		}
	}

	private int currentTurn;

	public IPlayer getCurrentTurn(){
		if(currentTurn <= players.size()) currentTurn = 0;
		return players.get(currentTurn++);

	}
	

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public Collection<? extends IPlayer> allPlayers() {
		return new ArrayList<>(this.players);
	}

	@Override
	public Karte GetTopCard() {
		return offenerStapel.ShowTopCard();
	}

	public Karte DrawOpen(){
		return offenerStapel.GetCard();
	}

	public Karte DrawClosed(){
		return verdeckterStapel.GetCard();
	}
	public void discardCard(Karte karte){
		this.offenerStapel.AddCard(karte);
	}

}
