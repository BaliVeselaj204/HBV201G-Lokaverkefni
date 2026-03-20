package is.vinnsla;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Ferd
 */
public class Recipe {
  private StringProperty name = new SimpleStringProperty();
  private StringProperty description = new SimpleStringProperty();

  private IntegerProperty cookTime = new SimpleIntegerProperty();

  private IntegerProperty calories = new SimpleIntegerProperty();
  private DoubleProperty protein = new SimpleDoubleProperty();
  private DoubleProperty carbs = new SimpleDoubleProperty();
  private DoubleProperty fat = new SimpleDoubleProperty();

  private IntegerProperty servings = new SimpleIntegerProperty();
  private StringProperty difficulty = new SimpleStringProperty();

  // Need to create Ingredient class first
  // private ObservableList<Ingredient> ingredients =
  // FXCollections.observableArrayList();

  public Recipe(String name, String description, int cookTime, int calories, double protein, double carbs, double fat,
      int servings, String difficulty) {
    this.setName(name);
    this.setDescription(description);
    this.setCookTime(cookTime);
    this.setCalories(calories);
    this.setProtein(protein);
    this.setCarbs(carbs);
    this.setFat(fat);
    this.setServings(servings);
    this.setDifficulty(difficulty);
  }

  public Recipe() {
  }

  public StringProperty nameProperty() {
    return name;
  }

  public String getName() {
    return name.get();
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public StringProperty descriptionProperty() {
    return description;
  }

  public String getDescription() {
    return description.get();
  }

  public void setDescription(String description) {
    this.description.set(description);
  }

  public IntegerProperty cookTimeProperty() {
    return cookTime;
  }

  public int getCookTime() {
    return cookTime.get();
  }

  public void setCookTime(int cookTime) {
    this.cookTime.set(cookTime);
  }

  public IntegerProperty caloriesProperty() {
    return calories;
  }

  public int getCalories() {
    return calories.get();
  }

  public void setCalories(int calories) {
    this.calories.set(calories);
  }

  public DoubleProperty proteinPropery() {
    return protein;
  }

  public double getProtein() {
    return protein.get();
  }

  public void setProtein(double protein) {
    this.protein.set(protein);
  }

  public DoubleProperty carbsProperty() {
    return carbs;
  }

  public double getCarbs() {
    return carbs.get();
  }

  public void setCarbs(double carbs) {
    this.carbs.set(carbs);
  }

  public DoubleProperty fatProperty() {
    return fat;
  }

  public double getFat() {
    return fat.get();
  }

  public void setFat(double fat) {
    this.fat.set(fat);
  }

  public IntegerProperty servingsProperty() {
    return servings;
  }

  public int getServings() {
    return servings.get();
  }

  public void setServings(int servings) {
    this.servings.set(servings);
  }

  public StringProperty difficultyProperty() {
    return difficulty;
  }

  public String getDifficulty() {
    return difficulty.get();
  }

  public void setDifficulty(String difficulty) {
    this.difficulty.set(difficulty);
  }

  @Override
  public String toString() {
    return name.get();
  }
}
