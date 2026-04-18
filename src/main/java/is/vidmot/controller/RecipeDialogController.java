package is.vidmot.controller;

import is.vidmot.view.RecipeCard;
import is.vinnsla.Recipe;
import javafx.fxml.FXML;

public class RecipeDialogController implements DataInterface<Recipe> {

  @FXML
  private RecipeCard fxRecipeCard;

  private Recipe recipe;

  @Override
  public void setGogn(Recipe recipe) {
    this.recipe = recipe;
    fxRecipeCard.setRecipe(recipe);
    fxRecipeCard.setEditable(true);
    fxRecipeCard.show(true);
  }

  public Recipe getRecipe() {
    return recipe;
  }

  @FXML
  private void initialize() {
    fxRecipeCard.getImageView().setFitWidth(150);
    fxRecipeCard.getImageView().setFitHeight(150);
    fxRecipeCard.getImageView().setPreserveRatio(true);
  }

  /**
   * @return
   *
   *         Skoðar hvort TextField sé tómt þegar við búum til nýjar uppskriftir
   */
  public boolean isEmpty() {
    return fxRecipeCard.isEmpty();
  }
}
