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
    fxRecipeCard.getHeitiProperty().bindBidirectional(recipe.nameProperty());
    fxRecipeCard.getAfangastadurProperty().bindBidirectional(recipe.descriptionProperty());
    fxRecipeCard.getDagsetningProperty().bindBidirectional(recipe.difficultyProperty());
  }

  public Recipe getRecipe() {
    return recipe;
  }

  /**
   * @return
   *
   *         Skoðar hvort TextField sé tómt þegar við búum til nýjar ferðir
   */
  public boolean erTomur() {
    return fxRecipeCard.getHeitiProperty().get().isBlank() ||
        fxRecipeCard.getAfangastadurProperty().get().isBlank() ||
        fxRecipeCard.getDagsetningProperty().get().isBlank();
  }
}
