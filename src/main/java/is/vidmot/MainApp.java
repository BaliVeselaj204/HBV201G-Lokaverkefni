package is.vidmot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import is.vinnsla.DatabaseManager;
import is.vinnsla.RecipeManager;

public class MainApp extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    Scene scene = new Scene(new Pane(), 900, 800);
    ViewSwitcher.setScene(scene);
    ViewSwitcher.switchTo(View.MAIN);
    stage.setScene(scene);
    stage.setResizable(false);
    stage.setTitle("Recipe Manager");
    stage.show();

    DatabaseManager.initialize();
    DatabaseManager.loadAllRecipes().forEach(recipe -> RecipeManager.getInstance().newRecipe(recipe));
  }
}
