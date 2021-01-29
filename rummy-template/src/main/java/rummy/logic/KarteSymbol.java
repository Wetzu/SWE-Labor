package rummy.logic;

public enum KarteSymbol {
	Ass(0x01), Zwei(0x02), Drei(0x03), Vier(0x04), Fünf(0x05), Sechs(0x06), Sieben(0x07), Acht(0x08), Neun(0x09), Zehn(0x0A), Bube(0x0B), Dame(0x0D), König(0x0E);

	private int value;

	KarteSymbol(int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}
}
