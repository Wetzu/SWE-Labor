package rummy.webui.views;

import org.springframework.ui.Model;

import org.thymeleaf.model.IStandaloneElementTag;
import rummy.logic.Karte;
import rummy.matchcenter.impl.Match;
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
	private static final String OPEN_DECK = "openDeck";
	private static final String OTHER_PLAYERS = "otherPlayers";
	private static final String PLAYER_1_COUNT = "player1Cards";
	private static final String PLAYER_2_COUNT = "player2Cards";
	private static final String PLAYER_3_COUNT = "player3Cards";
	private static final String PLAYER_1_NAME = "player1Name";
	private static final String PLAYER_2_NAME = "player2Name";
	private static final String PLAYER_3_NAME = "player3Name";
	private static final String CURRENT_PLAYER = "currentPlayer";
	private static final String CARD_BACK = "cardBack";

	String cardBackunicode = Character.toString(0x1F0A0);

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
		this.model.addAttribute(GameView.OPEN_HAND, ((Player) this.player).handkarten);
		List<Player> otherPlayers = playerList;
		otherPlayers.remove(match.getHost());
		this.model.addAttribute(GameView.PLAYER_1_COUNT, otherPlayers.get(0).handkarten);
		this.model.addAttribute(GameView.PLAYER_2_COUNT, ((otherPlayers.size() > 1) ? otherPlayers.get(1).handkarten : null));
		this.model.addAttribute(GameView.PLAYER_3_COUNT, ((otherPlayers.size() > 2) ? otherPlayers.get(2).handkarten : null));
		this.model.addAttribute(GameView.PLAYER_1_NAME, otherPlayers.get(0).getName());
		this.model.addAttribute(GameView.PLAYER_2_NAME, ((otherPlayers.size() > 1) ? otherPlayers.get(1).getName() : ""));
		this.model.addAttribute(GameView.PLAYER_3_NAME, ((otherPlayers.size() > 2) ? otherPlayers.get(2).getName() : ""));
		try {
			this.model.addAttribute(GameView.OPEN_DECK, match.GetTopCard().getStringRepresentation());
		}
		catch (IndexOutOfBoundsException e){
			this.model.addAttribute(GameView.OPEN_DECK, "");
		}
		this.model.addAttribute(GameView.CARD_BACK, cardBackunicode);
		return GameView.TEMPLATE_NAME;
	}
}
