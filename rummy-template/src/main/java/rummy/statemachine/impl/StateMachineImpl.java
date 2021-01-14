package rummy.statemachine.impl;

import java.util.ArrayList;
import java.util.List;

import rummy.statemachine.port.Observer;
import rummy.statemachine.port.State;
import rummy.statemachine.port.StateMachine;
import rummy.statemachine.port.Subject;

public class StateMachineImpl implements StateMachine, Subject {


	
	/**
	 * @directed true
	 * @supplierCardinality 0..*
	 * @supplierRole observer
	 */
	
	@SuppressWarnings("unused")
	private Observer lnkObserver;


	private List<Observer> observers = new ArrayList<>();

	
	/**
	 * @directed true
	 * @link aggregation
	 * @supplierNavigability NAVIGABLE_EXPLICITLY
	 * @supplierRole currentState
	 */
	
	private State currentState;

	public StateMachineImpl() {
		this.currentState = State.S.INITIAL_STATE;
	}

	@Override
	public synchronized void attach(Observer obs) {
		if (this.observers.add(obs))
			obs.update(this.currentState);
	}

	@Override
	public synchronized void detach(Observer obs) {
		if (this.observers.remove(obs))
			obs.update(this.currentState);
	}

	public synchronized void detachAll() {
		this.observers.clear();
	}

	@Override
	public synchronized State getState() {
		return this.currentState;
	}

	@Override
	public synchronized boolean setState(State state) {
		if (state == null)
			return false;
		this.currentState = state;
		this.observers.forEach(obs -> obs.update(state));
		return true;
	}
}