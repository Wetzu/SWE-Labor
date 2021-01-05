package rummy.maketurn;

import rummy.statemachine.StateMachine;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import rummy.maketurn.impl.MakeTurnIntImpl;
import rummy.maketurn.port.MakeTurnInt;
import rummy.maketurn.port.MakeTurnPort;
import rummy.statemachine.StateMachine;
import rummy.statemachine.impl.States;

@ApplicationScope
@Component
public class MakTurn implements MakeTurnPort, MakeTurnInt {
	StateMachine stateMachine;

	private MakeTurnIntImpl mtiImpl;

	public void karteZiehen(boolean hidden) {
		if (stateMachine.getState() == States.zugStart)
			this.mtiImpl.karteZiehen();
	}

	public void karteAblegen() {
		if (stateMachine.getState() == States.verdeckteKarteGezogen
				|| stateMachine.getState() == States.kartenAusgelegt)
			this.mtiImpl.karteAblegen();
	}
	public void meldingcards() {
		if ((stateMachine.getState() == States.offeneKarteGezogen) || (stateMachine.getState() == States.verdeckteKarteGezogen) )

		this.mtiImpl.zugBeenden();
	}
	
	public void quit() {
		if (stateMachine.getState() == States.rundeBeendet)

		this.mtiImpl.zugBeenden();
	}
	
	
	public void gewonnen() {
		if (stateMachine.getState() == States.zugEnde)

		this.mtiImpl.zugBeenden();
	}
	
	public void nexteSpieler() {
		if (stateMachine.getState() == States.zugEnde)

		this.mtiImpl.zugBeenden();
	}

	@Override
	public MakeTurnInt makeTurInt() {
		this.stateMachine = new StateMachine();
		return null;
	}
}
