package rummy.webui.views.container;

public class ResultContainer {

	protected int score;
	protected int pips;

	public ResultContainer(int score, int pips) {
		this.score = score;
		this.pips = pips;
	}

	public String getScore() {
		return "" + this.score;

	}

	public String getPips() {
		return "" + this.pips;

	}
}