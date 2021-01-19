package rummy.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import rummy.logic.port.MVCPort;
import rummy.logic.port.MatchPort;
import rummy.maketurn.port.MakeTurnInt;
import rummy.maketurn.port.MakeTurnPort;
import rummy.matchcenter.port.MatchFactory;
import rummy.matchcenter.port.MatchManagement;
import rummy.matchcenter.port.MatchManagementPort;
import rummy.statemachine.port.Subject;
import rummy.statemachine.port.SubjectPort;

@ApplicationScope
@Component
public class LogicImpl implements MatchPort, MVCPort {


	/**
	 * @directed true
	 * @link aggregation
	 * @supplierNavigability NAVIGABLE_EXPLICITLY
	 * @supplierRole subjectPort
	 */

	@Autowired
	private SubjectPort subjectPort;
	/**
	 * @directed true
	 * @link aggregation
	 * @supplierRole matchManagementPort
	 * @supplierVisibility private
	 */

	@Autowired
	private MatchManagementPort matchManagementPort;
	
	
	@Autowired
	MakeTurnPort mtPort;
	
    @Override
	public MakeTurnInt makeTurnInt() {
		return mtPort.makeTurnInt();
	}
	@Override
	public MatchFactory matchFactory() {
		return this.matchManagementPort.matchFactory();
	}

	@Override
	public MatchManagement matchManagement() {
		return this.matchManagementPort.matchManagement();
	}
	
	
	@Override
	public synchronized Subject subject(int id) {
		return this.subjectPort.subject(id);
	};
	
	

}
