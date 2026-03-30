package is.vidmot.view;

import java.io.IOException;

import is.vinnsla.Recipe;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

public class RecipeCard extends VBox {

  @FXML
  private ImageView recipeImage;

  @FXML
  private Button imageButton;

  @FXML
  private TextField nameField;

  @FXML
  private TextField servingsField;

  @FXML
  private TextField cookTimeField;

  @FXML
  private TextField caloriesField;

  @FXML
  private TextField proteinField;

  @FXML
  private TextField carbsField;

  @FXML
  private TextField fatField;

  @FXML
  private ComboBox<String> diffComboBox;

  @FXML
  private TextArea descriptionArea;

  private final NumberStringConverter converter = new NumberStringConverter();

  public RecipeCard() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/is/vidmot/recipe-card.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    try {
      fxmlLoader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    diffComboBox.getItems().addAll("Easy", "Medium", "Hard");
  }

  public void bindRecipe(Recipe recipe) {
    nameField.textProperty().bindBidirectional(recipe.nameProperty());
    servingsField.textProperty().bindBidirectional(recipe.servingsProperty(), converter);
    cookTimeField.textProperty().bindBidirectional(recipe.cookTimeProperty(), converter);
    caloriesField.textProperty().bindBidirectional(recipe.caloriesProperty(), converter);
    proteinField.textProperty().bindBidirectional(recipe.proteinProperty(), converter);
    carbsField.textProperty().bindBidirectional(recipe.carbsProperty(), converter);
    fatField.textProperty().bindBidirectional(recipe.fatProperty(), converter);
    descriptionArea.textProperty().bindBidirectional(recipe.descriptionProperty());
    diffComboBox.valueProperty().bindBidirectional(recipe.difficultyProperty());
  }

  public void setEditable(boolean editable) {
    nameField.setEditable(editable);
    servingsField.setEditable(editable);
    cookTimeField.setEditable(editable);
    caloriesField.setEditable(editable);
    proteinField.setEditable(editable);
    carbsField.setEditable(editable);
    fatField.setEditable(editable);
    descriptionArea.setEditable(editable);
    diffComboBox.setEditable(editable);
  }
}
