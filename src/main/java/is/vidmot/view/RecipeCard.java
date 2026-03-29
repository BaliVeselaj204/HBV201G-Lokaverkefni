package is.vidmot.view;

import java.io.IOException;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.converter.NumberStringConverter;

public class RecipeCard extends VBox {

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

  private SimpleStringProperty name = new SimpleStringProperty();
  private SimpleIntegerProperty servings = new SimpleIntegerProperty();
  private SimpleIntegerProperty cookTime = new SimpleIntegerProperty();
  private SimpleIntegerProperty calories = new SimpleIntegerProperty();
  private SimpleIntegerProperty carbs = new SimpleIntegerProperty();
  private SimpleIntegerProperty fat = new SimpleIntegerProperty();
  private SimpleStringProperty diffChoice = new SimpleStringProperty();
  private SimpleStringProperty description = new SimpleStringProperty();

  public RecipeCard() {
    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/is/vidmot/recipe-card.fxml"));
    fxmlLoader.setRoot(this);
    fxmlLoader.setController(this);
    try {
      fxmlLoader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    NumberStringConverter converter = new NumberStringConverter();

    nameField.textProperty().bindBidirectional(name);
    servingsField.textProperty().bindBidirectional(servings, converter);
    cookTimeField.textProperty().bindBidirectional(cookTime, converter);
    caloriesField.textProperty().bindBidirectional(calories, converter);
    carbsField.textProperty().bindBidirectional(carbs, converter);
    fatField.textProperty().bindBidirectional(fat, converter);
    // Need to get combobox choice
    descriptionArea.textProperty().bindBidirectional(description);
  }

  public void setEditable(boolean editable) {
    nameField.setEditable(editable);
    servingsField.setEditable(editable);
    cookTimeField.setEditable(editable);
  }

  public SimpleStringProperty getNamePropery() {
    return name;
  }

  public SimpleIntegerProperty getServingsProperty() {
    return servings;
  }

  public SimpleIntegerProperty getCookTimePropery() {
    return cookTime;
  }

  // More to come...
}
