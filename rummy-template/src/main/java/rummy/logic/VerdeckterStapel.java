package rummy.logic;

import rummy.logic.Karte;
import rummy.logic.KarteFarbe;
import rummy.logic.KarteSymbol;
import rummy.logic.Stapel;
import java.util.ArrayList;
import java.util.Collections;

public class VerdeckterStapel extends Stapel{

	private ArrayList<Karte> karten;


	public VerdeckterStapel() {
		Init();
	}
	
	public Karte GetCard() {
		return karten.remove(karten.size() - 1);
	}

	private void Init(){
		karten = new ArrayList<Karte>(104);
		for (KarteSymbol wert : KarteSymbol.values()) {
			for (KarteFarbe farbe : KarteFarbe.values()) {
				Karte karte = new Karte(wert, farbe);
				karten.add(karte);
				karten.add(karte);
			}
		}
		Collections.shuffle(karten);
	}
}