
package rummy.matchcenter.impl;

import rummy.logic.KarteFarbe;
import rummy.logic.KarteSymbol;
import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;

import java.util.ArrayList;

import rummy.logic.Karte;

public class Player implements IPlayer {

	private String name;
	private Match match;
	private boolean hasDrawn;
	public int id;

	public boolean isTurn() {
		return isTurn;
	}

	public void setTurn(boolean turn) {
		isTurn = turn;
	}

	private boolean isTurn;


	Player(Match match, String name) {
		this.name = name;
		this.match = match;
		this.hasDrawn = false;
		this.isTurn = true;
		id = 0;
	}

	@Override
	public IMatch currentMatch() {
		return this.match;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Karte> handkarten = new ArrayList<Karte>();

	@Override
	public int cardCount() {
		return handkarten.size();
	}

//	public void drawOpen(){
//		Karte drawedCard=this.match.DrawOpen();
//		handkarten.add(drawedCard);
//	}
//	public void drawClosed(){
//		Karte drawedCard=this.match.DrawClosed();
//		handkarten.add(drawedCard);
//	}

	@Override
	public void addCard(Karte karte) {
		this.handkarten.add(karte);
	}

	public Karte discardCard(int id){
		return handkarten.remove(id);
	}

	public boolean isHasDrawn() {
		return hasDrawn;
	}

	public void setHasDrawn(boolean hasDrawn) {
		this.hasDrawn = hasDrawn;
	}

	@Override
	public int getId() {
		return id;
	}
}
