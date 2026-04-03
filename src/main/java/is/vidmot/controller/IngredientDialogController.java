package is.vidmot.controller;

import is.vidmot.view.IngredientCard;
import is.vinnsla.Ingredient;
import javafx.fxml.FXML;

/**
 * IngredientDialogController
 */
public class IngredientDialogController implements DataInterface<Ingredient> {

  @FXML
  private IngredientCard fxIngredientCard;

  private Ingredient ingredient;

  @Override
  public void setGogn(Ingredient ingredient) {
    fxIngredientCard.bindIngredient(ingredient);
  }

  public Ingredient getIngredient() {
    return ingredient;
  }

  public boolean isEmpty() {
    return ingredient.getName() == null || ingredient.getName().isBlank() || ingredient.getAmount() <= 0
        || ingredient.getUnit() == null || ingredient.getUnit().isBlank();
  }
}
