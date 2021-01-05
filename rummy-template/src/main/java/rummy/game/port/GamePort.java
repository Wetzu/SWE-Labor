package rummy.game.port;

import rummy.layingoff.port.LayingOffInt;
import rummy.maketurn.port.MakeTurnInt;
import rummy.meldingcards.port.MeldingCardsInt;

public interface GamePort {
	
	
	LayingOffInt layingOffInt();
	MakeTurnInt makeTurInt();
	MeldingCardsInt meldingCardsInt();

}
