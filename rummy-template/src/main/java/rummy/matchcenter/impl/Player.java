
package rummy.matchcenter.impl;

import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;

public class Player implements IPlayer {

	private String name;
	private Match match;

	Player(Match match, String name) {
		this.name = name;
		this.match = match;
	}

	@Override
	public IMatch currentMatch() {
		return this.match;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
