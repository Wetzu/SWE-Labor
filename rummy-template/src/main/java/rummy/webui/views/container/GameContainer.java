package rummy.webui.views.container;

public class GameContainer {

	protected static final String PREFIX = "Game ";

	protected String label;

	public GameContainer(int i) {
		this.label = String.format("%s%d", GameContainer.PREFIX, i);
	}

	public String getLabel() {
		return this.label;
	}

}
