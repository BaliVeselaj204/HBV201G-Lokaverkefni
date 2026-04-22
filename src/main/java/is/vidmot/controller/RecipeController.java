package is.vidmot.controller;

import is.vinnsla.DatabaseManager;
import is.vinnsla.RecipeManager;
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

  @FXML
  private Button fxOkButton;

  @FXML
  private Button fxBackButton;

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

  /**
   * Fall sem keyrist þegar valið er update á uppskrift
   */
  @FXML
  private void fxOnUpdate() {
    editFields(true);
  }

  /**
   * Fall sem keyrist þegar ýtt er ok eftir uppfært er uppskrift
   */
  @FXML
  private void fxOnOk() {
    RecipeManager.getInstance().updateRecipe(currentRecipe, fxRecipeCard.getRecipe());
    DatabaseManager.updateRecipe(currentRecipe.getId(), fxRecipeCard.getRecipe());

    editFields(false);
  }

  /**
   * Gera fields editable sýnilega þegar valið er uppfæra uppskrift
   * 
   * @param state
   */
  private void editFields(boolean state) {
    fxRecipeCard.setEditable(state);
    fxRecipeCard.show(state);
    fxOkButton.setVisible(state);
    fxOkButton.setManaged(state);
    fxUpdateButton.setVisible(!state);
    fxBackButton.setDisable(state);
  }
}
