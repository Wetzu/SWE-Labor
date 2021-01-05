package rummy.layingoff;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import rummy.layingoff.impl.LayingOffIntImpl;
import rummy.layingoff.port.LayingOffInt;
import rummy.layingoff.port.LayingOffPort;
import rummy.maketurn.impl.MakeTurnIntImpl;

@ApplicationScope
@Component
public class LayingOff implements LayingOffPort, LayingOffInt{

	private LayingOffIntImpl loiImpl;
	
	public void karteAnlegen() {
		this.loiImpl.karteAnlegen();
	}
	
	public void jokerAustauschen() {
		this.loiImpl.jokerAustauschen();
		}
	
	@Override
	public LayingOffInt layingOffInt() {
		// TODO Auto-generated method stub
		return null;
	}
}
