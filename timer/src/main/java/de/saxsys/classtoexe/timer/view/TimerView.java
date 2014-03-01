package de.saxsys.classtoexe.timer.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import de.saxsys.classtoexe.timer.viewmodel.TimerViewModel;
import de.saxsys.jfx.mvvm.base.view.View;

public class TimerView extends View<TimerViewModel> {

	@FXML
	private Label helloLabel;

	@Override
	public void initialize(URL url, ResourceBundle resBundle) {
		helloLabel.textProperty().bind(getViewModel().helloMessage());
	}

}
