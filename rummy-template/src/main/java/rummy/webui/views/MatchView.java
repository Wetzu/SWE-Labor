package rummy.webui.views;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;
import rummy.matchcenter.port.MatchFactory;
import rummy.webui.views.container.GameContainer;
import rummy.webui.views.container.PlayerContainer;
import rummy.webui.views.container.StateContainer;

public class MatchView implements IView {

	private static final String TEMPLATE_NAME = "match";

	private static final String NAME_ATTR = "name";
	private static final String MATCHID_ATTR = "matchId";
	private static final String STATE_ATTR = "state";

	private static final String GAMES_ATTR = "games";
	private static final String PLAYERS_ATTR = "players";

	private IPlayer player;
	private IMatch match;
	private Model model;

	private boolean canStop;
	private boolean canStart;

	public MatchView(IMatch match, IPlayer player) {
		this.player = player;
		this.match = match;
	}

	public MatchView(IMatch match, IPlayer player, boolean enoughPlayer) {
		this.canStart = enoughPlayer;
		this.canStop = true;
		this.player = player;
		this.match = match;
	}

	@Override
	public synchronized String build(Model model) {
		this.model = model;

		this.setMachtInfo();
		this.setGamesInfo();
		this.setPlayersInfo();

		return MatchView.TEMPLATE_NAME;
	}

	private void setMachtInfo() {
		this.model.addAttribute(MatchView.STATE_ATTR, new StateContainer(this.canStop, this.canStart));
		this.model.addAttribute(MatchView.MATCHID_ATTR, String.format("%d", this.match.getId()));
		this.model.addAttribute(MatchView.NAME_ATTR, this.player.getName());
	}

	private void setGamesInfo() {
		List<GameContainer> gcl = new ArrayList<>();

		for (int i = 0; i < MatchFactory.NUMBER_OF_SERIES; i++)
			gcl.add(new GameContainer(i + 1));
		this.model.addAttribute(MatchView.GAMES_ATTR, gcl);

	}

	private void setPlayersInfo() {
		List<PlayerContainer> pcl = new ArrayList<>();

		for (IPlayer player : this.match.allPlayers()) {
			pcl.add(new PlayerContainer(player));
		}
		this.model.addAttribute(MatchView.PLAYERS_ATTR, pcl);
	}

}
