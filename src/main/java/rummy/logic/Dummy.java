package rummy.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
@Component
public class Dummy {

	List<DummyObserver> obs = new ArrayList<>();

	private int value = 0;

	public synchronized void attach(DummyObserver obs) {
		if (this.obs.add(obs))
			obs.update(this.value);
	}

	public synchronized void detach(DummyObserver obs) {
		if (this.obs.remove(obs))
			obs.update(this.value);
	}

	public synchronized void incValue() {
		this.value++;
		this.obs.forEach(obs -> obs.update(this.value));
	}

}
