package rummy.webui;


import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;

import rummy.logic.port.MVCPort;
import rummy.logic.port.MatchPort;
import rummy.matchcenter.impl.Match;
import rummy.matchcenter.impl.Player;
import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;
import rummy.socketmanagement.RummySocketController;
import rummy.statemachine.port.Observer;
import rummy.statemachine.port.State;
import rummy.webui.views.ErrorView;
import rummy.webui.views.GameView;
import rummy.webui.views.MatchView;
import rummy.webui.views.QuitView;

@SessionScope
@Controller
@RequestMapping("/rummy")
public class MainController implements Observer {

	/**
	 * @stereotype create
	 */

	/* #rummy.webui.views.MatchView lnkMatchView */

	/**
	 * @directed true
	 * @link aggregation
	 * @supplierNavigability NAVIGABLE_EXPLICITLY
	 * @supplierRole mvcPort
	 */
	@Autowired
	private MVCPort mvcPort;

	/**
	 * @directed true
	 * @link aggregation
	 * @supplierRole matchPort
	 */
	@Autowired
	private MatchPort matchPort;

	@Autowired
	private RummySocketController socket;

	
	private IMatch match;
	private IPlayer player;
	private State currentState;

	private void attach(IMatch match) {
		this.mvcPort.subject(match.getId()).attach(this);
	}

	
	@PreDestroy
	private void detach() {
		if (this.match != null)
			this.mvcPort.subject(this.match.getId()).detach(this);
		this.clear();
	}
	
	
	private void clear() {
		this.match = null;
		this.player = null;
		this.currentState = null;
	}

	@Override
	public synchronized void update(State state) {
		this.currentState = state;
		this.socket.update("");
	}
	
	private String update(Model model) {
		if (State.S.Closed.isSuperStateOf(this.currentState)) {
			this.clear();
			return new QuitView().build(model);
		}
		if (this.currentState.isSubStateOf(State.S.Play))
			return new GameView(this.match, this.player, this.match.getHost().equals(this.player)).build(model);
		
		if (this.currentState.isSubStateOf(State.S.Join)) {
			if (this.match.getHost().equals(this.player))
				return new MatchView(this.match, this.player, this.match.enoughPlayers()).build(model);
			return new MatchView(this.match, this.player).build(model);
		}
		return new ErrorView(ErrorView.Error.UnknownState, this.currentState).build(model);
	}
		
	@RequestMapping(value = "/join", method = { RequestMethod.POST, RequestMethod.GET })
	public synchronized String joinOrCreateMatch(//
				@RequestParam(name = "name", required = false) String name,
				@RequestParam(name = "matchId", required = false) String matchId, Model model, HttpServletRequest request) {

			if (request.getMethod().equals("GET")) 
				return this.update(model);
			if (this.match != null) 
				return new ErrorView(ErrorView.Error.MatchExists, this.currentState).build(model);
			
			String playerName = this.mkName(name);
			int id = this.mkId(matchId);
			String page = (id == -1) ? this.createMatch(playerName, model) : this.joinMatch(playerName, id, model);			
			return page;
		}

	@RequestMapping(value = "/start", method = { RequestMethod.POST, RequestMethod.GET })
	public synchronized String start(Model model, HttpServletRequest request) {

		if (request.getMethod().equals("GET")) 
			return this.update(model);
		if (this.match == null) 
			return new ErrorView(ErrorView.Error.NoMatch, this.currentState).build(model);
		
		if (!this.matchPort.matchManagement().startGame(this.match)) 
			return new MatchView(this.match, this.player, this.match.enoughPlayers()).build(model);
		return new GameView(this.match, this.player, this.match.getHost().equals(this.player)).build(model);
	}

	@RequestMapping(value = "/discard/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public synchronized String discardCard(Model model, HttpServletRequest request, @PathVariable String id){
//		if (request.getMethod().equals("GET"))
//			return this.update(model);
		if(this.match == null)
			return new ErrorView(ErrorView.Error.NoMatch, this.currentState).build(model);
		this.player.discardCard(Integer.parseInt(id));
		return new GameView(this.match, this.player, this.match.getHost().equals(this.player)).build(model);
	}

	@RequestMapping(value = "/drawOpen", method = {RequestMethod.GET, RequestMethod.POST})
	public synchronized String drawOpen(Model model, HttpServletRequest request){
//		if (request.getMethod().equals("GET"))
//			return this.update(model);
		if(this.match == null)
			return new ErrorView(ErrorView.Error.NoMatch, this.currentState).build(model);
		this.player.drawOpen();
//		update(State.S.offeneKarteGezogen);
		return new GameView(this.match, this.player, this.match.getHost().equals(this.player)).build(model);
	}

	@RequestMapping(value = "/drawClosed", method = {RequestMethod.GET, RequestMethod.POST})
	public synchronized String drawClosed(Model model, HttpServletRequest request){
//		if (request.getMethod().equals("GET"))
//			return this.update(model);
		if(this.match == null)
			return new ErrorView(ErrorView.Error.NoMatch, this.currentState).build(model);
		this.player.drawClosed();
//		update(State.S.verdeckteKarteGezogen);
		return new GameView(this.match, this.player, this.match.getHost().equals(this.player)).build(model);
	}
	
		
	@RequestMapping(value = "/quit", method = { RequestMethod.POST, RequestMethod.GET })
	public synchronized String quit(Model model, HttpServletRequest request) {

		if (request.getMethod().equals("GET")) 
			return this.update(model);
		if (this.match == null) 
			return new ErrorView(ErrorView.Error.NoMatch, this.currentState).build(model);
		if (!this.matchPort.matchFactory().closeMatch(this.match)) 
			return new ErrorView(ErrorView.Error.ClosingFailed, this.currentState).build(model);
		this.clear();
		return new QuitView().build(model);
	}

	
	
	private String createMatch(String hostName, Model model) {
		this.match = this.matchPort.matchFactory().createMatch(hostName);
		if (this.match == null)
			return new ErrorView(ErrorView.Error.CreationFailed, this.currentState).build(model);
		this.player = this.match.getHost();
		this.attach(match);
		return new MatchView(this.match, this.player, this.match.enoughPlayers()).build(model);
	}

	private String joinMatch(String name, int matchId, Model model) {

		this.player = this.matchPort.matchFactory().mkPlayer(name, matchId);
		if (this.player == null)
			return new ErrorView(ErrorView.Error.JoiningFailed, this.currentState).build(model);
		this.match = this.player.currentMatch();
		this.attach(match);
		return new MatchView(this.match, this.player).build(model);
	}
	
	private int mkId(String matchId) {
		if (matchId == null || matchId.isBlank())
			return -1;
		return Integer.parseInt(matchId.trim());
	}

	private String mkName(String name) {
		String theName = name == null ? "" : name.trim();
		if (theName.length() > 20)
			theName = theName.substring(0, 20);
		if (theName.isBlank())
			theName = "???";
		return theName;
	}

	
		
	

}
