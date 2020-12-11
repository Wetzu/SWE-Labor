package rummy.webui.views;

import org.springframework.ui.Model;

public class View {

	private static final String VALUE_ATTR = "value";
	private static final String WEB_TEMPLATE = "template";

	private String value = "";

	public View(int value) {
		this.value = String.format("Value is: %d", value);
	}

	public String update(Model model) {
		model.addAttribute(View.VALUE_ATTR, this.value);
		return View.WEB_TEMPLATE;
	}

}