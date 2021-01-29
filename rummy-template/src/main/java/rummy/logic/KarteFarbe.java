package rummy.logic;

public enum KarteFarbe {
	Herz(0xB0), Pik(0xA0), Kreuz(0xD0), Karo(0xC0);

	private int value;

	KarteFarbe(int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}
}
