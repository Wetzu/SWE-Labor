package rummy.logic.port;

import rummy.matchcenter.port.MatchFactory;
import rummy.matchcenter.port.MatchManagement;

public interface MatchPort {

	MatchFactory matchFactory();

	MatchManagement matchManagement();
}
