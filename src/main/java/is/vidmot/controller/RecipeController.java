package is.vidmot.controller;

import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import is.vidmot.view.RecipeSpjald;
import is.vinnsla.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * RecipeController
 */
public class RecipeController implements GognInterface {

  @FXML
  TextField heitiField;

  @FXML
  TextField afangastadurField;

  @FXML
  TextField dagsetningField;

  @FXML
  private RecipeSpjald fxFerdSpjald;

  @Override
  public void setGogn(Recipe f) {
    fxFerdSpjald.getHeitiProperty().bind(f.nameProperty());
    fxFerdSpjald.getAfangastadurProperty().bind(f.descriptionProperty());
    fxFerdSpjald.getDagsetningProperty().bind(f.difficultyProperty());
  }

  /**
   * Fara til baka í main view
   */
  @FXML
  private void fxOnBack() {
    ViewSwitcher.switchTo(View.MAIN);
  }
}
