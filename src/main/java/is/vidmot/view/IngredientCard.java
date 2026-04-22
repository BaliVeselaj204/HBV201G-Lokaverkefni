package is.vidmot.view;

import java.io.IOException;

import is.vidmot.FieldFormatter;
import is.vinnsla.Ingredient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * IngredientCard
 */
public class IngredientCard extends VBox {

  @FXML
  private TextField fxNameField;

  @FXML
  private TextField fxAmountField;

  @FXML
  private TextField fxUnitField;

  public IngredientCard() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/is/vidmot/ingredient-card.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    try {
      fxmlLoader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Búa til bindingu fyrir hráefni
   * 
   * @param ingredient
   */
  public void bindIngredient(Ingredient ingredient) {
    FieldFormatter formatter = new FieldFormatter();
    fxNameField.textProperty().bindBidirectional(ingredient.nameProperty());
    fxUnitField.textProperty().bindBidirectional(ingredient.unitPropery());
    formatter.bindDoubleField(fxAmountField, ingredient.amountPropery());
  }
}
