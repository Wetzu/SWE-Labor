package rummy.webui;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.annotation.SessionScope;

import rummy.logic.Dummy;
import rummy.logic.DummyObserver;
import rummy.socketmanagement.RummySocketController;
import rummy.webui.views.View;

@SessionScope
@Controller
@RequestMapping("/rummy")
public class MainController implements DummyObserver {

	@Autowired
	private Dummy dummy;

	@Autowired
	private RummySocketController socket;

	private int value;

	@PostConstruct
	private void attach() {
		this.dummy.attach(this);
	}

	@PreDestroy
	private void detach() {
		this.dummy.detach(this);
	}

	@Override
	public synchronized void update(int val) {
		this.value = val;
		this.socket.update("");
	}

	@RequestMapping(value = "/doIt", method = { RequestMethod.POST, RequestMethod.GET })
	public synchronized String doIt(//
			String matchId, HttpServletRequest request, Model model) {

		if (request.getMethod().equals("POST"))  // Unterscheidung zwischen aktivem Verändern (POST)
			this.dummy.incValue();               // und dem durch die Veränderung angestoßenen GET
		return new View(this.value).update(model);
	}

}
