package rummy.meldingcards;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import rummy.meldingcards.impl.MeldingCardsIntImpl;
import rummy.meldingcards.port.MeldingCardsInt;
import rummy.meldingcards.port.MeldingCardsPort;

@ApplicationScope
@Component
public class MeldingCard implements MeldingCardsPort, MeldingCardsInt {

	private MeldingCardsIntImpl mciImpl;
	
	public void auslegen() {
		this.mciImpl.auslegen();
	}
	
	@Override
	public MeldingCardsInt meldingCardsInt() {
		// TODO Auto-generated method stubs
		return null;
	}
}
