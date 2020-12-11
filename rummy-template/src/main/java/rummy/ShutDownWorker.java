package rummy;

import javax.annotation.PostConstruct;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShutDownWorker implements Runnable {

	@PostConstruct
	public void init() {
		System.out.println("\nNew ShutdownHook established!\n");
		Runtime.getRuntime().addShutdownHook(new Thread(this));
	}

	@Override
	public void run() {
		System.out.println("Run ShutdownHook!");

		// close external resources

		System.out.println("\nShutdownHook finished!");
	}

}
