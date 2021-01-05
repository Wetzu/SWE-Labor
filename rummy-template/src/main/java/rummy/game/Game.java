package rummy.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import rummy.game.port.GamePort;
import rummy.layingoff.port.LayingOffInt;
import rummy.layingoff.port.LayingOffPort;
import rummy.maketurn.port.MakeTurnInt;
import rummy.maketurn.port.MakeTurnPort;
import rummy.meldingcards.port.MeldingCardsInt;
import rummy.meldingcards.port.MeldingCardsPort;

@ApplicationScope
@Component
public class Game implements GamePort  {
	
	@Autowired
	LayingOffPort loPort;
	
	@Autowired
	MakeTurnPort mtPort;
	
	@Autowired
	MeldingCardsPort mcPort;
	
	
	@Override
	public LayingOffInt layingOffInt() {
		return loPort.layingOffInt();
	}

	@Override
	public MakeTurnInt makeTurInt() {
		return mtPort.makeTurInt();
	}

	@Override
	public MeldingCardsInt meldingCardsInt() {
		
		return mcPort.meldingCardsInt();
	}
}
