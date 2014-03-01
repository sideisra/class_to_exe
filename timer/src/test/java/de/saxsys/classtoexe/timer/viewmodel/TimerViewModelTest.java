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
	public void testGetMinutes() {
		assertThat(cut.getMinutes()).isEqualTo(1);
	}

	@Test
	public void testSetMinutes() {
		cut.setMinutes(2);
		assertThat(cut.getMinutes()).isEqualTo(2);
	}

	@Test
	public void testSeconds() {
		assertThat(cut.seconds()).isNotNull();
	}

	@Test
	public void testGetSeconds() {
		assertThat(cut.getSeconds()).isEqualTo(0);
	}

	@Test
	public void testSetSeconds() {
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
	public void testStartStop() {
		assertThat(cut.isRunning()).isEqualTo(false);
		cut.startStop();
		assertThat(cut.isRunning()).isEqualTo(true);
		cut.startStop();
		assertThat(cut.isRunning()).isEqualTo(false);
	}
}
