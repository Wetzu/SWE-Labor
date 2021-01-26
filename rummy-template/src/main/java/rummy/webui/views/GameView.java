package rummy.webui.views;

import org.springframework.ui.Model;

import rummy.matchcenter.impl.Player;
import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;
import rummy.webui.views.container.StateContainer;

import java.util.ArrayList;
import java.util.List;

public class GameView implements IView {

	private static final String TEMPLATE_NAME = "gameField";

	private static final String NAME_ATTR = "name";
	private static final String MATCHID_ATTR = "matchId";
	private static final String STATE_ATTR = "state";
	private static final String OPEN_HAND = "openHand";
	private static final String CLOSED_HAND_1 = "closedHand1";
	private static final String CLOSED_HAND_2 = "closedHand2";
	private static final String CLOSED_HAND_3 = "closedHand3";
	private static final String COUNT_OPEN = "countOpen";
	private static final String COUNT_CLOSED = "countClosed";
	private static final String CURRENT_PLAYER = "currentPlayer";

	private IPlayer player;
	private IMatch match;
	private Model model;
	private boolean canStop;
	static int countPlayers = 0;
	private List<Player> playerList;


	public GameView(IMatch match, IPlayer player, boolean canStop) {
		this.player = player;
		this.match = match;
		this.canStop = canStop;
		playerList = new ArrayList(match.allPlayers());
		countPlayers = playerList.size();
	}

	@Override
	public String build(Model model) {
		this.model = model;
		this.model.addAttribute(GameView.STATE_ATTR, new StateContainer(this.canStop, false));
		this.model.addAttribute(GameView.MATCHID_ATTR, String.format("%d", this.match.getId()));
		this.model.addAttribute(GameView.NAME_ATTR, this.player.getName());
		this.model.addAttribute(GameView.OPEN_HAND, this.player.);
		return GameView.TEMPLATE_NAME;
	}
}
