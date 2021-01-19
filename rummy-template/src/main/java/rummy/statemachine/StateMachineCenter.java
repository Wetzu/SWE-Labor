package rummy.statemachine;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import rummy.statemachine.impl.StateMachineImpl;
import rummy.statemachine.port.State;
import rummy.statemachine.port.StateMachine;
import rummy.statemachine.port.StateMachinePort;
import rummy.statemachine.port.Subject;
import rummy.statemachine.port.SubjectPort;


@ApplicationScope
@Component
public class StateMachineCenter implements StateMachinePort, SubjectPort {



	/**
	 * @link composition
	 * @supplierCardinality 0..*
	 * @supplierNavigability NAVIGABLE_EXPLICITLY
	 * @supplierRole openMachine
	 */
	
	

	@SuppressWarnings("unused")
	private StateMachineImpl lnkStateMachineImpl;
	private Map<Integer, StateMachineImpl> openMachines = new HashMap<>();

	@Override
	public synchronized StateMachine mkNewMachine(int id) {
		if (this.openMachines.containsKey(id))
			return null;
		StateMachineImpl stateMachine = new StateMachineImpl();
		this.openMachines.put(id, stateMachine);
		return stateMachine;

	}

	@Override
	public synchronized StateMachine getMachine(int id) {
		return this.openMachines.get(id);
	}

	@Override
	public synchronized void closeMachine(int id) {
		StateMachineImpl stateMachine = this.openMachines.get(id);
		if (stateMachine != null) {
			stateMachine.setState(State.S.Closed);
			stateMachine.detachAll();
		}

		this.openMachines.remove(id);
	}

	@Override
	public synchronized Subject subject(int id) {
		return this.openMachines.get(id);
	}



	

}
