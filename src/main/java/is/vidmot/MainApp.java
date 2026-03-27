package is.vidmot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;

public class MainApp extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    Scene scene = new Scene(new Pane(), 900, 700);
    ViewSwitcher.setScene(scene);
    ViewSwitcher.switchTo(View.MAIN);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.setTitle("Recipe Manager");
    stage.show();
  }
}
