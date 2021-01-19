package rummy.logic.port;

import rummy.maketurn.port.MakeTurnInt;
import rummy.matchcenter.port.MatchFactory;
import rummy.matchcenter.port.MatchManagement;

public interface MatchPort {

	MatchFactory matchFactory();

	MatchManagement matchManagement();

	MakeTurnInt makeTurnInt();
}
