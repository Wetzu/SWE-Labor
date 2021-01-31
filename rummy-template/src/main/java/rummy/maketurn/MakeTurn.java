package rummy.maketurn;



import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import rummy.maketurn.impl.MakeTurnIntImpl;
import rummy.maketurn.port.MakeTurnInt;
import rummy.maketurn.port.MakeTurnPort;

import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;
import rummy.statemachine.StateMachineCenter;
import rummy.statemachine.impl.StateMachineImpl;
import rummy.statemachine.port.State;


@ApplicationScope
@Component
public class MakeTurn implements MakeTurnPort, MakeTurnInt {
	


 private MakeTurnIntImpl mtiImpl = new MakeTurnIntImpl();

 public MakeTurn() {
  this.mtiImpl = new MakeTurnIntImpl();
 }

 public void offeneKarteZiehen(IMatch match, IPlayer player) {
  if (match.getStatemachine().getState().isSubStateOf(State.S.MussZiehen))
   return;
  this.mtiImpl.karteZiehen(false, match, player);
 }

 public void verdeckteKarteZiehen(IMatch match, IPlayer player){
  if (match.getStatemachine().getState().isSubStateOf(State.S.MussZiehen))
   return;
  this.mtiImpl.karteZiehen(true, match, player);
 }

 public void karteAblegen(IMatch match, IPlayer player, int index) {
  if (match.getStatemachine().getState() == State.S.verdeckteKarteGezogen
    || match.getStatemachine().getState() == State.S.offeneKarteGezogen)
   this.mtiImpl.karteAblegen(match, player, index);
 }

 @Override
 public MakeTurnInt makeTurnInt() {
  return null;
 }

// public void quit() {
//  if (stateMachine.getState() == State.S.zugEnde)
//
//  this.mtiImpl.zugBeenden();
// }
//
//
//
// public void nexteSpieler() {
//  if (stateMachine.getState() == State.S.zugEnde)
//  this.mtiImpl.zugBeenden();
// }

}


