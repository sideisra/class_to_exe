package de.saxsys.classtoexe.timer.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import de.saxsys.jfx.mvvm.base.viewmodel.ViewModel;

public class TimerViewModel implements ViewModel {
	private final StringProperty helloMessage = new SimpleStringProperty(
			"Hello World!");

	public StringProperty helloMessage() {
		return helloMessage;
	}

	public String getHelloMessage() {
		return helloMessage.get();
	}

	public void setHelloMessage(String message) {
		helloMessage.set(message);
	}
}
