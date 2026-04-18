package is.vidmot.controller;

import is.vinnsla.DatabaseManager;
import is.vinnsla.RecipeManager;
import javafx.stage.Window;
import java.util.Optional;
import is.vidmot.switcher.View;
import is.vidmot.switcher.ViewSwitcher;
import is.vidmot.view.RecipeCard;
import is.vinnsla.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * RecipeController
 */
public class RecipeController implements DataInterface<Recipe> {

  @FXML
  private RecipeCard fxRecipeCard;

  @FXML
  private Button fxUpdateButton;

  private Recipe currentRecipe;

  @Override
  public void setGogn(Recipe recipe) {
    this.currentRecipe = recipe;
    fxRecipeCard.bindRecipe(recipe);
    fxRecipeCard.setRecipe(recipe);
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

  @FXML
  private void fxOnUpdate() {
    RecipeManager.getInstance().updateRecipe(currentRecipe, fxRecipeCard.getRecipe());
    DatabaseManager.updateRecipe(currentRecipe.getId(), fxRecipeCard.getRecipe());

    fxRecipeCard.setEditable(true);
    fxRecipeCard.show(true);
    fxUpdateButton.setOnAction(e -> fxOnUpdate());
  }
}
