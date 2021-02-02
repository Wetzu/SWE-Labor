package rummy.matchcenter.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import rummy.logic.Karte;
import rummy.logic.OffenerStapel;
import rummy.logic.VerdeckterStapel;
import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;
import rummy.statemachine.impl.StateMachineImpl;
import rummy.statemachine.port.State;
import rummy.statemachine.port.StateMachine;

public class Match implements IMatch {

	/**
	 * @directed true
	 * @link aggregation
	 * @supplierRole host
	 */

	private Player host;
	private int currentTurn;
	public StateMachine stateMachine;

	public Match(StateMachine stateMachine) {
		this.stateMachine = new StateMachineImpl();
		currentTurn = 0;
	}

	public StateMachine getStateMachine(){
		return stateMachine;
	}

	public void setStateMachine(StateMachine stateMachine){
		this.stateMachine = stateMachine;
	}

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
		stateMachine = new StateMachineImpl();
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
		int id = 0;
		for (Player player:players) {
			for(int i = 0; i < 13; i++){
				player.handkarten.add(verdeckterStapel.GetCard());
			}
			player.id = id;
		}
		this.stateMachine.setState(State.S.zugStart);
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

	public void drawOpen(IPlayer player){
		if(!player.isHasDrawn()){
			player.addCard(offenerStapel.GetCard());
			player.setHasDrawn(true);
		}
		this.stateMachine.setState(State.S.offeneKarteGezogen);
	}

	public void drawClosed(IPlayer player){
		if(!player.isHasDrawn()){
			player.addCard(verdeckterStapel.GetCard());
			player.setHasDrawn(true);
		}
		this.stateMachine.setState(State.S.verdeckteKarteGezogen);
	}
	public void discardCard(IPlayer player, int index){
		if(player.isTurn()){
			Karte karte = player.discardCard(index);
			this.offenerStapel.AddCard(karte);
			player.setTurn(false);
			this.setNextCurrentTurn();
		}


	}

	public StateMachine getStatemachine(){
		return stateMachine;
	}

	@Override
	public int getCurrentTurn() {
		return currentTurn;
	}

	@Override
	public void setCurrentTurn(int currentTurn) {
		this.currentTurn = currentTurn;
	}

	@Override
	public void setNextCurrentTurn() {
		if(currentTurn == players.size()) setCurrentTurn(0);
		players.get(currentTurn).setTurn(true);
		players.get(currentTurn).setHasDrawn(false);
		currentTurn++;
	}
}
