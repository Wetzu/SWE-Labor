package rummy.matchcenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import rummy.matchcenter.impl.MatchFactoryImpl;
import rummy.matchcenter.impl.MatchMangementImpl;
import rummy.matchcenter.port.IMatch;
import rummy.matchcenter.port.IPlayer;
import rummy.matchcenter.port.MatchFactory;
import rummy.matchcenter.port.MatchManagement;
import rummy.matchcenter.port.MatchManagementPort;
import rummy.statemachine.port.State;
import rummy.statemachine.port.StateMachine;
import rummy.statemachine.port.StateMachinePort;

@ApplicationScope
@Component
class MatchCenterImpl implements MatchManagementPort, MatchFactory, MatchManagement {

	/**
	 * @directed true
	 * @link composition
	 */
	private MatchMangementImpl matchMangement;
	/**
	 * @directed true
	 * @link composition
	 */

	private MatchFactoryImpl matchFactory;

	@Autowired
	private StateMachinePort stateMachinePort;

	@Override
	public synchronized MatchFactory matchFactory() {
		if (this.matchFactory == null)
			this.matchFactory = new MatchFactoryImpl(this.stateMachinePort);
		return this;
	}

	@Override
	public synchronized MatchManagement matchManagement() {
		if (this.matchMangement == null)
			this.matchMangement = new MatchMangementImpl(this.stateMachinePort);
		return this;
	}

	@Override
	public synchronized IPlayer mkPlayer(String name, int id) {
		
		StateMachine stateMachine = this.stateMachinePort.getMachine(id);
		if (stateMachine == null)
			return null;
		State currentState = stateMachine.getState();
		if (State.S.MustJoin.isSuperStateOf(currentState) || State.S.JoinOrStart.isSuperStateOf(currentState))
			return this.matchFactory.mkPlayer(name, id);
		return null;
	}

	@Override
	public synchronized IMatch createMatch(String hostingPlayer) {
		return this.matchFactory.createMatch(hostingPlayer);
	}

	@Override
	public synchronized boolean startGame(IMatch match) {
		int idx = match == null ? -1 : match.getId();
		StateMachine stateMachine = this.stateMachinePort.getMachine(idx);
		if (stateMachine == null)
			return false;
		if (State.S.CanStart.isSuperStateOf(stateMachine.getState())){
			match.StartGame();
			return this.matchMangement.startGame(match);
		}
		return false;
	}

	public synchronized boolean drawOpen(IMatch match){
		int idx = match == null ? -1 : match.getId();
		StateMachine stateMachine = this.stateMachinePort.getMachine(idx);
		if (stateMachine == null)
			return false;
		if (State.S.zugStart.isSuperStateOf(stateMachine.getState())){
			match.getHost();
			return this.matchMangement.startGame(match);
		}
		return false;
	}
	
	@Override
	public synchronized boolean closeMatch(IMatch match) {
		int idx = match == null ? -1 : match.getId();
		StateMachine stateMachine = this.stateMachinePort.getMachine(idx);
		if (stateMachine == null)
			return false;
		State currentState = stateMachine.getState();
		if (State.S.Join.isSuperStateOf(currentState) || State.S.Play.isSuperStateOf(currentState))
			return this.matchFactory.closeMatch(match);
		return false;
	}

}
