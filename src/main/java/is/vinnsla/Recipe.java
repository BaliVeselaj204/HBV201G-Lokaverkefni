package is.vinnsla;

import java.util.List;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 * Recipe
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

  private ObservableList<Ingredient> ingredients = FXCollections.observableArrayList();
  private final ObjectProperty<Image> image = new SimpleObjectProperty<>();

  public Recipe(String name, String description, int cookTime, int calories, double protein, double carbs, double fat,
      int servings, String difficulty, List<Ingredient> ingredients, Image image) {
    this.setName(name);
    this.setDescription(description);
    this.setCookTime(cookTime);
    this.setCalories(calories);
    this.setProtein(protein);
    this.setCarbs(carbs);
    this.setFat(fat);
    this.setServings(servings);
    this.setDifficulty(difficulty);
    this.setImage(image);
    this.ingredients = FXCollections.observableArrayList(ingredients);
  }

  public Recipe(String name, String description, int cookTime, int calories, double protein, double carbs, double fat,
      int servings, String difficulty, List<Ingredient> ingredients) {
    this(name, description, cookTime, calories, protein, carbs, fat, servings, difficulty, ingredients, null);
  }

  public Recipe() {
  }

  public ObjectProperty<Image> imageProperty() {
    return image;
  }

  public Image getImage() {
    return image.get();
  }

  public void setImage(Image image) {
    this.image.set(image);
  }

  public ObservableList<Ingredient> getIngredientsList() {
    return ingredients;
  }

  public void newIngredient(Ingredient ingredient) {
    ingredients.add(ingredient);
  }

  public void removeIngredient(Ingredient ingredient) {
    ingredients.remove(ingredient);
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

  public DoubleProperty proteinProperty() {
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
