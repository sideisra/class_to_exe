package de.saxsys.classtoexe.timer.viewmodel;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.inject.Inject;

import de.saxsys.mvvmfx.ViewModel;


public class TimerViewModel implements ViewModel {
	private final IntegerProperty minutes = new SimpleIntegerProperty(1);
	private final IntegerProperty seconds = new SimpleIntegerProperty(0);
	private final BooleanProperty running = new SimpleBooleanProperty(false);

	@Inject
	private Pinger pinger;

	public TimerViewModel() {
		running.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldValue, Boolean newValue) {
				if (newValue) {
					pinger.startTimer(new Runnable() {

						@Override
						public void run() {
							Platform.runLater(new Runnable() {

								@Override
								public void run() {
									ping();
								}
							});
						}

					});
				} else {
					pinger.stopTimer();
				}
			}

		});
	}

	private void stopTimer() {
		running.set(false);
	}

	private void startTimer() {
		running.set(true);
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
		if (isRunning()) {
			stopTimer();
		} else {
			startTimer();
		}
	}

	public ReadOnlyBooleanProperty running() {
		return running;
	}

	public boolean isRunning() {
		return running.get();
	}
}
