package rummy.webui.views;

import org.springframework.ui.Model;

public class QuitView implements IView {

	private static final String TEMPLATE_NAME = "quit";

	@Override
	public String build(Model model) {
		return QuitView.TEMPLATE_NAME;
	}
}
