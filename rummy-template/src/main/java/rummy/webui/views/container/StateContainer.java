package rummy.webui.views.container;

public class StateContainer {

	boolean canStop;
	boolean canStart;

	public StateContainer(boolean canStop, boolean canStart) {
		this.canStart = canStart;
		this.canStop = canStop;

	}

	public boolean isCanStop() {
		return this.canStop;
	}

	public boolean isCanStart() {
		return this.canStart;
	}

}
