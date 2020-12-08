package rummy.socketmanagement;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class RummySocketController {

	private SimpMessagingTemplate simp;
	private static final String WS_MESSAGE_TRANSFER_DESTINATION = "/update_rummy";

	public RummySocketController(SimpMessagingTemplate simp) {
		this.simp = simp;
	}

	public void update(String matchId) {
		try {
			this.simp.convertAndSend(RummySocketController.WS_MESSAGE_TRANSFER_DESTINATION, matchId);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
