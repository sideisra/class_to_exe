package de.saxsys.classtoexe.timer;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import de.saxsys.classtoexe.timer.view.TimerView;
import de.saxsys.classtoexe.timer.viewmodel.TimerViewModel;
import de.saxsys.jfx.mvvm.viewloader.ViewLoader;
import de.saxsys.jfx.mvvm.viewloader.ViewTuple;

/**
 * Hello world!
 * 
 */
public class TimerStarter extends Application {
	public static void main(String... args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		stage.setTitle("Hello World Application");

		ViewLoader viewLoader = new ViewLoader();

		ViewTuple<TimerViewModel> viewTuple = viewLoader
				.loadViewTuple(TimerView.class);

		Parent root = viewTuple.getView();
		stage.setScene(new Scene(root));
		stage.show();
	}
}
