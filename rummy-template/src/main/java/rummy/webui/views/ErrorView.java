package rummy.webui.views;

import org.springframework.ui.Model;

import rummy.statemachine.port.State;


public class ErrorView implements IView {

	private static final String TEMPLATE_NAME = "error";
	private static final String ERROR_ATTR = "error";
	
	private State state;
	private Error error;
	private Model model;
	
	public ErrorView(Error error, State state) {
		this.error = error;
		this.state = state;
	}
	
	@Override
	public String build(Model model) {
		this.model = model;
		this.model.addAttribute(ErrorView.ERROR_ATTR, String.format("%s State: %s", this.error.msg(), (this.state == null) ? "No State": this.state.toString()));
		
		return ErrorView.TEMPLATE_NAME;
	}
	
	
	public static enum Error{
		
		MatchExists("You are currently in a running match so you cannot create a or join a new one."), //
		NoMatch("Before you can start or close a game, you must create a new one."), //
		CreationFailed("Creation failed, try again!"), //
		JoiningFailed("Impossible to join, try another match!"), //
		ClosingFailed("Impossible to close, try again!"), //
		UnknownState("Unexpected State!"); //

		private String str;

		private Error(String str) {
			this.str = str;
		}
		
		public String msg() {
			return this.str;
		}
		
		
	}
	
	

}
