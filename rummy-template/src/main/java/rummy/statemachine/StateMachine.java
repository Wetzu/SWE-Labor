package rummy.statemachine;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import rummy.statemachine.impl.States;
import rummy.statemachine.port.Observer;
import rummy.statemachine.port.StateMachinePort;
import rummy.statemachine.port.Subject;

@ApplicationScope
@Component
public class StateMachine implements Observer, StateMachinePort, Subject {
	private States currentState;

	public StateMachine() {
		this.currentState = States.zugStart;
	}



	public States getState() {

		return this.currentState;
	} 

	public void setState(States state) {

		this.currentState = state;
	}

}