package rummy.statemachine.port;



public interface StateMachinePort {
	
	StateMachine mkNewMachine(int id);

	StateMachine getMachine(int id);

	void closeMachine(int id);
	
	
}
