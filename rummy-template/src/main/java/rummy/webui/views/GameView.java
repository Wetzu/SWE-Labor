package rummy.webui.views;

import org.springframework.ui.Model;

import rummy.matchcenter.impl.Player;
import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;
import rummy.webui.views.container.StateContainer;

public class GameView implements IView {

	private static final String TEMPLATE_NAME = "gameField";

	private static final String NAME_ATTR = "name";
	private static final String MATCHID_ATTR = "matchId";
	private static final String STATE_ATTR = "state";

	private IPlayer player;
	private String[] playerNames = {"John", "Berry", "Someone Else"};
	private IMatch match;
	private Model model;
	private boolean canStop;

	public GameView(IMatch match, IPlayer player, boolean canStop) {
		this.player = player;
		this.match = match;
		this.canStop = canStop;
	}

	@Override
	public String build(Model model) {
		this.model = model;
		this.model.addAttribute(GameView.STATE_ATTR, new StateContainer(this.canStop, false));
		this.model.addAttribute(GameView.MATCHID_ATTR, String.format("%d", this.match.getId()));
		this.model.addAttribute(GameView.NAME_ATTR, this.player.getName());
		return GameView.TEMPLATE_NAME;
	}
}
