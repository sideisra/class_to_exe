package de.saxsys.classtoexe.timer.viewmodel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Pinger {
	private ScheduledExecutorService schedule;

	public void stopTimer() {
		schedule.shutdown();
	}

	public void startTimer(Runnable callback) {
		schedule = Executors.newScheduledThreadPool(1);
		schedule.scheduleAtFixedRate(callback, 0, 1, TimeUnit.SECONDS);
	}
}
