package de.saxsys.classtoexe.timer.viewmodel;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class TimerViewModelTest {

	private final TimerViewModel cut = new TimerViewModel();

	@Test
	public void testMinutes() {
		assertThat(cut.minutes()).isNotNull();
	}

	@Test
	public void testGetSetMinutes() {
		assertThat(cut.getMinutes()).isEqualTo(1);
		cut.setMinutes(2);
		assertThat(cut.getMinutes()).isEqualTo(2);
	}

	@Test
	public void testSeconds() {
		assertThat(cut.seconds()).isNotNull();
	}

	@Test
	public void testGetSetSeconds() {
		assertThat(cut.getSeconds()).isEqualTo(0);
		cut.setSeconds(3);
		assertThat(cut.getSeconds()).isEqualTo(3);
	}

	@Test
	public void testRunning() {
		assertThat(cut.running()).isNotNull();
	}

	@Test
	public void testIsRunning() {
		assertThat(cut.isRunning()).isEqualTo(false);
	}

	@Test
	public void testPingMinuteSwitch() {
		cut.running().set(true);
		cut.minutes().set(1);
		cut.seconds().set(1);
		cut.ping();
		assertThat(cut.getMinutes()).isEqualTo(1);
		assertThat(cut.getSeconds()).isEqualTo(0);
		cut.ping();
		assertThat(cut.getMinutes()).isEqualTo(0);
		assertThat(cut.getSeconds()).isEqualTo(59);
		assertThat(cut.isRunning()).isEqualTo(true);
	}

	@Test
	public void testPingEnd() {
		cut.minutes().set(0);
		cut.seconds().set(1);
		cut.running().set(true);
		cut.ping();
		assertThat(cut.getMinutes()).isEqualTo(0);
		assertThat(cut.getSeconds()).isEqualTo(0);
		assertThat(cut.isRunning()).isEqualTo(false);
	}

	@Test
	public void testStartStop() {
		assertThat(cut.isRunning()).isEqualTo(false);
		cut.startStop();
		assertThat(cut.isRunning()).isEqualTo(true);
		cut.startStop();
		assertThat(cut.isRunning()).isEqualTo(false);
	}
}
