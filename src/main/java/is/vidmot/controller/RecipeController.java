package is.vidmot.controller;

import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import is.vidmot.view.RecipeCard;
import is.vinnsla.Recipe;
import javafx.fxml.FXML;

/**
 * RecipeController
 */
public class RecipeController implements DataInterface {

  @FXML
  private RecipeCard fxRecipeCard;

  @Override
  public void setGogn(Recipe recipe) {
    fxRecipeCard.bindRecipe(recipe);
    fxRecipeCard.setIngredients(recipe);
    fxRecipeCard.setEditable(false);
    fxRecipeCard.show(false);
  }

  /**
   * Fara til baka í main view
   */
  @FXML
  private void fxOnBack() {
    ViewSwitcher.switchTo(View.MAIN);
  }
}
