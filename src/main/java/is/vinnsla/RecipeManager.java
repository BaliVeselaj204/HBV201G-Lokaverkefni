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
   * @param recipe
   *
   *               Bætir við nýju uppskrift í lista
   */
  public void newRecipe(Recipe recipe) {
    getList().add(recipe);
  }

  /**
   * @param recipe
   *
   *               Eyðir uppskrift úr lista
   */
  public void removeRecipe(Recipe recipe) {
    getList().remove(recipe);
  }

  public void updateRecipe(Recipe oldRecipe, Recipe updatedRecipe) {
    int index = list.indexOf(oldRecipe);
    if (index != -1) {
      list.set(index, updatedRecipe);
    }
  }
}
