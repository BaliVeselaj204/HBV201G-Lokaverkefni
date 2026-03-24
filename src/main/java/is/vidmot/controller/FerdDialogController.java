package is.vidmot.controller;

import is.vidmot.view.RecipeSpjald;
import is.vinnsla.Recipe;
import javafx.fxml.FXML;

public class FerdDialogController implements GognInterface {

  @FXML
  private RecipeSpjald fxFerdSpjald;

  private Recipe recipe;

  @Override
  public void setGogn(Recipe recipe) {
    this.recipe = recipe;
    fxFerdSpjald.setEditable(true);
    fxFerdSpjald.getHeitiProperty().bindBidirectional(recipe.nameProperty());
    fxFerdSpjald.getAfangastadurProperty().bindBidirectional(recipe.descriptionProperty());
    fxFerdSpjald.getDagsetningProperty().bindBidirectional(recipe.difficultyProperty());
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
    return fxFerdSpjald.getHeitiProperty().get().isBlank() ||
        fxFerdSpjald.getAfangastadurProperty().get().isBlank() ||
        fxFerdSpjald.getDagsetningProperty().get().isBlank();
  }
}
