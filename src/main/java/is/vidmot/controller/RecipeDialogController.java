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
    fxRecipeCard.setEditable(true);
    fxRecipeCard.show(true);
    fxRecipeCard.bindRecipe(recipe);
  }

  public Recipe getRecipe() {
    return recipe;
  }

  /**
   * @return
   *
   *         Skoðar hvort TextField sé tómt þegar við búum til nýjar uppskriftir
   */
  public boolean erTomur() {
    return recipe.getName() == null || recipe.getName().isBlank()
        || recipe.getServings() <= 0
        || recipe.getCookTime() <= 0;
  }
}
