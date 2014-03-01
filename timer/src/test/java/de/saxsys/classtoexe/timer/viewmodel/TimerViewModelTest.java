package de.saxsys.classtoexe.timer.viewmodel;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class TimerViewModelTest {

	private final TimerViewModel cut = new TimerViewModel();

	@Test
	public void testHelloMessage() {
		assertThat(cut.helloMessage()).isNotNull();
	}

	@Test
	public void testGetHelloMessage() {
		assertThat(cut.getHelloMessage()).isEqualTo("Hello World!");
	}

	@Test
	public void testSetHelloMessage() {
		cut.setHelloMessage("Test Hello!");
		assertThat(cut.getHelloMessage()).isEqualTo("Test Hello!");
	}

}
