package is.vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * RecipeManager
 */
public class RecipeManager {
  private ObservableList<Recipe> list;
  private static RecipeManager instance;

  public RecipeManager() {
    list = FXCollections.observableArrayList();
  }

  /**
   * Fall fyrir tilviksbreytur
   * 
   * @return
   */
  public static RecipeManager getInstance() {
    if (instance == null) {
      instance = new RecipeManager();
    }
    return instance;
  }

  public ObservableList<Recipe> getList() {
    return list;
  }

  /**
   * Bætir við nýju uppskrift í lista
   * 
   * @param recipe
   */
  public void newRecipe(Recipe recipe) {
    getList().add(recipe);
  }

  /**
   * Eyðir uppskrift úr lista
   * 
   * @param recipe
   */
  public void removeRecipe(Recipe recipe) {
    getList().remove(recipe);
  }

  /**
   * Uppfæra uppskrift
   * 
   * @param oldRecipe
   * @param updatedRecipe
   */
  public void updateRecipe(Recipe oldRecipe, Recipe updatedRecipe) {
    int index = list.indexOf(oldRecipe);
    if (index != -1) {
      list.set(index, updatedRecipe);
    }
  }

  /**
   * Eyða hráefni frá lista
   * 
   * @param ingredient
   * @param recipe
   */
  public void removeIngredient(Ingredient ingredient, Recipe recipe) {
    recipe.removeIngredient(ingredient);
  }
}
