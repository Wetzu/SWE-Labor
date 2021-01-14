package rummy.logic.port;

import rummy.statemachine.port.Subject;

public interface MVCPort {
	
	Subject subject(int id);
	
}
