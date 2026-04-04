package is.vidmot.view;

import java.io.IOException;

import java.util.Optional;
import is.vidmot.controller.IngredientDialogWrapper;
import is.vinnsla.FieldFormatter;
import is.vinnsla.Ingredient;
import is.vinnsla.Recipe;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Window;

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

  @FXML
  private Label fxDiffLabel;

  @FXML
  private ListView<Ingredient> fxIngredientsListView;

  @FXML
  private Button fxAddIngredientButton;

  private Recipe recipe;

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

  public void setRecipe(Recipe recipe) {
    this.recipe = recipe;
    bindRecipe(recipe);
    fxIngredientsListView.setItems(recipe.getIngredientsList());
    System.out.println("setRecipe called with: " + recipe);
  }

  public void bindRecipe(Recipe recipe) {
    FieldFormatter formatter = new FieldFormatter();
    nameField.textProperty().bindBidirectional(recipe.nameProperty());
    formatter.bindIntegerField(servingsField, recipe.servingsProperty());
    formatter.bindIntegerField(cookTimeField, recipe.cookTimeProperty());
    formatter.bindIntegerField(caloriesField, recipe.caloriesProperty());

    formatter.bindDoubleField(proteinField, recipe.proteinProperty());
    formatter.bindDoubleField(carbsField, recipe.carbsProperty());
    formatter.bindDoubleField(fatField, recipe.fatProperty());

    descriptionArea.textProperty().bindBidirectional(recipe.descriptionProperty());

    diffComboBox.valueProperty().bindBidirectional(recipe.difficultyProperty());
    fxDiffLabel.textProperty().bindBidirectional(recipe.difficultyProperty());
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
    diffComboBox.setDisable(!editable);
  }

  public void show(boolean visible) {
    imageButton.setVisible(visible);
    diffComboBox.setVisible(visible);
    fxDiffLabel.setVisible(!visible);
    fxAddIngredientButton.setVisible(visible);
  }

  @FXML
  private void onNew() {
    Window owner = fxAddIngredientButton.getScene().getWindow();
    Optional<Ingredient> result = IngredientDialogWrapper.birtaDialog(owner);
    result.ifPresent(ingredient -> {
      recipe.newIngredient(ingredient);
      // System.out.println("Ingredient added: " + ingredient);
      // System.out.println("Ingredients list size: " +
      // recipe.getIngredientsList().size());
    });
  }
}
