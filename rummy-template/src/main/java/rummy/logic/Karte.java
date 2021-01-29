package rummy.logic;


public class Karte {
	
private KarteSymbol karteSymbol;
private KarteFarbe karteFarbe;


public KarteSymbol getKarteSymbol() {
	return this.karteSymbol;
}


public KarteFarbe getKarteFarbe() {
	return this.karteFarbe;
}

public String getStringRepresentation(){
	int cardValue = 0x1F000;
	cardValue += karteSymbol.getValue();
	cardValue += karteFarbe.getValue();
	return Character.toString(cardValue);
}




public  Karte(KarteSymbol karteSymbol, KarteFarbe karteFarbe) {
	this.karteSymbol= karteSymbol;
	this.karteFarbe = karteFarbe;
}



}