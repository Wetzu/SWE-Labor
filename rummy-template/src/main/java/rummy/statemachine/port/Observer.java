package rummy.statemachine.port;

import rummy.statemachine.impl.States;

public interface Observer {
	
	void update(States state);
}
