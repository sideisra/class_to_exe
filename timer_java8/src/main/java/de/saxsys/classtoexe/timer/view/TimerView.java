package de.saxsys.classtoexe.timer.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import de.saxsys.classtoexe.timer.viewmodel.TimerViewModel;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;

public class TimerView implements FxmlView<TimerViewModel>, Initializable {

	private final class AllowNumbersOnlyListener implements
			ChangeListener<String> {

		private final TextField observedText;

		private AllowNumbersOnlyListener(TextField observedText) {
			super();
			this.observedText = observedText;
			this.observedText.textProperty().addListener(this);
		}

		@Override
		public void changed(ObservableValue<? extends String> arg0,
				String oldText, String newText) {
			if (!newText.matches("\\d*")
					|| (!newText.isEmpty() && Integer.parseInt(newText) > 59)) {
				observedText.setText(oldText);
			}
		}
	}

	@InjectViewModel
	private TimerViewModel timerViewModel;
	
	@FXML
	private TextField minutesInput;

	@FXML
	private TextField secondsInput;

	@FXML
	private Button startStop;

	private final StringConverter<? extends Number> converter = new IntegerStringConverter();

	@Override
	public void initialize(URL url, ResourceBundle resBundle) {
		valueBindings();
		actionBindings();
		enableDisableBindings();
		setEventFilter();
	}

	private void setEventFilter() {
		new AllowNumbersOnlyListener(minutesInput);
		new AllowNumbersOnlyListener(secondsInput);
	}

	private void enableDisableBindings() {
		minutesInput.disableProperty().bind(timerViewModel.running());
		secondsInput.disableProperty().bind(timerViewModel.running());
		timerViewModel.running().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldValue, Boolean newValue) {
				if (newValue) {
					startStop.setText("Stop");
				} else {
					startStop.setText("Start");
				}
			}
		});
	}

	private void actionBindings() {
		startStop.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				timerViewModel.startStop();
			}
		});
	}

	private void valueBindings() {
		Bindings.bindBidirectional(minutesInput.textProperty(), timerViewModel
				.minutes(), (StringConverter<Number>) converter);
		Bindings.bindBidirectional(secondsInput.textProperty(), timerViewModel
				.seconds(), (StringConverter<Number>) converter);
	}

}
