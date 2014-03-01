package de.saxsys.classtoexe.timer.viewmodel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;

public class TimerViewModel implements ViewModel {
	private final IntegerProperty minutes = new SimpleIntegerProperty(1);
	private final IntegerProperty seconds = new SimpleIntegerProperty(0);
	private final BooleanProperty running = new SimpleBooleanProperty(false);

	private ScheduledExecutorService schedule;

	public TimerViewModel() {
		running.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldValue, Boolean newValue) {
				if (newValue) {
					startTimer();
				} else {
					stopTimer();
				}
			}

		});
	}

	private void stopTimer() {
		schedule.shutdown();
	}

	private void startTimer() {
		schedule = Executors.newScheduledThreadPool(1);
		schedule.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						ping();
					}
				});
			}

		}, 0, 1, TimeUnit.SECONDS);

	}

	void ping() {
		int sec = seconds.get();
		int min = minutes.get();
		sec--;
		if (sec <= 0 && min <= 0) {
			running.set(false);
			sec = 0;
			min = 0;
		} else {
			if (sec < 0 && min > 0) {
				min--;
				sec = 59;
			}
		}
		seconds.set(sec);
		minutes.set(min);
	}

	public int getMinutes() {
		return minutes.get();
	}

	public void setMinutes(int minutesValue) {
		minutes.set(minutesValue);
	}

	public IntegerProperty minutes() {
		return minutes;
	}

	public int getSeconds() {
		return seconds.get();
	}

	public void setSeconds(int secondsValue) {
		seconds.set(secondsValue);
	}

	public IntegerProperty seconds() {
		return seconds;
	}

	public void startStop() {
		running.set(!running.get());
	}

	public BooleanProperty running() {
		return running;
	}

	public boolean isRunning() {
		return running.get();
	}
}
