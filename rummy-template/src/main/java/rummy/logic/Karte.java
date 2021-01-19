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




public  Karte(KarteSymbol karteSymbol, KarteFarbe karteFarbe) {
	this.karteSymbol= karteSymbol;
	this.karteFarbe = karteFarbe;
}



}