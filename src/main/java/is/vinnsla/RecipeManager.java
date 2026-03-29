package is.vinnsla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * RecipeManager
 */
public class RecipeManager {
  private ObservableList<Recipe> list;

  public RecipeManager() {
    list = FXCollections.observableArrayList();
  }

  public ObservableList<Recipe> getList() {
    return list;
  }

  /**
   * @param recipe
   *
   *             Bætir við nýju uppskrift í lista
   */
  public void newRecipe(Recipe recipe) {
    getList().add(recipe);
  }

  /**
   * @param recipe
   *
   *             Eyðir uppskrift úr lista
   */
  public void removeRecipe(Recipe recipe) {
    getList().remove(recipe);
  }
}
