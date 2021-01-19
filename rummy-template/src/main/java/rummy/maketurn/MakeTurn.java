package rummy.maketurn;



import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import rummy.maketurn.impl.MakeTurnIntImpl;
import rummy.maketurn.port.MakeTurnInt;
import rummy.maketurn.port.MakeTurnPort;

import rummy.statemachine.StateMachineCenter;
import rummy.statemachine.impl.StateMachineImpl;
import rummy.statemachine.port.State;


@ApplicationScope
@Component
public class MakeTurn implements MakeTurnPort, MakeTurnInt {
	

 StateMachineImpl stateMachine;

 private MakeTurnIntImpl mtiImpl;

 public void karteZiehen(boolean verdeckt) {
  if (stateMachine.getState() == State.S.zugStart)
   this.mtiImpl.karteZiehen(verdeckt);
 }

 public void karteAblegen() {
  if (stateMachine.getState() == State.S.verdeckteKarteGezogen
    || stateMachine.getState() == State.S.kartenAusgelegt)
   this.mtiImpl.karteAblegen();
 }
 
 public void kartenAuslegen() {
  if ((stateMachine.getState() == State.S.offeneKarteGezogen) || (stateMachine.getState() == State.S.verdeckteKarteGezogen) )
	  this.mtiImpl.zugBeenden();
 }
 
 public void quit() {
  if (stateMachine.getState() == State.S.zugEnde)

  this.mtiImpl.zugBeenden();
 }
 
 
 
 public void nexteSpieler() {
  if (stateMachine.getState() == State.S.zugEnde)
  this.mtiImpl.zugBeenden();
 }

 
 @Override
 public MakeTurnInt makeTurnInt() {
  this.stateMachine = new StateMachineImpl();
  return null;
 }
}


