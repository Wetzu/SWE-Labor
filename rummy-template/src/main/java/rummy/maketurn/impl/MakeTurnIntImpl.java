package rummy.maketurn.impl;

import rummy.logic.Karte;
import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;

public class MakeTurnIntImpl {
 
 
 public void karteZiehen(Boolean verdeckt, IMatch match, IPlayer player) {
  if(verdeckt) {
	 match.drawClosed(player);
  }
  else {
	  match.drawOpen(player);
  }
 }
 
 
 
 public void karteAblegen(IMatch match, IPlayer player, int index) {
  match.discardCard(player, index);
 }
 
 
 public void nextSpieler() {
	 
 }
 
 
 public void zugBeenden() {
 
 }
 
 
}
