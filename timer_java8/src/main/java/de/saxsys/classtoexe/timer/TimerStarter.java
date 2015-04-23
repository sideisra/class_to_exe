package de.saxsys.classtoexe.timer;

import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import com.google.inject.Module;

import de.saxsys.classtoexe.timer.view.TimerView;
import de.saxsys.classtoexe.timer.viewmodel.TimerViewModel;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.ViewTuple;
import de.saxsys.mvvmfx.guice.MvvmfxGuiceApplication;


/**
 * Timer start class.
 */
public class TimerStarter extends MvvmfxGuiceApplication {
	public static void main(String... args) {
		Application.launch(args);
	}

	@Override
	public void initGuiceModules(List<Module> arg0) throws Exception {
	}

	@Override
	public void startMvvmfx(Stage stage) throws Exception {
		stage.setTitle("Timer");

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				Platform.exit();
				System.exit(0);
			}
		});

		ViewTuple<TimerView, TimerViewModel> viewTuple = FluentViewLoader.fxmlView(TimerView.class).load();

		Parent root = viewTuple.getView();
		stage.setScene(new Scene(root));
		stage.show();
	}
}
