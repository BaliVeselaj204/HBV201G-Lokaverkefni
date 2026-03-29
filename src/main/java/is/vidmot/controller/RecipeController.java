package is.vidmot.controller;

import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import is.vidmot.view.RecipeCard;
import is.vinnsla.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * RecipeController
 */
public class RecipeController implements GognInterface {

  @FXML
  TextField nameField;

  @FXML
  TextField servingsField;

  @FXML
  TextField cookTimeField;

  @FXML
  private RecipeCard fxRecipeCard;

  @Override
  public void setGogn(Recipe recipe) {
    fxRecipeCard.getNamePropery().bind(recipe.nameProperty());
    fxRecipeCard.getServingsProperty().bind(recipe.servingsProperty());
    fxRecipeCard.getCookTimePropery().bind(recipe.cookTimeProperty());
  }

  /**
   * Fara til baka í main view
   */
  @FXML
  private void fxOnBack() {
    ViewSwitcher.switchTo(View.MAIN);
  }
}
