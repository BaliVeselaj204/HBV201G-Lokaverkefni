package is.vidmot.controller;

import is.vidmot.view.RecipeCard;
import is.vinnsla.Recipe;
import javafx.fxml.FXML;

public class RecipeDialogController implements GognInterface {

  @FXML
  private RecipeCard fxRecipeCard;

  private Recipe recipe;

  @Override
  public void setGogn(Recipe recipe) {
    this.recipe = recipe;
    fxRecipeCard.setEditable(true);
    fxRecipeCard.getNamePropery().bindBidirectional(recipe.nameProperty());
    fxRecipeCard.getServingsProperty().bindBidirectional(recipe.servingsProperty());
    fxRecipeCard.getCookTimePropery().bindBidirectional(recipe.cookTimeProperty());
  }

  public Recipe getRecipe() {
    return recipe;
  }

  /**
   * @return
   *
   *         Skoðar hvort TextField sé tómt þegar við búum til nýjar ferðir
   */
  // public boolean erTomur() {
  // return fxRecipeCard.getNamePropery().get().isBlank() ||
  // fxRecipeCard.getServingsProperty().get().isBlank() ||
  // fxRecipeCard.getCookTimePropery().get().isBlank();
  // }

  public boolean erTomur() {
    return fxRecipeCard.getNamePropery().get().isBlank();
  }
}
