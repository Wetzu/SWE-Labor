package rummy.webui.views.container;

import java.util.ArrayList;
import java.util.Collection;

import rummy.matchcenter.port.IPlayer;
import rummy.matchcenter.port.MatchFactory;

public class PlayerContainer {

	protected IPlayer player;
	Collection<ResultContainer> results = new ArrayList<>();

	public PlayerContainer(IPlayer player) {
		this.player = player;
		for (int i = 0; i < MatchFactory.NUMBER_OF_SERIES; i++) {
			this.results.add(new ResultContainer(0, 0));
		}
	}

	public String getName() {
		return this.player.getName();
	}

	public Collection<? extends ResultContainer> getResults() {
		return this.results;
	}

	public String getTotalScore() {
		int score = 0;
		return "" + score;
	}

	public String getTotalPips() {
		int pips = 0;
		return "" + pips;
	}
}